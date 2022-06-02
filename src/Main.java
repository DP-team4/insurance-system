import java.util.Scanner;

import view.customer.CustomerMenuView;
import view.viewUtility.ScannerUtility;

public class Main {
    private final static Scanner scanner = ScannerUtility.getScanner();

	public static void main(String[] args) {
		System.out.print("�̿��� ���񽺸� �������ּ��� - ��(1), ����(2) >> "); String input = scanner.nextLine().trim();
		if(input.equals("1")) new CustomerMenuView().show();
		else if(input.equals("2")) System.out.println("���� ȭ�� �̱���"); // new EmployeeView().show();
		else System.out.println("Invalid Option");
	}

}
