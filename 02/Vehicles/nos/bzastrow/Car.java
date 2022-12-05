package nos.bzastrow;

/**
* Class that describes a car, which inherits from the abstract class MotorizedVehicle
*/
public class Car extends MotorizedVehicle {
    private int countDoors = 4;

    /**
    * Argument-constructor for the Car class
    *
    * @param color  the color of the bicycle
    * @param maxSpeed  the maximum speed in km/h
    * @param weight the vehicle's weight in kg
    * @param enginePower  the available power of the engine in hp
    * @param fuelType  the type of fuel the engine needs to operate, e.g. "Diesel"
    * @param coxPerKM  the amount of carbon-mono-oxide and carbon-dioxide emitted (in g/km)
    * @param countDoors  the number of doors the car has
    */
    public Car(String color, int maxSpeed, int weight, int enginePower, String fuelType, double fuelLevel, float coxPerKM, int countDoors, String ownerName, String ownerBirthDate, String ownerAddress) {
        if(!color.isBlank() && !color.isEmpty()) {
            this.color = color;
        }
        if(maxSpeed > 0) {
            this.maxSpeed = maxSpeed;
        }
        if(weight > 0) {
            this.weight = weight;
        }
        if(enginePower > 0) {
            this.enginePower = enginePower;
        }
        if(fuelType.equalsIgnoreCase("benzin") || fuelType.equalsIgnoreCase("diesel") || fuelType.equalsIgnoreCase("electric") || fuelType.equalsIgnoreCase("gas")) {
            this.fuelType = fuelType;
        }
        if(fuelLevel >= 0 && fuelLevel <= 100) {
            this.fuelLevel = fuelLevel;
        }
        if(coxPerKM >= 0) {
            this.coxPerKM = coxPerKM;
        }
        if(countDoors >= 2) {
            this.countDoors = countDoors;
        }
        owner = new VehicleOwner(ownerName, ownerBirthDate, ownerAddress);
    }

    public int getDoorCount() {
        return countDoors;
    }

    /**
    * Drives the car for a given amount of kilometers! This lowers the fuel level.
    *
    * @param kilometers  The distance in km that the driver wants to drive
    * @return 'true' if the driver can go the given distance; 'false' if either the car engine is not running or the fuel level is not sufficient
    */
    @Override
    public boolean drive(int kilometers) {
        if(!engineRunning) {
            System.out.println("In order to drive, the engine must be running!");
            return false;
        } else if(fuelLevel < kilometers*0.25) {
            System.out.println("In order to drive, the fuel level must be sufficient!");
            return false;
        } else {
            fuelLevel -= kilometers * 0.25;
            System.out.printf("You drove %d km. This reduced the fuel level by %.2f percent. Fuel level is now at %.2f percent \n", kilometers, kilometers*0.25, fuelLevel);
            return true;
        }
    }

    /**
     * @return  a string containing all the fields of the class, as well as their current values.
     */
    @Override
    public String toString() {
        return super.toString() + "\nCar [countDoors=" + countDoors + "]";
    }

    
}
