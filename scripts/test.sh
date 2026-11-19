#!/usr/bin/env bash
set -euo pipefail

repo_root="$(cd "$(dirname "$0")/.." && pwd)"
cd "$repo_root"

(cd packages/sew-protocol-model && clojure -Srepro -M:test -m resolver-sim.protocols.sew.consumer-test-runner)
(cd extensions/sew-pro-rata-economics && clojure -Srepro -M:test -e "(require 'clojure.test 'resolver-sim.protocols.sew.pro-rata.economics-extension 'resolver-sim.protocols.sew.pro-rata.economics-adapter-test) (let [r (clojure.test/run-tests 'resolver-sim.protocols.sew.pro-rata.economics-adapter-test)] (when (pos? (+ (:fail r) (:error r))) (System/exit 1)))")
(cd extensions/sew-pro-rata-test-vectors && clojure -Srepro -M:test)
(cd extensions/sew-pro-rata-application && clojure -Srepro -M:test)
(cd extensions/sew-bounty-route && clojure -Srepro -M:test)
(cd extensions/sew-bounty && clojure -Srepro -M:test)
(cd extensions/sew-analysis && clojure -Srepro -M:test)
(cd extensions/sew-research-models && clojure -Srepro -M:test)
(cd extensions/sew-research && clojure -Srepro -M:test)
(cd packages/sew-protocol-model && clojure -Srepro -M:sew-pro-rata-economics:in-tree-test -m resolver-sim.protocols.sew.package-test-runner)
(cd extensions/sew-yield-integration && clojure -Srepro -M:test)
(cd extensions/sew-financial-lifecycle && clojure -Srepro -M:test)
(cd extensions/sew-pro-rata-slashing && clojure -Srepro -M:test)
(cd qualification/sew-domain-reference-v1 && ./scripts/run.sh && ./scripts/verify.sh)
