# spark-daemon-thread-debug
When use create non-daemon thread in spark, then the driver process won't exit when application killed by yarn.


当用户手动创建了非daemon线程在Spark的Driver程序时，如果改程序被Yarn结束或者Driver的业务逻辑已经执行完毕，则Driver依然不会退出，会占用Linux的内存。
