package nos.bzastrow;

/**
 * This class resembles a consumer entity that consumes one unit of produce from the managed array every 500ms
 * After this a log-message is emitted on stdout.
 * The thread will terminate upon interruption.
 */

public class Consumer implements Runnable {
    
    ConcurrentFifoQueue rb;

    public Consumer(ConcurrentFifoQueue rb) {
        this.rb = rb;
    }

    /**
     * Method that is called by the thread upon initiation
     * Waits 500ms, removes the next element from the queue and prints a status message.
     * Terminates the loop upon interrupt.
     */
    @Override
    public void run() {
        // Only run the action if the current thread is not interrupted.
        while(!Thread.interrupted()) {
            try {
                // Sleep 500ms and status message.
                Thread.sleep(500);
                System.out.println("[C-" + Thread.currentThread().getId() + "] consumed " + rb.pop());
            } catch (InterruptedException e) {
                // Status message and terminate the thread
                System.out.println("Thread C-" + Thread.currentThread().getId() + " has received interrupt signal and will shutdown now!");
                break;
            } 
        }
    }
}
