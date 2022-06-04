package view.viewUtility;

import java.util.Scanner;

public class ScannerUtility {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ScannerUtility instance = new ScannerUtility();

    private ScannerUtility(){}

    public static Scanner getScanner() {
        return scanner;
    }
    public static ScannerUtility getInstance() { return instance; }

    public String getString() {
        return scanner.nextLine().trim();
    }

    public <T extends Enum<T>> T getEnum(Class<T> enumClass) throws IllegalArgumentException{
        return T.valueOf(enumClass ,getString());
    }

    public long getLong() throws NumberFormatException{
        return Long.parseLong(getString());
    }

    public int getInt() throws NumberFormatException {
        return Integer.parseInt(getString());
    }
}
