# PRF Sew Core Model extension

This repository contains the PRF behavior adapter for the Solidity Sew Core
profile. It is deliberately narrower than the full Sew protocol model: it
validates the supported Sew Core actions and configuration, then delegates
model transitions to `prf-sew-protocol-model`.

## Scope and dependency

The adapter source, its contract tests, and pinned Solidity comparison vectors
live here. The Sew protocol implementation remains in the PRF monorepo at
`packages/sew-protocol-model` / `protocols_src`; this repository does not claim
to extract or independently own that implementation.

`deps.edn` resolves the model from immutable PRF Git revision
`b94b091cfbaf46ec34ae2ade4920f9a3751e3097`, using the model subdirectory as
the dependency root. The model manifest currently resolves the shared PRF
framework from that same checkout. Both limitations are explicit source-level
dependencies; neither relies on a local workspace path.

This is a local candidate repository. It has no configured remote and has not
been published. The original in-tree adapter remains in the PRF workspace
until the candidate has been reviewed and its integration path is agreed.

## Layout

- `src/` — Sew Core profile adapter
- `test/` — adapter contract tests and Solidity conformance vectors

Run the package tests from this directory with:

```sh
clojure -M -e "(require '[clojure.test :as t] 'prf.extensions.sew-core-model.adapter-test) (t/run-tests 'prf.extensions.sew-core-model.adapter-test)"
```
