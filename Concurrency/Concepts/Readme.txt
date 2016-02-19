Java Synchronized blocks
A java synchronized block marks a method or a block of code as synchronized, java synchronized blocks can be used to avoid race conditions.

The java synchronized keyword.
synchronized blocks in java are marked with the synchronized keyword.
A synchronized block in java is synchronized on some object.
All synchronized blocks synchronized on the same object can only one thread executing inside them at the same time.
All other threads attempting to enter the synchronized block are blocked until the thread inside the synchronized block exits the block.

The synchronized keyword can be used to mark four different types of blocks:

1. instance methods.
2. static methods.
3. Code blocks inside instance methods.
4. Code blocks inside static methods.


these blocks are synchronized on different objects.
Which type of synchronized block you need depends on the concrete situation.



Thread Pools
Thread pools are useful when you need to limit the number of threads running in your application at the same time.
there is a performance overhead associated with starting a new thread, and each thread is also allocated some memory for its stack etc.

instead of starting a new thread for every task to execute concurrently, the task can be passed to a thread pool.
As soon as the pool has any idle threads the task is assigned to one of them and executed.
internally the tasks are inserted into a blocking queue which the threads in the pool are dequeueing from.
when a new task is inserted into the queue one of the idle threads will dequeue it successfully and execute it.
the rest of the idle threads in the pool will be blocked waiting to dequeue the tasks.

Thread deadlock
A deadlock is when two or more threads are blocked waiting to obtain locks that some of the other threads in the deadlock are holding.
Deadlock can occur when multiple threads need the same locks, at the same time, but obtain them in differen order.

for instance, if thread 1 locks A, and tries to lock B, and thread 2 has already locked B, and tries to lock A, a deadlock arises.
Thread 1 can never get B, and thread 2 can never get A. 
In addition, neither of them will ever know.
THey will remain blocked on each their object, A and B, forever.
This situation is a deadlock.


Deadlock prevention
lock ordering
lock timeout
Deadlock detection.


Starvation and fairness
if a thread is not granted CPU time because other threads grab it all,
it is called "starvation", the thread is "starved to death" because other threads are allowed the CPU time instead of it.
the solution to starvation is called "fairness" -- that all threads are fairly granted a chance to execute.










