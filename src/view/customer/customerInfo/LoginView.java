package view.customer.customerInfo;

import java.util.Scanner;

import domain.customer.Customer;
import service.customerInfo.LoginService;
import service.customerInfo.LoginServiceImpl;
import view.viewUtility.ScannerUtility;

public class LoginView {
    private final Scanner scanner = ScannerUtility.getScanner();
	
	// Service
	private LoginService loginService = LoginServiceImpl.getInstance();

	public Customer show() {
		while(true) {
			System.out.println("\n[ �α��� ]");
			System.out.print("�̸��� >> "); String email = scanner.nextLine().trim();
			System.out.print("��й�ȣ >> "); String password = scanner.nextLine().trim();
			Customer customer = loginService.login(email, password);
			if(customer == null) {
				System.out.print("�α��� ����. �޴��� ���ư���(m) �ٽ� �õ�(��Ÿ)>> ");
				if(scanner.nextLine().trim().equals("m")) return null;
			} else {
				System.out.println("�α��� ����."); return customer;
			}
		}
	}
}
