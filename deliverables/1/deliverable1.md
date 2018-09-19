# CS1699 Deliverable 1: StringCoin

**ASSIGNED: September 18, 2018 ... DUE: October 4, 2018**

For the first deliverable, we are going to create a variation on ScroogeCoin called StringCoin.  Like ScroogeCoin, this is a centralized blockchain - Bill is the source of all coins.  ANY user can add a block as long as it is valid

You will read in the blockchain file, generate a blockchain, determine if it is valid, and print out all coins generated and who owns them.

All coins are named using string representations of four-hexit hexadecimal numbers (e.g., 0001, a873, 091b, etc.)

You may use any of the sample code provided in the repository for this project (and I recommend you do, instead of re-inventing the wheel).

## StringCoin blockchain format

Lines represent a single block in the StringCoin blockchain.  Each block consists of a single CREATE (which creates one coin) or a single TRANSFER (which transfers a coin from one address to another).

Every line starts with a hash of the entire previous line.

Public keys (addresses) and private keys are 256 bits using DSA with the default SUN provider (i.e., `KeyPairGenerator.getInstance("DSA", "SUN")` and `keyGen.initialize(256, random)`).  Message signatures are use SHA-1 w/DSA (i.e., `Signature.getInstance("SHA1withDSA", "SUN");`).

Hashes of the previous line use SHA-256 and thus are 256 bits.

All newly created coins start in the Bill account (public and secret keys below).

The specific formats are:

PREV,CREATE,COIN,COINSIG,SIG
PREV,TRANSFER,COIN,PK,SIG

PREV represents a SHA-256 of the entire previous line, excluding carriage returns, or "0" if this is the genesis block in the blockchain (i.e. no blocks are before it).  The input include commas.  For example, the second line in `test_blockchain.txt` has the previous hash `76ec8f7d80cd4885ac32690e9665bd4a60b20d4643ca077ac44f459c8d23953d`.  This is the SHA-256 hash of the entire first line:
```
0,CREATE,0000,302c021438f0d799617ce8df63fa9ce19933d6f0c0aa571402142a5ec931ed5869db154780d25a5282217a673f93,302d0215008d9a0e258486cf11f5e55ca0396bb61ff8d569c402147a09c9a7e63f225278d5b8262ce29a3e278a7c72
```

The second field can be one of two values, `CREATE` or `TRANSFER`.  `CREATE` generates a coin and gives it to Bill; `TRANSFER` transfers a coin - if a person owns it - to the address (public key) in the fourth field.  Any other command is an error and the blockchain is invalid.

The third field will always refer to a coin ID - either a non-existent one (in the case of `CREATE` commands) or one that a particular person already owns (in the case of `TRANSFER` command).

A coin ID may not be regenerated - once it exists, it cannot be re-created (that is, there cannot be two coins with the same ID, e.g., `000a` and `000a`).  A new coin with a new ID must be generated instead.

If the second field was `CREATE`, the fourth field will  be the name of the coin signed with Bill's secret key (below) to verify that he authorized the creation of this coin.  If the second field was `TRANSFER`, this will be the address (public key) of the recipient of the coin.

The final field (SIG) will always be a signature of the block up to this point as a string, signed with the secret key of the block creator (i.e. Bill for all CREATE blocks, whoever owns/is moving the coin for all TRANSFER blocks).

Note that this should NOT include the "final" comma since we have not yet added the final field.  For example, for the first block in `test_blockchain.txt`, the signature is `302d0215008d9a0e258486cf11f5e55ca0396bb61ff8d569c402147a09c9a7e63f225278d5b8262ce29a3e278a7c72` and applies to the previous string `0,CREATE,0000,302c021438f0d799617ce8df63fa9ce19933d6f0c0aa571402142a5ec931ed5869db154780d25a5282217a673f93`.


If there is a failure, it is enough to simply state that the blockchain cannot be read or that there is an error.  It is not necessary to state what specifically the error is, although it is a nice addition.  However, you should NOT try to display who owns what coins if there is an error!  An error indicates an invalid blockchain and you should just inform the user and exit the program.

Error output does not need to be precisely like mine - it is enough that you indicate that there is some sort of error - but your final output should follow the same format as mine, i.e.,

```
Coin *coin ID* / Owner = *public key*
```

This should be sorted by coin ID alphabetically.

## Tips

1. Be sure to read in data as plain ASCII, not UTF-8 (e.g., use string.getBytes())
2. I recommend you use the newly-added `/hash/Sha256Hash.java`, `/public_key_crypto/Sign.java`, and `/public_key_crypto/Verify.java` files to test your code, hashes, signing, verifying, reading, etc.  Note that you may have to adjust output size!
3. I wrote up much of the sample code to help you - use it!
4. I recommend that Work on getting the happy path (i.e. `test_blockchain.txt` which has no errors) to work, and just throw an exception if you hit a problem.  Then go back and ensure that you handle errors appropriately.

