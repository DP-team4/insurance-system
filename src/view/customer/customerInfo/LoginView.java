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
			System.out.println("\n[ 로그인 ]");
			System.out.print("이메일 >> "); String email = scanner.nextLine().trim();
			System.out.print("비밀번호 >> "); String password = scanner.nextLine().trim();
			Customer customer = loginService.login(email, password);
			if(customer == null) {
				System.out.print("로그인 실패. 메뉴로 돌아가기(m) 다시 시도(기타)>> ");
				if(scanner.nextLine().trim().equals("m")) return null;
			} else {
				System.out.println("로그인 성공."); return customer;
			}
		}
	}
}
