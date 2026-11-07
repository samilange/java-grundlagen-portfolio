import java.util.Locale;
import java.util.Scanner;

/**
 * Taschenrechner für die Konsole.
 * Zeigt: Variablen, Datentypen, Scanner, Schleifen, switch, Methoden.
 */
public class Taschenrechner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Deutsche Schreibweise: Dezimalkomma (z. B. 3,5)
        scanner.useLocale(Locale.GERMANY);

        System.out.println("=== Taschenrechner ===");
        System.out.println("Erlaubte Operatoren: +  -  *  /  %");

        boolean weiter = true;
        while (weiter) {
            double a = liesZahl(scanner, "Erste Zahl:  ");
            char operator = liesOperator(scanner);
            double b = liesZahl(scanner, "Zweite Zahl: ");

            if ((operator == '/' || operator == '%') && b == 0) {
                System.out.println("Fehler: Durch 0 kann nicht geteilt werden.");
            } else {
                double ergebnis = berechne(a, operator, b);
                System.out.printf(Locale.GERMANY, "%.2f %c %.2f = %.2f%n", a, operator, b, ergebnis);
            }

            System.out.print("Noch eine Rechnung? (j/n): ");
            String antwort = scanner.next();
            weiter = antwort.equalsIgnoreCase("j");
        }

        System.out.println("Tschüss!");
        scanner.close();
    }

    /** Führt die eigentliche Rechnung aus. */
    static double berechne(double a, char operator, double b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '%':
                return a % b;
            default:
                return 0;
        }
    }

    /** Fragt so lange nach einer Zahl, bis die Eingabe gültig ist. */
    static double liesZahl(Scanner scanner, String text) {
        System.out.print(text);
        while (!scanner.hasNextDouble()) {
            System.out.print("Das ist keine Zahl. Bitte nochmal: ");
            scanner.next(); // falsche Eingabe verwerfen
        }
        return scanner.nextDouble();
    }

    /** Fragt so lange nach einem Operator, bis die Eingabe gültig ist. */
    static char liesOperator(Scanner scanner) {
        String erlaubt = "+-*/%";
        System.out.print("Operator (+ - * / %): ");
        while (true) {
            String eingabe = scanner.next();
            if (eingabe.length() == 1 && erlaubt.indexOf(eingabe.charAt(0)) >= 0) {
                return eingabe.charAt(0);
            }
            System.out.print("Ungültiger Operator. Bitte nochmal: ");
        }
    }
}
