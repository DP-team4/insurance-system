package view.customer.customerInfo;

import java.util.Scanner;

import domain.customer.Customer;
import exception.InvalidInputException;
import service.customerInfo.LoginService;
import service.customerInfo.LoginServiceImpl;
import view.viewUtility.ScannerUtility;

public class LoginView {
    private final Scanner scanner = ScannerUtility.getScanner();
	
	// Service
	private LoginService loginService = LoginServiceImpl.getInstance();

	public Customer show() {
		try {
			while(true) {
				System.out.println("\n[ �α��� ]");
				System.out.print("�̸��� >> "); String email = getInputAndCheckInvalid();
				System.out.print("��й�ȣ >> "); String password = getInputAndCheckInvalid();
				Customer customer = loginService.login(email, password);
				if(customer == null) {
					System.out.println("�α��� ����. �޴��� ���ư���(m) �ٽ� �õ�(��Ÿ)");
					System.out.print(">> ");
					if(scanner.nextLine().trim().equals("m")) return null;
				} else { System.out.println("�α��� ����."); return customer; }
			}
		} catch (InvalidInputException invalidInputException) {
        	// ��ȿ���� ���� ���� �Է��� ���
            System.out.println(invalidInputException.getMessage());
		} return null;
	}

    // �α��� ���� �Է� ���θ� üũ�Ѵ�
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// ����
		if(input.equals("")) {
			throw new InvalidInputException("�Է°��� �����ϴ�.");
		}
		return input;
	}
}
