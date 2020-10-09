package services;

public class Services {
    private Services() {
    }

    public static final String MAN =
            "------Test.Services manual page------\n\n" +
                    "1. calc\n" +
                    "Syntax: calc FIRST_VALUE OPERATOR SECOND_VALUE\n" +
                    "Example: calc 256 * 19\n\n" +
                    "2. convert\n" +
                    "Syntax: convert INTEGER_VALUE_NUMBER INTEGER_VALUE_CURRENT_BASE INTEGER_VALUE_DESTINATION_BASE\n" +
                    "Example: convert 12 10 8\n\n";

    public static String calc(double first, String operation, double second) {
        switch (operation) {
            case "+":
                return String.valueOf(first + second);
            case "-":
                return String.valueOf(first - second);
            case "*":
                return String.valueOf(first * second);
            case "/":
                if (second == 0) {
                    return "Division by zero!";
                }
                return String.valueOf(first / second);
            default:
                return "Input has wrong format!";
        }
    }

    public static String convertNumberSystem(String number, int current, int destination) {
        int result = Integer.parseInt(number, current);
        return Integer.toString(result, destination);
    }
}

