# Sew PRF integration bundle

This is the single dependency entrypoint for PRF consumers that need the Sew
Core adapter, protocol model, and optional Sew application adapters. It places
the Sew-owned source roots from this repository on one classpath and pins PRF
core to an immutable revision.

Use this bundle when a consumer needs multiple Sew components. The individual
component directories remain useful for focused repository tests, but declaring
several of them as separate Git subdirectory dependencies can create duplicate
local sibling model roots.

From an external `deps.edn`, depend on this directory at a pinned Sew commit:

```clojure
org.protocol-robustness/sew-prf-integration
{:git/url "https://github.com/Sew-Protocol/prf-sew-core-model.git"
 :git/sha "<immutable Sew commit>"
 :deps/root "extensions/sew-prf-integration"}
```

The complete package and scenario qualification remains at the repository
root in `scripts/test.sh`.
