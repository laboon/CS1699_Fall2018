# Syllabus - Fall 2018
CS1699 Special Topics: Blockchain Technology and Cryptocurreny

_Although the instructor will make a best effort to have the class topic on the day listed, occasionally a change must be made (e.g., a lecture going long, or a guest lecturer unable to make it to class that day).  However, these are the topics that will be covered and the expected date that they will be taught._


Week 1 (Week of 27 AUG):

1. What is a blockchain?  What is cryptocurrency?

2. High-level overview _(READING: BCT, Foreword)_
  * Short history of digital currency
  * Problems with previous implementations
  * How they were solved by Bitcoin
  * Basics of using Bitcoin (wallets, transactions)

Week 2 (Week of 3 SEP):

1. Public-key cryptography _(READING: BCT, Chapter 1)_
   * How does it work?
   * Public and private keys
   * One-way functions

2. Hashing and cryptographic hashing algorithms - what makes a good hashing algorithm?
  * collision‐resistance
  * hiding
  * puzzle‐friendliness.

Week 3 (Week of 10 SEP):

1. Blockchain and related data structures
  * Hash pointers
  * Naive blockchain (linked list with hash pointers)
  * Merkle tree (binary tree with hash pointers)

2. More hashing
  * Merkle-Damgard transforms
  * Digital signatures
  * Public identities

Week 4 (Week of 17 SEP):

1. Decentralization and Bitcoin _(READING: BCT, Chapter 2)_
  * Distributed Consensus protocol
  * Attacks on distributed consensus
    * False spending - block creation/modification
    * Denial of service
    * Double-spend attack
    * 51% attack

2. Proof of Work and mining
  * What are miners doing? (Hash puzzles)
  * Bernoulli trials and Poisson process outcome
  * Transaction fees and block rewards

Week 5 (Week of 24 SEP):

1. Mechanics of Bitcoin _(READING: BCT, Chapter 3 - 3.4)_
  * What makes up a transaction?
  * Ledger vs account model
  * Bitcoin scripts
  * Anatomy of a Block (transactions and metadata)

2. The Bitcoin network _(READING: BCT, Chapter 3.5 - 3.6)_
  * Block propagation
  * Lightweight (SPV) vs full nodes
  * Hard and soft forks
  * Limitations of the current Bitcoin network, and how to improve

Week 6 (Week of 1 OCT):

1. MIDTERM 1

2. Using the Bitcoin network _(READING: BCT, Chapter 4 - 4.3)_
  * What does it really mean to "own"/"have" some bitcoin?
  * Key management
  * Hot and cold storage
  * Sharing and splitting keys (multi-sig, threshold)

2. Exchanges and Risks of Bitcoin  _(READING: BCT, Chapter 4.4 - 4.7)
  * What is an exchange?
  * Counterparty risk
  * Regulatory risk
  * Reserve risk
  * Quantum, SHA-256 weakness, or other "unforeseen" attack

Week 7 (Week of 8 OCT):

1. Mining in-depth (Chapter 5)
  * Specifics on how mining works
  * Mining difficulty (ever-increasing)
  * Hardware (CPU vs GPU vs FPGAs vs ASICs)
  * Mining pools
  * Drawbacks of mining

2. Bitcoin as Anonymous Currency _(READING: BCT, Chapter 6 - 6.3)_
  * Anonymity vs Pseudonymity
  * Deanonymization via side channel attacks
  * Taint analysis
  * Mixing

Week 8 (Week of 15 OCT):

1. NO CLASS ON TUESDAY - HAPPY FALL BREAK

2. Improving the Anonymity of Bitcoin _(READING: BCT, Chapter 6.4 - 6.5)_
  * Decentralized Mixing
  * CoinJoin
  * Zerocoin and Zerocash
  * zk-SNARKs and the challenges of a trusted setup

Week 9 (Week of 22 OCT):

1. Achieving Consensus _(READING: BCT, Chapter 7)_
  * Kinds of consensus: rules, history, value
  * Bitcoin Core
  * BIPs (Bitcoin Improvement Proposals)

2. Variations on a Theme: Non-SHA256 Puzzles _(READING: BCT, Chapter 8 - 8.2)_
  * Progress-freeness
  * ASIC-resistant (not ASIC-proof) puzzles
  * Memory-hard puzzles (Scrypt)

Week 10 (Week of 29 OCT):

1. More variations on a theme: Non-SHA256 Puzzles _(READING: BCT, Chapter 8.3 - 8.5)_
  * Proof-of-useful-work
  * Non-outsourceable Puzzles
  * Proof-of-stake vs Proof-of-work
  * Problems with Proof-of-stake

2. Bitcoin as a platform _(READING: BCT, Chapter 9 - 9.4)_
  * Secure timestamping
  * Burning bitcoin (unspendable transactions)
  * Overlay currencies
  * "Smart property" (colored coins)
  * Bitcoin as a source of randomness

Week 11 (Week of 5 NOV):

1. MIDTERM 2

2. Bitcoin applications _(READING: BCT, Chapter 9.5)_
  * Smart contracts
  * Bitcoin lotteries (e.g. SatoshiDice)
  * Decentralized prediction markets
  * Contract settlement

Week 12 (Week of 12 NOV):

1. Altcoins _(READING: BCT, Chapter 10 - 10.4)_
  * What is an altcoin?
  * How to create (story, initial allocation, parameter/code/consensus diffs)
  * Altcoin infanticide
  * Merge mining

2. A Tour of Altcoins
  * Litecoin - "Silver to Bitcoin's Gold"
  * Dogecoin - Inflationary Bitcoin
  * Ethereum - More powerful scripting language
  * Monero - Anonymous, not pseudonymous, transactions
  * Nano - Distributed Proof of Stake, multiple blockchains

Week 13 (Week of 19 NOV):

1. Atomic Swaps and Sidechains _(READING: BCT, Chapter 10.5 - 10.6)_
  * Cross-chain swap protocol
  * Sidechains - "Altcoins on Bitcoin"
  * SPV proofs
  * Transfer contesting
  * PoW sampling via PoW skiplists

2. Societal Impacts _(READING: BCT, Chapter 11)_
  * Decentralization of institutions
  * Blockchain interaction with the "real world"
  * Economic impact
  * Political impact

Week 14 (Week of 26 Nov):

1. Societal Impacts (discussion class)
  * DUE: Blockchain Impact Paper

2. Ethereum - the "World Computer"
  * Blockchain with Turing-complete programming
  * Smart contract programming model
  * Solidity and the Ethereum Virtual Machine
  * ERC-20 tokens and ICOs
  * dapps
  * Specific examples, e.g. Golem, Storj, Augur, Cryptokitties

Week 15 (Week of 3 Dec):

1. Overview of the Course and the Future of Blockchain Technology
  * Recent changes and roadmap of Bitcoin (Segregated Witness a.k.a. SegWit, Bitcoin / Bitcoin Cash fork, Lightning Network)
  * Philosophy of Bitcoin
  * Current political climate and its impact on cryptocurrency

2. MIDTERM 3
