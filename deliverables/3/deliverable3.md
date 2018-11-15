# CS1699 Deliverable 3: Non-SHA256 Puzzle

**ASSIGNED: 6 November 2018 ... DUE: 20 November 2018 (Late: 27 November 2018 / -20 points)**

_Note: Assignments (printout and final git commit) are due at the beginning of class on the day listed._

## Overview

For this assignment, we are going write our own hashing function, LaboonHash, which uses Merkle-Damgard strengthening and Merkle-Damgard transforms.  This will be similar to SHA-256 and other SHA algorithms.  Using LaboonHash as a base hashing function, we will then make a memory-hard version of this hashing function, LaboonCrypt, which operates in a similar manner as `scrypt`.


## Format
Please hand in a printed-out (hardcopy) cover page with:
* The name of the project ("CS1699 Deliverable 3: Non-SHA256 Puzzle")
* Your name
* Your GitHub username
* A link to your private repository on GitHub with the Java files

## Part 1: LaboonHash

LaboonHash accepts an arbitrary-length string, applies standard Merkle-Damgard strengthening, and applies the compression function, `c`, defined below.

Compression function c:

1. The initialization vector is the string (not number) `1AB0`
1. Input consists of two arguments, one of which is four characters and the other is eight characters
1. Each character in a string is assumed to be printable ASCII
1. The compression function itself consists of three phases, described below.  The four-character input argument shall be referred to as "lhs" and the eight-character input as "rhs".
1. Phase 1 of compression: The char values of lhs (ascending) and rhs (descending from rhs[3]) are added and put into the result.  By this, I mean result[0] is the result of adding lhs[0] and rhs[3], result[1] the result of adding lhs[1] and rhs[2], etc., for the first four chars of rhs)
1. Phase 2 of compression: The char values of result (note: NOT lhs) and rhs (descending from rhs[7]) are XORed and put into the result.  By this, I mean result[0] is the result of XORing result[0] and rhs[7], result[1] the result of XORing result[1] and rhs[6], etc., for the LAST four chars of rhs)
1. Phase 3 of compression: The char values of result (ascending) are XORed with the char values of result (descending).  By this, I mean that result[0] will be the result of result[0] XORed with result[3], result[1] the result of result[1] XORed with result[2], etc., for each of the bytes
1. Note that this is not simultaneous - for example, result[0] should be XORed with result[3], then after this result[3] is XORed with result[0].  The result[0] that is XORed in the latter case has already been modified by the result[0] XORed with result[3] earlier.
1. Merkle-Damgard strengthening output is in _hexadecimal_ and zero-padded.  Note that this should occur as part of the same final block (i.e., you should not generate a new block for strengthening).  Rather, you should take the length modulo 16^n, where n is the number of spaces available.  See Strengthening Description for more information and an example.
1. All hexadecimal output shall consist of the characters `0123456789ABCDEF` (i.e., hex letters are uppercase)
1. For output, the value of each result `char` is taken modulo 16 and the hexadecimal character corresponding to that value is added to the result.  For example, if the value in result[0] is 33, 33 % 16 = 1, which would be the character "1".  If the value is 31, 31 % 16 = 15, which equals F, and thus the character "F" is added.
1. Output is a string consisting of four hexadecimal characters

## Strengthening Description and Example

You should take the number of remaining spaces (n) in the last block, then take the length of the original string  modulo 16^n (0x10 ^ n) (since that is the max hex value that can be stored). For example, in the below example, the length is 0x17 (23) but 0x17 % 0x10 (23 % 16) = 0x7, so we store a 7 there.  Trying to store a "17" would mean that we would have created a new block since we would have "overflowed" the space remaining.

Example:

```
(1374) $ java LaboonHash "no more mr. nice guy..." -verbose
	Padded string: no more mr. nice guy...7
	Blocks: 
	no more 
	mr. nice
	 guy...7
	Iterating with 1AB0 / no more  = F74E
	Iterating with F74E / mr. nice = F963
	Iterating with F963 /  guy...7 = 5308
	Final result: 5308
LaboonHash hash = 5308
```

### Verbosity Levels

Running LaboonHash with the argument "-verbose" should display the following additional information:

1. The padded string (i.e., the string after Merkle-Damgard strengthening)
2. All of the blocks that were generated from the input string
3. Each iteration step, including the two inputs and result

