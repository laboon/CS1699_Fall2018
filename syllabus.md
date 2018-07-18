# Syllabus - Fall 2018
CS1699 Special Topics: Blockchain Technology and Cryptocurreny

_Although the instructor will make a best effort to have the class topic on the day listed, occasionally a change must be made (e.g., a lecture going long, or a guest lecturer unable to make it to class that day).  However, these are the topics that will be covered and the expected date that they will be taught._


Week 1 (Week of 27 AUG):

1. What is a blockchain?  What is cryptocurrency?

2. High-level overview _(READING: BCT, Foreword)_
  a. Short history of digital currency
  b. Problems with previous implementations
  c. How they were solved by Bitcoin
  d. Basics of using Bitcoin (wallets, transactions)

Week 2 (Week of 3 SEP):

1. Public-key cryptography _(READING: BCT, Chapter 1)_
   a. How does it work?
   b. Public and private keys
   c. One-way functions

2. Hashing and cryptographic hashing algorithms - what makes a good hashing algorithm?
  a. collision‐resistance
  b. hiding
  c. puzzle‐friendliness.

Week 3 (Week of 10 SEP):

1. Blockchain and related data structures
  a. Hash pointers
  b. Naive blockchain (linked list with hash pointers)
  c. Merkle tree (binary tree with hash pointers)

2. More hashing
  a. Merkle-Damgard transforms
  b. Digital signatures
  c. Public identities

Week 4 (Week of 17 SEP):

1. Decentralization and Bitcoin _(READING: BCT, Chapter 2)_
  a. Distributed Consensus protocol
  b. Attacks on distributed consensus
    1. False spending - block creation/modification
    2. Denial of service
    3. Double-spend attack
    4. 51% attack

2. Proof of Work and mining
  a. What are miners doing? (Hash puzzles)
  b. Bernoulli trials and Poisson process outcome
  a. Transaction fees and block rewards

Week 5 (Week of 24 SEP):

1. Mechanics of Bitcoin _(READING: BCT, Chapter 3 - 3.4)_
  a. What makes up a transaction?
  b. Ledger vs account model
  c. Bitcoin scripts
  d. Anatomy of a Block (transactions and metadata)

2. The Bitcoin network _(READING: BCT, Chapter 3.5 - 3.6)_
  a. Block propagation
  b. Lightweight (SPV) vs full nodes
  c. Hard and soft forks
  d. Limitations of the current Bitcoin network, and how to improve

Week 6 (Week of 1 OCT):

1. MIDTERM 1

2. Using the Bitcoin network _(READING: BCT, Chapter 4 - 4.3)_
  a. What does it really mean to "own"/"have" some bitcoin?
  b. Key management
  c. Hot and cold storage
  d. Sharing and splitting keys (multi-sig, threshold)

2. Exchanges and Risks of Bitcoin  _(READING: BCT, Chapter 4.4 - 4.7)
  a. What is an exchange?
  b. Counterparty risk
  c. Regulatory risk
  e. Reserve risk
  e. Quantum, SHA-256 weakness, or other "unforeseen" attack

Week 7 (Week of 8 OCT):

1. Mining in-depth (Chapter 5)
  a. Specifics on how mining works
  b. Mining difficulty (ever-increasing)
  c. Hardware (CPU vs GPU vs FPGAs vs ASICs)
  d. Mining pools
  e. Drawbacks of mining

2. Bitcoin as Anonymous Currency _(READING: BCT, Chapter 6 - 6.3)_
  a. Anonymity vs Pseudonymity
  b. Deanonymization via side channel attacks
  c. Taint analysis
  d. Mixing


Week 8 (Week of 15 OCT):

1. NO CLASS ON TUESDAY - HAPPY FALL BREAK

2. Improving the Anonymity of Bitcoin _(READING: BCT, Chapter 6.4 - 6.5)_
  a. Decentralized Mixing
  b. CoinJoin
  c. Zerocoin and Zerocash
  d. zk-SNARKs and the challenges of a trusted setup

Week 9 (Week of 22 OCT):

1. Achieving Consensus _(READING: BCT, Chapter 7)_
  a. Kinds of consensus: rules, history, value
  b. Bitcoin Core
  d. BIPs (Bitcoin Improvement Proposals)

2. Variations on a Theme: Non-SHA256 Puzzles _(READING: BCT, Chapter 8 - 8.2)_
  a. Progress-freeness
  b. ASIC-resistant (not ASIC-proof) puzzles
  c. Memory-hard puzzles (Scrypt)

Week 10 (Week of 29 OCT):

1. More variations on a theme: Non-SHA256 Puzzles _(READING: BCT, Chapter 8.3 - 8.5)_
  a. Proof-of-useful-work
  b. Non-outsourceable Puzzles
  c. Proof-of-stake vs Proof-of-work
  d. Problems with Proof-of-stake


2. Bitcoin as a platform _(READING: BCT, Chapter 9 - 9.4)_
  a. Secure timestamping
  b. Burning bitcoin (unspendable transactions)
  c. Overlay currencies
  d. "Smart property" (colored coins)
  e. Bitcoin as a source of randomness


Week 11 (Week of 5 NOV):

1. MIDTERM 2

2. Bitcoin applications _(READING: BCT, Chapter 9.5)_
  a. Smart contracts
  b. Bitcoin lotteries (e.g. SatoshiDice)
  c. Decentralized prediction markets
  d. Contract settlement

Week 12 (Week of 12 NOV):

1. Altcoins _(READING: BCT, Chapter 10 - 10.4)_
  a. What is an altcoin?
  b. How to create (story, initial allocation, parameter/code/consensus diffs)
  c. Altcoin infanticide
  d. Merge mining

2. A Tour of Altcoins
  a. Litecoin - "Silver to Bitcoin's Gold"
  b. Dogecoin - Inflationary Bitcoin
  c. Ethereum - More powerful scripting language
  d. Monero - Anonymous, not pseudonymous, transactions
  e. Nano - Distributed Proof of Stake, multiple blockchains

Week 13 (Week of 19 NOV):

1. Atomic Swaps and Sidechains _(READING: BCT, Chapter 10.5 - 10.6)_
  a. Cross-chain swap protocol
  b. Sidechains - "Altcoins on Bitcoin"
  c. SPV proofs
  d. Transfer contesting
  e. PoW sampling via PoW skiplists

2. Societal Impacts _(READING: BCT, Chapter 11)_
  a. Decentralization of institutions
  b. Blockchain interaction with the "real world"
  c. Economic impact
  d. Political impact

Week 14 (Week of 26 Nov):

1. Societal Impacts (discussion class)
  a. Pros and cons of impacts from Lecture 1

2. Ethereum - the "World Computer"
  a. Blockchain with Turing-complete programming
  b. Smart contract programming model
  c. Solidity and the Ethereum Virtual Machine
  d. ERC-20 tokens and ICOs
  e. dapps
  f. Specific examples, e.g. Golem, Storj, Augur, Cryptokitties


Week 15 (Week of 3 Dec):

1. Overview of the Course and the Future of Blockchain Technology
  a. Recent changes and roadmap of Bitcoin
    1. Segregated Witness (SegWit)
    2. Bitcoin / Bitcoin Cash fork
    3. Lightning Network
  b. Philosophy of Bitcoin
  c. Current political climate and its impact on cryptocurrency

2. MIDTERM 3
