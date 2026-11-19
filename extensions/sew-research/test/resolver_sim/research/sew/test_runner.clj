(ns resolver-sim.research.sew.test-runner
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.yield-extension]
            [resolver-sim.protocols.sew.research-ratification]
            [resolver-sim.protocols.sew.research-models.ring-batch]
            [resolver-sim.research.sew.adversarial.advanced-vulnerability]
            [resolver-sim.research.sew.adversarial.collusion-ring-test]
            [resolver-sim.research.sew.adversarial.collusion-ring]
            [resolver-sim.research.sew.adversarial.ema-convergence]
            [resolver-sim.research.sew.adversarial.epoch-solvency]
            [resolver-sim.research.sew.adversarial.equity-divergence]
            [resolver-sim.research.sew.adversarial.escalation-trap]
            [resolver-sim.research.sew.adversarial.evidence-fog]
            [resolver-sim.research.sew.adversarial.fair-slashing]
            [resolver-sim.research.sew.adversarial.falsification-lite]
            [resolver-sim.research.sew.adversarial.falsification-revised]
            [resolver-sim.research.sew.adversarial.legitimacy-loop]
            [resolver-sim.research.sew.adversarial.liveness-participation-test]
            [resolver-sim.research.sew.adversarial.liveness-participation]
            [resolver-sim.research.sew.adversarial.trust-floor]
            [resolver-sim.research.sew.analytic.phase-c-corruption-economics]
            [resolver-sim.research.sew.analytic.phase-e-evidence-integrity]
            [resolver-sim.research.sew.analytic.phase-f-economic-parameters]
            [resolver-sim.research.sew.analytic.phase-m-fairness-analysis]
            [resolver-sim.research.sew.economic.adaptive-attacker]
            [resolver-sim.research.sew.economic.belief-cascades]
            [resolver-sim.research.sew.economic.burst-concurrency]
            [resolver-sim.research.sew.economic.dispute-clustering]
            [resolver-sim.research.sew.economic.evidence-fog-lite]
            [resolver-sim.research.sew.economic.market-exit]
            [resolver-sim.research.sew.governance.adversary]
            [resolver-sim.research.sew.governance.bandwidth-floor]
            [resolver-sim.research.sew.governance.capture-drift]
            [resolver-sim.sim.phase-e-evidence-integrity-test]))

(defn -main [& _]
  (let [result
        (test/run-tests
         'resolver-sim.research.sew.adversarial.collusion-ring-test
         'resolver-sim.research.sew.adversarial.liveness-participation-test
         'resolver-sim.sim.phase-e-evidence-integrity-test)]
    (when (pos? (+ (:fail result) (:error result)))
      (System/exit 1))))