If any invalid options are passed in, or zero arguments, or more than two arguments, the system shall show usage and exit.

Examples:

```
(20289) $ java LaboonHash A -verbose
	Padded string: A0000001
	Blocks:
	A0000001
	Iterating with 1AB0 / A0000001 = 1310
	Final result: 1310
LaboonHash hash = 1310

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20290) $ java LaboonHash A
LaboonHash hash = 1310

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20291) $ java LaboonHash A -notavalidoption
Usage:
java LaboonHash *string* *verbosity_flag*
Verbosity flag can be omitted for hash output only
Other options: -verbose

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20292) $ java LaboonHash A -verbose -wocka -wocka -wocka
Usage:
java LaboonHash *string* *verbosity_flag*
Verbosity flag can be omitted for hash output only
Other options: -verbose

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20293) $ java LaboonHash "meow meow meow meow meow me" -verbose
	Padded string: meow meow meow meow meow me0001B
	Blocks:
	meow meo
	w meow m
	eow meow
	 me0001B
	Iterating with 1AB0 / meow meo = AF57
	Iterating with AF57 / w meow m = A13B
	Iterating with A13B / eow meow = C076
	Iterating with C076 /  me0001B = 7041
	Final result: 7041
LaboonHash hash = 7041

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20296) $ java LaboonHash "no more mr. nice guy...." -verbose
	Padded string: no more mr. nice guy....
	Blocks:
	no more
	mr. nice
	 guy....
	Iterating with 1AB0 / no more  = F74E
	Iterating with F74E / mr. nice = F963
	Iterating with F963 /  guy.... = C301
	Final result: C301
LaboonHash hash = C301

```

## Part 2: LaboonCrypt

Now that we have a properly working LaboonHash, we are going to generate a memory-hard variant, LaboonCrypt.  This will generate a 12x12 matrix of hash results from the input, and then use the input to maneuver around the existing matrix and "re-hash" the input at that particular location.  Finally, all of the results in the matrix will be concatenated and a final LaboonHash run will be executed on the concatenated string for the final result.

All locations will contain a single LaboonHash result and will thus always consist of four characters which can be interpreted as hexadecimals.  The initial location (`[0,0]`) of the matrix will be the LaboonHash result of the initial input.  For example, the LaboonHash of "meow" is "170C", and thus the 0,0th element in the matrix will be the string "170C".

The next location (`[0,1]`) will be the LaboonHash of the value at location `[0,0]`.  For example, the LaboonHash of the "170C" is "1810", and so location `[0,1]` will have the value "1810".  At the end of each row, you will "jump down" to the beginning of the next row, similar to reading from left to right.  For example, for "meow", the value at location `[0,11]` is "8920".  Thus, the value at `[1,0]` in the next row will be the LaboonHash of "8920", i.e., "D835".

After generating the initial matrix, we are going to re-read the characters input, starting at location 0.  The ASCII value of the character will be taken, and then we will determine how many locations we will move "down" (i.e., incrementing the `x` in `array[x][y]`) and "right" (i.e., incrementing the `y` in `array[x][y]`).  This will move the imaginary "cursor" which starts out pointing at `[0,0]`.

The number of locations to move down is the character's ASCII value times 11.  The number of locations to move right is the character's ASCII value + 3, THEN multiplied by 7 (i.e., `(value + 3) * 7`).  If the "cursor" loops around (i.e., goes past 12, which it generally should!), it should "loop around".  For example, `arr[13][4]` should loop around to `arr[1][4]` since `arr[13][4]` is past the bounds of our 12x12 array.

After moving to a new location, take the string stored at that location and apply the LaboonHash function to that string to get a new LaboonHash result.  Replace the value at that location with the new LaboonHash.  For example, if your cursor is at location `1,1` and the current value there is `093F`, it should be replaced by the LaboonHash of "093F", i.e., "3F43".

Continue doing this for all characters in the input string, moving and replacing values at each iteration.

When all characters have been iterated through, concatenate the entire array, row by row and column by column (i.e., like reading from left to right).  Do not add any delimiters - this result should be a string of characters which all can represent hexadecimal numbers (`0123456789ABCDEF`) with no spaces, commas, or other characters.

Finally, run LaboonHash on this final concatenated string to determine the final LaboonCrypt result.

Please refer to the sample output in case of any ambiguity.

