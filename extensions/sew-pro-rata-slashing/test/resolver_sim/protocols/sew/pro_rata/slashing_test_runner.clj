(ns resolver-sim.protocols.sew.pro-rata.slashing-test-runner
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.evidence.slashing-test]
            [resolver-sim.protocols.sew.phase-k-test]
            [resolver-sim.protocols.sew.reproduction-slash-bug]
            [resolver-sim.protocols.sew.slashing-test]
            [resolver-sim.protocols.sew.extension]
            [resolver-sim.protocols.sew.reference-validation-extension])
  (:gen-class))

(defn -main [& _args]
  (let [result (test/run-tests
                'resolver-sim.protocols.sew.evidence.slashing-test
                'resolver-sim.protocols.sew.phase-k-test
                'resolver-sim.protocols.sew.reproduction-slash-bug
                'resolver-sim.protocols.sew.slashing-test)]
    (when (pos? (+ (:fail result) (:error result)))
      (System/exit 1))))
