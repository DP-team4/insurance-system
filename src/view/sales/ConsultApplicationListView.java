package view.sales;

import java.util.Scanner;

import domain.consultApplication.ConsultApplication;

public class ConsultApplicationListView {
	
	Scanner scanner;

	public ConsultApplicationListView(Scanner scanner) {
		this.scanner = scanner;
	}

	public void showView() {
		while(true) {
			System.out.println("\n================상담신청 목록 화면================");
			this.showConsultApplicationList();
			this.showConsultApplicationManage();
			
			
			 System.out.println("반복합니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
			 String input = scanner.nextLine().trim();
			 if(!input.equals("1")) break;
		}
	}

	private void showConsultApplicationList() {
		// 상담신청 목록 가져오기
	}
	
	private void showConsultApplicationManage() {
		System.out.print("작업할 상담신청의 ID를 입력하세요 >> ");
		String consultApplicationId = scanner.nextLine().trim();

//		ConsultApplication consultApplication
//		if(consultApplication == null){
//		    System.out.println("잘못된 상담신청 ID입니다.");
//		    return;
//		}
		
		while(true) {
		    System.out.println("////// 해당 상담신청에 대한 작업을 시작합니다. //////");
		    System.out.println("승인(1), 거절(2), 고객 정보 보기(3), 뒤로가기 (0)");
		    String input = scanner.nextLine().trim();
	        if(input.equals("0")) break;
	        switch (input) {
	            case "1":
	                
	                break;
	            case "2":
	               
	                break;
	            case "3":
	         
	                break;
	            case "0":
	            	break;
	            default:
	                System.out.println("잘못된 입력입니다.");
	                break;
	        }
		}
	}

}
