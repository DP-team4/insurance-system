package view.salesManagement;

import java.util.ArrayList;
import java.util.Scanner;

import domain.consultApplication.ConsultApplicationState;
import domain.contract.Contract;
import domain.contract.ContractState;
import domain.customer.Customer;
import domain.insurance.Insurance;
import domain.uw.UW;
import domain.uw.UWDocument;
import domain.uw.UWState;
import service.SalesService;
import service.SalesServiceImpl;
import view.viewUtility.ScannerUtility;

public class ContractCompletionView {
	private final Scanner scanner = ScannerUtility.getScanner();
	private final SalesService salesService = SalesServiceImpl.getInstance();

	public ContractCompletionView() {}

	public void show() {
		while(true) {
			System.out.println("\n================�������� ��� ȭ��================");
			this.showContractRegisterList();
			this.showManageContractRegister();
			
			System.out.println("�������� ��� ȭ���Դϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
			 String input = scanner.nextLine().trim();
			 if(!input.equals("1")) break;
		}
	}

	private void showManageContractRegister() {
		System.out.print("�۾��� ���������� ID�� �Է��ϼ���");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
        Contract contract = salesService.getContract(input);
        
        while(true) {
		    System.out.println("////// �ش� ���������� ���� �۾��� �����մϴ�. //////");
		    System.out.println("�� ���� ����(1), ���� ���� ����(2), �μ��ɻ� ���� ����(3), �μ��ɻ� ���� ����(4), ����(5), ����(6), �ڷΰ��� (0)");
		    input = scanner.nextLine().trim();
	        if(input.equals("0")) break;
	        switch (input) {
	            case "1":
	                showCustomerView(contract);
	                break;
	            case "2":
	            	showInsuranceView(contract);
	                break;
	            case "3":
	            	showUWStateView(contract);
	                break;
	            case "4":
	            	showUWDocumentView(contract);
	            	break;
	            case "5":
	            	showUpdateStateView(contract, ContractState.ACCEPTED);
	            	break;
	            case "6":
	            	showUpdateStateView(contract, ContractState.REJECTED);
	            	break;
	            case "0":
	            	break;
	            default:
	                System.out.println("�߸��� �Է��Դϴ�.");
	                break;
	        }
		}
	}

	private void showUpdateStateView(Contract contract, ContractState state) {
		UW uw = salesService.getUW(contract);
		switch(uw.getUwState()) {
		case ACCEPTED:
			System.out.println(contract.getId() + "�� ���¸� �����մϴ�.");
			contract.setState(state);
			salesService.updateContract(contract);
			System.out.println(contract);
			break;
		case REJECTED:
			System.out.println("�μ� �ɻ翡 Ż���Ͽ� ���Կ� �����Ͽ����ϴ�.");
			break;
		case UNDER_AUDIT:
			System.out.println("�μ� �ɻ� ���Դϴ�.");
			break;
		case WAITING_DOCUMENT:
			System.out.println("�߰� ������ ��û�޾ҽ��ϴ�.");
			break;
		case UNDEFINED:
			System.out.println("�μ� �ɻ� ������ ���������� ���� �ʾҽ��ϴ�.");
			break;
		default:
			break;
		}
		
	}

	private void showUWDocumentView(Contract contract) {
		System.out.println("[�μ��ɻ� ���� ����]");
		UW uw = salesService.getUW(contract);
		ArrayList<UWDocument> uwDocuments = uw.getDocuments();
		uwDocuments.forEach(i -> {
			System.out.println(i);
            System.out.println();
		});
	}

	private void showUWStateView(Contract contract) {
		System.out.println("[�μ��ɻ� ���� ����]");
		UW uw = salesService.getUW(contract);
		System.out.println("�μ��ɻ� ����: " + uw.getUwState());
	}

	private void showInsuranceView(Contract contract) {
		System.out.println("[���� ����]");
		String id = contract.getInsuranceId();
		Insurance insurance = salesService.getInsurance(id);
		System.out.println(insurance);
	}

	private void showCustomerView(Contract contract) {
		System.out.println("[�� ����]");
		String id = contract.getCustomerId();
		Customer customer = salesService.getCustomer(id);
		System.out.println(customer.toStringBySecurity());
	}

	private void showContractRegisterList() {
		System.out.println("[�������� ���]");
		ArrayList<Contract> contractRegisters = salesService.getAllContractRegister();
		if(contractRegisters.size() < 1) System.out.println("���� ����� ���������� �����ϴ�.");
		else contractRegisters.forEach(i -> {
			System.out.println(i);
            System.out.println();
		});
		
	}

}
