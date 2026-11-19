(ns resolver-sim.protocols.sew.pro-rata-application-test-runner
  (:require [clojure.test :as test]
            [resolver-sim.adapters.sew.aggregate-held-credit-test]
            [resolver-sim.adapters.sew.aggregate-held-credit-numeric-realization-test]
            [resolver-sim.pro-rata.realized-allocation-aggregate-held-credit-test]
            [resolver-sim.protocols.sew.apply-slash-distribution-test]
            [resolver-sim.protocols.sew.pro-rata-application-test]))

(def ^:private test-namespaces
  '[resolver-sim.adapters.sew.aggregate-held-credit-test
    resolver-sim.adapters.sew.aggregate-held-credit-numeric-realization-test
    resolver-sim.pro-rata.realized-allocation-aggregate-held-credit-test
    resolver-sim.protocols.sew.apply-slash-distribution-test
    resolver-sim.protocols.sew.pro-rata-application-test])

(defn -main [& _]
  (let [result (apply test/run-tests test-namespaces)]
    (when (pos? (+ (:fail result) (:error result)))
      (System/exit 1))))
