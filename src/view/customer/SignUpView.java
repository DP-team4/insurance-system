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
		System.out.println("\n[ 회원가입 ]");
        try {
	        System.out.print("이름 >> "); customer.setName(scanner.nextLine().trim());
	        System.out.print("이메일 >> "); customer.setEmail(scanner.nextLine().trim());
	        System.out.print("비밀번호 >> "); customer.setPassword(scanner.nextLine().trim());
	        System.out.print("나이 >> "); customer.setAge(Integer.parseInt(scanner.nextLine().trim()));
	        boolean finished = false;
	        while(!finished) {
	            System.out.print("성별 남성(1), 여성(2) >> ");
	            input = scanner.nextLine().trim();
	            switch(input) {
					case "1": customer.setGender(true); finished = true; break;
					case "2": customer.setGender(false); finished = true; break;
					default: System.out.println("성별에 대한 입력이 올바르지 않습니다. 입력: " + input); break;
				};
	        }
	        System.out.print("주민등록번호 앞자리 >> "); input = scanner.nextLine().trim();
			System.out.print("주민등록번호 뒷자리 >> "); customer.setRegistrationNo(input + "-" + scanner.nextLine().trim());
	        System.out.print("전화번호 ex.01012345678 >> "); customer.setPhoneNo(scanner.nextLine().trim());
	        System.out.print("계좌번호 >> "); customer.setAccountNo(scanner.nextLine().trim());
	        finished = false;
	        while(!finished) {
	            System.out.print("결혼여부 기혼(1) 미혼(2) >> ");
	            input = scanner.nextLine().trim();
	            switch(input) {
					case "1": customer.setMarried(true); finished = true; break;
					case "2": customer.setMarried(false); finished = true; break;
					default: System.out.println("결혼여부에 대한 입력이 올바르지 않습니다. 입력: " + input); break;
	            };
	        }
	        AdditionalInfo additionalinfo = customer.getAdditionalInfo();
	        System.out.print("차값(원) >> "); additionalinfo.setCarPrice(Long.parseLong(scanner.nextLine().trim()));
	        System.out.print("집값(원) >> "); additionalinfo.setHousePrice(Long.parseLong(scanner.nextLine().trim()));
	        System.out.print("운전경력(년) >> "); additionalinfo.setDrivingCareer(Integer.parseInt(scanner.nextLine().trim()));
	        System.out.print("선박가격(원) >> "); additionalinfo.setShipPrice(Long.parseLong(scanner.nextLine().trim()));
			if(signUpService.signUp(customer)) System.out.println("회원가입 성공. 메뉴로 돌아갑니다.");
			else System.out.println("회원가입 실패. 메뉴로 돌아갑니다.");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        }
	}
}
