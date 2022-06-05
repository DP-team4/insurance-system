package view.contractMangement;

import java.util.ArrayList;
import java.util.Scanner;
import domain.cancelApplication.CancelApplication;
import domain.cancelApplication.CancelApplicationState;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Clause;
import domain.insurance.Insurance;
import exception.InvalidInputException;
import service.contractManagement.ContractManagementService;
import service.contractManagement.ContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

public class CancelApplicationListView extends View {
	
	private final Scanner scanner = ScannerUtility.getScanner();
	private final ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();

	@Override
	public void show() {
		while(true) {
			try {
				System.out.println("\n================ ��� ���� ��û ��� ȭ��================");
				if(!this.showCancelApplicationList()) break;;
				this.showCancelApplicationManage();
				System.out.println("���������û ��� ȭ���Դϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
				String input = scanner.nextLine().trim();
				if(!input.equals("1")) break;
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void showCancelApplicationManage() throws InvalidInputException {
		System.out.print("�۾��� ���������û�� ID�� �Է��ϼ��� >> ");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
		CancelApplication cancelApplication = contractManagementService.getCancelApplication(input);
		if(cancelApplication == null) {
			throw new InvalidInputException("�������� ���� ���������û ID �Դϴ�.");
		}
		
		while(true) {
		    System.out.println("////// �ش� ���������û�� ���� �۾��� �����մϴ�. //////");
		    System.out.println("����(1), ����(2), ��� ���� ����(3), �� ���� ����(4), ���� ���� ����(5), �ڷΰ��� (0)");
		    input = scanner.nextLine().trim();
	        if(input.equals("0")) break;
	        switch (input) {
	            case "1":
	                showChangeStateView(cancelApplication, CancelApplicationState.ACCEPTED);
	                break;
	            case "2":
	            	showChangeStateView(cancelApplication, CancelApplicationState.REJECTED);
	                break;
	            case "3":
	            	showContractInfoView(cancelApplication);
	                break;
	            case "4":
	            	showCustomerInfoView(cancelApplication);
	                break;
	            case "5":
	            	showInsuranceInfoView(cancelApplication);
	                break;
	            case "0":
	            	break;
	            default:
	                System.out.println("�߸��� �Է��Դϴ�.");
	                break;
	        }
		}
	}

	private void showCustomerInfoView(CancelApplication cancelApplication) {
		System.out.println("[�� ����]");
		String contractId = cancelApplication.getContractId();
		Contract contract = contractManagementService.getContract(contractId);
		Customer customer = contractManagementService.getCustomer(contract.getCustomerId());
		System.out.println(customer.toStringBySecurity());
	}

	private void showInsuranceInfoView(CancelApplication cancelApplication) {
		System.out.println("[���� ����]");
		String contractId = cancelApplication.getContractId();
		Contract contract = contractManagementService.getContract(contractId);
		Insurance insurance = contractManagementService.getInsurance(contract.getInsuranceId());
		System.out.println(insurance);
		System.out.println("[���� ����_��� ���]");
		ArrayList<Clause> clauses = insurance.getClauses();
		clauses.forEach(i -> {
			System.out.println(i);
		});
	}

	private void showContractInfoView(CancelApplication cancelApplication) {
		System.out.println("[��� ����]");
		String contractId = cancelApplication.getContractId();
		Contract contract = contractManagementService.getContract(contractId);
		System.out.println(contract);
	}

	private void showChangeStateView(CancelApplication cancelApplication, CancelApplicationState state) {
		System.out.println(cancelApplication.getId() + "�� ���¸� �����մϴ�.");
		cancelApplication.setState(state);
		contractManagementService.updateCancelApplication(cancelApplication);
		System.out.println(cancelApplication);
	}

	private boolean showCancelApplicationList() {
		ArrayList<CancelApplication> cancelApplications = contractManagementService.getAllCancelApplications();
		if(cancelApplications.size() < 1) {
			System.out.println("���� ����� ���������û�� �����ϴ�.");
			return false;
		}
		else {
			cancelApplications.forEach(i -> {
				System.out.println(i);
	            System.out.println();
			});
			return true;
		}
	}

}
