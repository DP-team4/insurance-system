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
				System.out.println("\n================계약관리 목록 화면================");
				if(!showContractList()) {
					break;
				};
				showContractManage();
				System.out.println("계약관리 목록 화면입니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
		        String input = scanner.nextLine().trim();
		        if(!input.equals("1")) break;
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	private void showContractManage() throws InvalidInputException {
		System.out.print("작업할 계약의 ID를 입력하세요 >> ");
        String input = scanner.nextLine().trim();
        this.contract = contractManagementService.getContract(input);
        if(this.contract == null) {
        	throw new InvalidInputException("유요하지 않은 계약 ID 입니다.");
        }
        
        while(true) {
    	    System.out.println("////// 해당 계약에 대한 작업을 시작합니다. //////");
    	    System.out.println("고객 정보 보기(1), 보험 정보 보기(2), 삭제(3), 뒤로가기 (0)");
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
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
            if(escape) break;
    	}
		
	}

	private void showDeleteView(Contract contract) {
		System.out.println("정말로 삭제하시겠습니까? 예(1) 아니오(나머지)");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
        if(input.equals("1")) {
        	if(!contractManagementService.deleteContract(contract.getId())) {
        		System.out.println("삭제 실패하였습니다.");
        	} else {
        		System.out.println("계약 " + contract.getId() + " 삭제합니다.");
        	}
        }
	}

	private void showInsuranceInfoView(Contract contract) {
		System.out.println("[보험 정보]");
		String insuranceId = contract.getInsuranceId();
		Insurance insurance = contractManagementService.getInsurance(insuranceId);
		System.out.println(insurance);
		System.out.println("[보험 정보_약관 목록]");
		ArrayList<Clause> clauses = insurance.getClauses();
		clauses.forEach(i -> {
			System.out.println(i);
		});
	}

	private void showCustomerInfoView(Contract contract) {
		System.out.println("[고객 정보]");
		String customerId = contract.getCustomerId();
		Customer customer = contractManagementService.getCustomer(customerId);
		System.out.println(customer.toStringBySecurity());
		
	}

	private boolean showContractList() {
		ArrayList<Contract> contracts = contractManagementService.getAllContracts();
		if(contracts.size() < 1) {
			System.out.println("현재 저장된 계약이 없습니다.");
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
