# Midterm 1 Study Guide

_The first (of three) midterms is on 2 OCTOBER 2018.  You should study the topics below in order to be prepared._

## Bitcoin Pre-History

1. What were some of the problems with previous implementations of online currencies (e.g. b-money, Bitgold, DigiCash, First Virtual) that Bitcoin solved?


## Basic Cryptography

1. What is the difference between a public and a secret (private) key in public-key cryptography?
1. What is a cryptographic hash function?
2. What three properties must a cryptographic hash fit for Bitcoin have?  Be able to explain each one.
3. What is a message digest?  Given a simple cryptographic hash function and a string, be able to calculate the message digest (aka the "hash" of the string).
4. Understand the algorithms behind a commitment scheme (`com = commit(msg, nonce)` and `verify(com, msg, nonce)`) and what it can be used for.
5. Why is the puzzle-friendliness in SHA-256 important in Bitcoin?
6. Understand Merkle-Damgard transforms and Merkle-Damgard strengthening.
7. Given some compression function `c` and initialization vector `iv`, be able to use Merkle-Damgard transforms and strengthening in order to calculate a fixed-size output from an arbitrary-length input.

## Data Structures in Bitcoin

1. You will need to understand hash pointers in-depth.  Given some hash function, some object and a way to get a string value from it, be able to calculate a hash pointer to it.
2. Be able to construct a block chain using hash pointers, and how to verify the validity of the block chain.
3. What is the point of using block chains instead of simple linked lists?
4. Understand Merkle trees and be able to create and determine their validity.

## Digital Signatures and Digital Identity

1. Be able to explain the Unforgeability Game and how it can be used to determine the strength/validity of a digital signature scheme.
1. Understand how identities work in Bitcoin.
1. Understand entirely decentralized identity management compared to a public key infrastructure (benefits and drawbacks).
1. Understand how digital signatures work and the three fundamental algorithms for a digital signature scheme:
```
(sk, pk) = generateKeys(keysize)
sig = sign(sk, msg)
isValid = verify(pk, message, sig)
```

## Centralized Cryptocurrencies

1. Review GoofyCoin and ScroogeCoin - understand how they use digital signature verification.
2. Understand double-spending and how Scrooge prevented it.
3. Understand the drawbacks of centralized ScroogeCoin.

## Bitcoin and Decentralization

1. What are the benefits/drawbacks of a decentralized currency?
2. How decentralized is Bitcoin?
3. Understand what a distributed consensus protocol is and what characteristics it must have.
4. How are Bitcoin transactions broadcast and confirmed? (transaction pool/mempool vs. blockchain)
5. How does the impossibility of a "global time standard" impact Bitcoin?
6. How does Bitcoin "get around" the Byzantine Generals' Problem via randomness and incentives?
7. Understand the naive consensus protocols (voting and random node selection).
8. Understand how both of the previous protocols are defeated via Sybil attacks or malicious nodes, and the impacts of a malicious node.  What can a malicious node do and what can it NOT do?


## Incentives and Proof of Work

1. How does Bitcoin mining prevent Sybil attacks?
2. Understand block rewards (the coinbase), including halvening, and transaction fees.
3. What are miners doing, exactly?  What are hash puzzles and how are they solved?  What are nonces?
4. What are the three most important properties of hash puzzles?
5. Understand Bernoulli trials and Poisson processes, and their impact on Bitcoin mining (e.g. exponential distribution of block times, block generation occurring independently, etc.)
6. What is hashpower?  Given that a miner controls some fraction of total hashpower of the network, what is the mean time to their next generated block?
7. Why is it important that the miner's results (i.e. the validity of the generated block with nonce) are trivial to verify compared to the computing power necessary to generate a block?
8. Be able to explain what a 51% attack is, how it can be accomplished, and what the attacker can and cannot do.  How does this compare to Scrooge in ScroogeCoin?

## Mechanics of Bitcoin

1. Understand transaction-based vs. account-based ledgers.  Which does Bitcoin use?  Why?
2. Understand how transactions work (inputs and outputs).
3. Understand the basics of Script - you do not need to actually write any Script or know commands, but do know what it is used for and what kind of language it is (stack-based), and why it is (deliberately) not Turing-complete.
4. What is Proof-of-Burn?  Why is it useful?
5. What is the difference between to pay-to-script-hash (P2SH) and pay-to-public-key-hash (P2PKH)?
6. Why are scripts used instead of just regular addresses?  What are some things that we can do with Script that we could not do otherwise?
7. What is the "lock time" on a Bitcoin transaction?

## Block Anatomy and the Bitcoin Network

1. Which data structures are used in blocks / the Bitcoin ledger as a whole?
2. What is the "coinbase" attribute used for?
3. Who controls which nodes join the Bitcoin network?
4. What is the topology (layout) of Bitcoin nodes?  How do new Bitcoin nodes discover this?
5. Understand the flooding algorithm (gossip protocol).
6. When a node receives a transaction, what are the four checks done to determine if it should be propagated or dropped?
7. What is the difference between a fully validating node and a lightweight node (SPV client)?
8. What is the difference between a hard fork and a soft fork?