# Cache behavior with Uniprocessor in Computer Architecture 
The implementation of my project "3-level memory hierarchy" comprised of interacting L1 and L2 cache controllers and a memory.
Included components are CPU Controler, L1Cache Controler,L2Cache Controler,L1Data, L2Data, Memory, Victim cache, L1 Write buffer and L2Write Buffer.

we have classes for each of the componenent and also separate class for scheduling.

Classes:
1)Caches.java
2)CacheControllers.java(Contains Main method)
3)CPUController.java
4)L1Controler.java
5)L1DataController.java
6)L1Victim.java
7)L2Buffer.java
8)L2Controler.java
9)L2DataController.java
10)Memory.java
11)Queues.java
12)Scheduling.java
13)WriteBuff.java


Below are The Test cases of my project and corresponding output trace:

a)	Single read:SingleRead
b)	Single write followed by a read to the same address: Single Stored followed Load
c)	Back-back read/write to the same line: MultipleLoadStores2sameAdrs
d)	Back-back read /write to different lines: MultipleStoresLoadsdifferentAddress
e)	Read from L1 victim cache: LOAD_L1VICMCACHE
f)	Read from L1 WB/L2 WB cache: Load from L1WriteBuffer/Load_L2WriteBuffer
g)	Mutual inclusion violation and fix.
	—Mutual inclusion is voilated when data from L2buffer is written back to Memory.
	-We have made fix,by removing data from L1 cache also while writing back to Memory
