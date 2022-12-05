package com.mcpi;

import java.rmi.RemoteException;

/** Separate implementation interface for remote object Random to calculate a random number between 0 and 1 method.
 *
 * An RMI interface extends from java.rmi.Remote.*/
public class RandomImpl implements Random {
    /** A simple wrapper for Java's own random library generating a number between 0 and 1 */
    @Override
    public double uniform() throws RemoteException {
        java.util.Random r = new java.util.Random();
        // SH: (Correction, compiles with Java API 18)
        return r.nextDouble(1);
    }
}
