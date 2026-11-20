# Sew reference validation adapter

This candidate supplies Sew replay, invariant-checker, and trace-export
capabilities to PRF's generic reference-validation harness. It also owns the
projection and trace-export implementations needed by the Sew scenarios.

The runtime pins PRF core, the Sew model, and yield integration to immutable
Git revisions. A clean `clojure -Srepro -P` resolves the published dependency
revisions. This package owns the five domain-reference scenario inputs under
`resources/scenarios/edn` and the canonical qualification suite under
`qualification/sew-domain-reference-v1`.

The five domain-reference scenario inputs are packaged under
`resources/scenarios/edn`, so PRF's scenario loader can resolve them as
classpath resources when this package is selected. PRF consumes these
classpath resources through its `:reference-validation` alias.

The former PRF-to-Solidity byte mappings are preserved in
`qualification/sew-domain-reference-v1/solidity-trace-history.edn`. The
current expected CDRS outputs have different hashes from those historical
Solidity fixtures, so the file records history only; current differential
equivalence still needs to be requalified.

Run the extracted domain-reference suite from the repository root:

```sh
qualification/sew-domain-reference-v1/scripts/run.sh
qualification/sew-domain-reference-v1/scripts/verify.sh
```

The PRF repository keeps only the generic validation harness. Scenario inputs,
expected outputs, and suite scripts are maintained here.
