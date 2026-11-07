import java.util.Random;
import java.util.Scanner;

/**
 * Galgenraten (Hangman): Ein Wort wird Buchstabe für Buchstabe erraten.
 * Zeigt: Arrays (String[], char[], boolean[]), Schleifen, Bedingungen, Methoden.
 */
public class Galgenraten {

    static final int MAX_FEHLER = 6;

    // Die Galgen-Bilder: Index = Anzahl der Fehler
    static final String[] GALGEN = {
        "  +---+\n  |   |\n      |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n  |   |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|   |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n========="
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random zufall = new Random();

        String[] woerter = {
            "JAVA", "ARRAY", "SCHLEIFE", "VARIABLE", "METHODE", "COMPUTER",
            "PROGRAMM", "TASTATUR", "BILDSCHIRM", "ENTWICKLER", "ZAHL", "KLASSE"
        };

        System.out.println("=== Galgenraten ===");
        boolean nochmal = true;

        while (nochmal) {
            String wort = woerter[zufall.nextInt(woerter.length)];
            char[] anzeige = new char[wort.length()];
            for (int i = 0; i < anzeige.length; i++) {
                anzeige[i] = '_';
            }
            boolean[] benutzt = new boolean[26]; // Index 0 = A, 1 = B, ...
            int fehler = 0;
            boolean gewonnen = false;

            while (fehler < MAX_FEHLER && !gewonnen) {
                zeigeStatus(anzeige, benutzt, fehler);
                char buchstabe = liesBuchstabe(scanner, benutzt);
                benutzt[buchstabe - 'A'] = true;

                boolean treffer = false;
                for (int i = 0; i < wort.length(); i++) {
                    if (wort.charAt(i) == buchstabe) {
                        anzeige[i] = buchstabe;
                        treffer = true;
                    }
                }

                if (treffer) {
                    System.out.println("Treffer!");
                } else {
                    fehler++;
                    System.out.println("Leider falsch.");
                }
                gewonnen = istKomplett(anzeige);
            }

            System.out.println();
            System.out.println(GALGEN[fehler]);
            if (gewonnen) {
                System.out.println("Gewonnen! Das Wort war: " + wort);
            } else {
                System.out.println("Verloren! Das Wort war: " + wort);
            }

            System.out.print("Noch eine Runde? (j/n): ");
            nochmal = scanner.next().equalsIgnoreCase("j");
        }

        System.out.println("Danke fürs Spielen!");
        scanner.close();
    }

    /** Zeigt Galgen, Wort mit Lücken, bereits benutzte Buchstaben und Fehlversuche. */
    static void zeigeStatus(char[] anzeige, boolean[] benutzt, int fehler) {
        System.out.println();
        System.out.println(GALGEN[fehler]);
        System.out.print("Wort: ");
        for (int i = 0; i < anzeige.length; i++) {
            System.out.print(anzeige[i] + " ");
        }
        System.out.println();

        System.out.print("Benutzt: ");
        for (int i = 0; i < benutzt.length; i++) {
            if (benutzt[i]) {
                System.out.print((char) ('A' + i) + " ");
            }
        }
        System.out.println();
        System.out.println("Fehler: " + fehler + "/" + MAX_FEHLER);
    }

    /** Prüft, ob im Array keine Lücke mehr ist. */
    static boolean istKomplett(char[] anzeige) {
        for (int i = 0; i < anzeige.length; i++) {
            if (anzeige[i] == '_') {
                return false;
            }
        }
        return true;
    }

    /** Liest einen noch nicht benutzten Buchstaben (A-Z) ein. */
    static char liesBuchstabe(Scanner scanner, boolean[] benutzt) {
        System.out.print("Dein Buchstabe: ");
        while (true) {
            String eingabe = scanner.next().toUpperCase();
            if (eingabe.length() != 1 || eingabe.charAt(0) < 'A' || eingabe.charAt(0) > 'Z') {
                System.out.print("Bitte genau einen Buchstaben (A-Z) eingeben: ");
            } else if (benutzt[eingabe.charAt(0) - 'A']) {
                System.out.print("Den hattest du schon. Anderer Buchstabe: ");
            } else {
                return eingabe.charAt(0);
            }
        }
    }
}
