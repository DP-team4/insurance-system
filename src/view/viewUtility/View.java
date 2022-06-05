package view.viewUtility;

import java.util.Scanner;

public abstract class View {
    protected ScannerUtility mScanner = ScannerUtility.getInstance();

    public abstract void show();
}
