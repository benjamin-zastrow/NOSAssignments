package nos.bzastrow;

import java.util.ArrayList;


/**
* Class that describes a car dealership, which is semi-deeply cloneable.
*/
public class CarDealership implements Cloneable {
    private ArrayList<Vehicle> storage = new ArrayList<Vehicle>();

    /**
     * Method for removing a vehicle from the car dealership storage and assigning it to a new VehicleOwner
     * @param id  Internal ID of the car to be sold
     * @param newOwner  Owner who will be attributed to the car
       // SH: You have two ways to handle "errors": In one case you return
       // null, in the other you throw an exception. Do not mix modalities.
     * @return The vehicle with the set ownership, unless an error occurs.
     */
    public Vehicle sellVehicle(int id, VehicleOwner newOwner) {
        if(storage.get(id) == null) {
            // SH: This is a misuse of NullPointerException
            throw new NullPointerException("ID cannot be found in CarDealership storage!");
        }
        if(!storage.get(id).accessOwner().isOwnershipSet()) {
            Vehicle tmp = storage.get(id);
            storage.remove(id);
            tmp.accessOwner().setOwnership(newOwner.getName(), newOwner.getBirthDate(), newOwner.getAddress());
            return tmp;
        } else {
            return null;
        }
        
    }
    /**
     * Method for removing the ownership of a vehicle and adding it to the storage if it is not already in store and has an owner
     * @param v  The vehicle to be "bought back"
     * @return  'true' if all conditions for buying the vehicle back are met, 'false' if either the ownership is not set or the car already exists in the dealership
     */
    public boolean buyBackVehicle(Vehicle v) {
        if(v.accessOwner().isOwnershipSet() && !storage.contains(v)) {
            v.accessOwner().clearOwnership();
            storage.add(v);
            return true;
        }
        return false;
    }
    /**
     * Semi-deep-clones the Dealership, which allows for the car storage to have multiple interfaces without being copied
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        CarDealership tmp = new CarDealership();
        tmp.storage = this.storage;
        return tmp;
    }

}
