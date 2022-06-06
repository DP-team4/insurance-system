package view.customer;

import java.util.Scanner;

import domain.customer.Customer;
import view.customer.benefitPaymentManagement.CustomerBenefitPaymentManageView;
import view.customer.carAccidentHandlingManagement.CustomerCarAccidentHandlingApplyView;
import view.customer.carAccidentHandlingManagement.CustomerCarAccidentHandlingManageView;
import view.customer.consultApplicationManagement.CustomerConsultApplicationManageView;
import view.customer.contractManagement.CustomerContractManageView;
import view.customer.customerInfo.LoginView;
import view.customer.customerInfo.SignUpView;
import view.viewUtility.ScannerUtility;

public class CustomerHomeView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
	private boolean logined; // 로그인 여부
	private Customer customer; // 로그인 된 고객정보 (로그인 안된 경우 null)
	
	// Views
	private CustomerBenefitPaymentManageView customerBenefitPaymentManageView = new CustomerBenefitPaymentManageView();
	private CustomerConsultApplicationManageView customerConsultApplicationManageView = new CustomerConsultApplicationManageView();
	private CustomerCarAccidentHandlingManageView customerCarAccidentHandlingManageView = new CustomerCarAccidentHandlingManageView();
	private CustomerInsuranceListView customerInsuranceListView = new CustomerInsuranceListView();
	private CustomerContractManageView customerContractManageView = new CustomerContractManageView();
	private LoginView loginView = new LoginView();
	private SignUpView signUpView = new SignUpView();
	
	private enum EMenu {
		insuranceList("보험상품 조회", "1"),
		consultApplicationManagement("가입 상담 신청 관리", "2"),
		contractsManagement("나의 보험상품 관리 및 계약 해지", "3"),
		carAccidentHandlingManagement("사고처리 신청 관리", "4"),
		benefitPaymentManagement("보험금 청구 관리", "5"),
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
				case "1": showInsurances(); break;
				case "2": manageConsultApplication(); break;
				case "3": manageContracts(); break;
				case "4": manageCarAccidentHandling(); break;
				case "5": manageBenefitPayment(); break;
				case "l":
					if(!logined) login();
					else logout();
					break;
				case "s":
					if(!logined) { signUpView.show(); break; }
				default: exit = true; break;
			}
		}
	}

	private void showInsurances() {
		customerInsuranceListView.show();
	}

	private void manageConsultApplication() {
		if(checkLogin()) customerConsultApplicationManageView.show(customer);
	}

	private void manageContracts() {
		if(checkLogin()) customerContractManageView.show(customer);
	}
	
	private void manageCarAccidentHandling() {
		if(checkLogin()) customerCarAccidentHandlingManageView.show(customer);
	}

	private void manageBenefitPayment() {
		if(checkLogin()) customerBenefitPaymentManageView.show(customer);
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
