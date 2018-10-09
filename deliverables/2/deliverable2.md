# CS1699 Deliverable 2: Mining

**ASSIGNED: 9 October 2018 ... DUE: 30 October 2018 (Late: 1 November 2018 / -20 points)**

_Note: Assignments (printout and final git commit) are due at the beginning of class on the day listed._

For the second deliverable, we are going to write code for a BillCoin miner, Mine, which accepts a file full of transactions and creates a valid candidate block which maximizes transaction fees.

_Note: generating a block with an **optimal** amount of transaction fees is NP-hard, as it is simply a variant of the knapsack problem.  I am interested in determining if your program is making a good effort to maximize transaction fees, not verifying that you have picked the absolutely optimal selection.  If you are interested, here is a good description of why this is so: https://freedom-to-tinker.com/2014/10/27/bitcoin-mining-is-np-hard/_

There are several limitations on the candidate block that you generate:

1. The hash of the block as a whole should meet the target difficulty passed in as the second argument (see difficulty calculation)
2. The size of the nonce field is four ASCII characters - you may fill it in with any four characters that you like, but no more or fewer (e.g., "A8hh" is a valid nonce, but neither "aa" nor "lalalalala" are)
3. There is a size limit to the size of the block you may create (see below)
4. It MUST contain at least one transaction.
5. A single coinbase transaction MUST be included.  It should send 50 BillCoins plus all fees in the block to the miner's address (1333dGpHU6gQShR596zbKHXEeSihdtoyLb)

You may assume that all transactions are valid (e.g. no previously-spent UTXOs, no improperly signed transactions, etc.).  Unlike the first deliverable, you may also assume that any file that I pass in will be entirely valid - I will not pass in an invalidly formatted or otherwise invalid file when grading the assignment.

You may use any of the sample code provided in the repository for this project (and I recommend you do, instead of re-inventing the wheel).

## Transaction Format

Transactions consist of a series of comma-delimited inputs, a semicolon, then a series of comma-delimited outputs.  All non-coinbase transactions will have at least one input, and all transactions will have at least one output.  There is no limit on the number of inputs or outputs for a given transactions other than that a transaction that is too large will not fit in a block!

Each input or output will have a `>` sign and a positive integer (i.e., > 0) indicating the value of that input or output in Billcoins.  BillCoin does not allow fractional, negative, or 0-coin transactions (although it does allow 0-fee transactions - but if you like, you can ignore those freeloaders!).

You may assume that all transactions are valid - correctly signed, the user has access to the UTXOs being used as input, etc.  If we were operating a real miner, we would obviously be much more paranoid!

All BillCoin transactions are P2PKH using the same address scheme as Bitcoin.  You do not need to worry about implementing/verifying P2SH or other more complex transactions.

### Sample Transaction

```
1E4BLSFNZo14q8ec7w3J3Wp6njNd7rWxbx>10;1J3ZQJ3ZBjig677KmjdJjhcQizrm4ek1Az>6,14cCzg1Xw5edM3viuTqtgeFX4tRjCXTx1H>3
```

This transaction contains one input: `1E4BLSFNZo14q8ec7w3J3Wp6njNd7rWxbx>10` consisting of 10 BillCoins; and two outputs, `1J3ZQJ3ZBjig677KmjdJjhcQizrm4ek1Az>6` and `14cCzg1Xw5edM3viuTqtgeFX4tRjCXTx1H>3`.

## Timestamp Format

Timestamps should be the number of milliseconds since the epoch.  This should not be hardcoded.  You do not need to re-obtain the timestamp for each hash, but you should at least get the current timestamp when first starting the Mine program.  You are allowed to obtain a new timestamp or increment/modify it at any point if you like, but you must start with the current timestamp (e.g., no starting at 0).

## Nonce Format

The nonce can be any four-character string of printable (8-bit) ASCII characters, i.e. ASCII codes 32-126 (decimal).  It cannot be more or less than four characters.

## "Concat Root"

