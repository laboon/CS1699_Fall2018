# Midterm 2 Study Guide

_The second (of three) midterms is on 6 Nov 2018.  You should study the topics below in order to be prepared._

## Using the Bitcoin Network
   * What does it really mean to "own"/"have" some bitcoin?
   * What is the difference between hot and cold storage?
   * What are the different kinds of wallets and their trade-offs?
   * Why is address re-use such a bad idea?
   * Understand a possible way to use Secret Sharing for arbitrary k-of-n splitting.
   * What are the different ways of sharing and splitting keys (e.g., multi-sig, threshold)?
   * What is a Bitcoin exchange?  How does it compare to an online wallet?
   * Understand the risks of Bitcoin: Counterparty, regulatory, reserve risk, quantum weakness, SHA-256 weakness, mining death spiral, denial of service attacks, or other "unforeseen" attack

## Mining in-depth
   * How exactly does mining work?
   * How is the difficulty of mining modified?  Based on what parameters?
   * What is the difference between pay-per-share and proportional payout schemes?
   * What Hardware (CPU vs GPU vs FPGAs vs ASICs)
   * How do mining pools work?
   * What is a "share" in a mining pool?
   * What is a forking attack?  How does it work?
   * What is punitive forking?  How does it work?
   * What is feather forking?  How does it work?
   * Understand selfish mining and block-witholding attacks.
   * What are some of the drawbacks of mining?

## Bitcoin as Anonymous Currency
   * Anonymity vs Pseudonymity - what is the difference?
   * Is Bitcoin anonymous?  Why or why not?
   * How does deanonymization, especially via side channel, attacks work?
   * What is an anonymity set?  How can we use it for anonymity?
   * What is linking?  How can it be used to deanonymize?
   * What are idioms of use?  How can they be used to deanonymize?
   * What is taint analysis?
   * Understand how network-level (as opposed to blockchain-level) deanonymization works.
   * Understand how to avoid network-level deanonymization and the challenges thereof.
   * Understand how mixing works, including weaknesses and why mixers use a single chunk size.
   * How does CoinJoin work?

##. Achieving Consensus
   * What are the different kinds of consensus that must be achieved for Bitcoin to operate properly?
   * What is Bitcoin Core?
   * What are BIPs?
   * What is a currency fork?
   * What is a UASF?
   * Understand the general philosophical differences of "big-blockers" vs. "small-blockers".
   * Understand the specific philosophical/implementation differences between Bitcoin and Bitcoin Cash.

## Variations on a Theme: Non-SHA256 Puzzles
   * Understand the rationale for non-SHA256 puzzles, especially why many puzzles try to be ASIC-resistant.
   * Can a puzzle be truly ASIC-proof and not just ASIC-resistant?
   * Understand the difference between memory-hard and memory-bound.
   * Understand memory-hard puzzles such as scrypt and Cuckoo Cycles.
   * Given a "simple implementation" of scrypt or Cuckoo Cycle algorithm, be able to work through creating a hash.
   * What is proof-of-useful-work?  Be able to explain how a system like this might work.
   * What are non-outsourceable puzzles?  Be able to explain how one might work.
   * Understand how proof-of-stake works.
   * What are the problems with proof-of-stake, especially in regards to the "nothing at stake" problem?

## Bitcoin as a Platform
   * How can we use the Bitcoin network for secure timestamping?
   * How does one burn bitcoin?  Why would one do that?
   * What are overlay currencies?  How do they work?
   * What is "smart property"?
   * How is smart property implemented via colored coins?
   * How can we use Bitcoin as a source of randomness?