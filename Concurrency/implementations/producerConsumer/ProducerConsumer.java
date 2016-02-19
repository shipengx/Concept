/* producer and consumer problem:
 * as a consumer, the logic is:
 *      if the queue is empty, wait for it to be not empty, then poll one element from it.
 * as a producer, the logic is:
 *      if the queue is full, wait for it to be not full, then offer one element to it.
 *
 * Building a blocking queue, such that
 *      when the queue is Empty, consumer() will wait until produce() provide one element.
 *      when the queue is full, produce() will wait until consume() relase one element.
 *
 *     both operations happens in a mutual exclusive fashion so that all operations are ordered.
 *
 *    Mutual exclusive == >> use locks.
 *    wait --> condition synchronization.
 *
 *
 *    Each java object is a Monitor.
 *    Each java object provides two concurrency premises:
 *
 *      1, one lock ===>> synchronized.
 *      2, one condition =>> wait().
 *
 *    what really is "Monitor"?
 *    in short, there are two queues controlling the access to enter the room:
 *          1, lock queue, there is only one thread at a time from the queue can enter.
 *          2, wait queue, there is a queue for the condition provided, all the thread wait() ,is in the queue, and wait for the notify() call to remove from the condition queue.
 *
 * Map the problem back, we can do something like this:
 */


public class ProducerConsumer {

    public static void main(String[] args) {
        
        Q q = new Q();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new Producer(q)));
        }
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new Consumer(q)));
        }
        for (Thread t: threads) {
            thread.start();
        }
    }

}//end class ProducerConsumer


class Producer implements Runnable {

    Q q;
    public Produce(Q q) {
        super();
        this.q = q;
    }
    @Override
    public void run() {
        q.put(0);
    }
}


class Consumer implements Runnable {
    Q q;
    public Consumer(Q q) {
        super();
        this.q = q;
    }
    @Override
    public void run() {
        System.out.println(q.take());
    }
}

class Q {
    private Queue<Integer> q;
    private final int limit;
    public Q (int limit) {
        this.q - new LinkedList<>();
        this.limit = limit;
    }

    public synchronized void put(Integer ele) {
        //synchronized(this)
        while (q.size() == limit) {
            wait();   //this.wait();
        }
        if (q.size() == 0) {
            notifyAll();
        }
        q.offer(ele);
    }

    public synchronized Integer take() {
        while (q.size() == 0) {
            wait();   
        }
        if (q.size() == limit) {
            notifyAll();
        }
        return q.poll();
    }

}//end class Q



/* pretty good, right? 
 * However, why the producer and the consumer wait on the same condition??
 * this is hard to understand.
 *
 * Because each object is only associated with one condition.
 * when q.size() == 0, the condition is "queue empty". 
 * When q.size() == limit, the condition is "queue full".
 * we can decouple these two conditions by the condition class associated with a lock.
 */





