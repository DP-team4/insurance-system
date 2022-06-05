import java.util.Scanner;

import view.EmployeeView;
import view.contractMangement.ContractIntergratedManagementView;
import view.customer.CustomerHomeView;
import view.salesManagement.SalesMainView;
import view.viewUtility.ScannerUtility;

public class Main {
    private final static Scanner scanner = ScannerUtility.getScanner();

	public static void main(String[] args) {
		System.out.print("�̿��� ���񽺸� �������ּ��� - ��(1), ����(2) >> "); String input = scanner.nextLine().trim();
		if(input.equals("1")) new CustomerHomeView().show();
		else if(input.equals("2")) new EmployeeView().show();
		else System.out.println("Invalid Option");
		ScannerUtility.closeScanner();
	}
}
