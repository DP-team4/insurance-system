package view.sales;

import java.time.LocalDateTime;
import java.util.Scanner;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
import service.SalesService;

public class SalesActivityView {
	
	Scanner scanner;
	SalesService salesService;

	public SalesActivityView(Scanner scanner) {
		this.scanner = scanner;
		this.salesService = new SalesService();
	}

	public void showView() {
		while(true) {
			System.out.println("\n================����Ȱ�� ȭ��================");
			String insuranceId = this.showInsurance();
			String customerId = this.showCustomer();
			// ���� ���� ���� �� �ݾ� ���
			
			System.out.println("�����Ͻðڽ��ϱ�? �� (1) �ƴϿ� (2)");
			String input = this.scanner.nextLine().trim();
			if(input.equals("1")) {
				this.showRequestUW(insuranceId, customerId);
			} else if(input.equals("2")) {
				break;
			} else {
				System.out.println("�߸��� �Է°��Դϴ�."); break;
			}
		}
	}

	private void showRequestUW(String insuranceId, String customerId) {
		Contract contract = new Contract();
		contract.setInsuranceId(insuranceId);
		contract.setCustomerId(customerId);
		contract.setContractDateTime(LocalDateTime.now());
		contract.setExpirationDateTime(LocalDateTime.now().plusDays(10));
		
		// ���� ���� ÷��
		String name;
		String file;
		System.out.println("�μ� �ɻ� ���� ������ ÷���Ͻÿ�.");
		System.out.println("�̸�: ");
		name = scanner.nextLine().trim();
		System.out.println("���� ���: ");
		file = scanner.nextLine().trim();
		// �μ��ɻ� ��û ?
		this.salesService.requestUW(contract);
		
	}

	private String showInsurance() {
		System.out.println("���� �̸��� �Է��Ͻÿ�.");
		System.out.print(">> ");
		String input = this.scanner.nextLine().trim();
		Insurance insurance = this.salesService.requestInsurance(input);
		System.out.println("[���� ����]");
		System.out.println(insurance);
		return insurance.getId();
	}

	private String showCustomer() {
		String customerId;
		// �̸�, ����, ������ �� ã�� => ?
		while(true) {
			System.out.println("�� ������ �Է��Ͻÿ�.");
			String name;
			boolean gender;
			String age;
			
			System.out.println("�̸�: ");
			name = (this.scanner.nextLine().trim());
			System.out.println("����(W=0, M=1): ");
			String input = this.scanner.nextLine().trim();
			if (input.equals("0")) { gender = false;} 
			else if(input.equals("1")) { gender = true; }
			else { System.out.println("�߸��� �Է°��Դϴ�."); continue; }
			System.out.println("����: ");
			age = this.scanner.nextLine().trim();	
			
			Customer customer = this.salesService.requestCustomer();
			System.out.println("[�� ����]");
			System.out.println(customer);
			customerId = customer.getId();
			break;
		}
		return customerId;
	}

}
