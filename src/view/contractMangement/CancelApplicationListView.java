package view.contractMangement;

import java.util.ArrayList;
import java.util.Scanner;
import domain.cancelApplication.CancelApplication;
import domain.cancelApplication.CancelApplicationState;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
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
			System.out.println("\n================ 계약 해지 신청 목록 화면================");
			if(!this.showCancelApplicationList()) break;;
			this.showCancelApplicationManage();
			
			
			 System.out.println("계약해지신청 목록 화면입니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
			 String input = scanner.nextLine().trim();
			 if(!input.equals("1")) break;
		}
	}

	private void showCancelApplicationManage() {
		System.out.print("작업할 계약해지신청의 ID를 입력하세요 >> ");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
		CancelApplication cancelApplication = contractManagementService.getCancelApplication(input);
		
		while(true) {
		    System.out.println("////// 해당 계약해지신청에 대한 작업을 시작합니다. //////");
		    System.out.println("승인(1), 거절(2), 계약 정보 보기(3), 고객 정보 보기(4), 보험 정보 보기(5), 뒤로가기 (0)");
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
	                System.out.println("잘못된 입력입니다.");
	                break;
	        }
		}
	}

	private void showCustomerInfoView(CancelApplication cancelApplication) {
		System.out.println("[고객 정보]");
		String contractId = cancelApplication.getContractId();
		Contract contract = contractManagementService.getContract(contractId);
		Customer customer = contractManagementService.getCustomer(contract.getCustomerId());
		System.out.println(customer.toStringBySecurity());
	}

	private void showInsuranceInfoView(CancelApplication cancelApplication) {
		System.out.println("[계약 정보]");
		String contractId = cancelApplication.getContractId();
		Contract contract = contractManagementService.getContract(contractId);
		Insurance insurance = contractManagementService.getInsurance(contract.getInsuranceId());
		System.out.println(insurance);
	}

	private void showContractInfoView(CancelApplication cancelApplication) {
		System.out.println("[계약 정보]");
		String contractId = cancelApplication.getContractId();
		
		Contract contract = contractManagementService.getContract(contractId);
		System.out.println(contract);
	}

	private void showChangeStateView(CancelApplication cancelApplication, CancelApplicationState state) {
		System.out.println(cancelApplication.getId() + "의 상태를 변경합니다.");
		cancelApplication.setState(state);
		contractManagementService.updateCancelApplication(cancelApplication);
		System.out.println(cancelApplication);
	}

	private boolean showCancelApplicationList() {
		ArrayList<CancelApplication> cancelApplications = contractManagementService.getAllCancelApplications();
		if(cancelApplications.size() < 1) {
			System.out.println("현재 저장된 계약해지신청이 없습니다.");
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
