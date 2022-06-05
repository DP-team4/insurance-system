package view.salesManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
import domain.uw.UWDocument;
import service.SalesService;
import service.SalesServiceImpl;
import view.viewUtility.ScannerUtility;

public class SalesActivityView {
	
	private final Scanner scanner = ScannerUtility.getScanner();
	private final SalesService salesService = SalesServiceImpl.getInstance();
	private Insurance insurance;
	private Customer customer;
	private int totalPaymentMonth;
	
	public SalesActivityView() {}

	public void show() {
		while(true) {
			System.out.println("\n================����Ȱ�� ȭ��================");
			this.showInsurance();
			this.showCustomer();
			// �� ���Է�, ����ݾ�
			this.showInsurancePremiumAndInsuredAmount();
			System.out.println();
			System.out.println("�����Ͻðڽ��ϱ�? �� (1) �ƴϿ� (2)");
			String input = this.scanner.nextLine().trim();
			if(input.equals("1")) {
				this.showRequestUW(this.insurance.getId(), this.customer.getId());
				break;
			} else if(input.equals("2")) {
				break;
			} else {
				System.out.println("�߸��� �Է°��Դϴ�."); 
				break;
			}
		}
	}

	private void showInsurancePremiumAndInsuredAmount() {
		System.out.println();
		System.out.println("�ѳ��Կ��� �Է��Ͻÿ�. ex) 12, 24");
		System.out.print(">> ");
		this.totalPaymentMonth = Integer.parseInt(this.scanner.nextLine().trim());
		
		System.out.println();
		System.out.println("[���� ���� ����]");
		System.out.println("���� �̸�: " + this.insurance.getName());
		long insurancePremium = this.salesService.getInsurancePremium(this.insurance, this.customer, totalPaymentMonth);
		long insuredAmount = this.salesService.getInsuredAmount(insurance, customer);
		System.out.println("�� ���Է�: " + insurancePremium);
		System.out.println("���� �ݾ�: " + insuredAmount);
	}

	private void showRequestUW(String insuranceId, String customerId) {
		System.out.println();
		System.out.println("[�μ��ɻ� ��û]");
		Contract contract = new Contract();
		contract.setInsuranceId(insuranceId);
		contract.setCustomerId(customerId);
		contract.setContractDateTime(LocalDateTime.now());
		contract.setExpirationDateTime(LocalDateTime.now().plusMonths(this.totalPaymentMonth));
		
		// ���� ���� ÷��
		ArrayList<UWDocument> documents = new ArrayList<>();
		while(true) {
			System.out.println("�μ� �ɻ� ���� ������ ÷���Ͻÿ�.");
			UWDocument uwDocument = new UWDocument();
			System.out.print("�̸�: ");
			String name = scanner.nextLine().trim();
			uwDocument.setName(name);
			System.out.print("���� ���: ");
			String filePath = scanner.nextLine().trim();
			uwDocument.setPath(filePath);	
			documents.add(uwDocument);
			
			System.out.println("������ �� ÷���Ͻðڽ��ϱ�? �� (1) �ƴϿ�(������)");
			String input = this.scanner.nextLine().trim();
			if(!input.equals(1)) {
				break;
			}
		}
		// �μ��ɻ� ��û
		boolean result = this.salesService.requestUW(contract, documents);
		if(result) {
			System.out.println("�μ� �ɻ� ������ �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println("�μ� �ɻ� ������ �����Ͽ����ϴ�.");
		}
		
	}

	private void showInsurance() {
		System.out.println("���� �̸��� �Է��Ͻÿ�.");
		System.out.print(">> ");
		String input = this.scanner.nextLine().trim();
		Insurance insurance = this.salesService.getInsuranceByName(input);
		System.out.println("[���� ����]");
		System.out.println(insurance);
		this.insurance = insurance;
	}

	private void showCustomer() {
		System.out.println();
		String email;
		// �̸�, ����, ������ �� ã�� => �̸���
		System.out.println("�� �̸����� �Է��Ͻÿ�.");
		System.out.print(">> ");
		email = (this.scanner.nextLine().trim());

		Customer customer = this.salesService.getCustomerByEmail(email);
		System.out.println("[�� ����]");
		System.out.println(customer.toStringBySecurity());
		this.customer = customer;
	}

}
