# Sew pro-rata test vectors

Sew-specific canonical test vectors for slash allocation. The package calls
the Sew economics adapter and records its output, invariants, policy metadata,
and hashes. It includes the five canonical slash JSON vectors and tests them
against the generator. Generic liquidity-fulfillment vectors remain in PRF.

The economics dependency uses the sibling package in this repository, so this
is part of the Sew repository candidate rather than an independently published
artifact. Run the contract tests with:

```sh
clojure -Srepro -M:test
```
