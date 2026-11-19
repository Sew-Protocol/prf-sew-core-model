# Sew pro-rata slashing adapter

This extension builds Sew slash allocation and evidence on top of PRF's
generic pro-rata evaluation and evidence APIs. Its compatibility namespace
re-exports the explicit `resolver-sim.protocols.sew.pro-rata.evidence`
implementation.

The runtime pins PRF core to `4c4b9ac4b3bbbac3e44a47cb4df7d5197bee7dc3` and
uses the sibling Sew model and pro-rata economics packages from this candidate
repository. Tests also compose the yield and reference-validation adapters
because the slashing scenarios exercise those optional capabilities.

Run the extension contract suite from this directory:

```sh
clojure -Srepro -M:test
```

The matching PRF source remains in place until this candidate revision is
published and PRF consumers qualify against it.
