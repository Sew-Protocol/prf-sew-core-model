# Sew Core model and protocol extensions

This public Sew-owned repository contains the Solidity Sew Core behavior
adapter and its yield capability integration, the Sew protocol model,
application adapters, research models, and qualification material extracted
from PRF. Its root `deps.edn` is the single runtime entrypoint for consumers.

The original Sew Core package and its source history are retained. The PRF
monorepo copies of the newly extracted model and extensions remain until this
candidate revision is published and PRF consumers resolve and qualify against
it.

## Dependencies

The repository-level runtime entrypoint pins PRF core to immutable revision
`4c4b9ac4b3bbbac3e44a47cb4df7d5197bee7dc3`. Individual subdirectories retain
focused manifests and tests; consumers needing multiple Sew components should
depend on this repository root rather than declaring sibling packages
separately.

## Layout

- `src/prf/extensions/sew_core_model/` — Sew Core behavior adapter
- `src/resolver_sim/protocols/sew/` — required Sew yield capabilities
- `packages/sew-protocol-model/` — Sew escrow state and economic model
- `extensions/` — Sew-specific yield, lifecycle, allocation, slashing,
  analysis, research, bounty, and reference-validation adapters
- `qualification/sew-domain-reference-v1/` — Sew scenarios and expected
  evidence
- `examples/sew-protocol-fixture.edn` — non-authoritative legacy fixture

## Qualification

Run the existing Sew Core adapter contract suite from the repository root:

```sh
clojure -Srepro -M:test
```

Run the extracted model and extension suites with:

```sh
scripts/test.sh
```

The extension candidate revision requires its own review and qualification.
The PRF source-removal gate is documented in
[`docs/extraction-boundary.md`](docs/extraction-boundary.md).
