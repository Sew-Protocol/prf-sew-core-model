(ns resolver-sim.sew.research-models-test-runner
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.research-models.bribery-markets]
            [resolver-sim.protocols.sew.research-models.delegation]
            [resolver-sim.protocols.sew.research-models.escalation-economics]
            [resolver-sim.protocols.sew.research-models.evidence-spoofing]
            [resolver-sim.protocols.sew.research-models.information-cascade]
            [resolver-sim.protocols.sew.research-models.resolver-ring]
            [resolver-sim.delegation-test]))

(defn -main [& _]
  (let [result (test/run-tests 'resolver-sim.delegation-test)]
    (when (pos? (+ (:fail result) (:error result)))
      (System/exit 1))))
