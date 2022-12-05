package nos.bzastrow;

/**
 * This class provides one static method to print a vehicle's property.
 * Making the method static is suitable since it is independent of any data within it's class, which means that both the input and the output of the method have nothing to do with the class VehiclePrinter.
 * Having to create an instance of VehiclePrinter in order to use it's only function therefore does not make sense.
 * @param v  The vehicle whose core information is to be printed
 */

public class VehiclePrinter {
    // SH: There is no rationale behind the final here. (Note that static
    // methods cannot be overriden anyhow, but final also prohibits hidding the
    // method by a static method in a subclass.)
    public static final void print(Vehicle v) {
        System.out.println(v.toString());
        System.out.println();
    }
}
