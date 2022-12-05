package com.mcpi;

import java.rmi.*;

/** Interface for remote object to return a random number between 0 and 1.
 *
 * An RMI interface extends from java.rmi.Remote.*/
public interface Random extends Remote {
    /** Returns the value of Pi calculated using the MonteCarlo method */
    double uniform() throws RemoteException;
}
