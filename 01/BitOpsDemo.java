import java.util.*;

/** A demo for bit operations */
class BitOpsDemo {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        int value = 0, bitIndex = 0;
        String answer;
        System.out.print("Enter a decimal number: ");
        // Read and set value.
        value = scanner.nextInt();
        System.out.printf("Initial value is %d (0x%x)\n", value, value);

        while (true) {
            System.out.print("What bit operation shall be applied (bitset, bitclr, invert)? ");

            // Read answer, implement switch-statement and apply result.
            answer = scanner.next();
            switch(answer.toLowerCase()) {
                case "bitset": {
                    System.out.print("Which bit? ");
                    bitIndex = scanner.nextInt();
                    // SH: I like that you check the user input!
                    if(bitIndex > 31 || bitIndex < 0) {
                        System.out.println("Error: Bit must be within range [0; 31]");
                        break;
                    }

                    value = value | (1 << bitIndex);
                    break;
                }
                case "bitclr": {
                    System.out.print("Which bit? ");
                    bitIndex = scanner.nextInt();
                    if(bitIndex > 31 || bitIndex < 0) {
                        System.out.println("Error: Bit must be within range [0; 31]");
                        break;
                    }

                    value = value & ~(1 << bitIndex);
                    break;
                }
                case "invert": {
                    value = ~value;
                    break;
                }
                default: {
                    System.out.println("Error, operation must be one of the following: (bitset, bitclr, invert)");
                    break;
                }
            }

            System.out.printf("Value is %d (0x%x)\n", value, value);

            // Ask user whether to stop. -> default answer is yes
            System.out.print("Stop? [Y/n]: ");
            answer = scanner.next();
            // SH: This piece code could be much simpler and conciser, two
            // lines effectively.
            switch(answer.toLowerCase()) {
                case "y": {
                    return;
                }
                case "n": {
                    break;
                }
                default: {
                    return;
                }
            }
            scanner.close();
        }
    }
}
