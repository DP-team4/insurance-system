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
			// �����������(�������, �����Ͻ�, ������(������, �ֹι�ȣ)), '��û�ϱ�', '����ϱ�' ��ư�� �����ش�
			System.out.println("\n[ ����� û�� ]");
			System.out.println("û���� �����ǰ ������ �������ּ��� -");
			System.out.print("�ػ���(1), �����ں���(2), ȭ�纸��(3) >> ");
			String input = scanner.nextLine().trim();
	        // �Է¿� �´� ����� �����´�.
	        this.insuranceCategory = null;
			switch(input) {
				case "1": insuranceCategory = InsuranceCategory.MARINE; break;
				case "2": insuranceCategory = InsuranceCategory.DRIVER; break;
				case "3": insuranceCategory = InsuranceCategory.FIRE; break;
				// A3. �ùٸ��� ���� �˻� ������ �Է��� ��� -> "�Է°��� ��ȿ���� �ʽ��ϴ�" �޽����� �����ش�
				default : System.out.println("\n�Է°��� ��ȿ���� �ʽ��ϴ�."); return;
			}
			// �ڰ� �˻�
			contract = customerContractService.getUnmaturedContractByCategory(this.customer.getId(), insuranceCategory);
			if(contract == null) { System.out.println("����� û�� ������ ���� ������ ���� ���񽺸� �̿��Ͻ� �� �����ϴ�. ����ȭ������ ���ư��ϴ�."); return; }

	        System.out.print("û�� ���� >> "); String content = getInputAndCheckInvalid();
	        System.out.println("��� ��¥ >> ");
	        System.out.print("��(year) : "); String year = getInputAndCheckInvalid();
	        System.out.print("��(month) : "); String month = getInputAndCheckInvalid();
	        System.out.print("��(day) : "); String dayOfMonth = getInputAndCheckInvalid();
	        System.out.print("��(hour) : "); String hour = getInputAndCheckInvalid();
	        System.out.print("��(minute) : "); String minute = getInputAndCheckInvalid();
			switch(insuranceCategory) {
				case MARINE:
				case FIRE:
					System.out.print("������� : ");
					break;
				case DRIVER:
					System.out.print("�������� : ");
					break;
				default: System.out.println("ERROR"); break;
			}
			String totalLoss = getInputAndCheckInvalid();
	        System.out.print("\n����� ��û�Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	        	applyBenefitPayment(year, month, dayOfMonth, hour, minute, content, totalLoss);
	        } else {
	        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
	    		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
	    		if(input.equals("1")) return;
	        	applyBenefitPayment(year, month, dayOfMonth, hour, minute, content, totalLoss);
	        }
	    // A1. ��� ��û ����� �� �Է��� ���
		} catch (InvalidInputException invalidInputException) {
        	System.out.println(invalidInputException.getMessage());
        }	
	}

	// ���ó�� ��û ������ ���� ��û�Ѵ�
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
            // "������ �Ϸ� �Ǿ����ϴ�" �޽����� �����ְ� ����� û�� ��Ȳ ȭ���� �����ش�
    		if(customerBenefitPaymentService.applyBenefitPayment(benefitPayment)) {
    			System.out.println("\n������ �Ϸ� �Ǿ����ϴ�.");
    			customerBenefitPaymentListView.show(customer);
    		} else System.out.println("\n����� û�� ����. ���� ȭ������ ���ư��ϴ�.");
        } catch (NumberFormatException e) {
        	// A3. ����û���뿡 ��ȿ���� ���� ���� �Է��� ���
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
