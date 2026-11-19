(ns resolver-sim.protocols.sew.financial.test-runner
  "Run Sew financial-lifecycle tests from this extension's test tree."
  (:require [clojure.java.io :as io]
            [clojure.test :as test]))

(defn- test-namespaces []
  (->> (file-seq (io/file "test"))
       (filter #(and (.isFile %) (.endsWith (.getName %) "_test.clj")))
       (map (fn [file]
              (with-open [reader (java.io.PushbackReader. (io/reader file))]
                (second (read reader)))))
       sort
       vec))

(defn -main [& _]
  (let [namespaces (test-namespaces)]
    (doseq [namespace namespaces]
      (require namespace))
    (let [{:keys [fail error]} (apply test/run-tests namespaces)]
      (System/exit (+ fail error)))))