### Verbosity Levels

There are four levels of verbosity which can be specified for LaboonCrypt.

1. Not entering anything for an argument should show only the final hash
2. Entering the argument "-verbose" should show the initial matrix and the final matrix, as well as everything in option 1
3. Entering the argument "-veryverbose" should show everything in option 2, plus the movement of the "cursor" to different locations in the matrix and how the matrix was modified at that location
4. Entering the argument "-ultraverbose" should show everything in option 3, plus all of the output from the verbose mode of LaboonHash for each LaboonHash call *(Note: this will result in a very large amount of output!  There are a large number of calls to LaboonHash for each instance of LaboonCrypt.)*

If any invalid options are passed in, or zero arguments, or more than two arguments, the system shall show usage and exit.

```
[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20308) $ java LaboonCrypt
Usage:
java LaboonCrypt *string* *verbosity_flag*
Verbosity flag can be omitted for hash output only
Other options: -verbose -veryverbose -ultraverbose

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20309) $ java LaboonCrypt a b c d e
Usage:
java LaboonCrypt *string* *verbosity_flag*
Verbosity flag can be omitted for hash output only
Other options: -verbose -veryverbose -ultraverbose

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20310) $ java LaboonCrypt "meow"
LaboonCrypt hash: 9318

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20311) $ java LaboonCrypt "meow" -verbose
Initial array:
170C 1810 4825 6932 1F47 DD5C 4060 1575 3F82 4197 89AC 8920
D835 6E42 1257 D26C 4370 1D85 3F92 42A7 862C 8B30 D045 6752
1F67 DF7C 4080 1B95 3EA2 4527 843C 8240 D155 6562 1077 DA8C
4A90 19A5 3922 4837 8E4C 8250 D265 6372 1D87 DF9C 42A0 1625
3B32 4047 875C 8F60 DF75 6082 1B97 DEAC 4520 1435 3242 4157
856C 8070 DA85 6A92 19A7 D92C 4830 1E45 3252 4267 837C 8D80
DF95 62A2 1627 DB3C 4040 1755 3F62 4F77 808C 8B90 DEA5 6522
1437 D24C 4150 1565 3072 4A87 8A9C 89A0 D925 6832 1E47 D25C
4260 1375 3D82 4F97 82AC 8620 DB35 6042 1757 DF6C 4F70 1085
3B92 4EA7 852C 8430 D245 6152 1567 D07C 4A80 1A95 39A2 4927
883C 8E40 D255 6262 1377 DD8C 4F90 12A5 3622 4B37 804C 8750
DF65 6F72 1087 DB9C 4EA0 1525 3432 4247 815C 8560 D075 6A82
Final array:
170C 1810 4825 6932 1F47 DD5C 4060 1575 3F82 4197 89AC 8920
D835 6E42 1257 D26C 4370 1D85 3F92 42A7 862C 8B30 D045 6752
1F67 DF7C 4080 1B95 3EA2 4527 843C 8240 D155 6562 1077 DA8C
4A90 19A5 3922 4837 8E4C 8250 6372 6372 1D87 DF9C 42A0 1625
3B32 4047 875C 8F60 DF75 6082 1B97 DEAC 1435 1435 3242 4157
856C 8070 DA85 6A92 19A7 D92C 4830 1E45 3252 4267 837C 8D80
62A2 62A2 1627 DB3C 4040 1755 3F62 4F77 808C 8B90 DEA5 6522
1437 D24C 4150 1565 3072 4A87 8A9C 89A0 D925 6832 1E47 D25C
4260 1375 3D82 4F97 82AC 8620 DB35 6042 1757 DF6C 4F70 1085
3B92 4EA7 852C 8430 D245 6152 1567 D07C 4A80 1A95 39A2 4927
883C 8E40 D255 6262 1377 DD8C 4F90 12A5 3622 4B37 804C 8750
DF65 6F72 1087 DB9C 1525 1525 3432 4247 815C 8560 D075 6A82
LaboonCrypt hash: 9318

[laboon@ekaterina ~/pitt/pitt_private/CS1699_deliverable_3]
(20312) $ java LaboonCrypt "meow" -veryverbose
Initial array:
170C 1810 4825 6932 1F47 DD5C 4060 1575 3F82 4197 89AC 8920
D835 6E42 1257 D26C 4370 1D85 3F92 42A7 862C 8B30 D045 6752
1F67 DF7C 4080 1B95 3EA2 4527 843C 8240 D155 6562 1077 DA8C
4A90 19A5 3922 4837 8E4C 8250 D265 6372 1D87 DF9C 42A0 1625
3B32 4047 875C 8F60 DF75 6082 1B97 DEAC 4520 1435 3242 4157
856C 8070 DA85 6A92 19A7 D92C 4830 1E45 3252 4267 837C 8D80
DF95 62A2 1627 DB3C 4040 1755 3F62 4F77 808C 8B90 DEA5 6522
1437 D24C 4150 1565 3072 4A87 8A9C 89A0 D925 6832 1E47 D25C
4260 1375 3D82 4F97 82AC 8620 DB35 6042 1757 DF6C 4F70 1085
3B92 4EA7 852C 8430 D245 6152 1567 D07C 4A80 1A95 39A2 4927
883C 8E40 D255 6262 1377 DD8C 4F90 12A5 3622 4B37 804C 8750
DF65 6F72 1087 DB9C 4EA0 1525 3432 4247 815C 8560 D075 6A82
Moving 1199 down and 784 right - modifying [11, 4] from 4EA0 to 1525
Moving 1111 down and 728 right - modifying [6, 0] from DF95 to 62A2
Moving 1221 down and 798 right - modifying [3, 6] from D265 to 6372
Moving 1309 down and 854 right - modifying [4, 8] from 4520 to 1435
Final array:
170C 1810 4825 6932 1F47 DD5C 4060 1575 3F82 4197 89AC 8920
D835 6E42 1257 D26C 4370 1D85 3F92 42A7 862C 8B30 D045 6752
1F67 DF7C 4080 1B95 3EA2 4527 843C 8240 D155 6562 1077 DA8C
4A90 19A5 3922 4837 8E4C 8250 6372 6372 1D87 DF9C 42A0 1625
3B32 4047 875C 8F60 DF75 6082 1B97 DEAC 1435 1435 3242 4157
856C 8070 DA85 6A92 19A7 D92C 4830 1E45 3252 4267 837C 8D80
62A2 62A2 1627 DB3C 4040 1755 3F62 4F77 808C 8B90 DEA5 6522
1437 D24C 4150 1565 3072 4A87 8A9C 89A0 D925 6832 1E47 D25C
4260 1375 3D82 4F97 82AC 8620 DB35 6042 1757 DF6C 4F70 1085
3B92 4EA7 852C 8430 D245 6152 1567 D07C 4A80 1A95 39A2 4927
883C 8E40 D255 6262 1377 DD8C 4F90 12A5 3622 4B37 804C 8750
DF65 6F72 1087 DB9C 1525 1525 3432 4247 815C 8560 D075 6A82
LaboonCrypt hash: 9318

(20306) $ java LaboonCrypt "Maggie comes fleet foot - face full of black soot - talkin that the heat put - plants in the bed but" -verbose
Initial array:
A9CF 2F43 2D50 7065 5572 2F87 E19C 59A0 0925 2832 5E47 925C
9260 C375 1D82 6F97 A2AC 1620 4B35 6042 1757 DF6C 4F70 1085
3B92 4EA7 852C 8430 D245 6152 1567 D07C 4A80 1A95 39A2 4927
883C 8E40 D255 6262 1377 DD8C 4F90 12A5 3622 4B37 804C 8750
DF65 6F72 1087 DB9C 4EA0 1525 3432 4247 815C 8560 D075 6A82
1A97 D9AC 4920 1835 3E42 4257 826C 8370 DD85 6F92 12A7 D62C
4B30 1045 3752 4F67 8F7C 8080 DB95 6EA2 1527 D43C 4240 1155
3562 4077 8A8C 8A90 D9A5 6922 1837 DE4C 4250 1265 3372 4D87
8F9C 82A0 D625 6B32 1047 D75C 4F60 1F75 3082 4B97 8EAC 8520
D435 6242 1157 D56C 4070 1A85 3A92 49A7 892C 8830 DE45 6252
1267 D37C 4D80 1F95 32A2 4627 8B3C 8040 D755 6F62 1F77 D08C
4B90 1EA5 3522 4437 824C 8150 D565 6072 1A87 DA9C 49A0 1925
Final array:
7065 2F43 2D50 7065 5572 2F87 59A0 59A0 0925 C375 5E47 925C
9260 C375 A2AC 6F97 A2AC 6042 4B35 6042 4F70 DF6C 4F70 4EA7
3B92 4EA7 852C 8430 D07C 6152 1567 D07C 4A80 1A95 4927 4927
D255 8E40 D255 3622 1377 DD8C 4F90 12A5 3622 DF65 804C 8750
DF65 6F72 4EA0 DB9C 4EA0 3432 3432 4247 D075 8560 D075 1835
1A97 4257 4920 1835 4257 4257 826C 6F92 DD85 6F92 4B30 D62C
3752 1045 3752 8080 8F7C 8080 1527 6EA2 1527 3562 4240 1155
3562 4077 D9A5 8A90 D9A5 4250 1837 DE4C 8F9C 1265 3372 8F9C
8F9C D625 D625 6B32 1F75 D75C 4F60 8520 3082 4B97 D435 8520
6242 6242 1157 4070 4070 1A85 8830 49A7 892C DE45 DE45 6252
1267 D37C 4D80 1F95 32A2 8B3C 8B3C 8040 6F62 6F62 1F77 3522
4B90 824C 3522 4437 8150 8150 D565 1A87 1A87 DA9C 6D82 1925
LaboonCrypt hash: 3A8E

(20305) $ java LaboonCrypt "Maggie comes fleet foot - face full of black soot - talkin that the heat put - plants in the bed but" -veryverbose
Initial array:
A9CF 2F43 2D50 7065 5572 2F87 E19C 59A0 0925 2832 5E47 925C
9260 C375 1D82 6F97 A2AC 1620 4B35 6042 1757 DF6C 4F70 1085
3B92 4EA7 852C 8430 D245 6152 1567 D07C 4A80 1A95 39A2 4927
883C 8E40 D255 6262 1377 DD8C 4F90 12A5 3622 4B37 804C 8750
DF65 6F72 1087 DB9C 4EA0 1525 3432 4247 815C 8560 D075 6A82
1A97 D9AC 4920 1835 3E42 4257 826C 8370 DD85 6F92 12A7 D62C
4B30 1045 3752 4F67 8F7C 8080 DB95 6EA2 1527 D43C 4240 1155
3562 4077 8A8C 8A90 D9A5 6922 1837 DE4C 4250 1265 3372 4D87
8F9C 82A0 D625 6B32 1047 D75C 4F60 1F75 3082 4B97 8EAC 8520
D435 6242 1157 D56C 4070 1A85 3A92 49A7 892C 8830 DE45 6252
1267 D37C 4D80 1F95 32A2 4627 8B3C 8040 D755 6F62 1F77 D08C
4B90 1EA5 3522 4437 824C 8150 D565 6072 1A87 DA9C 49A0 1925
Moving 847 down and 560 right - modifying [7, 8] from 4250 to 1265
Moving 1067 down and 700 right - modifying [6, 0] from 4B30 to 1045
Moving 1133 down and 742 right - modifying [11, 10] from 49A0 to 1925
Moving 1133 down and 742 right - modifying [4, 8] from 815C to 8560
Moving 1155 down and 756 right - modifying [7, 8] from 1265 to 3372
Moving 1111 down and 728 right - modifying [2, 4] from D245 to 6152
Moving 352 down and 245 right - modifying [6, 9] from D43C to 4240
Moving 1089 down and 714 right - modifying [3, 3] from 6262 to 1377
Moving 1221 down and 798 right - modifying [0, 9] from 2832 to 5E47
Moving 1199 down and 784 right - modifying [11, 1] from 1EA5 to 3522
Moving 1111 down and 728 right - modifying [6, 9] from 4240 to 1155
Moving 1265 down and 826 right - modifying [11, 7] from 6072 to 1A87
Moving 352 down and 245 right - modifying [3, 0] from 883C to 8E40
Moving 1122 down and 735 right - modifying [9, 3] from D56C to 4070
Moving 1188 down and 777 right - modifying [9, 0] from D435 to 6242
Moving 1111 down and 728 right - modifying [4, 8] from 8560 to D075
Moving 1111 down and 728 right - modifying [11, 4] from 824C to 8150
Moving 1276 down and 833 right - modifying [3, 9] from 4B37 to 804C
Moving 352 down and 245 right - modifying [7, 2] from 8A8C to 8A90
Moving 1122 down and 735 right - modifying [1, 5] from 1620 to 4B35
Moving 1221 down and 798 right - modifying [10, 11] from D08C to 4B90
Moving 1221 down and 798 right - modifying [7, 5] from 6922 to 1837
Moving 1276 down and 833 right - modifying [11, 10] from 1925 to 3832
Moving 352 down and 245 right - modifying [3, 3] from 1377 to DD8C
Moving 495 down and 336 right - modifying [6, 3] from 4F67 to 8F7C
Moving 352 down and 245 right - modifying [10, 8] from D755 to 6F62
Moving 1122 down and 735 right - modifying [4, 11] from 6A82 to 1A97
Moving 1067 down and 700 right - modifying [3, 3] from DD8C to 4F90
Moving 1089 down and 714 right - modifying [0, 9] from 5E47 to 925C
Moving 1111 down and 728 right - modifying [7, 5] from 1837 to DE4C
Moving 352 down and 245 right - modifying [11, 10] from 3832 to 4E47
Moving 1122 down and 735 right - modifying [5, 1] from D9AC to 4920
Moving 1287 down and 840 right - modifying [8, 1] from 82A0 to D625
Moving 1188 down and 777 right - modifying [8, 10] from 8EAC to 8520
Moving 1188 down and 777 right - modifying [8, 7] from 1F75 to 3082
Moving 352 down and 245 right - modifying [0, 0] from A9CF to 2F43
Moving 1221 down and 798 right - modifying [9, 6] from 3A92 to 49A7
Moving 1122 down and 735 right - modifying [3, 9] from 804C to 8750
Moving 352 down and 245 right - modifying [7, 2] from 8A90 to D9A5
Moving 1078 down and 707 right - modifying [5, 1] from 4920 to 1835
Moving 1188 down and 777 right - modifying [5, 10] from 12A7 to D62C
Moving 1067 down and 700 right - modifying [4, 2] from 1087 to DB9C
Moving 1089 down and 714 right - modifying [1, 8] from 1757 to DF6C
Moving 1177 down and 770 right - modifying [2, 10] from 39A2 to 4927
Moving 352 down and 245 right - modifying [6, 3] from 8F7C to 8080
Moving 1265 down and 826 right - modifying [11, 1] from 3522 to 4437
Moving 1221 down and 798 right - modifying [8, 7] from 3082 to 4B97
Moving 1221 down and 798 right - modifying [5, 1] from 1835 to 3E42
Moving 1276 down and 833 right - modifying [9, 6] from 49A7 to 892C
Moving 352 down and 245 right - modifying [1, 11] from 1085 to 3B92
Moving 495 down and 336 right - modifying [4, 11] from 1A97 to D9AC
Moving 352 down and 245 right - modifying [8, 4] from 1047 to D75C
Moving 1276 down and 833 right - modifying [0, 9] from 925C to 9260
Moving 1067 down and 700 right - modifying [11, 1] from 4437 to 824C
Moving 1188 down and 777 right - modifying [11, 10] from 4E47 to 825C
Moving 1177 down and 770 right - modifying [0, 0] from 2F43 to 2D50
Moving 1155 down and 756 right - modifying [3, 0] from 8E40 to D255
Moving 1210 down and 791 right - modifying [1, 11] from 3B92 to 4EA7
Moving 352 down and 245 right - modifying [5, 4] from 3E42 to 4257
Moving 1276 down and 833 right - modifying [9, 9] from 8830 to DE45
Moving 1144 down and 749 right - modifying [1, 2] from 1D82 to 6F97
Moving 1067 down and 700 right - modifying [0, 6] from E19C to 59A0
Moving 1276 down and 833 right - modifying [4, 11] from D9AC to 4920
Moving 352 down and 245 right - modifying [8, 4] from D75C to 4F60
Moving 1276 down and 833 right - modifying [0, 9] from 9260 to C375
Moving 1144 down and 749 right - modifying [4, 2] from DB9C to 4EA0
Moving 1111 down and 728 right - modifying [11, 10] from 825C to 8260
Moving 352 down and 245 right - modifying [3, 3] from 4F90 to 12A5
Moving 1144 down and 749 right - modifying [7, 8] from 3372 to 4D87
Moving 1111 down and 728 right - modifying [2, 4] from 6152 to 1567
Moving 1067 down and 700 right - modifying [1, 8] from DF6C to 4F70
Moving 1276 down and 833 right - modifying [5, 1] from 3E42 to 4257
Moving 352 down and 245 right - modifying [9, 6] from 892C to 8830
Moving 1232 down and 805 right - modifying [5, 7] from 8370 to DD85
Moving 1287 down and 840 right - modifying [8, 7] from 4B97 to 8EAC
Moving 1276 down and 833 right - modifying [0, 0] from 2D50 to 7065
Moving 352 down and 245 right - modifying [4, 5] from 1525 to 3432
Moving 495 down and 336 right - modifying [7, 5] from DE4C to 4250
Moving 352 down and 245 right - modifying [11, 10] from 8260 to D375
Moving 1232 down and 805 right - modifying [7, 11] from 4D87 to 8F9C
Moving 1188 down and 777 right - modifying [7, 8] from 4D87 to 8F9C
Moving 1067 down and 700 right - modifying [6, 0] from 1045 to 3752
Moving 1210 down and 791 right - modifying [4, 11] from 4920 to 1835
Moving 1276 down and 833 right - modifying [8, 4] from 4F60 to 1F75
Moving 1265 down and 826 right - modifying [1, 2] from 6F97 to A2AC
Moving 352 down and 245 right - modifying [5, 7] from DD85 to 6F92
Moving 1155 down and 756 right - modifying [8, 7] from 8EAC to 8520
Moving 1210 down and 791 right - modifying [6, 6] from DB95 to 6EA2
Moving 352 down and 245 right - modifying [10, 11] from 4B90 to 1EA5
Moving 1276 down and 833 right - modifying [2, 4] from 1567 to D07C
Moving 1144 down and 749 right - modifying [6, 9] from 1155 to 3562
Moving 1111 down and 728 right - modifying [1, 5] from 4B35 to 6042
Moving 352 down and 245 right - modifying [5, 10] from D62C to 4B30
Moving 1078 down and 707 right - modifying [3, 9] from 8750 to DF65
Moving 1111 down and 728 right - modifying [10, 5] from 4627 to 8B3C
Moving 1100 down and 721 right - modifying [6, 6] from 6EA2 to 1527
Moving 352 down and 245 right - modifying [10, 11] from 1EA5 to 3522
Moving 1078 down and 707 right - modifying [8, 10] from 8520 to D435
Moving 1287 down and 840 right - modifying [11, 10] from D375 to 6D82
Moving 1276 down and 833 right - modifying [3, 3] from 12A5 to 3622
Final array:
7065 2F43 2D50 7065 5572 2F87 59A0 59A0 0925 C375 5E47 925C
9260 C375 A2AC 6F97 A2AC 6042 4B35 6042 4F70 DF6C 4F70 4EA7
3B92 4EA7 852C 8430 D07C 6152 1567 D07C 4A80 1A95 4927 4927
D255 8E40 D255 3622 1377 DD8C 4F90 12A5 3622 DF65 804C 8750
DF65 6F72 4EA0 DB9C 4EA0 3432 3432 4247 D075 8560 D075 1835
1A97 4257 4920 1835 4257 4257 826C 6F92 DD85 6F92 4B30 D62C
3752 1045 3752 8080 8F7C 8080 1527 6EA2 1527 3562 4240 1155
3562 4077 D9A5 8A90 D9A5 4250 1837 DE4C 8F9C 1265 3372 8F9C
8F9C D625 D625 6B32 1F75 D75C 4F60 8520 3082 4B97 D435 8520
6242 6242 1157 4070 4070 1A85 8830 49A7 892C DE45 DE45 6252
1267 D37C 4D80 1F95 32A2 8B3C 8B3C 8040 6F62 6F62 1F77 3522
4B90 824C 3522 4437 8150 8150 D565 1A87 1A87 DA9C 6D82 1925
LaboonCrypt hash: 3A8E
```

For `-ultraverbose`, see the file `sample_output_ultra_verbose.txt` in this subdirectory.

## Tips

1. I recommend getting LaboonHash to work correctly before working on LaboonCrypt.
2. Remember that hash functions are entirely deterministic, and so you should get the exact same result shown in the example output.

If you have any questions, please come to office hours or email me.