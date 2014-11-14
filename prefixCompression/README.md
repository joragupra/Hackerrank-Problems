Prefix Compression
==================

You are incharge of data transfer between two data-centres. Each set of data is represented by a pair of strings. Over a period of time you have observed a trend: most of the times both strings share some prefix. You want to utilize this observation to design a data compression algorithm which will be used to reduce amount of data to be transferred. 

You are given two strings, x and y, representing the data, you need to find the longest common prefix (p) of the two strings. Then you will send substring p, x′ and y′, where x′ and y′ are the substring left after stripping p from them. 

For example, if x=‘‘abcdefpr" and y=‘‘abcpqr", then p=‘‘abc",x′=‘‘defpr",y′=‘‘pqr".

Input Format
------------
Input will contain two lines. First line will contain string x and second line string y.

Output Format
-------------
In first line, print the length of substring p, followed by prefix p. In second line, print the length of substring x′, followed by substring x′. Similary in third line, print the length of substring y′, followed by substring y′.

Constraints
-----------
x and y will contain only lowercase Latin characters ('a'-'z').
1≤length(x),length(y)≤105

Sample Input #00

abcdefpr
abcpqr

Sample Output #00

3 abc
5 defpr
3 pqr

Explanation #00

Already explained above in the problem statement.

Sample Input #01

kitkat
kit

Sample Output #01

3 kit
3 kat
0

Explanation

Here p is ‘‘kit" which is also y. So x′ will be ‘‘kat", while y′ will be an empty string.

Sample Input #02

puppy
puppy

Sample Output #02

5 puppy
0
0

Explanation

Since both strings are same, prefix will cover both the strings, and hence x′ and y′ will be empty strings.
