# Sew research extensions

Sew-specific adversarial, economic, and governance analyses composed over
PRF's shared stochastic simulation tools. The code here is research
instrumentation; it does not define generic PRF simulation capabilities.

The sources and tests were copied from PRF's `agent-c-3` profile component.
Runtime dependencies pin PRF core and use local sibling Sew model and research
model packages, so this package is not independently resolvable yet. The test
alias also composes the Sew yield adapter for protocol initialization tests.

Run the package suite from this directory:

```sh
clojure -Srepro -M:test
```

Keep the matching PRF sources until this candidate revision is published and
PRF consumers qualify against it.
