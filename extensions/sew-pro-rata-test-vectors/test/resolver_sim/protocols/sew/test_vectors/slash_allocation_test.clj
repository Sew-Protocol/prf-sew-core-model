(ns resolver-sim.protocols.sew.test-vectors.slash-allocation-test
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.test :refer [deftest is testing]]
            [resolver-sim.protocols.sew.pro-rata.economics :as economics]
            [resolver-sim.protocols.sew.test-vectors.slash-allocation :as sut]))

(defn- amount [x]
  (bigint x))

(defn- canonical-json [value]
  ((ns-resolve 'resolver-sim.protocols.sew.test-vectors.slash-allocation
               'canonical-json)
   value))

(deftest golden-vectors-are-sew-slash-vectors
  (let [vectors (mapv sut/emit-slash-allocation-vector sut/slash-golden-specs)]
    (is (= 5 (count vectors)))
    (doseq [vector vectors]
      (testing (:vector-id vector)
        (is (= sut/slash-schema-version (:schema-version vector)))
        (is (= "slash-allocation" (:domain vector)))
        (is (= 64 (count (:full-vector-hash vector))))
        (let [out (:expected-output vector)]
          (is (= (amount (:total-obligation out))
                 (+ (amount (:total-debited out))
                    (amount (:total-unmet out))
                    (amount (:remainder out)))))
          (doseq [party (:liability-per-party out)]
            (is (not (neg? (amount (:debited party)))))
            (is (not (neg? (amount (:unmet party)))))
            (when-let [cap (:cap party)]
              (is (<= (amount (:debited party)) (amount cap))))))))))

(deftest vector-reference-output-matches-sew-economics
  (let [spec {:vector-id "wrapper-slash"
              :description "wrapper check"
              :slash-obligation 400
              :liable-parties [{:id :alice :slashable-stake 1000 :available-slashable 1000}
                               {:id :bob :slashable-stake 500 :available-slashable 60}
                               {:id :carol :slashable-stake 500 :available-slashable 500}]}
        expected (economics/calculate-sew-slash-allocation
                  {:slash-obligation 400
                   :liable-parties (:liable-parties spec)
                   :basis :slashable-stake
                   :cap-field :available-slashable})
        emitted (sut/emit-slash-allocation-vector spec)]
    (is (= expected (get-in emitted [:expected-output :reference-output])))))

(deftest vector-hashes-are-deterministic
  (doseq [spec sut/slash-golden-specs]
    (let [left (sut/emit-slash-allocation-vector spec)
          right (sut/emit-slash-allocation-vector spec)]
      (is (= (:canonical-input-hash left) (:canonical-input-hash right)))
      (is (= (:canonical-expected-output-hash left)
             (:canonical-expected-output-hash right)))
      (is (= (:full-vector-hash left) (:full-vector-hash right))))))

(deftest checked-in-vectors-match-the-generator
  (doseq [spec sut/slash-golden-specs]
    (let [expected (sut/emit-slash-allocation-vector spec)
          resource-path (str "test-vectors/pro-rata/slash-allocation-"
                             (:vector-id spec) ".json")
          resource (io/resource resource-path)]
      (testing resource-path
        (is (some? resource))
        (when resource
          (let [stored-json (slurp resource)
                stored (json/read-str stored-json :key-fn keyword)]
            (is (= (:canonical-input-hash expected)
                   (:canonical-input-hash stored)))
            (is (= (:canonical-expected-output-hash expected)
                   (:canonical-expected-output-hash stored)))
            (is (= (:full-vector-hash expected) (:full-vector-hash stored)))
            (is (= (canonical-json expected)
                   (str/trim stored-json)))))))))
