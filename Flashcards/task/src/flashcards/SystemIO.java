package flashcards;

import java.util.Scanner;

class SystemIO {
    private static Scanner scanner = new Scanner(System.in);
    private static LogBuilder log = new LogBuilder();

    static String scan() {
        String str = scanner.nextLine();
        log.add(str);
        log.add(System.lineSeparator());
        return str;
    }

    static void println(String str) {
        log.add(str);
        log.add(System.lineSeparator());
        System.out.println(str);
    }

    static void println() {
        log.add(System.lineSeparator());
        System.out.println();
    }

    static void printf(String s, String... args) {
        String str = String.format(s, args);
        System.out.print(str);
        log.add(str);
    }
}
