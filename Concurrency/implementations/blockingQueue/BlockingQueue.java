/* blocking queue
 * A blocking queue is a queue that blocks when you try to dequeue from it when the queue is empty.
 * or if you try to enqueue items to it and the queue is already full.
 *
 * a thread trying to dequeue from an empty queue is blocked until some other thread inserts an item into the queue.
 * a thread trying to enqueue an item into a full queue is blocked until some other threads makes space in the queue.
 * either by dequeueing one or more items or clearing the queue completely.
 *
 * the implementation of a blocking queue looks similar to a Bounded Semaphore, here is a simple implementation of a blocking queue.
 *
 * Notice how notifyAll() is only called from enqueue() and dequeue() if the queue size is equal to the size bounds(0 or limit).
 * if the queue size is not equal to either bound when enqueue() or dequeue() is called, there can be no threads waiting to either enequeue or dequeue items.
 *
 */

public class BlockingQueue {
    
    private int queue = new LinkedList();
    private int limit = 10;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(Object item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }//end enqueue

    public synchronized Object dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }
        return this.queue.remove(0);
    }//end dequeue.
    
}//end BlockingQueue
