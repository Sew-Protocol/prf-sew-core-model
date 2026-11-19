(ns resolver-sim.protocols.sew.bounty-escrow-route-test-runner
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.bounty-escrow-route-test]))

(defn -main [& _]
  (let [result (test/run-tests
                'resolver-sim.protocols.sew.bounty-escrow-route-test)]
    (when (pos? (+ (:fail result) (:error result)))
      (System/exit 1))))
