import java.util.Scanner;

import view.customer.CustomerHomeView;
import view.salesManagement.SalesMainView;
import view.viewUtility.ScannerUtility;

public class Main {
    private final static Scanner scanner = ScannerUtility.getScanner();

	public static void main(String[] args) {
		System.out.print("이용할 서비스를 선택해주세요 - 고객(1), 직원(2) >> "); String input = scanner.nextLine().trim();
		if(input.equals("1")) new CustomerHomeView().show();
		else if(input.equals("2")) System.out.println("직원 화면 미구현"); // new EmployeeView().show();
		else System.out.println("Invalid Option");
		new SalesMainView().show();
		ScannerUtility.closeScanner();
	}
}
