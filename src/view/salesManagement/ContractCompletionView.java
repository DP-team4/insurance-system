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
			System.out.println("\n================가입접수 목록 화면================");
			this.showContractRegisterList();
			this.showManageContractRegister();
			
			System.out.println("가입접수 목록 화면입니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
			 String input = scanner.nextLine().trim();
			 if(!input.equals("1")) break;
		}
	}

	private void showManageContractRegister() {
		System.out.print("작업할 가입접수의 ID를 입력하세요");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
        Contract contract = salesService.getContract(input);
        
        while(true) {
		    System.out.println("////// 해당 가입접수에 대한 작업을 시작합니다. //////");
		    System.out.println("고객 정보 보기(1), 보험 정보 보기(2), 인수심사 상태 보기(3), 인수심사 서류 보기(4), 승인(5), 거절(6), 뒤로가기 (0)");
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
	                System.out.println("잘못된 입력입니다.");
	                break;
	        }
		}
	}

	private void showUpdateStateView(Contract contract, ContractState state) {
		UW uw = salesService.getUW(contract);
		switch(uw.getUwState()) {
		case ACCEPTED:
			System.out.println(contract.getId() + "의 상태를 변경합니다.");
			contract.setState(state);
			salesService.updateContract(contract);
			System.out.println(contract);
			break;
		case REJECTED:
			System.out.println("인수 심사에 탈락하여 가입에 실패하였습니다.");
			break;
		case UNDER_AUDIT:
			System.out.println("인수 심사 중입니다.");
			break;
		case WAITING_DOCUMENT:
			System.out.println("추가 서류를 요청받았습니다.");
			break;
		case UNDEFINED:
			System.out.println("인수 심사 접수가 정상적으로 되지 않았습니다.");
			break;
		default:
			break;
		}
		
	}

	private void showUWDocumentView(Contract contract) {
		System.out.println("[인수심사 서류 정보]");
		UW uw = salesService.getUW(contract);
		ArrayList<UWDocument> uwDocuments = uw.getDocuments();
		uwDocuments.forEach(i -> {
			System.out.println(i);
            System.out.println();
		});
	}

	private void showUWStateView(Contract contract) {
		System.out.println("[인수심사 상태 정보]");
		UW uw = salesService.getUW(contract);
		System.out.println("인수심사 상태: " + uw.getUwState());
	}

	private void showInsuranceView(Contract contract) {
		System.out.println("[보험 정보]");
		String id = contract.getInsuranceId();
		Insurance insurance = salesService.getInsurance(id);
		System.out.println(insurance);
	}

	private void showCustomerView(Contract contract) {
		System.out.println("[고객 정보]");
		String id = contract.getCustomerId();
		Customer customer = salesService.getCustomer(id);
		System.out.println(customer.toStringBySecurity());
	}

	private void showContractRegisterList() {
		System.out.println("[가입접수 목록]");
		ArrayList<Contract> contractRegisters = salesService.getAllContractRegister();
		if(contractRegisters.size() < 1) System.out.println("현재 저장된 가입접수가 없습니다.");
		else contractRegisters.forEach(i -> {
			System.out.println(i);
            System.out.println();
		});
		
	}

}
