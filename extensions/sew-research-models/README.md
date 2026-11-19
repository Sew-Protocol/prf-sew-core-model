# Sew research models

This package contains Sew-specific delegation, escalation economics, bribery,
evidence spoofing, information cascade, and resolver-ring models. Generic PRF
stochastic simulation primitives remain in PRF; this package composes them into
Sew research cases.

The source and contract test were copied from PRF's `agent-c-3` profile
component. Runtime dependencies pin PRF core and use the sibling Sew model via
a local path, so this package is not independently resolvable yet.

Run the contract suite from this directory:

```sh
clojure -Srepro -M:test
```

Keep the matching PRF source until this candidate revision is published and
PRF consumers qualify against it.
