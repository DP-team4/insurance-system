package view.customer;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class CustomerMenuView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
	private boolean logined; // �α��� ����
	private Customer customer; // �α��� �� ������ (�α��� �ȵ� ��� null)
	
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
		applyClaim("����� û��", "1"),
		applyConsultation("���� ��� ��û", "2"),
		applyCoverage("���ó�� ��û", "3"),
		cancelContract("��� ���� ��û", "4"),
		showInsurances("�����ǰ ��ȸ", "5"),
		manageContracts("���� �����ǰ ����", "6"),
		payPremium("����� ����", "7"),
		exit("���α׷� ����", "x");
		
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
		System.out.println("\n[ �α׾ƿ� ]");
		this.customer = null;
		this.logined = false;
		System.out.println("�α׾ƿ� �Ϸ�.");
	}
}
