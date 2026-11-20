# Sew reference validation adapter

This candidate supplies Sew replay, invariant-checker, and trace-export
capabilities to PRF's generic reference-validation harness. It also owns the
projection and trace-export implementations needed by the Sew scenarios.

The runtime pins PRF core, the Sew model, and yield integration to immutable
Git revisions. A clean `clojure -Srepro -P` resolves the currently published
dependency revisions. The resource relocation in this candidate is not yet in
a published revision, so PRF retains its local scenario copies until the pin
advances and repository-based consumers switch to the package.

The five domain-reference scenario inputs are packaged under
`resources/scenarios/edn`, so PRF's scenario loader can resolve them as
classpath resources when this package is selected. PRF retains its existing
source copies until its manifests and repository-based forensic tools consume
the published package revision.

Run the extracted domain-reference suite from the repository root:

```sh
qualification/sew-domain-reference-v1/scripts/run.sh
qualification/sew-domain-reference-v1/scripts/verify.sh
```

The original PRF suite and scenario sources remain in place until a fetchable
Sew revision is available and PRF consumers have switched to it.
