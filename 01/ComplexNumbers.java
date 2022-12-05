import java.util.*;
import java.lang.Math;

/** A demo for complex numbers. */
class ComplexNumbers {
    public static void main(String args[]) {
        // SH: Always define variables as local as possible. In this case, you
        // could define those variables on their first use below, when you read
        // in the input.
        double real, imag, absol, angle;
        Scanner scanner = new Scanner(System.in);

        // Enter real part of the number
        System.out.print("Enter real part: ");
        // Attention: If you run a german locale then you may need to enter a
        // "," as comma separator!
        real = scanner.nextDouble();

        // Enter imaginary part of the number
        System.out.print("Enter imaginary part: ");
        imag = scanner.nextDouble();

        // Let c be the complex number formed by real and imag. Output this
        // number in polar coordinates, i.e., absolute value and angle
        // (argument). Note that real and imag can both be postive or negative.


        // SH: pow() is slow and in general imprecise. Depending on the CPU,
        // pow() is reduced to exp() and log(), like pow(x, y) = exp(y*log(x)),
        // which again might involve Taylor series. But you could have done it
        // with a simple multiplication, i.e., x*x instead of pow(x,2). Also,
        // write pow(real, 2.0) in order to indicate that the second argument
        // is actually a double, not an int!
        // SH: Note that there is Math.hypot()
        //
        // Calculate the absolute value and print it
        absol = Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
        System.out.println("Absolute value is " + absol);

        // SH: When real is zero you have a division by zero!
        // SH: Since tan() is pi-periodic, this cannot be correct. In
        // particular, when you set real=imag=-1 then the result is the same as
        // if real=imag=1. Use atan2()!
        // Calculate the angle and print it
        angle = Math.atan(imag/real);
        System.out.println("Angle is " + angle);

        // Normalize the complex number to unit length and output it again.

        real /= absol;
        imag /= absol;
        // SH: Use %n instead of \n for a platform-independent newline.
        System.out.print("Normalised real is " + real + "\nNormalised imag is " + imag + "\n");


        // Enter real part of the number
        System.out.print("Enter absolute value: ");
        absol = scanner.nextDouble();

        // Enter imaginary part of the number
        System.out.print("Enter angle (argument): ");
        angle = scanner.nextDouble();

        // Output the real and imaginary part of the complex number given by
        // the absolute value and angle.

        imag = Math.sin(angle) * absol;
        real = Math.cos(angle) * absol;

        System.out.println("Real is " + real);
        System.out.println("Imag is " + imag);

        scanner.close();
    }
}
