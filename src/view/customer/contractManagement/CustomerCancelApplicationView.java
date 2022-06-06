package view.customer.contractManagement;

import java.util.Scanner;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import exception.InvalidInputException;
import service.customer.CustomerCancelApplicationService;
import service.customer.CustomerCancelApplicationServiceImpl;
import service.customer.CustomerContractService;
import service.customer.CustomerContractServiceImpl;
import view.viewUtility.ScannerUtility;

public class CustomerCancelApplicationView {
    private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
    
    // View
	private CustomerCancelApplicationListView customerCancelApplicationListView;

	// Service
	private CustomerCancelApplicationService customerCancelApplicationService = CustomerCancelApplicationServiceImpl.getInstance();
    private CustomerContractService customerContractService = CustomerContractServiceImpl.getInstance();
	
	public void show(CustomerContractListView customerContractListView, CustomerCancelApplicationListView cancellationListView, Customer customer) {
		try {
			this.customerCancelApplicationListView = cancellationListView;
			
			System.out.println("\n[ 계약 해지 신청 ]");
			if(customerContractService.getCustomerContracts(customer.getId()).size() == 0) {
				System.out.println("가입한 보험이 없어 해지 신청이 불가합니다. 이전 화면으로 돌아갑니다."); return;
			}
			System.out.println("계약 목록을 조회하려면 (list)");
			boolean showList = true;
			String contractId = "";
			while(showList) {
				System.out.print("해지할 계약 ID >> "); contractId = getInputAndCheckInvalid();
		        if(contractId.equals("list")) {
		        	customerContractListView.show(cancellationListView, customer);
		        } else {
		        	showList = false;
		        }
			}
			// 해지 신청 화면(해지할 아이디 입력창  + '확인', '취소' 버튼)을 보여준다
	        System.out.print("\n계약 해지를 진행하시겠습니까? 예(1) 아니오(기타) >> "); String input = scanner.nextLine().trim();
			if(input.equals("1")) {
	        	applyCancellation(contractId);
	        } else {
	        	// A2. 내용을 입력한 후 취소 버튼을 클릭할 경우
        		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 확인(1) 취소(기타) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
        		applyCancellation(contractId);
	        }
		} catch (InvalidInputException invalidInputException) {
			System.out.println(invalidInputException.getMessage());
		}
	}

	// 해지 신청 정보를 저장을 요청한다
	private void applyCancellation(String contractId) {
		CancelApplication cancelApplication = new CancelApplication();
		cancelApplication.setContractId(contractId);
		if(customerCancelApplicationService.applyCancellation(cancelApplication)) {
			System.out.println("\n접수가 완료 되었습니다.");
			customerCancelApplicationListView.show(customer);
		}
        else System.out.println("\n해지신청 실패. 이전 화면으로 돌아갑니다.");
	}

    // 로그인 내용 입력 여부를 체크한다
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// 공백
		if(input.equals("")) {
			throw new InvalidInputException("입력값이 없습니다.");
		}
		return input;
	}
}
