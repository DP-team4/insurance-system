package view.customer.carAccidentHandlingManagement;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.carAccidentHandling.AccidentCar;
import domain.carAccidentHandling.AccidentPerson;
import domain.carAccidentHandling.CarAccidentHandling;
import domain.carAccidentHandling.ECarAccidentHandlingState;
import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.InsuranceCategory;
import exception.InvalidAccidentDateTimeException;
import exception.InvalidInputException;
import service.customer.CustomerCarAccidentHandlingService;
import service.customer.CustomerCarAccidentHandlingServiceImpl;
import service.customer.CustomerContractService;
import service.customer.CustomerContractServiceImpl;
import view.viewUtility.ScannerUtility;

public class CustomerCarAccidentHandlingApplyView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;    
	
    // View
 	private CustomerCarAccidentHandlingListView customerCarAccidentHandlingListView;
 	
 	// Service
 	private CustomerContractService customerContractService = CustomerContractServiceImpl.getInstance();
 	private CustomerCarAccidentHandlingService customerCarAccidentHandlingService = CustomerCarAccidentHandlingServiceImpl.getInstance();
 	
	public void show(CustomerCarAccidentHandlingListView customerCarAccidentHandlingListView, Customer customer) {
		try {
			this.customer = customer;
			this.customerCarAccidentHandlingListView = customerCarAccidentHandlingListView;
			
			System.out.println("\n[ 사고처리 신청 ]");
			// 사고처리신청 가능 여부를 확인한다 (해당 고객이 가진 모든 Contract의 InsuranceId를 비교해서 만료되지 않은 CarInsurance가 있는지 확인)
			Contract carContract = customerContractService.getUnmaturedContractByCategory(this.customer.getId(), InsuranceCategory.CAR);
			if(carContract == null) { System.out.println("가입된 만료되지 않은 자동차 보험이 없어 서비스를 이용하실 수 없습니다. 이전화면으로 돌아갑니다."); return; }
			// 사고접수정보(사고일시, 사고 위치, 사고내용, 사고 차량 정보(차량 번호, 차주 이름, 차주 번호, 차량 수리점), 사고 관련자 정보(이름, 전화번호, 방문병원)), '확인/취소' 버튼을 보여준다
	        System.out.println("사고 일시 >> ");
	        System.out.print("년(year) : "); String year = getInputAndCheckInvalid();
	        System.out.print("월(month) : "); String month = getInputAndCheckInvalid();
	        System.out.print("일(day) : "); String dayOfMonth = getInputAndCheckInvalid();
	        System.out.print("시(hour) : "); String hour = getInputAndCheckInvalid();
	        System.out.print("분(minute) : "); String minute = getInputAndCheckInvalid();
	        System.out.print("사고 위치 >> "); String location = getInputAndCheckInvalid();
	        System.out.print("사고 내용 >> "); String content = getInputAndCheckInvalid();
	        ArrayList<AccidentCar> accidentCars = new ArrayList<>();
	        System.out.println("\n< 사고 차량 정보 >");
	        String input = "";
	        while(true) {
	        	AccidentCar accidentCar = new AccidentCar();
	        	System.out.print("차량 번호 >> "); accidentCar.setCarNo(getInputAndCheckInvalid());
	        	System.out.print("차주 이름 >> "); accidentCar.setOwnerName(getInputAndCheckInvalid());
	        	System.out.print("차주 번호 >> "); accidentCar.setOwnerPhoneNo(getInputAndCheckInvalid());
	        	System.out.print("차량 수리점 >> "); accidentCar.setVisitedShopName(getInputAndCheckInvalid());
	        	accidentCars.add(accidentCar);
	        	System.out.print("\n차량 정보 입력을 계속하시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
	        	if(!input.equals("1")) break;
	        }
	        ArrayList<AccidentPerson> accidentPeople = new ArrayList<>();
	        System.out.println("\n< 사고 관련자 정보 >");
	        while(true) {
	        	AccidentPerson accidentPerson = new AccidentPerson();
	        	System.out.print("이름 >> "); accidentPerson.setName(getInputAndCheckInvalid());
	        	System.out.print("전화번호 >> "); accidentPerson.setPhoneNo(getInputAndCheckInvalid());
	        	System.out.print("방문병원 >> "); accidentPerson.setVisitedHospitalName(getInputAndCheckInvalid());
	        	accidentPeople.add(accidentPerson);
	        	System.out.print("\n정보 입력을 계속하시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
	        	if(!input.equals("1")) break;
	        }
	        System.out.print("\n사고 처리를 신청하시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	        	applyCarAccidentHandling(carContract.getId(), year, month, dayOfMonth, hour, minute, content, location, accidentCars, accidentPeople);
	        } else {
	        	// A2. 내용을 모두 입력한 후 취소 버튼을 클릭할 경우
	    		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
	    		if(input.equals("1")) return;
	        	applyCarAccidentHandling(carContract.getId(), year, month, dayOfMonth, hour, minute, content, location, accidentCars, accidentPeople);
	        }
	    // A1. 상담 신청 양식을 덜 입력한 경우
		} catch (InvalidInputException invalidInputException) {
        	System.out.println(invalidInputException.getMessage());
        }	
	}

	// 사고처리 신청 정보를 저장 요청한다
	private void applyCarAccidentHandling(String contractId, String year, String month, String dayOfMonth, String hour, String minute, String content, String location, ArrayList<AccidentCar> accidentCars, ArrayList<AccidentPerson> accidentPeople) {
		CarAccidentHandling carAccidentHandling = new CarAccidentHandling();
		carAccidentHandling.setState(ECarAccidentHandlingState.ONREVIEW);
		carAccidentHandling.setRequestDate(LocalDateTime.now());
		carAccidentHandling.setContractId(contractId);
		carAccidentHandling.setAccidentContent(content);
		carAccidentHandling.setAccidentLocation(location);
		carAccidentHandling.setAccidentCars(accidentCars);
		carAccidentHandling.setAccidentPeople(accidentPeople);
        try {
            LocalDateTime accidentDateTime = LocalDateTime.of(
            		Integer.parseInt(year),
            		Integer.parseInt(month),
            		Integer.parseInt(dayOfMonth),
            		Integer.parseInt(hour),
            		Integer.parseInt(minute)
            );
    		carAccidentHandling.setAccidentDate(accidentDateTime);
            checkInvalidDate(accidentDateTime);
            // "접수가 완료 되었습니다" 메시지를 보여주고 사고 처리 신청 현황 화면을 보여준다
    		if(customerCarAccidentHandlingService.applyCarAccidentHandling(carAccidentHandling)) {
    			System.out.println("\n접수가 완료 되었습니다.");
    			customerCarAccidentHandlingListView.show(customer);
    		} else System.out.println("\n사고 처리 신청 실패. 이전 화면으로 돌아갑니다.");
    	// A3. 사고처리신청내용에 유효하지 않은 값을 입력한 경우
        } catch (NumberFormatException e) {
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
