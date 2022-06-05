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
				System.out.println("\n[ 로그인 ]");
				System.out.print("이메일 >> "); String email = getInputAndCheckInvalid();
				System.out.print("비밀번호 >> "); String password = getInputAndCheckInvalid();
				Customer customer = loginService.login(email, password);
				if(customer == null) {
					System.out.println("로그인 실패. 메뉴로 돌아가기(m) 다시 시도(기타)");
					System.out.print(">> ");
					if(scanner.nextLine().trim().equals("m")) return null;
				} else { System.out.println("로그인 성공."); return customer; }
			}
		} catch (InvalidInputException invalidInputException) {
        	// 유효하지 않은 값을 입력한 경우
            System.out.println(invalidInputException.getMessage());
		} return null;
	}

    // 로그인 내용 입력 여부를 체크한다
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// 공백
		if(input.equals("")) {
			throw new InvalidInputException("입력값이 없습니다.");
		}
		return input;
	}
}
