# Sew analysis extension

Optional Sew projections, claimable classification, equilibrium analysis, and
validator registry adapters. The extension uses the Sew model and PRF's shared
yield evidence and scenario APIs; it does not change the model's fallback
behavior when omitted.

The source and contract tests were copied from PRF's `agent-c-3` profile
component. Runtime dependencies pin PRF core and use the sibling Sew model via
a local path, so this package is not independently resolvable yet.

Run its contract suite from this directory:

```sh
clojure -Srepro -M:test
```

Keep the matching PRF source until this candidate revision is published and
PRF profile consumers qualify against the package.
