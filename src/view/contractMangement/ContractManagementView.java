package view.contractMangement;

import java.util.ArrayList;
import java.util.Scanner;

import domain.consultApplication.ConsultApplication;
import domain.consultApplication.ConsultApplicationState;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
import service.SalesService;
import service.SalesServiceImpl;
import service.contractManagement.ContractManagementService;
import service.contractManagement.ContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

public class ContractManagementView extends View {
	
	private final Scanner scanner = ScannerUtility.getScanner();
	private final ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();

	@Override
	public void show() {
		while(true) {
			System.out.println("\n================������ ��� ȭ��================");
			showContractList();
			showContractManage();
		}
		
	}

	private void showContractManage() {
		System.out.print("�۾��� ����û�� ID�� �Է��ϼ��� >> ");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
        Contract contract = contractManagementService.getContract(input);
        
        while(true) {
    	    System.out.println("////// �ش� ����û�� ���� �۾��� �����մϴ�. //////");
    	    System.out.println("�� ���� ����(1), ���� ���� ����(2), ����(3), �ڷΰ��� (0)");
    	    input = scanner.nextLine().trim();
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    showCustomerInfoView(contract);
                    break;
                case "2":
                	showInsuranceInfoView(contract);
                    break;
                case "3":
                	showDeleteView(contract);
                    break;
                case "0":
                	break;
                default:
                    System.out.println("�߸��� �Է��Դϴ�.");
                    break;
            }
    	}
		
	}

	private void showDeleteView(Contract contract) {
		System.out.println("������ �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(������)");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
        if(input.equals("1")) {
        	System.out.println(contract.getId() + "�� �����մϴ�.");
        	if(!contractManagementService.deleteContract(input)) {
        		System.out.println("���� �����Ͽ����ϴ�.");
        	};
        }
	}

	private void showInsuranceInfoView(Contract contract) {
		System.out.println("[���� ����]");
		String insuranceId = contract.getInsuranceId();
		Insurance insurance = contractManagementService.getInsurance(insuranceId);
		System.out.println(insurance);
	}

	private void showCustomerInfoView(Contract contract) {
		System.out.println("[�� ����]");
		String customerId = contract.getCustomerId();
		Customer customer = contractManagementService.getCustomer(customerId);
		System.out.println(customer);
		
	}

	private void showContractList() {
		ArrayList<Contract> contracts = contractManagementService.getAllContracts();
		if(contracts.size() < 1) System.out.println("���� ����� ����� �����ϴ�.");
		else contracts.forEach(i -> {
			System.out.println(i);
            System.out.println();
		});
	}
	

}
