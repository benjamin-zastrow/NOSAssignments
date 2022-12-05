package nos.bzastrow;

// SH: Your VehicleOwner is modeled in a strange way: You decided that a
// vehicle is composed of an owner! But speaking of objects, now you vehicle
// "owns" the owner. In particular, it is impossible that two vehicles refer to
// the same owner.
/**
 * This class describes a vehicle owner with the properties name, birth-date, address, as well as an internal ownership boolean that allows vehicles to be unowned.
 * The properties are to be accessed using the respective getter and setter methods.
 */
public class VehicleOwner {
    // SH: "-" is a bad default. Do add meaning (semantics) to »special«
    // strings like "-", i.e., do not encode the state »not set« by "-". For
    // starters, use null instead. But all this is actually only necessary
    // because a Vehicle does not just refer to VehicleOwner, but is composed
    // of it.
    // SH: This initializations are redundant to your constructors. Kill
    // redundancy. Redundancy invites inconsistencies invites bugs.
    private String name = "-";
    private String birthDate = "-";
    private String address = "-";
    private boolean isOwner = false;

    public String getName() {
        return name;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public String getAddress() {
        return address;
    }
    public boolean isOwnershipSet() {
        return isOwner;
    }

    /**
     * no-argument constructor that initialises in an unowned state
     */
    VehicleOwner() {
        this("-", "-", "-");
        isOwner = false;

    }
    /**
     * constructor that initialises all fields and sets ownership to true
     * @param name  Name of the new owner
     * @param birthDate  BirthDate of the owner as String in format "DD-MM-YYYY"
     * @param address  Address of the owner as String in format "Street No, ZIP City, Province/State, Country"
     */
    VehicleOwner(String name, String birthDate, String address) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        isOwner = true;
    }

    /**
     * reset the ownership object to an unowned state.
     */
    public void clearOwnership() {
        name = "-";
        birthDate = "-";
        address = "-";
        isOwner = false;
    }

    /**
     * set the ownership to the given arguments, which are checked before being set.
     * @param name  Name of the new owner
     * @param birthDate  BirthDate of the owner as String in format "DD-MM-YYYY"
     * @param address  Address of the owner as String in format "Street No, ZIP City, Province/State, Country"
     * @return  'true' if all entered input is correct (format checks ignored), 'false' if some entered input is wrong.
     */
    public boolean setOwnership(String name, String birthDate, String address) {
        if(name.isBlank() || name.isEmpty() || name.contains("-.,@!§$%&/()=?")) {
            return false;
        }
        if(address.isBlank() || address.isEmpty()) {
            return false;
        }
        if(birthDate.isBlank() || birthDate.isEmpty()) {
            return false;
        }
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        isOwner = true;
        return true;
    }

    /**
     * @return  a string containing all the fields of the class, as well as their current values.
     */
    @Override
    public String toString() {
        return "VehicleOwner [address=" + address + ", birthDate=" + birthDate + ", isOwner=" + isOwner + ", name="
                + name + "]";
    }


}
