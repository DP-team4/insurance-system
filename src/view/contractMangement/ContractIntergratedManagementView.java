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
			System.out.println("================������հ��� ���� ȭ��================");
			System.out.println("������(1), ������(2), ��� ���� ��û ���(3), �ڷΰ���(0)");
			System.out.print(">> ");
			String input = this.scanner.nextLine().trim();
			if(input.equals("0")) break;	
			switch(input) {
			case "1": new ContractManagementView().show();		break;
			case "2": showMatureContracts();					break;
			case "3": new CancelApplicationListView().show();	break;
			case "0": break;
			default:  System.out.println("�߸��� �Է��Դϴ�.");		break;
		}
		System.out.println("������հ��� ȭ���Դϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
        input = scanner.nextLine().trim();
        if(!input.equals("1")) break;
		}
	}


	private void showMatureContracts() {
		System.out.println("[������ ���]");
		ArrayList<Contract> matureContracts = salesService.getAllMatureContracts();
		if(matureContracts.size() < 1) {
			System.out.println("���� ����� �������� �����ϴ�.");
		}else {
			matureContracts.forEach(i -> {
				System.out.println(i);
				System.out.println();
			});	
		}
	}

}
