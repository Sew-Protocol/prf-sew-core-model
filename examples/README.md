# Sew examples

`sew-protocol-fixture.edn` is a copied legacy PRF fixture for illustration.
It is not a canonical Sew deployment configuration or an authoritative set of
economic parameters. The inspected PRF tree contains no checked-in scenario
reference to the `:protocol/sew` fixture key. Consumers should supply explicit,
versioned protocol parameters for any actual run.

`economic-assumptions.edn` is an illustrative use-case input. It is not loaded
by the reference-validation runner and does not define protocol defaults.

The PRF source copy remains until the Sew repository is fetchable and PRF
profiles and consumers have switched.
