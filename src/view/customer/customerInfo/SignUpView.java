package view.customer.customerInfo;

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
		System.out.println("\n[ ȸ������ ]");
        System.out.print("�̸� >> "); String name = scanner.nextLine().trim();
        System.out.print("�̸��� >> "); String email = scanner.nextLine().trim();
        System.out.print("��й�ȣ >> "); String password = scanner.nextLine().trim();
        System.out.print("���� >> "); String age = scanner.nextLine().trim();
        boolean finished = false;
        boolean gender = false;
        while(!finished) {
            System.out.print("���� ����(1), ����(2) >> ");
            switch(scanner.nextLine().trim()) {
				case "1": gender = true; finished = true; break;
				case "2": gender = false; finished = true; break;
				default: System.out.println("������ ���� �Է��� �ùٸ��� �ʽ��ϴ�."); break;
			};
        }
        System.out.print("�ֹε�Ϲ�ȣ ���ڸ� >> "); String registrationNo = scanner.nextLine().trim();
		System.out.print("�ֹε�Ϲ�ȣ ���ڸ� >> "); registrationNo += ("-" + scanner.nextLine().trim());
        System.out.print("��ȭ��ȣ ex.01012345678 >> "); String phoneNo = scanner.nextLine().trim();
        System.out.print("���¹�ȣ >> "); String accountNo = scanner.nextLine().trim();
        finished = false;
        boolean isMarried = false;
        while(!finished) {
            System.out.print("��ȥ���� ��ȥ(1) ��ȥ(2) >> ");
            switch(scanner.nextLine().trim()) {
				case "1": isMarried = true; finished = true; break;
				case "2": isMarried = false; finished = true; break;
				default: System.out.println("��ȥ���ο� ���� �Է��� �ùٸ��� �ʽ��ϴ�."); break;
            };
        }
        System.out.print("����(��) >> "); String carPrice = scanner.nextLine().trim();
        System.out.print("����(��) >> "); String housePrice = scanner.nextLine().trim();
        System.out.print("�������(��) >> "); String drivingCareer = scanner.nextLine().trim();
        System.out.print("���ڰ���(��) >> "); String shipPrice = scanner.nextLine().trim();
        System.out.print("\nȸ�������Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
        // ȸ�� ���� ���� �Է� ���θ� üũ�Ѵ�
        boolean blankExist = name.equals("") || email.equals("") || password.equals("") || age.equals("")
        		|| registrationNo.equals("") || phoneNo.equals("") || accountNo.equals("")
        		|| carPrice.equals("") || housePrice.equals("") || drivingCareer.equals("") || shipPrice.equals("");
        if(input.equals("1")) {
            // A1. ��� ��û ����� �� �Է��� ���
        	if(blankExist) { System.out.println("\n��û ����� ���� �Է����ּ���."); return; }signUp(name, email, password, age, gender, registrationNo, phoneNo, accountNo, isMarried, carPrice, housePrice, drivingCareer, shipPrice);
        } else {
        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
        	if(!blankExist) {
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;signUp(name, email, password, age, gender, registrationNo, phoneNo, accountNo, isMarried, carPrice, housePrice, drivingCareer, shipPrice);
        	}
        }
	}
	
	private void signUp(String name, String email, String password, String age, boolean gender, String registrationNo, String phoneNo, String accountNo, boolean isMarried, String carPrice, String housePrice, String drivingCareer, String shipPrice) {
        Customer customer = new Customer();
        AdditionalInfo additionalinfo = customer.getAdditionalInfo();
		try {
            customer.setName(name);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setAge(Integer.parseInt(age));
            customer.setGender(gender);
            customer.setRegistrationNo(registrationNo);
	        customer.setPhoneNo(phoneNo);
	        customer.setMarried(isMarried);
	        additionalinfo.setCarPrice(Long.parseLong(carPrice));
	        additionalinfo.setHousePrice(Long.parseLong(housePrice));
	        additionalinfo.setDrivingCareer(Integer.parseInt(drivingCareer));
	        additionalinfo.setShipPrice(Long.parseLong(shipPrice));
			if(signUpService.signUp(customer)) System.out.println("ȸ������ ����. �޴��� ���ư��ϴ�.");
			else System.out.println("ȸ������ ����. �޴��� ���ư��ϴ�.");
        } catch (NumberFormatException e) {
        	// ��ȿ���� ���� ���� �Է��� ���
            System.out.println("�߸��� �Է��Դϴ�.");
        }
	}
}
