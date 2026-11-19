# Sew protocol model

Status: Sew-owned source candidate. This directory contains the Sew behavioral
model source and package test suite; it is not a released package.

## Qualification in this checkout

From this directory, run the clean-consumer contract suite. It resolves PRF
core from its pinned revision and exercises the public kernel API and escrow
state transitions without local integration sources:

```sh
clojure -Srepro -M:test -m resolver-sim.protocols.sew.consumer-test-runner
```

The broader in-tree model suite also exercises the optional Sew yield and
pro-rata economics adapters. It uses the local yield integration source only
through the explicitly named `:in-tree-test` alias:

```sh
clojure -Srepro -M:sew-pro-rata-economics:in-tree-test -m resolver-sim.protocols.sew.package-test-runner
```

To exercise the optional Sew pro-rata economics adapter against the pinned
PRF API source, run:

```sh
clojure -Srepro -M:sew-pro-rata-economics:test -e "(require 'resolver-sim.protocols.sew.pro-rata.economics-extension 'clojure.test 'resolver-sim.protocols.sew.economics-test) (let [r (clojure.test/run-tests 'resolver-sim.protocols.sew.economics-test)] (when (pos? (+ (:fail r) (:error r))) (System/exit 1)))"
```

The normal `:test` alias has no local source dependencies. The broader
`:in-tree-test` suite adds the sibling yield integration source because the
adapter depends on the Sew model. Run the repository-level gate with:

```sh
cd ../..
scripts/test.sh
```

## Extraction status

The runtime manifest pins PRF core revision
`4c4b9ac4b3bbbac3e44a47cb4df7d5197bee7dc3`. The optional
`:sew-pro-rata-economics` alias resolves to the sibling extension package in
this repository. Slash allocation remains unavailable until the consumer
selects that alias and loads
`resolver-sim.protocols.sew.pro-rata.economics-extension`. The model runtime
and clean-consumer contract suite resolve from pinned public dependencies. The
broad integration suite uses the sibling yield adapter source from this Sew
repository.

This package is maintained in the `sew-model-extensions` repository alongside
the Sew yield integration that composes it. Keep the clean-consumer suite
independent of that integration; the `:in-tree-test` alias exercises the
combined model and adapter behavior.