Instead of constructing a Merkle tree, you should simply concatenate all of the transactions together in order (no delimiter) and get the SHA-256 hash of it.  This will be referred to as the "concat root".  For example, assume you decide to include the following two transactions:

```
1E4BLSFNZo14q8ec7w3J3Wp6njNd7rWxbx>10;1J3ZQJ3ZBjig677KmjdJjhcQizrm4ek1Az>6,14cCzg1Xw5edM3viuTqtgeFX4tRjCXTx1H>3
1AY7sesEcVihM5LR8Bw9WXv6bWSt7vKak4>30,1AY7sesEcVihM5LR8Bw9WXv6bWSt7vKak4>20;1EinGRFVjNuTwzPT7dZ3K9JwhAJM3qpn3U>45
```

You would concatenate them together to form:

```
1E4BLSFNZo14q8ec7w3J3Wp6njNd7rWxbx>10;1J3ZQJ3ZBjig677KmjdJjhcQizrm4ek1Az>6,14cCzg1Xw5edM3viuTqtgeFX4tRjCXTx1H>31AY7sesEcVihM5LR8Bw9WXv6bWSt7vKak4>30,1AY7sesEcVihM5LR8Bw9WXv6bWSt7vKak4>20;1EinGRFVjNuTwzPT7dZ3K9JwhAJM3qpn3U>45
```

Then run SHA-256 hash on it to get the "concat root":

```
dab45c1455517304246cb33d205f217849ed70025e45866b8125d9ddb54fadb5
```


## Calculating the Block Hash

