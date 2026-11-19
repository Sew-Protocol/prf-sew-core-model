(ns resolver-sim.protocols.sew.reference-validation-extension
  "Sew capabilities for PRF's generic reference-validation harness."
  (:require [resolver-sim.protocols.registry :as registry]
            [resolver-sim.protocols.sew :as sew]
            [resolver-sim.protocols.sew.invariants :as invariants]
            [resolver-sim.protocols.sew.runner-replay :as runner-replay]
            [resolver-sim.protocols.sew.io.trace-export :as trace-export]
            [resolver-sim.protocols.sew.yield-extension]))

(defn register-capabilities! []
  (registry/register-capability!
   "sew-v1" :runner/replay runner-replay/replay-with-sew-protocol)
  (registry/register-capability!
   "sew-v1" :reference-validation/replay
   (fn [scenario opts] (sew/replay-with-sew-protocol scenario opts)))
  (registry/register-capability!
   "sew-v1" :reference-validation/invariant-checker
   {:world-invariant-ids invariants/world-invariant-ids
    :check-all-fn invariants/check-all})
  (registry/register-capability!
   "sew-v1" :runner/export-trace-fixture
   (fn [result scenario trace-path]
     (trace-export/write-fixture-file
      (trace-export/export-trace-fixture result scenario)
      trace-path))))

(register-capabilities!)
