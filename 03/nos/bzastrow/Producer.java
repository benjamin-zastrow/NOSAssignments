package nos.bzastrow;

/**
 * This class resembles a producer entity that produces one unit of produce and adds it to the managed array every 500ms
 * After this a log-message is emitted on stdout.
 * The thread will terminate upon interruption.
 */

public class Producer implements Runnable {

    ConcurrentFifoQueue rb;
    int nextInt = 0;

    public Producer(ConcurrentFifoQueue rb) {
        this.rb = rb;
    }

    /**
     * Method that is called by the thread upon initiation
     * Waits 500ms, adds an element (sample data: increasing integer) from the queue and prints a status message.
     * Terminates the loop upon interrupt.
     */
    @Override
    public void run() {
        // Only run the action if the current thread is not interrupted.
        while(!Thread.interrupted()) {
            try {
                // Sleep 500ms and push the next value
                Thread.sleep(500);
                rb.push(++nextInt);
                // Status message
                System.out.println("[P-" + Thread.currentThread().getId() + "] produced " + nextInt);

            } catch (InterruptedException e) {
                // Status message and terminate the thread
                System.out.println("Thread P-" + Thread.currentThread().getId() + " has received interrupt signal and will shutdown now!");
                break;
            } 
        }
    }
}
