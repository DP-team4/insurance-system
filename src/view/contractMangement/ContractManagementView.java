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
			System.out.println("\n================계약관리 목록 화면================");
			showContractList();
			showContractManage();
		}
		
	}

	private void showContractManage() {
		System.out.print("작업할 상담신청의 ID를 입력하세요 >> ");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
        Contract contract = contractManagementService.getContract(input);
        
        while(true) {
    	    System.out.println("////// 해당 상담신청에 대한 작업을 시작합니다. //////");
    	    System.out.println("고객 정보 보기(1), 보험 정보 보기(2), 삭제(3), 뒤로가기 (0)");
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
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
    	}
		
	}

	private void showDeleteView(Contract contract) {
		System.out.println("정말로 삭제하시겠습니까? 예(1) 아니오(나머지)");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
        if(input.equals("1")) {
        	System.out.println(contract.getId() + "을 삭제합니다.");
        	if(!contractManagementService.deleteContract(input)) {
        		System.out.println("삭제 실패하였습니다.");
        	};
        }
	}

	private void showInsuranceInfoView(Contract contract) {
		System.out.println("[보험 정보]");
		String insuranceId = contract.getInsuranceId();
		Insurance insurance = contractManagementService.getInsurance(insuranceId);
		System.out.println(insurance);
	}

	private void showCustomerInfoView(Contract contract) {
		System.out.println("[고객 정보]");
		String customerId = contract.getCustomerId();
		Customer customer = contractManagementService.getCustomer(customerId);
		System.out.println(customer);
		
	}

	private void showContractList() {
		ArrayList<Contract> contracts = contractManagementService.getAllContracts();
		if(contracts.size() < 1) System.out.println("현재 저장된 계약이 없습니다.");
		else contracts.forEach(i -> {
			System.out.println(i);
            System.out.println();
		});
	}
	

}
