Word Transformation
===================

_____________________________________________________________________________________
Programming Challenge: Word Transformation 

A word puzzle found in many newspapers and magazines is the word transformation. By taking a starting word and successively substituting a single letter to make a new word, one can build a sequence of words which changes the original word to a given end word. For instance, the word "spice" can be transformed in four steps to the word "stock" according to the following sequence: spice, slice, slick, stick, stock. Each successive word differs from the previous word in only a single character position while the word length remains the same.

Given a dictionary of words from which to make transformations, plus a starting and ending word, your goal is to write a program to determine the sequence of words in the shortest possible transformation.

_____________________________________________________________________________________
Input and Output

The program should be written in java, and be invoked with a command line of the following form:

java WordTransformer DICTIONARYFILE STARTWORD ENDWORD

and it should produce as output only the series of words in the transformation, one per line, written to standard out. The program should then exit.

We have supplied two sample dictionary files, 850words.txt and 17words.txt. 

Sample Input/Output

> java WordTransformer 17words.txt spice stock
spice
slice
slick
stick
stock

> java WordTransformer 17words.txt maple may
No transformation found.

_____________________________________________________________________________________
Evaluation

We will be looking not only for correctness but also for design and efficiency. Please write code which employs nice modular decomposition and is easily maintainable. Your application should be written in Java 1.6 and should not use any libraries other than the core library classes.

_____________________________________________________________________________________
Optional Challenges

These are optional parts of the challenge, if you have enough time.

1. Suppose that we allow other operators other than substitution, such as insertion and deletion of characters. How do you change your code? Build a command-line switch to turn this feature on.
2. Suppose the different operators have different weights? Suppose substitution is worth 2, but insertion/deletion is worth 1. Can you find the shortest path?
