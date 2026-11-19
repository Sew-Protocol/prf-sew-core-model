# Sew bounty escrow route adapter

This adapter binds Sew bounty payable and backing identities to a Sew escrow
workflow, and checks withdrawal category attribution against independently
reconstructed evidence. It does not authenticate chain observations or settle
the bounty obligation; those boundaries remain with the relevant PRF and Sew
settlement APIs.

The source and contract test were copied from PRF's `agent-c-3` workspace. The
runtime uses PRF core at the pinned revision, so it has no local sibling
dependency. This package is part of the unpublished extension candidate branch
in the public Sew repository.

Run the contract suite from this directory:

```sh
clojure -Srepro -M:test
```

The matching PRF source remains until this candidate revision is published and
the PRF consumer path qualifies against it.
