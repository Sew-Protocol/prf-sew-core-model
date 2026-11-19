# Sew reference validation adapter

This candidate supplies Sew replay, invariant-checker, and trace-export
capabilities to PRF's generic reference-validation harness. It also owns the
projection and trace-export implementations needed by the Sew scenarios.

The runtime pins PRF core to `4c4b9ac4b3bbbac3e44a47cb4df7d5197bee7dc3`.
The Sew model and yield integration are sibling packages in this candidate
repository and are resolved through local paths, so this is not yet an
independently resolvable published package.

Run the extracted domain-reference suite from the repository root:

```sh
qualification/sew-domain-reference-v1/scripts/run.sh
qualification/sew-domain-reference-v1/scripts/verify.sh
```

The original PRF suite and scenario sources remain in place until a fetchable
Sew revision is available and PRF consumers have switched to it.
