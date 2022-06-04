package view.customer.contractManagement;

import java.util.Scanner;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import service.customer.MyCancelApplicationService;
import service.customer.MyCancelApplicationServiceImpl;
import service.customer.MyContractManagementService;
import service.customer.MyContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;

public class CancelApplicationView {
    private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
    
    // View
	private CancellationListView cancellationListView;

	// Service
	private MyCancelApplicationService cancelApplicationService = MyCancelApplicationServiceImpl.getInstance();
    private MyContractManagementService myContractManagementService = MyContractManagementServiceImpl.getInstance();
	
	public void show(ContractListView contractListView, CancellationListView cancellationListView, Customer customer) {
		this.cancellationListView = cancellationListView;
		
		System.out.println("\n[ 계약 해지 신청 ]");
		if(myContractManagementService.getCustomerContracts(customer.getId()).size() == 0) {
			System.out.println("가입한 보험이 없어 해지 신청이 불가합니다. 이전 화면으로 돌아갑니다."); return;
		}
		System.out.println("계약 목록을 조회하려면 (list)");
		boolean showList = true;
		String contractId = "";
		while(showList) {
			System.out.print("해지할 계약 ID >> "); contractId = scanner.nextLine().trim();
	        if(contractId.equals("list")) {
	        	contractListView.show(cancellationListView, customer);
	        } else {
	        	showList = false;
	        }
		}
		// 해지 신청 화면(해지할 아이디 입력창  + '확인', '취소' 버튼)을 보여준다
        System.out.print("\n계약 해지를 진행하시겠습니까? 예(1) 아니오(기타) >> "); String input = scanner.nextLine().trim();
		if(input.equals("1")) {
	        // A1. 해지 신청 양식을 덜 입력한 경우
			if(contractId.equals("")) { System.out.println("신청 양식을 입력해주세요."); return; }
        	applyCancellation(contractId);
        } else {
        	// A2. 내용을 입력한 후 취소 버튼을 클릭할 경우
        	if(!contractId.equals("")) {
        		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 확인(1) 취소(기타) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
        		applyCancellation(contractId);
        	}
        }
		
	}

	// 해지 신청 정보를 저장을 요청한다
	private void applyCancellation(String contractId) {
		CancelApplication cancelApplication = new CancelApplication();
		cancelApplication.setContractId(contractId);
		if(cancelApplicationService.applyCancellation(cancelApplication)) {
			System.out.println("\n접수가 완료 되었습니다.");
			cancellationListView.show(customer);
		}
        else System.out.println("\n해지신청 실패. 이전 화면으로 돌아갑니다.");
	}
}
