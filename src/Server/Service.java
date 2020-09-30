package Server;

public class Service {
    public static String man =
            "------Services manual page------\n\n" +
                    "1. calc\n" +
                    "Syntax: calc FIRST_VALUE OPERATOR SECOND_VALUE\n" +
                    "Example: calc 256 * 19\n\n" +
                    "2. bin\n" +
                    "Syntax: bin INTEGER_VALUE\n" +
                    "Example: bin 12\n\n" +
                    "3. hex\n" +
                    "Syntax: hex INTEGER_VALUE\n" +
                    "Example: hex 21\n\n" +
                    "4. oct\n" +
                    "Syntax: oct INTEGER_VALUE\n" +
                    "Example: oct 69";

    public static double calc(double first, String operation, double second) {
        return switch (operation) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            default -> first / second;
        };
    }

    public static String toBinary(int dec) {
        return Integer.toBinaryString(dec);
    }

    public static String toHex(int dec) {
        return Integer.toHexString(dec);
    }

    public static String toOct(int dec) {
        return Integer.toOctalString(dec);
    }
}

