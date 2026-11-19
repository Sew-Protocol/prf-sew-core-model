# Sew pro-rata application adapter

Sew-specific application of portable PRF allocations to Sew held-credit state,
aggregate held-custody projections, and verified slash-distribution plans.
Generic allocation semantics remain in PRF; this package owns the Sew binding
and state application.

The source and contract tests were copied from PRF's `agent-c-3` profile
candidate. Runtime dependencies use the pinned PRF core revision and the
neighboring Sew model package. The local model path means this package is
qualified through the repository checkout, not as a separately published
artifact.

Run the package contract suite from this directory:

```sh
clojure -Srepro -M:test
```

The matching PRF source remains in place until this candidate revision is
published and PRF profile consumers qualify against it.
