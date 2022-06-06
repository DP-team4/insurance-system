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
    
	private boolean logined; // �α��� ����
	private Customer customer; // �α��� �� ������ (�α��� �ȵ� ��� null)
	
	// Views
	private CustomerBenefitPaymentManageView customerBenefitPaymentManageView = new CustomerBenefitPaymentManageView();
	private CustomerConsultApplicationManageView customerConsultApplicationManageView = new CustomerConsultApplicationManageView();
	private CustomerCarAccidentHandlingManageView customerCarAccidentHandlingManageView = new CustomerCarAccidentHandlingManageView();
	private CustomerInsuranceListView customerInsuranceListView = new CustomerInsuranceListView();
	private CustomerContractManageView customerContractManageView = new CustomerContractManageView();
	private LoginView loginView = new LoginView();
	private SignUpView signUpView = new SignUpView();
	
	private enum EMenu {
		insuranceList("�����ǰ ��ȸ", "1"),
		consultApplicationManagement("���� ��� ��û ����", "2"),
		contractsManagement("���� �����ǰ ���� �� ��� ����", "3"),
		carAccidentHandlingManagement("���ó�� ��û ����", "4"),
		benefitPaymentManagement("����� û�� ����", "5"),
		exit("���α׷� ����", "��Ÿ");
		
		private String string;
		private String key;
		
		private EMenu(String string, String key) { this.string = string; this.key = key; }
		public String getString() { return this.string; }
		public String getKey() { return this.key; }
	};

	public void show() {
		boolean exit = false;
		while(!exit) {
			System.out.println("\n[ �޴� ]");
			for(EMenu menu: EMenu.values()) {
				System.out.print(menu.getString() + "(" + menu.getKey() + ") ");
			}
			if(!logined) System.out.print("�α���(l) ȸ������(s)");
			else System.out.print("�α׾ƿ�(l) ");
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
		// �α��� ���θ� Ȯ���Ѵ�
		if(!logined) {
			// A?. �α����� �Ǿ����� ���� ���
			System.out.println("�α����� �ʿ��� ����Դϴ�."); return login();
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
		System.out.println("\n[ �α׾ƿ� ]");
		this.customer = null;
		this.logined = false;
		System.out.println("�α׾ƿ� �Ϸ�.");
	}
}
