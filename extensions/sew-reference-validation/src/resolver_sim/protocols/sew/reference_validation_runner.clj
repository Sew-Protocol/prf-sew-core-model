(ns resolver-sim.protocols.sew.reference-validation-runner
  "Bootstrap the Sew adapters, then run PRF's generic reference-validation CLI."
  (:require [resolver-sim.protocols.sew.reference-validation-extension]
            [resolver-sim.sim.reference-validation :as reference-validation])
  (:gen-class))

(defn -main [& args]
  (apply reference-validation/-main args))
