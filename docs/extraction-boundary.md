# Extraction boundary

This note tracks which economic and qualification artifacts belong in this
Sew-owned repository and what must be true before PRF copies can be removed.

## Sew-owned candidate contents

- The Sew model package includes
  `resources/sew/policies/default-slash-distribution-v1.edn`. Its content
  matches the copy in PRF's current `packages/sew-protocol-model` tree.
- The Sew-specific yield adapter is under `extensions/sew-yield-integration`.
- The Sew financial lifecycle models and tests are under
  `extensions/sew-financial-lifecycle`.
- The Sew pro-rata economics adapter is under
  `extensions/sew-pro-rata-economics`.
- Sew-specific allocation application, held-custody projection, and slash-plan
  application adapters are under `extensions/sew-pro-rata-application`.
- The Sew bounty escrow-to-workflow route is under
  `extensions/sew-bounty-route`.
- The Sew application adapter for generic with-bounty plans is under
  `extensions/sew-bounty`.
- Optional Sew analysis projections and validators are under
  `extensions/sew-analysis`.
- Sew-specific economic and adversarial research models are under
  `extensions/sew-research-models`; generic Monte Carlo primitives remain in
  PRF.
- Sew research analyses are under `extensions/sew-research`; the shared PRF
  simulation runtime remains in PRF.
- The Sew pro-rata slashing and evidence adapter is under
  `extensions/sew-pro-rata-slashing`.
- Sew slash-allocation conformance vectors are under
  `extensions/sew-pro-rata-test-vectors`; generic liquidity-fulfillment
  vectors remain in PRF.

These additions are in a candidate branch of the public Sew repository. Keep
their PRF copies until this candidate revision is published and PRF consumers
resolve and qualify against it.

The repository root `deps.edn` is the consumer entrypoint: it exposes the
Sew-owned runtime roots together and pins PRF core. Consumers do not need to
compose sibling Git subdirectory dependencies.

## Existing `prf-sew-core-model` repository

