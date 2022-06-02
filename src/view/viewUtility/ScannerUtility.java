package view.viewUtility;

import java.util.Scanner;

public class ScannerUtility {
    private static final Scanner scanner = new Scanner(System.in);

    private ScannerUtility(){}

    public static Scanner getScanner() {
        return scanner;
    }
}
