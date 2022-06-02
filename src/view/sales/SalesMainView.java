package view.sales;

import java.util.Scanner;

public class SalesMainView {

	Scanner scanner;
	
	private enum EMenu {
		salesActivity("영업활동", "1"),
		contractCompletion("가입접수", "2"),
		consultApplicationList("상담신청목록", "3"),
		exit("뒤로가기", "x");
		
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
	
	public SalesMainView(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void showView() {
		while(true) {
			System.out.println();
			System.out.println("================영업 메인 화면================");
			this.showMenu();
			System.out.print(">> ");
			String input = this.scanner.nextLine().trim();
			if(input.equals("0")) break;
			switch(input) {
				case "1": new SalesActivityView(this.scanner).showView();			break;
				case "2": new ContractCompletionView(this.scanner).showView();		break;
				case "3": new ConsultApplicationListView(this.scanner).showView();	break;
				case "x": break;
				default:  System.out.println("잘못된 입력입니다.");						break;
			}
			System.out.println("반복됩니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
	        input = scanner.nextLine().trim();
	        if(!input.equals("1")) break;
		}
		
	}
	
	private void showMenu() {
		System.out.println("[ 메뉴 ]");
		for(EMenu emenu: EMenu.values()) {
			System.out.print(emenu.getTag() + "(" + emenu.getKey() + ") ");
		}
	}
}
