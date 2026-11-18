# PRF Sew Core Model extension candidate

This repository candidate contains the behavior adapter for the Solidity Sew
Core profile and the Sew-specific yield capability provider needed by the
shared PRF model. It validates the supported Sew Core actions and configuration,
then delegates transitions to the pinned `prf-sew-protocol-model` package.

## Dependencies

`deps.edn` pins the public PRF source revision
`54a5d925edb974a61d71229e81d1e62e4baf78f2`. Its Sew model manifest pins PRF
core revision `4b628c8ef15b1970425804aaf06014c960469357` and Sew pro-rata
economics revision `ae258b36f3b6f1c0ee4bcf9d913e4cea136994c0`. The repository
includes the small Sew yield integration adapter because the model requires its
initialization and invariant capabilities even when Sew Core disables yield.
The shared yield runtime comes from the pinned PRF core dependency.

This repository is public at
https://github.com/Sew-Protocol/prf-sew-core-model. A fresh clone with an empty
Clojure dependency cache resolved the pinned dependencies and passed the
contract suite (10 tests, 62 assertions). The GitHub Actions workflow repeats
dependency resolution and package tests on pushes and pull requests; its first
run passed on commit `8c952167`.

The PRF monorepo source remains authoritative until the package integration is
independently reviewed and the PRF profile has migrated to this repository.

## Layout

- `src/prf/extensions/sew_core_model/` — Sew Core behavior adapter
- `src/resolver_sim/protocols/sew/` — required Sew yield capability integration
- `test/` — adapter contract tests and Solidity-linked vectors

Run the package tests from this directory with:

```sh
clojure -Srepro -M:test
```
