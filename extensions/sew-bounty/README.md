# Sew bounty application adapter

This package applies PRF's generic with-bounty plans to Sew state through the
canonical payable, backing, custody, and claimable paths. Generic bounty policy
and plan evaluation remain in PRF; this package owns the Sew state adapter.

The runtime pins PRF core and uses the sibling Sew model package through a local
path. Test-only fixture support is included here so the adapter contract suite
does not require PRF's monorepo test tree. This package is part of the
unpublished extension candidate branch in the public Sew repository.

Run the package contract suite from this directory:

```sh
clojure -Srepro -M:test
```

Keep the PRF adapter source until this candidate revision is published and PRF
consumers qualify against it.
