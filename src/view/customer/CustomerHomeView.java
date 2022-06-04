package view.customer;

import java.util.Scanner;

import domain.customer.Customer;
import view.customer.consultationManagement.ConsultationManagementView;
import view.customer.contractManagement.ContractManagementView;
import view.viewUtility.ScannerUtility;

public class CustomerHomeView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
	private boolean logined; // �α��� ����
	private Customer customer; // �α��� �� ������ (�α��� �ȵ� ��� null)
	
	// Views
	private ClaimApplicationView claimApplicationView = new ClaimApplicationView();
	private ConsultationManagementView consultationManagementView = new ConsultationManagementView();
	private CoverageApplicationView coverageApplicationView = new CoverageApplicationView();
	private InsuranceListView insuranceListView = new InsuranceListView();
	private ContractManagementView contractsManagementView = new ContractManagementView();
	private LoginView loginView = new LoginView();
	private SignUpView signUpView = new SignUpView();
	
	private enum EMenu {
		insuranceList("�����ǰ ��ȸ", "1"),
		consultationManagement("���� ��� ��û ����", "2"),
		contractsManagement("���� �����ǰ ���� �� ��� ����", "3"),
		coverageApplication("���ó�� ��û", "4"),
		claimApplication("����� û��", "5"),
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
				case "1": showInsurances(); break; // �Ϸ�
				case "2": manageConsultation(); break; // Date �̻��� �� Exception ó��
				case "3": manageContracts(); break; // �Ϸ�
				case "4": applyCoverage(); break;
				case "5": applyClaim(); break;
				case "l": // �Է°� üũ
					if(!logined) login();
					else logout();
					break;
				case "s": // �Է°� üũ, ���̵�(�̸���) �ߺ� Ȯ��
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
