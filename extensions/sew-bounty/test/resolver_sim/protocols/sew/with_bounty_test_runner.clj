(ns resolver-sim.protocols.sew.with-bounty-test-runner
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.with-bounty-contract-test]
            [resolver-sim.protocols.sew.with-bounty-test]))

(defn -main [& _]
  (let [result (test/run-tests
                'resolver-sim.protocols.sew.with-bounty-contract-test
                'resolver-sim.protocols.sew.with-bounty-test)]
    (when (pos? (+ (:fail result) (:error result)))
      (System/exit 1))))
