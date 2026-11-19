(ns resolver-sim.protocols.sew.consumer-test-runner
  "Run the Sew model's independently resolvable consumer contract tests."
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.kernel-test]
            [resolver-sim.protocols.sew.state-machine-test]))

(defn -main [& _]
  (let [{:keys [fail error]}
        (test/run-tests 'resolver-sim.protocols.sew.kernel-test
                        'resolver-sim.protocols.sew.state-machine-test)]
    (System/exit (+ fail error))))
