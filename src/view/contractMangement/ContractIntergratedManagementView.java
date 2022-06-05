package view.contractMangement;

import java.util.ArrayList;
import java.util.Scanner;

import domain.contract.Contract;
import service.SalesService;
import service.SalesServiceImpl;
import service.contractManagement.ContractManagementService;
import service.contractManagement.ContractManagementServiceImpl;
import view.salesManagement.ConsultApplicationListView;
import view.salesManagement.ContractCompletionView;
import view.salesManagement.SalesActivityView;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

public class ContractIntergratedManagementView extends View{
	private final Scanner scanner = ScannerUtility.getScanner();
	private final ContractManagementService salesService = ContractManagementServiceImpl.getInstance();
	
	
	@Override
	public void show() {
		while(true) {
			System.out.println();
			System.out.println("================계약통합관리 메인 화면================");
			System.out.println("계약관리(1), 만기계약(2), 계약 해지 신청 목록(3), 뒤로가기(0)");
			System.out.print(">> ");
			String input = this.scanner.nextLine().trim();
			if(input.equals("0")) break;	
			switch(input) {
			case "1": new ContractManagementView().show();		break;
			case "2": showMatureContracts();					break;
			case "3": new CancelApplicationListView().show();	break;
			case "0": break;
			default:  System.out.println("잘못된 입력입니다.");		break;
		}
		System.out.println("계약통합관리 화면입니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
        input = scanner.nextLine().trim();
        if(!input.equals("1")) break;
		}
	}


	private void showMatureContracts() {
		System.out.println("[만기계약 목록]");
		ArrayList<Contract> matureContracts = salesService.getAllMatureContracts();
		if(matureContracts.size() < 1) {
			System.out.println("현재 저장된 만기계약이 없습니다.");
		}else {
			matureContracts.forEach(i -> {
				System.out.println(i);
				System.out.println();
			});	
		}
	}

}
