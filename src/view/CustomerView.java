package view;

import java.util.Scanner;

import domain.customer.Customer;
import service.InsuranceService;
import service.LoginServiceImpl;

public class CustomerView {
	private Scanner scanner;
	private boolean logined; // �α��� ����
	private Customer customer; // �α��� �� ������ (�α��� �ȵ� ��� null)

	private LoginServiceImpl loginService = LoginServiceImpl.getInstance();
	private InsuranceService insuranceService = new InsuranceService();
	
	private enum EMenu {
		applyClaim("����� û��", "1"),
		applyConsultation("���� ��� ��û", "2"),
		applyCoverage("���ó�� ��û", "3"),
		cancelContract("��� ���� ��û", "4"),
		showInsurances("�����ǰ ��ȸ", "5"),
		manageContracts("�����ǰ ����", "6"),
		payPremium("����� ����", "7"),
		exit("���α׷� ����", "x");
		
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
		System.out.println("\n[ �޴� ]");
		for(EMenu menu: EMenu.values()) {
			System.out.print(menu.getString() + "(" + menu.getKey() + ") ");
		}
		if(!logined) System.out.print("�α���(l) ");
		else System.out.print("�α׾ƿ�(l) ");
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
			System.out.println("\n[ �α��� ]");
			System.out.print("���̵�(�̸���) >> "); String email = scanner.nextLine().trim();
			System.out.print("��й�ȣ >> "); String password = scanner.nextLine().trim();
			customer = loginService .login(email, password);
			if(customer == null) {
				System.out.print("�α��� ����. �޴��� ���ư���(m) �ٽ� �õ�(��Ÿ)>> ");
				if(scanner.nextLine().trim().equals("m")) goMenu = true;
			} else {
				System.out.println("�α��� ����."); logined = true;
			}
		}
		showMenu();
	}
	
	private void showLogoutView() {
		System.out.println("\n[ �α׾ƿ� ]");
		customer = null;
		System.out.println("�α׾ƿ� �Ϸ�."); logined = false; showMenu();
	}

	// ����� û��
	private void showApplyClaimView() {
		if(!logined) {
			System.out.println("�� ���񽺸� �̿��ϱ� ���ؼ��� �α����� �ʿ��մϴ�."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.applyClaim.getString() + " ]");
		System.out.println("�̱��� ����Դϴ�. �޴��� ���ư��ϴ�."); showMenu();
	}
	
	// ���� ��� ��û
	private void showApplyConsultationView() {
		System.out.println("\n[ " + EMenu.applyConsultation.getString() + " ]");
		System.out.println("�̱��� ����Դϴ�. �޴��� ���ư��ϴ�."); showMenu();
	}
	
	// ���ó�� ��û
	private void showApplyCoverageView() {
		if(!logined) {
			System.out.println("�� ���񽺸� �̿��ϱ� ���ؼ��� �α����� �ʿ��մϴ�."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.applyCoverage.getString() + " ]");
		System.out.println("�̱��� ����Դϴ�. �޴��� ���ư��ϴ�."); showMenu();
	}
	
	// ��� ���� ��û
	private void showCancelContractView() {
		if(!logined) {
			System.out.println("�� ���񽺸� �̿��ϱ� ���ؼ��� �α����� �ʿ��մϴ�."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.cancelContract.getString() + " ]");
		System.out.println("�̱��� ����Դϴ�. �޴��� ���ư��ϴ�."); showMenu();
	}
	
	// �����ǰ ��ȸ
	private void showInsurancesView() {
		System.out.println("\n[ " + EMenu.showInsurances.getString() + " ]");
		insuranceService.getAllInsurances();
		System.out.print("�޴� ����(m) ����(��Ÿ)>> ");
		if(scanner.nextLine().trim().equals("m")) showMenu();
		else System.out.println("���α׷� ����.");
	}
	
	// �����ǰ ����
	private void showManageContractsView() {
		if(!logined) {
			System.out.println("�� ���񽺸� �̿��ϱ� ���ؼ��� �α����� �ʿ��մϴ�."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.manageContracts.getString() + " ]");
		System.out.println("�̱��� ����Դϴ�. �޴��� ���ư��ϴ�."); showMenu();
	}
	
	// ����� ����
	private void showPayPremiumView() {
		if(!logined) {
			System.out.println("�� ���񽺸� �̿��ϱ� ���ؼ��� �α����� �ʿ��մϴ�."); showLoginView();
		}
		System.out.println("\n[ " + EMenu.payPremium.getString() + " ]");
		System.out.println("�̱��� ����Դϴ�. �޴��� ���ư��ϴ�."); showMenu();
	}
}
