package view.customer.benefitPaymentManagement;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

import domain.benefitPayment.BenefitPayment;
import domain.benefitPayment.EBenefitPaymentState;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.InsuranceCategory;
import exception.InvalidAccidentDateTimeException;
import exception.InvalidInputException;
import service.customer.CustomerBenefitPaymentService;
import service.customer.CustomerBenefitPaymentServiceImpl;
import service.customer.CustomerContractService;
import service.customer.CustomerContractServiceImpl;
import view.viewUtility.ScannerUtility;

public class CustomerBenefitPaymentApplyView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
    private Contract contract;
    private InsuranceCategory insuranceCategory;
	
    // View
 	private CustomerBenefitPaymentListView customerBenefitPaymentListView;
 	
 	// Service
 	private CustomerContractService customerContractService = CustomerContractServiceImpl.getInstance();
 	private CustomerBenefitPaymentService customerBenefitPaymentService = CustomerBenefitPaymentServiceImpl.getInstance();
 	
	public void show(CustomerBenefitPaymentListView customerBenefitPaymentListView, Customer customer) {
		try {
			this.customer = customer;
			this.customerBenefitPaymentListView = customerBenefitPaymentListView;
			// 사고접수정보(사고유형, 접수일시, 고객정보(고객성명, 주민번호)), '신청하기', '취소하기' 버튼을 보여준다
			System.out.println("\n[ 보험금 청구 ]");
			System.out.println("청구할 보험상품 종류를 선택해주세요 -");
			System.out.print("해상보험(1), 운전자보험(2), 화재보험(3) >> ");
			String input = scanner.nextLine().trim();
	        // 입력에 맞는 계약을 가져온다.
	        this.insuranceCategory = null;
			switch(input) {
				case "1": insuranceCategory = InsuranceCategory.MARINE; break;
				case "2": insuranceCategory = InsuranceCategory.DRIVER; break;
				case "3": insuranceCategory = InsuranceCategory.FIRE; break;
				// A3. 올바르지 않은 검색 조건을 입력한 경우 -> "입력값이 유효하지 않습니다" 메시지를 보여준다
				default : System.out.println("\n입력값이 유효하지 않습니다."); return;
			}
			// 자격 검사
			contract = customerContractService.getUnmaturedContractByCategory(this.customer.getId(), insuranceCategory);
			if(contract == null) { System.out.println("보험금 청구 가능한 가입 보험이 없어 서비스를 이용하실 수 없습니다. 이전화면으로 돌아갑니다."); return; }

	        System.out.print("청구 내용 >> "); String content = getInputAndCheckInvalid();
	        System.out.println("사고 날짜 >> ");
	        System.out.print("년(year) : "); String year = getInputAndCheckInvalid();
	        System.out.print("월(month) : "); String month = getInputAndCheckInvalid();
	        System.out.print("일(day) : "); String dayOfMonth = getInputAndCheckInvalid();
	        System.out.print("시(hour) : "); String hour = getInputAndCheckInvalid();
	        System.out.print("분(minute) : "); String minute = getInputAndCheckInvalid();
			switch(insuranceCategory) {
				case MARINE:
				case FIRE:
					System.out.print("재산피해 : ");
					break;
				case DRIVER:
					System.out.print("대인피해 : ");
					break;
				default: System.out.println("ERROR"); break;
			}
			String totalLoss = getInputAndCheckInvalid();
	        System.out.print("\n상담을 신청하시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	        	applyBenefitPayment(year, month, dayOfMonth, hour, minute, content, totalLoss);
	        } else {
	        	// A2. 내용을 모두 입력한 후 취소 버튼을 클릭할 경우
	    		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
	    		if(input.equals("1")) return;
	        	applyBenefitPayment(year, month, dayOfMonth, hour, minute, content, totalLoss);
	        }
	    // A1. 상담 신청 양식을 덜 입력한 경우
		} catch (InvalidInputException invalidInputException) {
        	System.out.println(invalidInputException.getMessage());
        }	
	}

	// 사고처리 신청 정보를 저장 요청한다
	private void applyBenefitPayment(String year, String month, String dayOfMonth, String hour, String minute, String content, String totalLossStr) {
		BenefitPayment benefitPayment = new BenefitPayment();
		benefitPayment.setState(EBenefitPaymentState.ONREVIEW);
		benefitPayment.setRequestDate(LocalDateTime.now());
		benefitPayment.setContractId(contract.getId());
		benefitPayment.setAccidentContent(content);
		long totalLoss = Long.parseLong(totalLossStr);
		switch(insuranceCategory) {
			case MARINE:
			case FIRE:
				benefitPayment.setTotalPropertyLoss(totalLoss);
				break;
			case DRIVER:
				benefitPayment.setTotalPersonLoss(totalLoss);
				break;
			default: System.out.println("ERROR"); break;
		}
		// set
        try {
            LocalDateTime accidentDateTime = LocalDateTime.of(
            		Integer.parseInt(year),
            		Integer.parseInt(month),
            		Integer.parseInt(dayOfMonth),
            		Integer.parseInt(hour),
            		Integer.parseInt(minute)
            );
    		benefitPayment.setAccidentDate(accidentDateTime);
            checkInvalidDate(accidentDateTime);
            // "접수가 완료 되었습니다" 메시지를 보여주고 보험금 청구 현황 화면을 보여준다
    		if(customerBenefitPaymentService.applyBenefitPayment(benefitPayment)) {
    			System.out.println("\n접수가 완료 되었습니다.");
    			customerBenefitPaymentListView.show(customer);
    		} else System.out.println("\n보험금 청구 실패. 이전 화면으로 돌아갑니다.");
        } catch (NumberFormatException e) {
        	// A3. 상담신청내용에 유효하지 않은 값을 입력한 경우
        	System.out.println("입력값이 유효하지 않습니다.");
        } catch (DateTimeException dateTimeException) {
        	System.out.println("날짜가 올바르지 않습니다.");
        } catch (InvalidAccidentDateTimeException invalidAccidentDateTimeException) {
        	System.out.println(invalidAccidentDateTimeException.getMessage());
        }
	}
	
	private void checkInvalidDate(LocalDateTime accidentDateTime) throws InvalidAccidentDateTimeException {
		// 신청날짜가 신청 가능 날짜 이후일 때
		if(accidentDateTime.isAfter(LocalDateTime.now())) {
			throw new InvalidAccidentDateTimeException("신청할 수 없는 날짜입니다.");
		}
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
