package view;

import java.util.Scanner;

import domain.customer.Customer;
import service.InsuranceService;
import service.LoginServiceImpl;

public class CustomerView {
	private Scanner scanner;
	private boolean logined; // 로그인 여부
	private Customer customer; // 로그인 된 고객정보 (로그인 안된 경우 null)

	private LoginServiceImpl loginService = LoginServiceImpl.getInstance();
	private InsuranceService insuranceService = new InsuranceService();
	
	private enum EMenu {
		applyClaim("보험금 청구", "1"),
		applyConsultation("가입 상담 신청", "2"),
		applyCoverage("사고처리 신청", "3"),
		cancelContract("계약 해지 신청", "4"),
		showInsurances("보험상품 조회", "5"),
		manageContracts("보험상품 관리", "6"),
		payPremium("보험료 납부", "7"),
		exit("프로그램 종료", "x");
		
		private String string;
		private String key;
		
		private EMenu(String string, String key) { this.string = string; this.key = key; }
		public String getString() { return this.string; }
		public String getKey() { return this.key; }
	};

	public CustomerView(Scanner scanner) {
		this.scanner = scanner;
	}

	public void showView() {
		showMenu();
	}

	private void showMenu() {
		System.out.println("\n[ 메뉴 ]");
		for(EMenu menu: EMenu.values()) {
			System.out.print(menu.getString() + "(" + menu.getKey() + ") ");
		}
		if(!logined) System.out.print("로그인(l) ");
		else System.out.print("로그아웃(l) ");
        System.out.print(">> ");
		switch(scanner.nextLine().trim()) {
			case "1": showApplyClaimView(); break;
			case "2": showApplyConsultationView(); break;
			case "3": showApplyCoverageView(); break;
			case "4": showCancelContractView(); break;
			case "5": showInsurancesView(); break;
			case "6": showManageContractsView(); break;
			case "7": showPayPremiumView(); break;
			case "x": break;
			case "l":
				if(!logined) showLoginView();
				else showLogoutView();
				break;
			default: System.out.println("Invalid Option"); break;
		}
	}

	private void showLoginView() {
		boolean goMenu = false;
		while(!logined && !goMenu) {
			System.out.println("\n[ 로그인 ]");
			System.out.print("아이디(이메일) >> "); String email = scanner.nextLine().trim();
			System.out.print("비밀번호 >> "); String password = scanner.nextLine().trim();
			customer = loginService .login(email, password);
			if(customer == null) {
				System.out.print("로그인 실패. 메뉴로 돌아가기(m) 다시 시도(기타)>> ");
				if(scanner.nextLine().trim().equals("m")) goMenu = true;
			} else {
				System.out.println("로그인 성공."); logined = true;
			}
		}
		showMenu();
	}
	
	private void showLogoutView() {
		System.out.println("\n[ 로그아웃 ]");
		customer = null;
		System.out.println("로그아웃 완료."); logined = false; showMenu();
	}

	// 보험금 청구
	private void showApplyClaimView() {
		if(!logined) {
			System.out.println("이 서비스를 이용하기 위해서는 로그인이 필요합니다."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.applyClaim.getString() + " ]");
		System.out.println("미구현 기능입니다. 메뉴로 돌아갑니다."); showMenu();
	}
	
	// 가입 상담 신청
	private void showApplyConsultationView() {
		System.out.println("\n[ " + EMenu.applyConsultation.getString() + " ]");
		System.out.println("미구현 기능입니다. 메뉴로 돌아갑니다."); showMenu();
	}
	
	// 사고처리 신청
	private void showApplyCoverageView() {
		if(!logined) {
			System.out.println("이 서비스를 이용하기 위해서는 로그인이 필요합니다."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.applyCoverage.getString() + " ]");
		System.out.println("미구현 기능입니다. 메뉴로 돌아갑니다."); showMenu();
	}
	
	// 계약 해지 신청
	private void showCancelContractView() {
		if(!logined) {
			System.out.println("이 서비스를 이용하기 위해서는 로그인이 필요합니다."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.cancelContract.getString() + " ]");
		System.out.println("미구현 기능입니다. 메뉴로 돌아갑니다."); showMenu();
	}
	
	// 보험상품 조회
	private void showInsurancesView() {
		System.out.println("\n[ " + EMenu.showInsurances.getString() + " ]");
		insuranceService.getAllInsurances();
		System.out.print("메뉴 보기(m) 종료(기타)>> ");
		if(scanner.nextLine().trim().equals("m")) showMenu();
		else System.out.println("프로그램 종료.");
	}
	
	// 보험상품 관리
	private void showManageContractsView() {
		if(!logined) {
			System.out.println("이 서비스를 이용하기 위해서는 로그인이 필요합니다."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.manageContracts.getString() + " ]");
		System.out.println("미구현 기능입니다. 메뉴로 돌아갑니다."); showMenu();
	}
	
	// 보험료 납부
	private void showPayPremiumView() {
		if(!logined) {
			System.out.println("이 서비스를 이용하기 위해서는 로그인이 필요합니다."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.payPremium.getString() + " ]");
		System.out.println("미구현 기능입니다. 메뉴로 돌아갑니다."); showMenu();
	}
}
