# Sew financial lifecycle

This package contains Sew's insolvency lifecycle, episode reporting, and
reclaimed-yield reconstruction models with their conformance tests. These are
Sew application models built on PRF shared accounting, hashing, and assurance
code; they do not define general PRF economics.

The source and tests were copied from the PRF `agent-c-3` workspace. The PRF
copies remain in place until this candidate revision is published and PRF
consumers are qualified against it.

Within this candidate repository the package uses the sibling
`sew-protocol-model` package by local path. Its test classpath also includes the
sibling yield integration and model test fixtures. This is an in-tree package
candidate, not an independently resolvable release.

Run the package tests from this directory with:

```sh
clojure -Srepro -M:test
```

The repository-level `scripts/test.sh` includes these tests in the broader
candidate qualification.
