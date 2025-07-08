# DESIGN — Anagram Finder

## Purpose
To group words that are anagrams of each other from a text file input.

## Design Approach
We use a "HashMap<String, List<String>>", where:
  The key is a **sorted version of the word** (e.g., "race" -> "acer")
  The value is a **list of original words** that match the key

For consistency and output readability:
  Groups are printed in **one line** each
  All groups are **alphabetically sorted internally**
  Even single-word groups are printed

## Data Structures
  "LinkedHashMap" preserves insertion order
  "ArrayList" stores each group of anagrams

## Scalability Considerations
### Up to 10 million words

The current approach works efficiently:
"Files.lines()" streams input line by line
Only one pass over the data
Memory usage scales with the number of distinct keys

### Scaling to 100 billion words

This size exceeds RAM. We would need:

External sorting (disk-based processing)
Chunk-based grouping
Distributed systems (e.g., Hadoop, Apache Spark)
Possibly: hash partitioning + intermediate merging

## Dependencies
Only Java Standard Library is used:
java.io for file input/output
java.nio.file for file reading using Files.lines()
java.util for data structures: HashMap, ArrayList, Collections

These libraries are chosen because:
They are efficient and reliable
They are readily available in any Java environment
They provide the needed performance for processing large datasets
No third-party dependencies keep the code portable and easy to run anywhere

