package view.customer;

import java.util.Scanner;

import domain.customer.AdditionalInfo;
import domain.customer.Customer;
import service.customerInfo.SignUpService;
import service.customerInfo.SignUpServiceImpl;
import view.viewUtility.ScannerUtility;

public class SignUpView {
    private final Scanner scanner = ScannerUtility.getScanner();
	
	// Service
	private SignUpService signUpService = SignUpServiceImpl.getInstance();
	
	public void show() {
		String input = "";
        Customer customer = new Customer();
		System.out.println("\n[ ȸ������ ]");
        try {
	        System.out.print("�̸� >> "); customer.setName(scanner.nextLine().trim());
	        System.out.print("�̸��� >> "); customer.setEmail(scanner.nextLine().trim());
	        System.out.print("��й�ȣ >> "); customer.setPassword(scanner.nextLine().trim());
	        System.out.print("���� >> "); customer.setAge(Integer.parseInt(scanner.nextLine().trim()));
	        boolean finished = false;
	        while(!finished) {
	            System.out.print("���� ����(1), ����(2) >> ");
	            input = scanner.nextLine().trim();
	            switch(input) {
					case "1": customer.setGender(true); finished = true; break;
					case "2": customer.setGender(false); finished = true; break;
					default: System.out.println("������ ���� �Է��� �ùٸ��� �ʽ��ϴ�. �Է�: " + input); break;
				};
	        }
	        System.out.print("�ֹε�Ϲ�ȣ ���ڸ� >> "); input = scanner.nextLine().trim();
			System.out.print("�ֹε�Ϲ�ȣ ���ڸ� >> "); customer.setRegistrationNo(input + "-" + scanner.nextLine().trim());
	        System.out.print("��ȭ��ȣ ex.01012345678 >> "); customer.setPhoneNo(scanner.nextLine().trim());
	        System.out.print("���¹�ȣ >> "); customer.setAccountNo(scanner.nextLine().trim());
	        finished = false;
	        while(!finished) {
	            System.out.print("��ȥ���� ��ȥ(1) ��ȥ(2) >> ");
	            input = scanner.nextLine().trim();
	            switch(input) {
					case "1": customer.setMarried(true); finished = true; break;
					case "2": customer.setMarried(false); finished = true; break;
					default: System.out.println("��ȥ���ο� ���� �Է��� �ùٸ��� �ʽ��ϴ�. �Է�: " + input); break;
	            };
	        }
	        AdditionalInfo additionalinfo = customer.getAdditionalInfo();
	        System.out.print("����(��) >> "); additionalinfo.setCarPrice(Long.parseLong(scanner.nextLine().trim()));
	        System.out.print("����(��) >> "); additionalinfo.setHousePrice(Long.parseLong(scanner.nextLine().trim()));
	        System.out.print("�������(��) >> "); additionalinfo.setDrivingCareer(Integer.parseInt(scanner.nextLine().trim()));
	        System.out.print("���ڰ���(��) >> "); additionalinfo.setShipPrice(Long.parseLong(scanner.nextLine().trim()));
			if(signUpService.signUp(customer)) System.out.println("ȸ������ ����. �޴��� ���ư��ϴ�.");
			else System.out.println("ȸ������ ����. �޴��� ���ư��ϴ�.");
        } catch (NumberFormatException e) {
            System.out.println("�߸��� �Է��Դϴ�.");
        }
	}
}
