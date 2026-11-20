# Sew PRF runtime composition

This code-free package resolves the complete Sew runtime for PRF's runner and
CLI aliases. It composes the focused Sew Core, model, yield, financial,
pro-rata, bounty, analysis, research, and reference-validation packages at
immutable revisions.

The package also supplies Sew-owned runtime defaults as
`META-INF/prf/defaults.edn`. PRF's generic defaults loader discovers these
resources from the classpath; PRF core does not contain Sew scenario defaults.

Profile views should depend on the individual package components they select.
Full PRF runner aliases can depend on this composition to avoid repeating the
package list.
