package view.salesManagement;

import java.util.Scanner;

import view.viewUtility.ScannerUtility;

public class SalesMainView {
	private final Scanner scanner = ScannerUtility.getScanner();
	
	private enum EMenu {
		salesActivity("����Ȱ��", "1"),
		contractCompletion("�����������", "2"),
		consultApplicationList("����û���", "3"),
		exit("�ڷΰ���", "x");
		
		private String tag;
		private String key;

		EMenu(String tag, String key) {
			this.tag = tag;
			this.key = key;
		}

		public String getTag() {return tag;}
		public void setTag(String tag) {this.tag = tag;}
		public String getKey() {return key;}
		public void setKey(String key) {this.key = key;}
		
	};
	
	public void show() {
		while(true) {
			System.out.println();
			System.out.println("================���� ���� ȭ��================");
			this.showMenu();
			System.out.print(">> ");
			String input = this.scanner.nextLine().trim();
			if(input.equals("x")) break;
			switch(input) {
				case "1": new SalesActivityView().show();			break;
				case "2": new ContractCompletionView().show();		break;
				case "3": new ConsultApplicationListView().show();	break;
				case "x": break;
				default:  System.out.println("�߸��� �Է��Դϴ�.");		break;
			}
			System.out.println("���� ���� ȭ���Դϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
	        input = scanner.nextLine().trim();
	        if(!input.equals("1")) break;
		}
		
	}
	
	private void showMenu() {
		System.out.println("[ �޴� ]");
		for(EMenu emenu: EMenu.values()) {
			System.out.print(emenu.getTag() + "(" + emenu.getKey() + ") ");
		}
	}
}
