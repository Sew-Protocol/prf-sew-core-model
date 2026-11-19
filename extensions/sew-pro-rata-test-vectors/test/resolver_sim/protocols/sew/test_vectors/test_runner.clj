(ns resolver-sim.protocols.sew.test-vectors.test-runner
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.test-vectors.slash-allocation-test]))

(defn -main [& _]
  (let [result (test/run-tests
                'resolver-sim.protocols.sew.test-vectors.slash-allocation-test)]
    (when (pos? (+ (:fail result) (:error result)))
      (System/exit 1))))
