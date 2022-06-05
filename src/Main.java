import java.util.Scanner;

import view.EmployeeView;
import view.customer.CustomerHomeView;
import view.viewUtility.ScannerUtility;

public class Main {
    private final static Scanner scanner = ScannerUtility.getScanner();

	public static void main(String[] args) {
		System.out.println("////////////////////// DP 2022-1 ������� //////////////////////");
		while (true) {
			System.out.print("�̿��� ���񽺸� �������ּ��� - ��(1), ����(2), ����(exit) >> "); String input = scanner.nextLine().trim();
			switch (input) {
				case "1":
					new CustomerHomeView().show();
					break;
				case "2":
					new EmployeeView().show();
					break;
				case "exit":
					System.out.println("Bye~");
					return;
				default:
					System.out.println("�߸��� �Է��Դϴ�. �Է�: " + input);
					break;
			}
		}
	}
}
