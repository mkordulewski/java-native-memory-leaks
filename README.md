# Java native memory leaks

Testing native heap memory leaks caused by:
* class ```java.util.zip.Inflater``` and its native method ```java.util.zip.Inflater.init(boolean)```,
* class ```java.util.zip.ZipInputStream``` using the first one.

refs:
* http://bugs.java.com/view_bug.do?bug_id=4797189
* http://bugs.java.com/view_bug.do?bug_id=6751792


## Building and running
```
mvn install
java -jar target/memory-leaks.jar
```


## Testing memory utilisation with Inflater class
| Garbage Collector              | HotSpot 6 @ Windows 7 x64 | HotSpot 8 @ Windows 7 x64 | HotSpot 6 @ OpenSuse 42.2 | HotSpot 8 @ Solaris 11 |
|:-------------------------------|:-------------------------:|:-------------------------:|:-------------------------:|:----------------------:|
| Serial GC                      |          leak (1)         |         leak (1)          |          leak (1)         |        leak (2)        |
| Parallel GC                    |          leak (1)         |         leak (1)          |          leak (1)         |        leak (2)        |
| Old Parallel GC                |          leak (1)         |         leak (1)          |          leak (1)         |        leak (2)        |
| CMS (Concurrent Mark Sweep) GC |          leak (1)         |         leak (1)          |          leak (1)         |        leak (2)        |
| G1 (Garbage First) GC          |          leak (1)         |       **no leak**         |          leak (1)         |        leak (2)        |

Table legend:
* leak (1) - used whole OS memory, OutOfMemoryError
* leak (2) - used part of OS memory, OutOfMemoryError or Exception

G1 GC was called with flags:
* HotSpot 8: ```-XX:+UseG1GC```
* HotSpot 6:   ```-XX:+UseG1GC -XX:+UnlockExperimentalVMOptions```


Run with different GCs - **Be careful, it really causes memory leak** (except G1 GC with HotSpot 8):
```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
```

Testing memory leak in native heap when GC is called periodically - it's safe, no memory leak:
```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
```

Testing memory leak in native heap when objects intentionally litter Java heap - it's safe, no memory leak:
```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
```


## Testing memory utilisation with ZipInputStream class
| Garbage Collector              | HotSpot 6 @ OpenSuse 42.2 | HotSpot 7 @ OpenSuse 42.2 | HotSpot 8 @ OpenSuse 42.2 |
|:-------------------------------|:-------------------------:|:-------------------------:|:-------------------------:|
| Serial GC                      |           no leak         |           no leak         |           no leak         |
| Parallel GC                    | **memory leak and error** |           no leak         |           no leak         |
| Old Parallel GC                | **memory leak and error** |           no leak         |           no leak         |
| CMS (Concurrent Mark Sweep) GC |           no leak         |           no leak         |           no leak         |
| G1 (Garbage First) GC          | **memory leak and error** |           no leak         |           no leak         |

Run with different GCs:
```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithZipInputStreamGeneratorRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithZipInputStreamGeneratorRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithZipInputStreamGeneratorRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithZipInputStreamGeneratorRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithZipInputStreamGeneratorRunner
```


### Alternative flags
```
java -XX:+AggressiveHeap     -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
```


## Error messages

Windows 7 x64, OpenSuse 42 (x64), Solaris 11 (x63):
```
java.lang.OutOfMemoryError
    at java.util.zip.Inflater.init(Native Method)
```

Solaris 11 (x64):
```
Exception in thread "main" #
# There is insufficient memory for the Java Runtime Environment to continue.
```


## Used JVMs

| description               | version of OS | version   |
|:--------------------------|:--------------|:----------|
| HotSpot 6 @ OpenSuse 42.2 |      x64      | 1.6.0_45  |
| HotSpot 7 @ OpenSuse 42.2 |      x64      | 1.7.0_79  |
| HotSpot 8 @ OpenSuse 42.2 |      x64      | 1.8.0_131 |


## Mapping different GCs to flags
| Garbage Collector              |flag                     |
|:-------------------------------|:------------------------|
| Serial GC                      | -XX:+UseSerialGC        |
| Parallel GC                    | -XX:+UseParallelGC      |
| Old Parallel GC                | -XX:+UseParallelOldGC   |
| CMS (Concurrent Mark Sweep) GC | -XX:+UseConcMarkSweepGC |
| G1 (Garbage First) GC          | -XX:+UseG1GC            |
