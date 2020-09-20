package Server;

public class Service {
    public static double calc(double first, String operation, double second) {
        return switch (operation) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            default -> first / second;
        };
    }
}
