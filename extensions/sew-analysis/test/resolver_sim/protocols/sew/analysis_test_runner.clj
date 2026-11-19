(ns resolver-sim.protocols.sew.analysis-test-runner
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.accounting-projection-test]
            [resolver-sim.protocols.sew.claimable-classification-test]
            [resolver-sim.protocols.sew.funds-ledger-projection-test]
            [resolver-sim.protocols.sew.validator-registry-test]
            [resolver-sim.protocols.sew.analysis-extension]
            [resolver-sim.protocols.sew.advisory]
            [resolver-sim.protocols.sew.claimable-classification]
            [resolver-sim.protocols.sew.equilibrium]
            [resolver-sim.protocols.sew.projection]
            [resolver-sim.protocols.sew.validator-registry]
            [resolver-sim.protocols.sew.yield-extension]
            [resolver-sim.scenario.equilibrium-test]))

(defn -main [& _]
  (let [result
        (test/run-tests
         'resolver-sim.protocols.sew.accounting-projection-test
         'resolver-sim.protocols.sew.claimable-classification-test
         'resolver-sim.protocols.sew.funds-ledger-projection-test
         'resolver-sim.protocols.sew.validator-registry-test
         'resolver-sim.scenario.equilibrium-test)]
    (when (pos? (+ (:fail result) (:error result)))
      (System/exit 1))))
