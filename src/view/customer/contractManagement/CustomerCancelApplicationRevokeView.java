package view.customer.contractManagement;

import java.util.Scanner;

import domain.customer.Customer;
import exception.InvalidInputException;
import service.customer.CustomerCancelApplicationService;
import service.customer.CustomerCancelApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class CustomerCancelApplicationRevokeView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
    
    // View
    private CustomerCancelApplicationListView customerCancelApplicationListView;

	// Service
	private CustomerCancelApplicationService customerCancelApplicationService = CustomerCancelApplicationServiceImpl.getInstance();

	public void show(CustomerCancelApplicationListView cancellationListView, Customer customer) {
		try {
			// 계약 해지 신청 취소 화면(해지 아이디 입력창  + '확인', '취소' 버튼)을 보여준다
			this.customerCancelApplicationListView = cancellationListView;
			this.customer = customer;
			
			System.out.println("\n[ 계약 해지 신청 취소 ]");
			if(customerCancelApplicationService.getByCustomerId(customer.getId()).size() == 0) {
				System.out.println("신청 내역이 없어 해지 신청 취소가 불가합니다. 이전 화면으로 돌아갑니다."); return;
			}
			System.out.println("계약 해지 신청 목록을 조회하려면 (list)");
			boolean showList = true;
			String id = "";
			while(showList) {
		        System.out.print("취소할 해지 아이디 >> "); id = getInputAndCheckInvalid();
		        if(id.equals("list")) {
		        	cancellationListView.show(customer);
		        } else {
		        	showList = false;
		        }
			}
	        System.out.print("\n해지 신청 취소를 진행하시겠습니까? 예(1) 아니오(기타) >> "); String input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	        	revokeCancellation(id, customer.getId());
	        } else {
	        	// A2. 내용을 모두 입력한 후 취소 버튼을 클릭할 경우
        		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
        		revokeCancellation(id, customer.getId());
	        }
		} catch (InvalidInputException invalidInputException) {
        	// 유효하지 않은 값을 입력한 경우
            System.out.println(invalidInputException.getMessage());
		}
	}

	private void revokeCancellation(String id, String customerId) {
        if(customerCancelApplicationService.deleteMyCancellation(id, customerId)) {
        	System.out.println("삭제되었습니다.");
        	customerCancelApplicationListView.show(customer);
        }
        else System.out.println("삭제 실패하였습니다.");
	}

    // 해지 신청 취소 양식 입력 여부를 체크한다
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// 공백
		if(input.equals("")) {
			throw new InvalidInputException("입력값이 없습니다.");
		}
		return input;
	}
}
