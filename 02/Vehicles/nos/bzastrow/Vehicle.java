package nos.bzastrow;


/**
 * This abstract class generally describes a vehicle with the properties color, max-speed, weight, owner and whether it is currently driving.
 * The properties are to be accessed using the respective getter and setter methods (accessOwner() for the ownership property).
 */  
public abstract class Vehicle {
    // SH: See my comment on MotorizedVehicle on protected
    protected String color = "white";
    protected int maxSpeed = 100;
    protected boolean isDriving = false;
    protected int weight = 1000;

    protected VehicleOwner owner;

    public void setColor(String nColor) {
        color = nColor;
    }
    public String getColor() {
        return this.color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getWeight() {
        return weight;
    }

    /**
     * Gives access to the current owner field of the vehicle
     * @return  VehicleOwner object that allows further operations regarding ownership
     */
    public VehicleOwner accessOwner() {
        return owner;
    }

    /**
     * Abstract method for driving -> to be overridden by specific implementations of vehicles.
     * @param kilometers  Distance to be driven in km
     * @return  'true' or 'false' depending on the success of the drive
     */
    public abstract boolean drive(int kilometers);

    /**
     * @return  a string containing all the fields of the class, as well as their current values.
     */
    @Override
    public String toString() {
        return "Vehicle [color=" + color + ", isDriving=" + isDriving + ", maxSpeed=" + maxSpeed + ", owner=" + owner.toString()
                + ", weight=" + weight + "]";
    }
}