[`Sew-Protocol/prf-sew-core-model`](https://github.com/Sew-Protocol/prf-sew-core-model)
already contains the Solidity Sew Core behavior adapter and its yield
integration. This candidate adds the broader Sew model and extensions to that
repository while preserving the existing package and its history. The candidate
branch must pass its combined repository qualification before publication.

## PRF component migration map

This maps the inspected PRF component registry to the candidate package layout.
“Copied” means the sources and tests exist here; it does not mean the PRF
component can be removed before the consumer switch.

| PRF component | Candidate disposition |
| --- | --- |
| `:prf/sew-escrow-kernel-v1`, `:prf/sew-protocol-model-v1` | `packages/sew-protocol-model/`; copied and clean runtime dependency resolution passed. |
| `:prf/sew-yield-integration-v1` | `extensions/sew-yield-integration/`; copied. Shared `:prf/yield-runtime-v1` remains in PRF. |
| `:prf/sew-core-extension-v1` | Existing adapter under `src/prf/extensions/sew_core_model/`; its source and history remain in this repository. |
| `:prf/sew-force-authorisation-v1` | Not included here; another agent owns this extraction. Keep it in PRF until that work is reviewed and switched. |
| `:prf/sew-analysis-v1`, `:prf/sew-research-models-v1`, `:prf/sew-research-v1` | `extensions/sew-analysis/`, `extensions/sew-research-models/`, and `extensions/sew-research/`; copied. Shared PRF simulation primitives remain in PRF. |
| `:prf/sew-financial-lifecycle-v1` | `extensions/sew-financial-lifecycle/`; copied. |
| `:prf/sew-pro-rata-economics-v1`, `:prf/sew-pro-rata-slashing-v1`, `:prf/sew-pro-rata-application-v1` | `extensions/sew-pro-rata-economics/`, `extensions/sew-pro-rata-slashing/`, and `extensions/sew-pro-rata-application/`; copied. Generic pro-rata allocation and verification remain in PRF. |
| `:prf/sew-pro-rata-test-vectors-v1` | Sew slash-allocation emitter, five canonical JSON vectors, and contract tests are copied to `extensions/sew-pro-rata-test-vectors/`. The generic vector wrapper and liquidity fixtures stay in PRF; remove only the Sew-owned source/material after consumer references switch. |
| `:prf/sew-bounty-adapter-v1`, `:prf/sew-bounty-route-v1` | `extensions/sew-bounty/` and `extensions/sew-bounty-route/`; copied. Generic bounty composition remains in PRF. |
| Sew domain-reference validation material | `extensions/sew-reference-validation/` and `qualification/sew-domain-reference-v1/`; copied. Its generic validation harness remains in PRF. |

After this candidate revision is published, migrate the copied PRF component
profiles and declared test material to the immutable Sew revision. Then remove
only the source/material paths owned exclusively by those switched components.
The mixed vector component, force-authorisation, generic yield runtime,
generic pro-rata, generic bounty, and shared research/runtime code require
separate ownership decisions and must not be removed as part of that bulk
switch.

## Keep in PRF

- Generic pro-rata allocation and its portable contract remain PRF concepts
  and implementations. Sew-specific settlement behavior belongs in this repo.
- `config/defaults.edn` in the current `agent-c-3` workspace has no asset
  decimals default. Its `:pro-rata-parallelism` value is execution tuning, not
  an allocation rule.
- Generic yield and liquid-lending suites remain PRF-owned. Examples and sample
  claims remain non-authoritative PRF examples unless a Sew application
  contract adopts them.
- PRF now separates the generic `pro-rata` profile from the optional
  `sew-pro-rata-application` profile. The latter still includes the in-tree
  Sew model and is not an independently consumable package.

## Sew domain-reference qualification suite

The candidate copies `suites/sew-domain-reference-v1` into
`qualification/sew-domain-reference-v1`, and owns copies of its five scenario
inputs under `extensions/sew-reference-validation/scenarios/edn`. The package
also owns the Sew replay, invariant-checker, projection, and trace-export
adapter. Its CLI delegates to PRF's generic reference-validation harness at
the pinned PRF core revision.

The original PRF suite and scenario files remain in place because PRF artifacts
still refer to them. Keep those source copies until this repository has a
fetchable revision and the PRF-side consumer gate passes. When PRF consumers
switch, remove or replace duplicate references as a separate change.

## Audit of the requested economic artifacts

This audit distinguishes Sew-owned definitions from reusable PRF mechanisms
and illustrative inputs. It does not authorize deleting the PRF copies before
the removal gate below is met.

| Artifact group | Disposition |
| --- | --- |
| `protocols/sew/policies/default-slash-distribution-v1.edn` | Sew-owned policy is already copied to `packages/sew-protocol-model/resources/sew/policies/`; SHA-256 matched the inspected PRF staging copy at `66829bbd9781a7f16f43a7b88a9ff69ae07a8d71`. Keep the PRF source until consumers switch. |
| `config/defaults.edn` asset decimals | The inspected staging snapshot has `:yield/default-asset-decimals 18`; the current `agent-c-3` source no longer has this key. In the staging code, the value feeds `token-decimals`, but the current quantization functions ignore their decimals argument, so this fallback does not presently change numeric results. Keep the shared fallback with PRF unless the yield API semantics change; protocol deployments should supply token metadata explicitly. |
| Generic `profiles/pro-rata*.edn` and `pro-rata/portable-contract/` | Remain PRF-owned. The Sew-specific realization is copied under `extensions/sew-pro-rata-application/`; the generic allocation contract and portable profile stay in PRF. |
| Sew yield and financial lifecycle profiles | The Sew yield integration and financial lifecycle code/tests are copied under `extensions/sew-yield-integration/` and `extensions/sew-financial-lifecycle/`. PRF profiles remain as in-tree compositions until package dependencies are fetchable. |
| Scenario assumptions and expected results | The five Sew domain-reference scenarios and expected evidence are copied under `qualification/sew-domain-reference-v1/`. The inspected tree has no `suites/sew-domain-reference-v1/configs/economic-assumptions.edn`; the similarly named file is under generic `suites/reference-validation-v1`. Yield and liquid-lending suites remain PRF-owned. |
| `data/fixtures/protocol/sew.edn` | Copied to `examples/sew-protocol-fixture.edn` as a non-authoritative legacy sample. No checked-in scenario fixture reference to `:protocol/sew` was found. Keep the PRF copy until consumers switch. |
| Examples and sample claims | Keep generic pro-rata requests and non-authoritative demos in PRF as examples. The `sew-claims.edn` registry is still bundled with `sew-runner`; its claims have no validation dates, cite scenario files outside the extracted Sew suite, and include force-authorisation claims owned by other work. Do not present it as qualified evidence or move it as part of this extraction. |
| PRF README economic language | The current README already describes pro-rata parameters as versioned reference-application inputs, not PRF-wide defaults. Keep that distinction when the repository documentation changes. |

## Removal gate

Do not remove PRF source based on an unpublished candidate branch. First
publish a fetchable immutable revision, update PRF profile and consumer
dependency declarations, and pass the clean-clone consumer and qualification
gates against that revision.
