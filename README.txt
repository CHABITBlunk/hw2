# README

Author: HABIT Blunk
Date: 2023-10-09
Contents: Memory.java (the main file with all methods including a main method)

## Task 1: Volatile vs Cached
| Experiment | Volatile  | Cached    |
| ---------- | --------- | --------- |
| 1          | 0.03471 s | 0.00061 s |
| 2          | 0.04050 s | 0.00281 s |
| 3          | 0.04356 s | 0.00303 s |
On my machine, I noticed extremely fast cache speeds in the thousandths to the ten-thousandths of a 
second in some instances, in contrast to the hundredths of seconds spent going from CPU to ram in 
the volatile examples. The advantages of caching are immediately apparent.

## Task 3: TreeSet vs LinkedList
|  Experiment  | TreeSet       | LinkedList     |
| ------------ | ------------- | -------------- |
| 1            | 9877.50 ns    | 30844343.55 ns |
| 2            | 141021.00 ns  | 44679557.00 ns |
| 3            | 129550.00 ns  | 59848859.00 ns |
Interestingly enough, TreeSets are much better data structures to check for values in than LinkedLists.
The difference in average times to find an element between the two are very large, so much so that
TreeSets take a fraction of the time of a LinkedList to find an element.

