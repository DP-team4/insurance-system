package view.customer;

import java.util.Scanner;

import domain.customer.Customer;
import view.customer.consultationManagement.ConsultationManagementView;
import view.customer.contractManagement.ContractManagementView;
import view.viewUtility.ScannerUtility;

public class CustomerHomeView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
	private boolean logined; // 로그인 여부
	private Customer customer; // 로그인 된 고객정보 (로그인 안된 경우 null)
	
	// Views
	private ClaimApplicationView claimApplicationView = new ClaimApplicationView();
	private ConsultationManagementView consultationManagementView = new ConsultationManagementView();
	private CoverageApplicationView coverageApplicationView = new CoverageApplicationView();
	private InsuranceListView insuranceListView = new InsuranceListView();
	private ContractManagementView contractsManagementView = new ContractManagementView();
	private LoginView loginView = new LoginView();
	private SignUpView signUpView = new SignUpView();
	
	private enum EMenu {
		insuranceList("보험상품 조회", "1"),
		consultationManagement("가입 상담 신청 관리", "2"),
		contractsManagement("나의 보험상품 관리 및 계약 해지", "3"),
		coverageApplication("사고처리 신청", "4"),
		claimApplication("보험금 청구", "5"),
		exit("프로그램 종료", "기타");
		
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
	        System.out.print("\n>> ");
			switch(scanner.nextLine().trim()) {
				case "1": showInsurances(); break; // 완료
				case "2": manageConsultation(); break; // Date 이상할 때 Exception 처리
				case "3": manageContracts(); break; // 완료
				case "4": applyCoverage(); break;
				case "5": applyClaim(); break;
				case "l": // 입력값 체크
					if(!logined) login();
					else logout();
					break;
				case "s": // 입력값 체크, 아이디(이메일) 중복 확인
					if(!logined) { signUpView.show(); break; }
				default: exit = true; break;
			}
		}
	}

	private void showInsurances() {
		insuranceListView.show();
	}

	private void manageConsultation() {
		if(checkLogin()) consultationManagementView.show(customer);
	}

	private void manageContracts() {
		if(checkLogin()) contractsManagementView.show(customer);
	}
	
	private void applyCoverage() {
		if(checkLogin()) coverageApplicationView.show();
	}

	private void applyClaim() {
		if(checkLogin()) claimApplicationView.show();
	}
	
	private boolean checkLogin() {
		// 로그인 여부를 확인한다
		if(!logined) {
			// A?. 로그인이 되어있지 않은 경우
			System.out.println("로그인이 필요한 기능입니다."); return login();
		}
		return true;
	}

	private boolean login() {
		Customer customer = loginView.show();
		if(customer != null) {
			this.customer = customer;
			this.logined = true;
		}
		return this.logined;
	}

	private void logout() {
		System.out.println("\n[ 로그아웃 ]");
		this.customer = null;
		this.logined = false;
		System.out.println("로그아웃 완료.");
	}
}
