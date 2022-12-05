package com.mcpi;

import java.rmi.*;

/** Interface for remote object to calculate Pi using the MonteCarlo method.
 *
 * An RMI interface extends from java.rmi.Remote.*/
public interface MonteCarloPi extends Remote {
    /** Returns the value of Pi calculated using the MonteCarlo method */
    double compute(int n) throws RemoteException;
}
