package view.customer.consultationManagement;

import java.util.Scanner;

import domain.customer.Customer;
import service.customer.MyConsultApplicationService;
import service.customer.MyConsultApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class ConsultationRevocationView {
	private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
	
	// View
	private ConsultationListView consultationListView;

	// Service
	private MyConsultApplicationService consultApplicationService = MyConsultApplicationServiceImpl.getInstance();

	public void show(ConsultationListView consultationListView, Customer customer) {
		this.consultationListView = consultationListView;
		this.customer = customer;
		// 상담 신청 취소 화면(상담 아이디 입력창  + '확인', '취소' 버튼)을 보여준다
		System.out.println("\n[ 가입 상담 신청 취소 ]");
		if(consultApplicationService.getByCustomerId(customer.getId()).size() == 0) {
			System.out.println("신청 내역이 없어 상담 신청 취소가 불가합니다. 이전 화면으로 돌아갑니다."); return;
		}
		System.out.println("가입 상담 신청 목록을 조회하려면 (list)");
		boolean showList = true;
		String id = "";
		while(showList) {
	        System.out.print("취소할 상담 아이디 >> "); id = scanner.nextLine().trim();
	        if(id.equals("list")) {
	        	consultationListView.show(customer);
	        } else {
	        	showList = false;
	        }
		}
        System.out.print("\n상담 신청 취소를 진행하시겠습니까? 예(1) 아니오(기타) >> "); String input = scanner.nextLine().trim();
        boolean blankExist = id.equals("");
        if(input.equals("1")) {
            // A1. 상담 신청 취소 양식을 덜 입력한 경우
        	if(blankExist) { System.out.println("\n신청 취소 양식을 전부 입력해주세요."); return; }
        	revokeConsultation(id, customer.getId());
        } else {
        	// A2. 내용을 모두 입력한 후 취소 버튼을 클릭할 경우
        	if(!blankExist) {
        		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
            	revokeConsultation(id, customer.getId());
        	}
        }
	}

	private void revokeConsultation(String id, String customerId) {
        if(consultApplicationService.deleteMyConsultation(id, customerId)) {
        	System.out.println("삭제되었습니다.");
        	consultationListView.show(customer);
        }
        else System.out.println("삭제 실패하였습니다.");
	}
}
