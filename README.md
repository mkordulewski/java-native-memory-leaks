# Java native memory leaks

Testing native heap memory leaks caused by:
* class ```java.util.zip.Inflater``` and its native method ```java.util.zip.Inflater.init(boolean)```,
* class ```java.util.zip.ZipInputStream``` using the first one.

**Remember**: proper way to avoid memory leaks is deallocating memory by calling methods ```Inflater#end()``` or ```ZipInputStream#close()```. But sometimes it's necessary to use a workaround for a legacy or thid-party code with bugs.

refs:
* http://bugs.java.com/view_bug.do?bug_id=4797189
* http://bugs.java.com/view_bug.do?bug_id=6751792


## Building and running
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
Next sections shows commands to run tests with 5 different garbage collectors and some results.


## Testing memory utilisation with ```Inflater``` class
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

Testing memory utilisation in native heap when instance of Inflater class is _not_ properly ended - **be careful, it really may cause memory leak**:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedRunner
```

Testing memory utilisation in native heap when GC is called periodically - it's usually safe, no memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithCollectingRunner
```

Testing memory utilisation in native heap when objects intentionally litter Java heap - it's usually safe, no memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterNotProperlyEndedWithLitteringRunner
```

Testing memory utilisation in native heap when instance of Inflater class is properly ended - it's safe, no memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterProperlyEndedRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterProperlyEndedRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterProperlyEndedRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterProperlyEndedRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.InflaterProperlyEndedRunner
```



## Testing memory utilisation with ```ZipInputStream``` class
| Garbage Collector              |  Java 6 @ Linux  | Java 7 @ Linux | Java 8 @ Linux | Java 6 @ Windows | Java 7 @ Windows | Java 8 @ Windows |
|:-------------------------------|:----------------:|:--------------:|:--------------:|:----------------:|:----------------:|:----------------:|
| Serial GC                      |      no leak     |    no leak     |    no leak     |      no leak     |      no leak     |      no leak     |
| Parallel GC                    |**leak and error**|    no leak     |    no leak     |**leak and error**|      no leak     |      no leak     |
| Old Parallel GC                |**leak and error**|    no leak     |    no leak     |**leak and error**|      no leak     |      no leak     |
| CMS (Concurrent Mark Sweep) GC |      no leak     |    no leak     |    no leak     |      no leak     |      no leak     |      no leak     |
| G1 (Garbage First) GC          |**leak and error**|    no leak     |    no leak     |**leak and error**|      no leak     |      no leak     |

Testing memory utilisation in native heap when instance of ZipInputStream class is _not_ properly ended - be carefull, it really may cause memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedRunner
```

Testing memory utilisation in native heap when GC is called periodically - it's usually safe, no memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithCollectingRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithCollectingRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithCollectingRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithCollectingRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithCollectingRunner
```

Testing memory utilisation in native heap when objects intentionally litter Java heap - it's usually safe, no memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithLitteringRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithLitteringRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithLitteringRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithLitteringRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamNotProperlyClosedWithLitteringRunner
```

Testing memory utilisation in native heap when instance of ZipInputStream class is properly ended - it's safe, no memory leak:
```
java -XX:+UseSerialGC        -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamProperlyClosedRunner
java -XX:+UseParallelGC      -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamProperlyClosedRunner
java -XX:+UseParallelOldGC   -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamProperlyClosedRunner
java -XX:+UseConcMarkSweepGC -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamProperlyClosedRunner
java -XX:+UseG1GC            -cp memory-leaks.jar pl.kordulewski.memory.leaks.runners.ZipInputStreamProperlyClosedRunner
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

G1 GC was called with flags:
* HotSpot 6: ```-XX:+UseG1GC -XX:+UnlockExperimentalVMOptions```
* all other cases: ```-XX:+UseG1GC```


## All java classes ready to run
Class names are self-describing:

| class                                               | safe?                                     | description                            |
|:----------------------------------------------------|:------------------------------------------|:---------------------------------------|
| InflaterProperlyEndedRunner                         | safe                                      | instance is properly ended             |
| InflaterNotProperlyEndedRunner                      | may cause leak                            | instance is _not_ properly ended       |
| InflaterNotProperlyEndedWithCollectingRunner        | tested as a workaround - potentially safe | GC is called periodically              |
| InflaterNotProperlyEndedWithLitteringRunner         | tested as a workaround - potentially safe | objects intentionally litter Java heap |
| ZipInputStreamProperlyClosedRunner                  | safe                                      | instance is properly closed            |
| ZipInputStreamNotProperlyClosedRunner               | may cause leak                            | instance is _not_ properly closed      |
| ZipInputStreamNotProperlyClosedWithCollectingRunner | tested as a workaround - potentially safe | GC is called periodically              |
| ZipInputStreamNotProperlyClosedWithLitteringRunner  | tested as a workaround - potentially safe | objects intentionally litter Java heap |