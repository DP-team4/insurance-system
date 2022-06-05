package view.customer.customerInfo;

import java.util.Scanner;

import domain.customer.AdditionalInfo;
import domain.customer.Customer;
import exception.InvalidInputException;
import service.customerInfo.SignUpService;
import service.customerInfo.SignUpServiceImpl;
import view.viewUtility.ScannerUtility;

public class SignUpView {
    private final Scanner scanner = ScannerUtility.getScanner();
	
	// Service
	private SignUpService signUpService = SignUpServiceImpl.getInstance();
	
	public void show() {
		try {
			System.out.println("\n[ ȸ������ ]");
	        System.out.print("�̸� >> "); String name = getInputAndCheckInvalid();
	        System.out.print("�̸��� >> "); String email = getInputAndCheckInvalid();
	        if(signUpService.isEmailDuplicated(email)) { System.out.println("�̹� �����ϴ� �̸����Դϴ�."); return; }
	        System.out.print("��й�ȣ >> "); String password = getInputAndCheckInvalid();
	        System.out.print("���� >> "); String age = getInputAndCheckInvalid();
	        boolean finished = false;
	        boolean gender = false;
	        while(!finished) {
	            System.out.print("���� ����(1), ����(2) >> ");
	            switch(getInputAndCheckInvalid()) {
					case "1": gender = true; finished = true; break;
					case "2": gender = false; finished = true; break;
					default: System.out.println("������ ���� �Է��� �ùٸ��� �ʽ��ϴ�."); break;
				};
	        }
	        System.out.print("�ֹε�Ϲ�ȣ ���ڸ� >> "); String registrationNo = getInputAndCheckInvalid();
			System.out.print("�ֹε�Ϲ�ȣ ���ڸ� >> "); registrationNo += ("-" + getInputAndCheckInvalid());
	        if(signUpService.isRegistrationNoDuplicated(registrationNo)) { System.out.println("�̹� �����ϴ� �ֹε�Ϲ�ȣ�Դϴ�."); return; }
	        System.out.print("��ȭ��ȣ ex.01012345678 >> "); String phoneNo = getInputAndCheckInvalid();
	        if(signUpService.isPhoneNoDuplicated(phoneNo)) { System.out.println("�̹� �����ϴ� ��ȭ��ȣ�Դϴ�."); return; }
	        System.out.print("���� >> "); String bank = getInputAndCheckInvalid();
	        System.out.print("���¹�ȣ >> "); String accountNo = getInputAndCheckInvalid();
	        finished = false;
	        boolean isMarried = false;
	        while(!finished) {
	            System.out.print("��ȥ���� ��ȥ(1) ��ȥ(2) >> ");
	            switch(getInputAndCheckInvalid()) {
					case "1": isMarried = true; finished = true; break;
					case "2": isMarried = false; finished = true; break;
					default: System.out.println("��ȥ���ο� ���� �Է��� �ùٸ��� �ʽ��ϴ�."); break;
	            };
	        }
	        System.out.print("����(��) >> "); String carPrice = getInputAndCheckInvalid();
	        System.out.print("����(��) >> "); String housePrice = getInputAndCheckInvalid();
	        System.out.print("�������(��) >> "); String drivingCareer = getInputAndCheckInvalid();
	        System.out.print("���ڰ���(��) >> "); String shipPrice = getInputAndCheckInvalid();
	        System.out.print("\nȸ�������Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	        	signUp(name, email, password, age, gender, registrationNo, phoneNo, bank, accountNo, isMarried, carPrice, housePrice, drivingCareer, shipPrice);
	        } else {
	        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
	        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
	        		if(input.equals("1")) return;signUp(name, email, password, age, gender, registrationNo, phoneNo, bank, accountNo, isMarried, carPrice, housePrice, drivingCareer, shipPrice);
	        }
		} catch (InvalidInputException invalidInputException) {
			System.out.println(invalidInputException.getMessage());
		}
	}
	
	private void signUp(String name, String email, String password, String age, boolean gender, String registrationNo, String phoneNo, String bank, String accountNo, boolean isMarried, String carPrice, String housePrice, String drivingCareer, String shipPrice) {
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
	        customer.setAccountNo(bank + " " + accountNo);
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

    // ȸ�� ���� ���� �Է� ���θ� üũ�Ѵ�
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// ����
		if(input.equals("")) {
			throw new InvalidInputException("�Է°��� �����ϴ�.");
		}
		return input;
	}
}
