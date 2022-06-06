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
			
			System.out.println("\n[ ���ó�� ��û ]");
			// ���ó����û ���� ���θ� Ȯ���Ѵ� (�ش� ���� ���� ��� Contract�� InsuranceId�� ���ؼ� ������� ���� CarInsurance�� �ִ��� Ȯ��)
			Contract carContract = customerContractService.getUnmaturedContractByCategory(this.customer.getId(), InsuranceCategory.CAR);
			if(carContract == null) { System.out.println("���Ե� ������� ���� �ڵ��� ������ ���� ���񽺸� �̿��Ͻ� �� �����ϴ�. ����ȭ������ ���ư��ϴ�."); return; }
			// �����������(����Ͻ�, ��� ��ġ, �����, ��� ���� ����(���� ��ȣ, ���� �̸�, ���� ��ȣ, ���� ������), ��� ������ ����(�̸�, ��ȭ��ȣ, �湮����)), 'Ȯ��/���' ��ư�� �����ش�
	        System.out.println("��� �Ͻ� >> ");
	        System.out.print("��(year) : "); String year = getInputAndCheckInvalid();
	        System.out.print("��(month) : "); String month = getInputAndCheckInvalid();
	        System.out.print("��(day) : "); String dayOfMonth = getInputAndCheckInvalid();
	        System.out.print("��(hour) : "); String hour = getInputAndCheckInvalid();
	        System.out.print("��(minute) : "); String minute = getInputAndCheckInvalid();
	        System.out.print("��� ��ġ >> "); String location = getInputAndCheckInvalid();
	        System.out.print("��� ���� >> "); String content = getInputAndCheckInvalid();
	        ArrayList<AccidentCar> accidentCars = new ArrayList<>();
	        System.out.println("\n< ��� ���� ���� >");
	        String input = "";
	        while(true) {
	        	AccidentCar accidentCar = new AccidentCar();
	        	System.out.print("���� ��ȣ >> "); accidentCar.setCarNo(getInputAndCheckInvalid());
	        	System.out.print("���� �̸� >> "); accidentCar.setOwnerName(getInputAndCheckInvalid());
	        	System.out.print("���� ��ȣ >> "); accidentCar.setOwnerPhoneNo(getInputAndCheckInvalid());
	        	System.out.print("���� ������ >> "); accidentCar.setVisitedShopName(getInputAndCheckInvalid());
	        	accidentCars.add(accidentCar);
	        	System.out.print("\n���� ���� �Է��� ����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
	        	if(!input.equals("1")) break;
	        }
	        ArrayList<AccidentPerson> accidentPeople = new ArrayList<>();
	        System.out.println("\n< ��� ������ ���� >");
	        while(true) {
	        	AccidentPerson accidentPerson = new AccidentPerson();
	        	System.out.print("�̸� >> "); accidentPerson.setName(getInputAndCheckInvalid());
	        	System.out.print("��ȭ��ȣ >> "); accidentPerson.setPhoneNo(getInputAndCheckInvalid());
	        	System.out.print("�湮���� >> "); accidentPerson.setVisitedHospitalName(getInputAndCheckInvalid());
	        	accidentPeople.add(accidentPerson);
	        	System.out.print("\n���� �Է��� ����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
	        	if(!input.equals("1")) break;
	        }
	        System.out.print("\n��� ó���� ��û�Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	        	applyCarAccidentHandling(carContract.getId(), year, month, dayOfMonth, hour, minute, content, location, accidentCars, accidentPeople);
	        } else {
	        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
	    		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
	    		if(input.equals("1")) return;
	        	applyCarAccidentHandling(carContract.getId(), year, month, dayOfMonth, hour, minute, content, location, accidentCars, accidentPeople);
	        }
	    // A1. ��� ��û ����� �� �Է��� ���
		} catch (InvalidInputException invalidInputException) {
        	System.out.println(invalidInputException.getMessage());
        }	
	}

	// ���ó�� ��û ������ ���� ��û�Ѵ�
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
            // "������ �Ϸ� �Ǿ����ϴ�" �޽����� �����ְ� ��� ó�� ��û ��Ȳ ȭ���� �����ش�
    		if(customerCarAccidentHandlingService.applyCarAccidentHandling(carAccidentHandling)) {
    			System.out.println("\n������ �Ϸ� �Ǿ����ϴ�.");
    			customerCarAccidentHandlingListView.show(customer);
    		} else System.out.println("\n��� ó�� ��û ����. ���� ȭ������ ���ư��ϴ�.");
    	// A3. ���ó����û���뿡 ��ȿ���� ���� ���� �Է��� ���
        } catch (NumberFormatException e) {
        	System.out.println("�Է°��� ��ȿ���� �ʽ��ϴ�.");
        } catch (DateTimeException dateTimeException) {
        	System.out.println("��¥�� �ùٸ��� �ʽ��ϴ�.");
        } catch (InvalidAccidentDateTimeException invalidAccidentDateTimeException) {
        	System.out.println(invalidAccidentDateTimeException.getMessage());
        }
	}
	
	private void checkInvalidDate(LocalDateTime accidentDateTime) throws InvalidAccidentDateTimeException {
		// ��û��¥�� ��û ���� ��¥ ������ ��
		if(accidentDateTime.isAfter(LocalDateTime.now())) {
			throw new InvalidAccidentDateTimeException("��û�� �� ���� ��¥�Դϴ�.");
		}
	}

    // �α��� ���� �Է� ���θ� üũ�Ѵ�
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// ����
		if(input.equals("")) {
			throw new InvalidInputException("�Է°��� �����ϴ�.");
		}
		return input;
	}
}
