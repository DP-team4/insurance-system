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
		System.out.println("\n[ 회원가입 ]");
        System.out.print("이름 >> "); String name = scanner.nextLine().trim();
        System.out.print("이메일 >> "); String email = scanner.nextLine().trim();
        System.out.print("비밀번호 >> "); String password = scanner.nextLine().trim();
        System.out.print("나이 >> "); String age = scanner.nextLine().trim();
        boolean finished = false;
        boolean gender = false;
        while(!finished) {
            System.out.print("성별 남성(1), 여성(2) >> ");
            switch(scanner.nextLine().trim()) {
				case "1": gender = true; finished = true; break;
				case "2": gender = false; finished = true; break;
				default: System.out.println("성별에 대한 입력이 올바르지 않습니다."); break;
			};
        }
        System.out.print("주민등록번호 앞자리 >> "); String registrationNo = scanner.nextLine().trim();
		System.out.print("주민등록번호 뒷자리 >> "); registrationNo += ("-" + scanner.nextLine().trim());
        System.out.print("전화번호 ex.01012345678 >> "); String phoneNo = scanner.nextLine().trim();
        System.out.print("계좌번호 >> "); String accountNo = scanner.nextLine().trim();
        finished = false;
        boolean isMarried = false;
        while(!finished) {
            System.out.print("결혼여부 기혼(1) 미혼(2) >> ");
            switch(scanner.nextLine().trim()) {
				case "1": isMarried = true; finished = true; break;
				case "2": isMarried = false; finished = true; break;
				default: System.out.println("결혼여부에 대한 입력이 올바르지 않습니다."); break;
            };
        }
        System.out.print("차값(원) >> "); String carPrice = scanner.nextLine().trim();
        System.out.print("집값(원) >> "); String housePrice = scanner.nextLine().trim();
        System.out.print("운전경력(년) >> "); String drivingCareer = scanner.nextLine().trim();
        System.out.print("선박가격(원) >> "); String shipPrice = scanner.nextLine().trim();
        System.out.print("\n회원가입하시겠습니까? 예(1) 아니오(기타) >> "); String input = scanner.nextLine().trim();
        // 회원 가입 내용 입력 여부를 체크한다
        boolean blankExist = name.equals("") || email.equals("") || password.equals("") || age.equals("")
        		|| registrationNo.equals("") || phoneNo.equals("") || accountNo.equals("")
        		|| carPrice.equals("") || housePrice.equals("") || drivingCareer.equals("") || shipPrice.equals("");
        if(input.equals("1")) {
            // A1. 상담 신청 양식을 덜 입력한 경우
        	if(blankExist) { System.out.println("\n신청 양식을 전부 입력해주세요."); return; }signUp(name, email, password, age, gender, registrationNo, phoneNo, accountNo, isMarried, carPrice, housePrice, drivingCareer, shipPrice);
        } else {
        	// A2. 내용을 모두 입력한 후 취소 버튼을 클릭할 경우
        	if(!blankExist) {
        		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
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
			if(signUpService.signUp(customer)) System.out.println("회원가입 성공. 메뉴로 돌아갑니다.");
			else System.out.println("회원가입 실패. 메뉴로 돌아갑니다.");
        } catch (NumberFormatException e) {
        	// 유효하지 않은 값을 입력한 경우
            System.out.println("잘못된 입력입니다.");
        }
	}
}
