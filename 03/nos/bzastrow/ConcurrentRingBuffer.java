package nos.bzastrow;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** 
 *  This class implements a thread-safe (synchronised) ConcurrentFifoQueue
 *  that stores and manages a fixed-size array of Object using ReentrantLock and Conditions. 
 */ 

public class ConcurrentRingBuffer implements ConcurrentFifoQueue {
    // SH: Missing javadoc
    // SH: Missing private
    Object[] array;
    int readPtr = 0;
    int writePtr = 0;
    int elements = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition emptyCond = lock.newCondition();
    Condition fullCond = lock.newCondition();

    public ConcurrentRingBuffer(int size) {
        array = new Object[size];
    }

    /** 
     * @return true if the managed array is currently empty.
     */
    public boolean empty() {
        return (elements == 0);
    }

    /** 
     * @return true if the managed array is currently full.
     */
    public boolean full() {
        return (elements == array.length);
    }

    /** 
     * Thread-safe blocking (not-async) method to insert an object into the managed array.
     * The calling thread will be blocked until space for the new Object is available, if the managed array happens to be full.
     * 
     * @param o The object to be pushed into the array 
     * @throws InterruptedException in case the thread gets interrupted during the method's execution.
     */
    public void push(Object o) throws InterruptedException {
        // Locks the shared resource (queue) 
        lock.lock();
        try {
            while (full()) {
                // Halts and blocks the thread until the condition (full()) is not met anymore.
                fullCond.await();
            }
            // Write the object to the designated spot and increase the writePtr (circular), as well as the element-count.
            array[writePtr++] = o;
            if(writePtr == array.length) {
                writePtr = 0;
            }
            elements++;
            //Release all threads that are blocked due to the queue being empty (not the case anymore).
            emptyCond.signalAll();
        
        // SH: Kill this catch block!
        } catch(InterruptedException e) {
            throw e;
        } finally {
            // Release the lock (end of synchronisation)
            lock.unlock();
        }
    }

    
    /** 
     * Thread-safe blocking (not-async) method to remove and return an object from the managed array.
     * The calling thread will be blocked until an Object to be returned is available, if the managed array happens to be empty.
     * 
     * @return Object The object next in line to be read by the readPointer.
     * @throws InterruptedException in case the thread gets interrupted during the method's execution.
     */
    public Object pop() throws InterruptedException {
        // Locks the shared resource (queue)
        lock.lock();
        Object tmp = null;
        try {
            while (empty()) {
                // Halts and blocks the thread until the condition (empty()) is not met anymore.
                emptyCond.await();
            }
            // Read from designated spot, increase readPtr (circular), decrease the element-count
            tmp = array[readPtr];
            array[readPtr++] = null;
            if(readPtr == array.length) {
                readPtr = 0;
            }
            elements--;
            // Release all threads that are blocked due to the queue being full (not the case anymore).
            fullCond.signalAll();
        } catch (InterruptedException e) {
            throw e;
        } finally {
            // Release the lock (end of synchronisation)
            lock.unlock();
        }
        return tmp;
    }

    
    /** 
     * Thread-safe blocking (not-async) method to remove all objects from the managed array.
     * 
     * @throws InterruptedException in case the thread gets interrupted during the method's execution. 
     */
    public void clear() throws InterruptedException {
        // Locks the shared resource (queue)
        lock.lock();
        try {
            // Clear the array (optional, but hardcore linux penetration-testers might find a way around this)
            for(int i = 0; i < array.length; ++i) {
                array[i] = null;
            }
            // Reset the pointers and element count.
            elements = 0;
            readPtr = 0;
            writePtr = 0; 
            // SH: Missing emptyCond.signalAll()
        } finally {
            // Release the lock (end of synchronisation)
            lock.unlock();
        }
    }
}
