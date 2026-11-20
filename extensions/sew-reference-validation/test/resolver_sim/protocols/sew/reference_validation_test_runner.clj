(ns resolver-sim.protocols.sew.reference-validation-test-runner
  (:require [clojure.test :as test]
            [resolver-sim.protocols.sew.reference-trace-portability-test]))

(defn -main [& _]
  (let [{:keys [fail error]}
        (test/run-tests 'resolver-sim.protocols.sew.reference-trace-portability-test)]
    (System/exit (+ fail error))))