You should use the SHA-256 hashing algorithm on the concatenated result of: previous hash, number of transactions in the block, current timestamp, difficulty, nonce, and the concat root of the concatenation of all the transactions.  These values should not be delimited by any other characters (e.g., if previous hash is "0982" and number of transactions is "4", the concatenated string should be "09824" - *not* "0982,4", "0982 4" etc.

## Block Size Limit

Blocks cannot be of unlimited size.  Although the Bitcoin weighting mechanism is a bit more complex (see https://en.bitcoin.it/wiki/Weight_units) and block size has been a very controversial topic (see https://en.bitcoin.it/wiki/Block_size_limit_controversy), we are going to just say that any candidate block must have a MAXIMUM of 16 transaction inputs and/or outputs.  That is, the total number of all inputs and outputs cannot be greater than 16.

Recall that the coinbase transaction does not have any input transactions, so in terms of transaction weighting, it will count as "one transaction output" (outputting the block reward plus fees to the miner's address).

## Target Difficulty Calculation

The target is calculated from the difficulty by dividing the maximimum target in Billcoin by the difficulty.  The maximum target is 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF.  Difficulty is input and displayed in DECIMAL, not HEXADECIMAL, format (e.g., 15, not 0xF), although you are allowed to convert to hexadecimal internally if you find this easier.

For example, if the difficulty is 16, the target is 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF / 16 (0x10), or 0x0FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF.

Note that the maximum BillCoin target is different from the maximum Bitcoin target (0x00000000FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF).  This is to make mining easier (mining with difficulty = 1 in BillCoin means that almost any value will be a valid hash) as well as to simplify the concept.  In BillCoin, the difficulty is also approximately the mean number of Bernoulli trials necessary before meeting the objective of `H(block) < target` (e.g., if the difficulty is set to 100, the mean number of attempts to find a valid block will be ~ 100).

## Valid Block and Output

Upon finding a valid block, you should first output the words "CANDIDATE BLOCK - Hash " followed by the calculated hash of the block (which must be less than the target), and then display the candidate block in the format below.

A valid block must contain the following attributes in the correct (specified) order, each followed by a newline character:

1. Previous hash
2. Number of transaction inputs and outputs in this block (must be <= 16)
3. Current timestamp (in milliseconds since the epoch)
4. Difficulty
5. Nonce
6. concat root of sorted transaction list
7. List of transactions (transaction inputs, transaction outputs, hash of transaction)

### Sample Blocks From transaction_list.txt:

Note: you will almost certainly not obtain the exact same results as these, especially due to changes in which transactions you add (and thus the concat root) and the timestamp.

```
CANDIDATE BLOCK = Hash 00002812958efbee699a8283933e64f0736b09199f2feacce374b95a5f2d4b3c
---
000019fa7744d87c6cff6cfa18aa779af7904e01c6b0b86e3444721314609b49
16
1539045994344
100000
 ,{E
5dba50d1d34ae0801833f27753acbdd4d0bb0a499e164f24896d39a31d466a37
182mG8b9jm6ZfYAqRnLoZ8tFsxHxAXZcRy>100;1N3QPvVzXRAurG9JpN3CZGU8o5Fwh9JDwC>10,1CmMtdA8sZrQbbH3LCHfrpTGXr3CdqH7Aa>10,15d9uVAJowRYs7EYvTtBbfGWyG7e5YHwbA>10,1PJwzxwtCmvyx92hnFX3L9VMEeAUx9JhZo>50
1Lrqp1LHYH7aJX5ajy7TNyzbNv4URr5Yjp>28,1Lrqp1LHYH7aJX5ajy7TNyzbNv4URr5Yjp>20;13fEqFzndapuffXZj5TjFrrQsmaubg7hGJ>30,1CDFt9ZWYp55gd9PD7e4t9aYKMSQJAsZNr>7
1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100;14P72s4mMcKqs3SDCsX3h7qh8526gC8dve>225,13QxtTz5FnTsg8VyZRg8hPgunC8VW8nz2i>30,1BfTK9ftgnaXgK3Wj17Da4J9Mw5KmzLzfe>30
;1333dGpHU6gQShR596zbKHXEeSihdtoyLb>96
```

```
CANDIDATE BLOCK = Hash 00008236acdb717c5adc45a20c0f81ce5537c078990caefd4591cf5182e36f36
---
00002812958efbee699a8283933e64f0736b09199f2feacce374b95a5f2d4b3c
16
1539046044010
100000
 *yi
5dba50d1d34ae0801833f27753acbdd4d0bb0a499e164f24896d39a31d466a37
182mG8b9jm6ZfYAqRnLoZ8tFsxHxAXZcRy>100;1N3QPvVzXRAurG9JpN3CZGU8o5Fwh9JDwC>10,1CmMtdA8sZrQbbH3LCHfrpTGXr3CdqH7Aa>10,15d9uVAJowRYs7EYvTtBbfGWyG7e5YHwbA>10,1PJwzxwtCmvyx92hnFX3L9VMEeAUx9JhZo>50
1Lrqp1LHYH7aJX5ajy7TNyzbNv4URr5Yjp>28,1Lrqp1LHYH7aJX5ajy7TNyzbNv4URr5Yjp>20;13fEqFzndapuffXZj5TjFrrQsmaubg7hGJ>30,1CDFt9ZWYp55gd9PD7e4t9aYKMSQJAsZNr>7
1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100;14P72s4mMcKqs3SDCsX3h7qh8526gC8dve>225,13QxtTz5FnTsg8VyZRg8hPgunC8VW8nz2i>30,1BfTK9ftgnaXgK3Wj17Da4J9Mw5KmzLzfe>30
;1333dGpHU6gQShR596zbKHXEeSihdtoyLb>96
```

### Sample Blocks from small_transaction_list.txt

Note: you will almost certainly not obtain the exact same results as these, especially due to changes in how you construct the concat root and the timestamp.


```
CANDIDATE BLOCK = Hash 0005dba7f6480e7e7e51d01ac1b7420f80a44ce4381e491ca87b4eb2941a10dd
---
0002b89b85e814a5b390f73d6da47f9744666796c69891157e50787e666594fc
16
1539045557708
10000
 ![(
4814ffcf04b5a49e9fc8e5d2be73923ec5b0b85e7352de65b894087b5f6a6f61
1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100;14P72s4mMcKqs3SDCsX3h7qh8526gC8dve>225,13QxtTz5FnTsg8VyZRg8hPgunC8VW8nz2i>30,1BfTK9ftgnaXgK3Wj17Da4J9Mw5KmzLzfe>30
19DbSByZsVJiARvXTF5krFuuEYusZKobT>100;17Nc9KSSuULvTnoba7MdprY5vnDtLWW3DS>89,18RENfoKJsV5XfH5b2vu4tNGMRfmHnXCHn>6
1NQrwAQrvFgKC4jt1whz2iaaAsjzxSLgbd>20,18soCPK9vE9oPesxSKKvupYo7Yi9rnGQus>10;1HkwVee51yb1z3ks5v9yj4K1QZQuJhjuHG>26
1BRsmBq9a4WraBokXZcZcd8vgcaTtdiTFc>5;1ErF9Q3z82PTpzTBQ2wvjQQ7j7UNGjtMuJ>3,19xiyFGHNujpA6PiWmsmrm8TmsvkVq8G5a>2
;1333dGpHU6gQShR596zbKHXEeSihdtoyLb>74
```

```
CANDIDATE BLOCK = Hash 00018802ad03e606db9d240eb3caa1a0d5f0ea7121072d5e92d242a4491d3205
---
0002b89b85e814a5b390f73d6da47f9744666796c69891157e50787e666594fc
16
1539045855611
10000
  Ro
4814ffcf04b5a49e9fc8e5d2be73923ec5b0b85e7352de65b894087b5f6a6f61
1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100;14P72s4mMcKqs3SDCsX3h7qh8526gC8dve>225,13QxtTz5FnTsg8VyZRg8hPgunC8VW8nz2i>30,1BfTK9ftgnaXgK3Wj17Da4J9Mw5KmzLzfe>30
19DbSByZsVJiARvXTF5krFuuEYusZKobT>100;17Nc9KSSuULvTnoba7MdprY5vnDtLWW3DS>89,18RENfoKJsV5XfH5b2vu4tNGMRfmHnXCHn>6
1NQrwAQrvFgKC4jt1whz2iaaAsjzxSLgbd>20,18soCPK9vE9oPesxSKKvupYo7Yi9rnGQus>10;1HkwVee51yb1z3ks5v9yj4K1QZQuJhjuHG>26
1BRsmBq9a4WraBokXZcZcd8vgcaTtdiTFc>5;1ErF9Q3z82PTpzTBQ2wvjQQ7j7UNGjtMuJ>3,19xiyFGHNujpA6PiWmsmrm8TmsvkVq8G5a>2
;1333dGpHU6gQShR596zbKHXEeSihdtoyLb>74
```

### Sample Blocks From ultra_small_transaction_list.txt

You will note that here I did not have enough transactions to even reach the maximum number of inputs/outputs per block!  There are only nine in the whole list, plus one for the coinbase.  But making a small block is better than no block!

```
CANDIDATE BLOCK = Hash 0238cd4f3bff11853f97c0739afd5e0fe944ab75d155bba4dd7b53bb702dcc6c
---
00FF2812958efbee699a8283933e64f0736b09199f2feacce374b95a5f2d4b3c
10
1539046104738
100
   *
80228e31576a358a643671595e25e8c4c405c1cc23cd4c3e1b94beb45fdf0ead
1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100;14P72s4mMcKqs3SDCsX3h7qh8526gC8dve>225,13QxtTz5FnTsg8VyZRg8hPgunC8VW8nz2i>30,1BfTK9ftgnaXgK3Wj17Da4J9Mw5KmzLzfe>30
1NQrwAQrvFgKC4jt1whz2iaaAsjzxSLgbd>20,18soCPK9vE9oPesxSKKvupYo7Yi9rnGQus>10;1HkwVee51yb1z3ks5v9yj4K1QZQuJhjuHG>26
;1333dGpHU6gQShR596zbKHXEeSihdtoyLb>69
```

```
CANDIDATE BLOCK = Hash 01b0db7b7a32fa6fcee338df2ca37b27b795818292e59b5b7b090d1040484533
---
0238cd4f3bff11853f97c0739afd5e0fe944ab75d155bba4dd7b53bb702dcc6c
10
1539046213683
100
   "
80228e31576a358a643671595e25e8c4c405c1cc23cd4c3e1b94beb45fdf0ead
1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100,1Khee1UxMsrvhN2tZCGZa4tZC41kumYNr9>100;14P72s4mMcKqs3SDCsX3h7qh8526gC8dve>225,13QxtTz5FnTsg8VyZRg8hPgunC8VW8nz2i>30,1BfTK9ftgnaXgK3Wj17Da4J9Mw5KmzLzfe>30
1NQrwAQrvFgKC4jt1whz2iaaAsjzxSLgbd>20,18soCPK9vE9oPesxSKKvupYo7Yi9rnGQus>10;1HkwVee51yb1z3ks5v9yj4K1QZQuJhjuHG>26
;1333dGpHU6gQShR596zbKHXEeSihdtoyLb>69
```

## Turning the Assignment In

You will turn in a sheet to me (see Format, below) and create your code and upload it to a repository on GitHub.

The repository you create should be private.  Usernames `laboon` (the instructor) and `chzGrd` (Raymond Zhang, the TA) should be added as collaborators.  The code should be entirely on the master branch.  Aside from the code, the only files in the repository should be the optional `README.md` file and .gitignore files.

You will need to have an account on GitHub and be able to create private repositories.  If you cannot create a private repository on GitHub, please sign up for a Student Developer Pack at https://education.github.com/.  The Student Developer Pack is free and will give you the ability to create private repositories as well as other benefits.

## Format
Please hand in a printed-out (hardcopy) cover page with:
* The name of the project ("CS1699 Deliverable 2: Mining")
* Your name
* Your GitHub username
* A link to your private repository on GitHub with the Java files

* ADDITIONALLY, you will include a short description of how your mining program maximizes transaction fees.  This should be anywhere from a paragraph to a page; please do not make your description much longer than a page.

## Compiling Mine

There should be no IDE files in the repository and I should be able to compile the program with a simple `javac *.java`.  Do not turn in an assignment where I will have to modify the classpath, include .jar files or other libraries, etc. Do not turn in an assignment using Gradle, ant or other build control system.

### Running Mine

The program shall be run with the following command:

```
java Mine *candidate_transaction_file* *difficulty* *prev_hash*
```

## Using Sample Code

You are allowed to copy/paste/modify any sample code from the `./sample_code/*` subdirectories in order to complete this assignment.  Do not copy code from other sources.

## Tips

1. I will always give a VALID transaction file.  Do not waste much time on worrying about invalid formats, invalid transactions, etc.
5. Don't worry too much about performance.  Although efficient hashpower is extremely important in a real miner, I am concerned about correctness, not hashes per second.
2. All transaction values and totals can be held within a standard signed 32-bit int (i.e., a Java int).  There will be no transaction files with a number of transactions that cannot be stored by an int, with a number of inputs or outputs not able to be stored by an int.
3. TreeMaps were very useful for me when developing my solution - these act like automatically sorted-by-key HashMaps. https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
3. Be sure to trim carriage returns before hashing!
3. Don't be wedded to the object-oriented paradigm!  I have found that for this deliverable, it is much more useful to approach problems in a more functional/procedural way (lots of static methods in my solution).
5. For more information on different approaches to the knapsack problem, I recommend this paper: "Different Approaches to Solve the 0/1 Knapsack Problem" by Kristakeva and Shrestha as an overview: http://www.micsymposium.org/mics_2005/papers/paper102.pdf  The "0/1" in the title refers to the fact that an item (e.g., transaction) can either be in or out, you can't have multiple copies of it.
4. For storing large numbers in Java, try BigInteger (https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html).  Example, reading in a large value with a radix of 16 (i.e. hexadecimal):


```
    public static final BigInteger MAX_TARGET = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", 16);
```
As always, feel free to email me or come to office hours with any questions.