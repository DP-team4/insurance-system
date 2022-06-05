import java.util.Scanner;

import view.EmployeeView;
import view.customer.CustomerHomeView;
import view.viewUtility.ScannerUtility;

public class Main {
    private final static Scanner scanner = ScannerUtility.getScanner();

	public static void main(String[] args) {
		System.out.println("////////////////////// DP 2022-1 보험사조 //////////////////////");
		while (true) {
			System.out.print("이용할 서비스를 선택해주세요 - 고객(1), 직원(2), 종료(exit) >> "); String input = scanner.nextLine().trim();
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
					System.out.println("잘못된 입력입니다. 입력: " + input);
					break;
			}
		}
	}
}
