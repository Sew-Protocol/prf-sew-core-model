(ns resolver-sim.protocols.sew.reference-trace-portability-test
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [resolver-sim.evidence.forensic-graph :as forensic]
            [resolver-sim.evidence.graph-producer :as producer]))

(defn- evidence
  [subject role & {:as opts}]
  (apply forensic/build-forensic-evidence-node
         (merge {:subject subject :role role :visibility :commitment-only
                 :causal-parent-roots []}
                opts)
         []))

(deftest simulator-trace-remains-a-portability-fixture-not-chain-evidence
  (let [trace-resource (io/resource
                        "sew-domain-reference-v1/expected/traces/sew-002-pending-settlement-expiry.trace.json")
        sidecar-resource (io/resource
                          "sew-domain-reference-v1/expected/traces/sew-002-pending-settlement-expiry.trace.sha256")
        bytes (with-open [input (io/input-stream trace-resource)] (.readAllBytes input))
        digest (apply str (map #(format "%02x" (bit-and (int %) 0xff))
                               (.digest (java.security.MessageDigest/getInstance "SHA-256") bytes)))
        pinned "f6378049c5f6e2c9fb9a385baa2e219fcfe852ce0b8819db772f6b9936ed9660"
        trace (json/read-str (String. bytes "UTF-8"))
        sidecar (str/trim (slurp sidecar-resource))
        trace-root (str "sha256:" digest)
        subject {:subject/root trace-root :subject/kind :sew/simulator-trace}
        first-node (evidence subject :declared :causal-parent-roots [])
        steps (get trace "steps")
        step-nodes (second
                    (reduce (fn [[parent-hash nodes] step]
                              (let [cutpoint-kind (keyword
                                                   (str "sew-step-" (get step "seq") "-"
                                                        (-> (get step "event_type")
                                                            str/lower-case
                                                            (str/replace #"[^a-z0-9]+" "-"))))
                                    entry (evidence subject :declared
                                                    :parent-nodes [parent-hash]
                                                    :causal-parent-roots [trace-root]
                                                    :actor {:actor/root trace-root
                                                            :actor/role :system}
                                                    :cutpoint {:cutpoint/root trace-root
                                                               :cutpoint/kind cutpoint-kind})]
                                [(:node-hash entry) (conj nodes entry)]))
                            [(:node-hash first-node) []]
                            steps))
        gap-node (evidence subject :declared
                           :parent-nodes [(-> step-nodes last :node-hash)]
                           :causal-parent-roots [trace-root]
                           :gap {:gap/status :not-verifiable
                                 :gap/reason :simulator-only-no-chain-observation})
        nodes (into [first-node] (conj step-nodes gap-node))
        production (producer/produce
                    {:adapter-id :sew/reference-trace
                     :adapter-version 1
                     :artifact-roots [trace-root]
                     :nodes nodes})]
    (is (some? trace-resource))
    (is (some? sidecar-resource))
    (is (= pinned digest))
    (is (= pinned sidecar))
    (is (= 6 (count steps)))
    (is (:valid? (get-in production [:graph])))
    (is (= (+ 2 (count steps)) (get-in production [:graph :summary :node-count])))
    (is (= :sew/reference-trace (:adapter/id production)))
    (is (every? #(= :sew/simulator-trace
                    (get-in % [:extensions :forensic/subject :subject/kind]))
                (:nodes production)))
    (is (= (set (map #(keyword
                       (str "sew-step-" (get % "seq") "-"
                            (-> (get % "event_type")
                                str/lower-case
                                (str/replace #"[^a-z0-9]+" "-"))))
                     steps))
           (set (keep #(get-in % [:extensions :forensic/cutpoint :cutpoint/kind])
                      (:nodes production))))
        "Cutpoints identify the ordered source trace steps.")
    (is (= :not-verifiable
           (->> (:nodes production)
                (keep #(get-in % [:extensions :forensic/gap :gap/status]))
                first))
        "The graph does not promote simulator steps to chain observations.")))
