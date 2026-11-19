# Sew Core model and protocol extensions

This public Sew-owned repository contains the Solidity Sew Core behavior
adapter and its yield capability integration. The current candidate branch
also adds the broader Sew protocol model, application adapters, research
models, and qualification material extracted from PRF.

The original Sew Core package and its source history are retained. The PRF
monorepo copies of the newly extracted model and extensions remain until this
candidate revision is published and PRF consumers resolve and qualify against
it.

## Dependencies

The existing Sew Core adapter pins PRF source revision
`54a5d925edb974a61d71229e81d1e62e4baf78f2`. Its model manifest pins PRF core
revision `4b628c8ef15b1970425804aaf06014c960469357` and Sew pro-rata economics
revision `ae258b36f3b6f1c0ee4bcf9d913e4cea136994c0`. The shared yield runtime
comes from pinned PRF core.

The extracted model and extension packages pin PRF core to immutable public
Git revisions. Their sibling package paths are local to this repository, so
the supported qualification entrypoint is the repository-level test script;
not every subdirectory is independently resolvable as a published package.

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