## Public/Secret Keys

_Note: These are the public/private keys used in the sample blockchain files.  However, they are not the ONLY possible public/private keypairs!  You may create your own for testing purposes and the files that will be graded may contain other keypairs other than these.  Your program should as long as the key pairs are valid!_

```
Bill (Creator of all coins):

Public key (pk): 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10
Private key (sk): 3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca404160214556d46e1888b30bccf9c4a5ea71b41c107b5d219

Mario:

Public key (pk): 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca40343000240437cefda4a79ded357c6a976803e73ba9f08cebc257e401dd42d8e71a04e7d8fb97f3d70c7fdd1b7579af65b407b2f382f316d3afb9b687d1c289c1bf6a1ee04
Private key (sk): 3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4041602141da8d6f0b3653346a5377cab2fc7140022206a31

Peach:

Public key (pk): 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca40343000240502d40adb1c58beaede4cee3ce8450626a5922b60ef66f4d96d7496cda8661dd2c06c3a09b4fadcd3a6c36b7bdec474fde18cf7bff68258f0edfa281857d72aa
Private key (sk): 3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4041602140767f5828e24761782413054778d969a06b0ce26

```

## Sample Output

Good blockchain (`test_blockchain.txt`) - Coins 0000 and 0004 belong to Peach, Coin 0001 belongs to Mario, Coins 0002 and 0003 belong to Bill.

```
(24270) $ java StringCoin test_blockchain.txt
Coin 0000 / Owner = 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca40343000240502d40adb1c58beaede4cee3ce8450626a5922b60ef66f4d96d7496cda8661dd2c06c3a09b4fadcd3a6c36b7bdec474fde18cf7bff68258f0edfa281857d72aa
Coin 0001 / Owner = 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca40343000240437cefda4a79ded357c6a976803e73ba9f08cebc257e401dd42d8e71a04e7d8fb97f3d70c7fdd1b7579af65b407b2f382f316d3afb9b687d1c289c1bf6a1ee04
Coin 0002 / Owner = 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10
Coin 0003 / Owner = 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10
Coin 0004 / Owner = 3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca40343000240502d40adb1c58beaede4cee3ce8450626a5922b60ef66f4d96d7496cda8661dd2c06c3a09b4fadcd3a6c36b7bdec474fde18cf7bff68258f0edfa281857d72aa
```

Bad blockchain - creation of Coin 0000 is invalidly signed

```
(24272) $ java StringCoin invalid_signature_0000_creation.txt
InvalidDataException: Invalid coin 0000
	at StringCoin.computeLine(StringCoin.java:39)
	at StringCoin.main(StringCoin.java:117)
Caught exception InvalidDataException: Invalid coin 0000
Quitting..
```

Bad blockchain - SHA-256 previous hash on third block is incorrect

```
(24274) $ java StringCoin invalid_prev_hash_line_3.txt
InvalidDataException: Invalid hash: aaa389f3260ddde836c401da556ecc34a5a1daae2d861bbcb2e0148c35afe7bd does not match 555389f3260ddde836c401da556ecc34a5a1daae2d861bbcb2e0148c35afe7bd
	at StringCoin.computeLine(StringCoin.java:29)
	at StringCoin.main(StringCoin.java:117)
Caught exception InvalidDataException: Invalid hash: aaa389f3260ddde836c401da556ecc34a5a1daae2d861bbcb2e0148c35afe7bd does not match 555389f3260ddde836c401da556ecc34a5a1daae2d861bbcb2e0148c35afe7bd
Quitting..
```

Bad blockchain - Line 2 was not properly signed

```
(24276) $ java StringCoin invalid_signature_line_2.txt
InvalidDataException: Invalid line 76ec8f7d80cd4885ac32690e9665bd4a60b20d4643ca077ac44f459c8d23953d,CREATE,0001,302c02147db1925ee26abc8675aaf2aa47da437e8bc4f2e00214484c7f49c40a94935d0606cf9f8a6eaf0a0a6401
	at StringCoin.computeLine(StringCoin.java:50)
	at StringCoin.main(StringCoin.java:117)
Caught exception InvalidDataException: Invalid line 76ec8f7d80cd4885ac32690e9665bd4a60b20d4643ca077ac44f459c8d23953d,CREATE,0001,302c02147db1925ee26abc8675aaf2aa47da437e8bc4f2e00214484c7f49c40a94935d0606cf9f8a6eaf0a0a6401
Quitting..
```