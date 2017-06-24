# Java native memory leaks

Testing native heap memory leaks caused by:
* class ```java.util.zip.Inflater``` and its native method ```java.util.zip.Inflater.init(boolean)```,
* class ```java.util.zip.ZipInputStream``` using the first one.

refs:
* http://bugs.java.com/view_bug.do?bug_id=4797189
* http://bugs.java.com/view_bug.do?bug_id=6751792


## Building and running
Build:
```
mvn install
```

Go to _target_ directory to run jar file:
```
cd target
```

Show commands:
```
java -jar memory-leaks.jar
```


## Testing memory utilisation with Inflater class
| Garbage Collector              | Java 6 @ Windows | Java 7 @ Windows | Java 8 @ Windows |  Java 6 @ Linux  |  Java 7 @ Linux  |  Java 8 @ Linux  | Java 8 @ Solaris |
|:-------------------------------|:----------------:|:----------------:|:----------------:|:----------------:|:----------------:|:----------------:|:----------------:|
| Serial GC                      |leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (2)|
| Parallel GC                    |leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (2)|
| Old Parallel GC                |leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (2)|
| CMS (Concurrent Mark Sweep) GC |leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (1)|leak and error (2)|
| G1 (Garbage First) GC          |leak and error (1)|leak and error (1)|   **no leak**    |leak and error (1)|leak and error (1)|leak and error (1)|leak and error (2)|

Table legend:
* leak and error (1) - whole OS memory allocated, OutOfMemoryError
* leak and error (2) - part of OS memory allocated, OutOfMemoryError or Exception

See 'Error messages' section.

G1 GC was called with flags:
* HotSpot 8: ```-XX:+UseG1GC```
* HotSpot 6:   ```-XX:+UseG1GC -XX:+UnlockExperimentalVMOptions```


Run with different GCs - **Be careful, it really causes memory leak** (except G1 GC with HotSpot 8):
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
```

Testing memory leak in native heap when GC is called periodically - it's safe, no memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
```

Testing memory leak in native heap when objects intentionally litter Java heap - it's safe, no memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
```


## Testing memory utilisation with ZipInputStream class
| Garbage Collector              |  Java 6 @ Linux  | Java 7 @ Linux | Java 8 @ Linux | Java 6 @ Windows | Java 7 @ Windows | Java 8 @ Windows |
|:-------------------------------|:----------------:|:--------------:|:--------------:|:----------------:|:----------------:|:----------------:|
| Serial GC                      |      no leak     |    no leak     |    no leak     |      no leak     |      no leak     |      no leak     |
| Parallel GC                    |**leak and error**|    no leak     |    no leak     |**leak and error**|      no leak     |      no leak     |
| Old Parallel GC                |**leak and error**|    no leak     |    no leak     |**leak and error**|      no leak     |      no leak     |
| CMS (Concurrent Mark Sweep) GC |      no leak     |    no leak     |    no leak     |      no leak     |      no leak     |      no leak     |
| G1 (Garbage First) GC          |**leak and error**|    no leak     |    no leak     |**leak and error**|      no leak     |      no leak     |

Run with different GCs:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
```


### Alternative flags
```
java -XX:+AggressiveHeap     -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
```


## Error messages
```
java.lang.OutOfMemoryError
    at java.util.zip.Inflater.init(Native Method)
```

```
Exception in thread "main" #
# There is insufficient memory for the Java Runtime Environment to continue.
```

```
#
# There is insufficient memory for the Java Runtime Environment to continue.
```


## Used JVMs

| description      |   JVM   | version of JVM  |       OS      | version of OS |
|:-----------------|:-------:|:----------------|:-------------:|:-------------:|
| Java 6 @ Linux   | HotSpot | 1.6.0_45        | OpenSuse 42.2 |      x64      |
| Java 7 @ Linux   | HotSpot | 1.7.0_79        | OpenSuse 42.2 |      x64      |
| Java 8 @ Linux   | HotSpot | 1.8.0_131       | OpenSuse 42.2 |      x64      |
| Java 6 @ Windows | HotSpot | 1.6.0_45        | Windows 7     |      x64      |
| Java 7 @ Windows | HotSpot | 1.7.0_79        | Windows 7     |      x64      |
| Java 8 @ Windows | HotSpot | 1.8.0_45        | Windows 7     |      x64      |
| Java 8 @ Solaris | HotSpot | 1.8.0_60        | Solaris 11.3  |      x86      |


## Mapping different GCs to flags
| Garbage Collector              |flag                     |
|:-------------------------------|:------------------------|
| Serial GC                      | -XX:+UseSerialGC        |
| Parallel GC                    | -XX:+UseParallelGC      |
| Old Parallel GC                | -XX:+UseParallelOldGC   |
| CMS (Concurrent Mark Sweep) GC | -XX:+UseConcMarkSweepGC |
| G1 (Garbage First) GC          | -XX:+UseG1GC            |
