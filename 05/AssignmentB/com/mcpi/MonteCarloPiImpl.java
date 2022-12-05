package com.mcpi;

import java.rmi.RemoteException;

/** Separate implementation interface for remote object MonteCarloPi to calculate Pi using the MonteCarlo method.
 *
 * An RMI interface extends from java.rmi.Remote.*/
public class MonteCarloPiImpl implements MonteCarloPi {
    /** The random object containing the necessary method to calculate a random number between 0 and 1.
     *  Using RMI internally as well would have been possible, but likely highly inefficient. 
     *  I have therefore opened the Random-RMI interface to the network as well to show it is working.
     */
    Random r = new RandomImpl();

    /**The method using the MonteCarlo algorithm to calculate pi at the "precision" of n
     * @param n The number of algorithmic cycles to be done in order to approximate pi
     */
    @Override
    public double compute(int n) throws RemoteException {
        double x, y;
        int k = 0;
        for(int i = 0; i < n; ++i) {
            // SH: Not a remote call to the distributed object for random service
            x = r.uniform();
            y = r.uniform();
            // SH: Use double literals for 1.0
            if((x*x + y*y) <= 1) {
                ++k;
            }
        }
        // SH: Error-prone way of casting. Use 4.0 for double literals to
        // indicate double multiplication instead.
        return (double)(4*k)/n;
    }
}
