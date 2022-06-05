package view.contractMangement;

import java.util.ArrayList;
import java.util.Scanner;

import domain.consultApplication.ConsultApplication;
import domain.consultApplication.ConsultApplicationState;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Clause;
import domain.insurance.Insurance;
import exception.InvalidInputException;
import service.SalesService;
import service.SalesServiceImpl;
import service.contractManagement.ContractManagementService;
import service.contractManagement.ContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

public class ContractManagementView extends View {
	
	private final Scanner scanner = ScannerUtility.getScanner();
	private final ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();
	private Contract contract;
	
	@Override
	public void show() {
		while(true) {
			try {
				System.out.println("\n================������ ��� ȭ��================");
				if(!showContractList()) {
					break;
				};
				showContractManage();
				System.out.println("������ ��� ȭ���Դϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
		        String input = scanner.nextLine().trim();
		        if(!input.equals("1")) break;
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	private void showContractManage() throws InvalidInputException {
		System.out.print("�۾��� ����� ID�� �Է��ϼ��� >> ");
        String input = scanner.nextLine().trim();
        this.contract = contractManagementService.getContract(input);
        if(this.contract == null) {
        	throw new InvalidInputException("�������� ���� ��� ID �Դϴ�.");
        }
        
        while(true) {
    	    System.out.println("////// �ش� ��࿡ ���� �۾��� �����մϴ�. //////");
    	    System.out.println("�� ���� ����(1), ���� ���� ����(2), ����(3), �ڷΰ��� (0)");
    	    input = scanner.nextLine().trim();
    	    boolean escape = false;
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    showCustomerInfoView(this.contract);
                    break;
                case "2":
                	showInsuranceInfoView(this.contract);
                    break;
                case "3":
                	showDeleteView(this.contract);
                	escape = true;
                    break;
                case "0":
                	break;
                default:
                    System.out.println("�߸��� �Է��Դϴ�.");
                    break;
            }
            if(escape) break;
    	}
		
	}

	private void showDeleteView(Contract contract) {
		System.out.println("������ �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(������)");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
        if(input.equals("1")) {
        	if(!contractManagementService.deleteContract(contract.getId())) {
        		System.out.println("���� �����Ͽ����ϴ�.");
        	} else {
        		System.out.println("��� " + contract.getId() + " �����մϴ�.");
        	}
        }
	}

	private void showInsuranceInfoView(Contract contract) {
		System.out.println("[���� ����]");
		String insuranceId = contract.getInsuranceId();
		Insurance insurance = contractManagementService.getInsurance(insuranceId);
		System.out.println(insurance);
		System.out.println("[���� ����_��� ���]");
		ArrayList<Clause> clauses = insurance.getClauses();
		clauses.forEach(i -> {
			System.out.println(i);
		});
	}

	private void showCustomerInfoView(Contract contract) {
		System.out.println("[�� ����]");
		String customerId = contract.getCustomerId();
		Customer customer = contractManagementService.getCustomer(customerId);
		System.out.println(customer.toStringBySecurity());
		
	}

	private boolean showContractList() {
		ArrayList<Contract> contracts = contractManagementService.getAllContracts();
		if(contracts.size() < 1) {
			System.out.println("���� ����� ����� �����ϴ�.");
			return false;
		}
		else {
			contracts.forEach(i -> {
				System.out.println(i);
	            System.out.println();
			});
			return true;
		}
	}
	

}
