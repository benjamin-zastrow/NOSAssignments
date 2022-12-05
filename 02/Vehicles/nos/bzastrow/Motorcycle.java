package nos.bzastrow;

/**
* Class that describes a car, which inherits from the abstract class MotorizedVehicle
*/
public class Motorcycle extends MotorizedVehicle {
    private int countStroke = 4;


    /**
    * Argument-constructor for the motorcycle class
    *
    * @param color  the color of the bicycle
    * @param maxSpeed  the maximum speed in km/h
    * @param weight the vehicle's weight in kg
    * @param enginePower  the available power of the engine in hp
    * @param fuelType  the type of fuel the engine needs to operate, e.g. "Diesel"
    * @param coxPerKM  the amount of carbon-mono-oxide and carbon-dioxide emitted (in g/km)
    * @param countStroke  the type of the engine (either 2 for 2-stroke or 4 for 4-stroke)
    */
    // SH: Too long lines! People have troubles grasping so long lines
    public Motorcycle(String color, int maxSpeed, int weight, int enginePower, String fuelType, short fuelLevel, float coxPerKM, int countStroke, String ownerName, String ownerBirthDate, String ownerAddress) {
        // SH: Delegate to super ctor
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
        if(countStroke == 2 || countStroke == 4) {
            this.countStroke = countStroke;
        }
        owner = new VehicleOwner(ownerName, ownerBirthDate, ownerAddress);
    }
    public int getStrokeCount() {
        return countStroke;
    }

    /**
    * Rides the motorcycle for a given amount of kilometers! This lowers the fuel level depending on the stroke count.
    *
    * @param kilometers  The distance in km that the driver wants to drive
    * @return 'true' if the driver can go the given distance; 'false' if either the car engine is not running or the fuel level is not sufficient
    */
    @Override
    public boolean drive(int kilometers) {
        if(!engineRunning) {
            System.out.println("In order to drive, the engine must be running!");
            return false;
        } else {
            double fuelLevelBefore = fuelLevel;
            if(countStroke == 4) {
                fuelLevel -= kilometers * 0.1;
            } else if(countStroke == 2) {
                fuelLevel -= kilometers * 0.2;
            }
            System.out.printf("You drove %dkm. This reduced the fuel level by %.2f %. Fuel level is now at %.2f %\n", kilometers, fuelLevelBefore-fuelLevel, fuelLevel);
            return true;
        }
    }

    /**
     * @return  a string containing all the fields of the class, as well as their current values.
     */
    @Override
    public String toString() {
        return super.toString() + "\nMotorcycle [countStroke=" + countStroke + "]";
    }
    
    
}
