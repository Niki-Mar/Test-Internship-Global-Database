# DESIGN — Anagram Finder

## Purpose
To group words that are anagrams of each other from a text file input.

## Design Approach
I use a "HashMap<String, List<String>>", where:
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
- `Files.lines()` streams input line by line  
- Only one pass over the data  
- Memory usage scales with the number of distinct keys  

### Scaling to 100 billion words

This size exceeds available RAM and requires significant architectural changes. To scale the solution for such large datasets, the following adaptations would be necessary:

- **Chunking input data:** Split the input file into smaller manageable chunks and process each chunk separately, grouping anagrams within each.  
- **Intermediate storage:** Store partial grouping results in temporary files on disk instead of keeping everything in memory.  
- **External merging:** Perform multi-way merge of temporary files to combine groups with identical sorted keys into final output groups.  
- **Distributed processing:** Utilize distributed computing frameworks like Hadoop MapReduce or Apache Spark to parallelize processing across multiple machines.  
- **Database or key-value store:** Alternatively, use persistent storage systems that support efficient grouping and querying without requiring all data in memory at once.  

These steps enable processing datasets far beyond the capacity of a single machine’s RAM, while preserving correctness and performance.

## Dependencies
Only Java Standard Library is used:  
- `java.io` for file input/output  
- `java.nio.file` for file reading using `Files.lines()`  
- `java.util` for data structures: `HashMap`, `ArrayList`, `Collections`

These libraries are chosen because:  
- They are efficient and reliable  
- They are readily available in any Java environment  
- They provide the needed performance for processing large datasets  
- No third-party dependencies keep the code portable and easy to run anywhere
