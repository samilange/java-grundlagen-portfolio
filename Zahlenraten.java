import java.util.Random;
import java.util.Scanner;

/**
 * Zahlenraten mit drei Schwierigkeitsstufen.
 * Zeigt: Random, Schleifen, Bedingungen, Methoden und Arrays.
 */
public class Zahlenraten {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random zufall = new Random();

        // Die Schwierigkeitsstufen stehen in Arrays (gleicher Index = gleiche Stufe)
        String[] namen = {"Leicht", "Mittel", "Schwer"};
        int[] obergrenzen = {10, 100, 1000};
        int[] maxVersuche = {5, 8, 12};

        int gespielt = 0;
        int gewonnen = 0;
        boolean nochmal = true;

        System.out.println("=== Zahlenraten ===");

        while (nochmal) {
            // Stufe wählen
            System.out.println();
            for (int i = 0; i < namen.length; i++) {
                System.out.println((i + 1) + ") " + namen[i]
                        + " (1 bis " + obergrenzen[i] + ", " + maxVersuche[i] + " Versuche)");
            }
            System.out.print("Stufe wählen: ");
            int stufe = liesZahl(scanner, 1, namen.length) - 1;

            int geheimzahl = zufall.nextInt(obergrenzen[stufe]) + 1;
            int[] tipps = new int[maxVersuche[stufe]]; // merkt sich alle Tipps
            int versuch = 0;
            boolean erraten = false;

            while (versuch < maxVersuche[stufe] && !erraten) {
                System.out.print("Versuch " + (versuch + 1) + "/" + maxVersuche[stufe] + ": ");
                int tipp = liesZahl(scanner, 1, obergrenzen[stufe]);
                tipps[versuch] = tipp;
                versuch++;

                if (tipp == geheimzahl) {
                    erraten = true;
                } else if (tipp < geheimzahl) {
                    System.out.println("Zu klein!");
                } else {
                    System.out.println("Zu groß!");
                }
            }

            gespielt++;
            if (erraten) {
                gewonnen++;
                System.out.println("Richtig! Du hast die Zahl " + geheimzahl
                        + " in " + versuch + " Versuchen erraten.");
            } else {
                System.out.println("Leider verloren. Die Zahl war " + geheimzahl + ".");
            }

            // Alle Tipps aus dem Array ausgeben
            System.out.print("Deine Tipps: ");
            for (int i = 0; i < versuch; i++) {
                System.out.print(tipps[i] + (i < versuch - 1 ? ", " : ""));
            }
            System.out.println();

            System.out.print("Noch eine Runde? (j/n): ");
            nochmal = scanner.next().equalsIgnoreCase("j");
        }

        System.out.println("Statistik: " + gewonnen + " von " + gespielt + " Runden gewonnen.");
        scanner.close();
    }

    /** Liest eine ganze Zahl zwischen min und max ein (wiederholt bei falscher Eingabe). */
    static int liesZahl(Scanner scanner, int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int zahl = scanner.nextInt();
                if (zahl >= min && zahl <= max) {
                    return zahl;
                }
            } else {
                scanner.next(); // falsche Eingabe verwerfen
            }
            System.out.print("Bitte eine Zahl von " + min + " bis " + max + " eingeben: ");
        }
    }
}
