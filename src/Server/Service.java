package Server;

public class Service {
    public static String man =
            "------Services manual page------\n\n" +
                    "1. calc\n" +
                    "Syntax: calc FIRST_VALUE OPERATOR SECOND_VALUE\n" +
                    "Example: calc 256 * 19\n\n" +
                    "2. convert\n" +
                    "Syntax: convert INTEGER_VALUE_NUMBER INTEGER_VALUE_CURRENT_BASE INTEGER_VALUE_DESTINATION_BASE\n" +
                    "Example: convert 12 10 8\n\n";

    public static double calc(double first, String operation, double second) {
        return switch (operation) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            default -> first / second;
        };
    }

    public static String convertNumberSystem(int number, int current, int destination) {
        int result = Integer.parseInt(Integer.toString(number), current);
        return Integer.toString(result, destination);
    }

}

