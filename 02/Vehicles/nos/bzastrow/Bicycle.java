package nos.bzastrow;

/**
* Class that describes a bicycle, which inherits from the abstract class Vehicle
*/
public class Bicycle extends Vehicle {
    // SH: missing javadocs.
    // SH: Do not use "fits often" values for initialization. If there is no
    // reasonable default value for countGears then do not initialize it by
    // "some arbitrary" value, like 12. Do not try to be smart or polite, but
    // force the caller of your class to make up his mind instead.
    private int countGears = 12;
    private float range = 20.0f;

    /**
    * Argument-constructor for the Bicycle class
    *
    * @param color  the color of the bicycle
    * @param maxSpeed  the maximum speed in km/h
    * @param countGears the amount of available gears
    * @param bikerRange  the range that the bicicle's driver can go in km
    */
    // SH: Too long line
    public Bicycle(String color, int maxSpeed, int weight, int countGears, float bikerRange, String ownerName, String ownerBirthDate, String ownerAddress) {
        // SH: Do not initialize the super classes' fields, but call super
        // class ctor: It is not the concern of Bicycle, avoid code duplication
        // and avoid scattered code of initialization. You essentially violate
        // the open-close principle of OOP.
        if(!color.isBlank() && !color.isEmpty()) {
            this.color = color;
        }
        if(maxSpeed > 0) {
            this.maxSpeed = maxSpeed;
        }
        if(weight > 0) {
            this.weight = weight;
        }
        if(countGears > 0) {
            this.countGears = countGears;
        }
        if(bikerRange >= 0) {
            this.range = bikerRange;
        }
        owner = new VehicleOwner(ownerName, ownerBirthDate, ownerAddress);
    }
    public int getGearCount() {
        return countGears;
    }

    /**
    * Rides the bicycle for a given amount of kilometers!
    *
    * @param kilometers  The distance in km that the driver wants to ride the bike
    * @return 'true' if the biker can ride the distance; 'false' if the biker needs more training to ride the given distance
    */
    @Override
    public boolean drive(int kilometers) {
        if(kilometers > range) {
            System.out.println("You lack the necessary strength to bike that far. Try a lower distance and watch your muscles grow.");
            return false;
        } else {
            float rangeBefore = range;
            range += kilometers * 0.05;
            System.out.printf("You drove %dkm, which increased your range by %fkm. Your current range is at %fkm.\n", kilometers, range-rangeBefore, range);
            return true;
        }
    }

    /**
     * @return  a string containing all the fields of the class, as well as their current values.
     */
    @Override
    public String toString() {
        return super.toString() + "\nBicycle [countGears=" + countGears + ", range=" + range + "]";
    }
    
}
