(ns prf.extensions.sew-core-model.test-runner
  (:require [clojure.test :as test]
            [prf.extensions.sew-core-model.adapter-test]))

(defn -main [& _]
  (let [{:keys [fail error]} (test/run-tests 'prf.extensions.sew-core-model.adapter-test)]
    (when (pos? (+ fail error))
      (System/exit 1))))
