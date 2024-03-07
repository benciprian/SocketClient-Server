package ro.ubb.server;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {
    private TCPClient client;
    private Scanner scanner;

    public ConsoleUI(TCPClient client) {
        this.client = client;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n*** Meniu Client TCP ***");
            System.out.println("1. Calculeaza suma numerelor introduse de la tastatura");
            System.out.println("2. Calculeaza produsul numerelor introduse de la tastatura");
            System.out.println("3. Afiseaza numerele divizibile cu d din intervalul [a, b)");
            System.out.println("4. Afiseaza numerele din intervalul [a, b) ale caror ultime 2 cifre sunt divizibile cu d");
            System.out.println("5. Iesire");

            System.out.print("Alege o optiune: ");

            int optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    handlePoint1(optiune);
                    break;
                    case 2:
                    handlePoint2(optiune);
                    break;
                case 3:
                     handlePoint3(optiune);
                     break;
                case 4:
                    handlePoint4(optiune);
                    break;
                case 5:
                    System.out.println("Iesire din aplicatie...");
                    return;

                default:
                    System.out.println("Optiune invalida!");
                    break;
            }
        }
    }

    private void handlePoint1(int optiune) {
        System.out.println("Introdu numerele separate prin virgula: ");
        String numbersList = scanner.next();
        client.sendAndReceiveList(optiune, numbersList);
    }
    private void handlePoint2(int optiune) {
        System.out.println("Introdu numerele separate prin virgula: ");
        String numbersList = scanner.next();
        client.sendAndReceiveList(optiune, numbersList);
    }
    private void handlePoint4(int optiune) {
        try {
            System.out.print("Introdu a: ");
            int a = scanner.nextInt();
            System.out.print("Introdu b: ");
            int b = scanner.nextInt();
            System.out.print("Introdu d: ");
            int d = scanner.nextInt();

            client.sendAndReceive(optiune, a, b, d);
        } catch (InputMismatchException e) {
            System.out.println("Te rog sa introduci numere intregi.");
            scanner.next();
        }
    }

    private void handlePoint3(int optiune) {
        try {
            System.out.print("Introdu a: ");
            int a = scanner.nextInt();
            System.out.print("Introdu b: ");
            int b = scanner.nextInt();
            System.out.print("Introdu d: ");
            int d = scanner.nextInt();

            client.sendAndReceive(optiune, a, b, d);
        } catch (InputMismatchException e) {
            System.out.println("Te rog sa introduci numere intregi.");
            scanner.next();
        }
    }

}
