package view.customer;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class CustomerMenuView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
	private boolean logined; // 로그인 여부
	private Customer customer; // 로그인 된 고객정보 (로그인 안된 경우 null)
	
	// Views
	private ApplyClaimView applyClaimView = new ApplyClaimView();
	private ApplyConsultationView applyConsultationView = new ApplyConsultationView();
	private ApplyCoverageView applyCoverageView = new ApplyCoverageView();
	private CancelContractView cancelContractView = new CancelContractView();
	private ShowInsurancesView showInsurancesView = new ShowInsurancesView();
	private ManageContractsView manageContractsView = new ManageContractsView();
	private PayPremiumView payPremiumView = new PayPremiumView();
	private LoginView loginView = new LoginView();
	private SignUpView signUpView = new SignUpView();
	
	private enum EMenu {
		applyClaim("보험금 청구", "1"),
		applyConsultation("가입 상담 신청", "2"),
		applyCoverage("사고처리 신청", "3"),
		cancelContract("계약 해지 신청", "4"),
		showInsurances("보험상품 조회", "5"),
		manageContracts("나의 보험상품 관리", "6"),
		payPremium("보험료 납부", "7"),
		exit("프로그램 종료", "x");
		
		private String string;
		private String key;
		
		private EMenu(String string, String key) { this.string = string; this.key = key; }
		public String getString() { return this.string; }
		public String getKey() { return this.key; }
	};

	public void show() {
		boolean exit = false;
		while(!exit) {
			System.out.println("\n[ 메뉴 ]");
			for(EMenu menu: EMenu.values()) {
				System.out.print(menu.getString() + "(" + menu.getKey() + ") ");
			}
			if(!logined) System.out.print("로그인(l) 회원가입(s)");
			else System.out.print("로그아웃(l) ");
	        System.out.print(">> ");
			switch(scanner.nextLine().trim()) {
				case "1": applyClaimView.show(); break;
				case "2": applyConsultationView.show(customer); break;
				case "3": applyCoverageView.show(); break;
				case "4": cancelContractView.show(); break;
				case "5": showInsurancesView.show(); break;
				case "6": manageContractsView.show(); break;
				case "7": payPremiumView.show(); break;
				case "x": exit = true; break;
				case "l":
					if(!logined) login();
					else logout();
					break;
				case "s":
					if(!logined) { signUpView.show(); break; }
				default: System.out.println("Invalid Option"); break;
			}
		}
	}
	
	private void login() {
		Customer customer = loginView.show();
		if(customer != null) {
			this.customer = customer;
			this.logined = true;
		}
	}

	private void logout() {
		System.out.println("\n[ 로그아웃 ]");
		this.customer = null;
		this.logined = false;
		System.out.println("로그아웃 완료.");
	}
}
