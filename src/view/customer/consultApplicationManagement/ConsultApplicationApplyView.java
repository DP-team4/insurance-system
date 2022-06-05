package view.customer.consultApplicationManagement;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

import domain.consultApplication.ConsultApplication;
import domain.customer.Customer;
import exception.InvalidConsultDateTimeException;
import exception.InvalidInputException;
import service.customer.ConsultApplicationService;
import service.customer.ConsultApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class ConsultApplicationApplyView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
	
    // View
	private ConsultApplicationListView consultApplicationListView;
	
	// Service
	private ConsultApplicationService consultApplicationService = ConsultApplicationServiceImpl.getInstance();
	
	public void show(ConsultApplicationListView consultationListView, Customer customer) {
		try {
			this.consultApplicationListView = consultationListView;
			this.customer = customer;
			// ��� ��û ȭ��(��� ����, ��� ��¥(��, ��, ��, ��, ��) �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
			System.out.println("\n[ ���� ��� ��û ]");
	        System.out.print("��� ���� >> "); String content = getInputAndCheckInvalid();
	        System.out.println("��� ��¥ (��û�� �ּ� �Ϸ� �ĺ��� �����մϴ�) >> ");
	        System.out.print("��(year) : "); String year = getInputAndCheckInvalid();
	        System.out.print("��(month) : "); String month = getInputAndCheckInvalid()
	        System.out.print("��(day) : "); String dayOfMonth = getInputAndCheckInvalid()
	        System.out.print("��(hour) : "); String hour = getInputAndCheckInvalid()
	        System.out.print("��(minute) : "); String minute = getInputAndCheckInvalid()
	        System.out.print("\n����� ��û�Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	            // A1. ��� ��û ����� �� �Է��� ���
	        	applyConsultation(content, year, month, dayOfMonth, hour, minute);
	        } else {
	        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
	    		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
	    		if(input.equals("1")) return;
	        	applyConsultation(content, year, month, dayOfMonth, hour, minute);
	        }
		} catch (InvalidInputException invalidInputException) {
        	System.out.println(invalidInputException.getMessage());
        }
	}

	// ��� ��û ������ ������ ��û�Ѵ�
	private void applyConsultation(String content, String year, String month, String dayOfMonth, String hour, String minute) {
		ConsultApplication consultApplication = new ConsultApplication();
        consultApplication.setCustomerId(customer.getId());
        consultApplication.setContent(content);
        try {
            LocalDateTime consultDateTime = LocalDateTime.of(
            		Integer.parseInt(year),
            		Integer.parseInt(month),
            		Integer.parseInt(dayOfMonth),
            		Integer.parseInt(hour),
            		Integer.parseInt(minute)
            );
            consultApplication.setConsultationDate(consultDateTime);
            checkInvalidDate(consultDateTime);
            // "������ �Ϸ� �Ǿ����ϴ�" �޽����� �����ְ� ���� ��� ��û ��Ȳ ȭ���� �����ش�
    		if(consultApplicationService.applyConsultation(consultApplication)) {
    			System.out.println("\n������ �Ϸ� �Ǿ����ϴ�.");
    			consultApplicationListView.show(customer);
    		}
            else System.out.println("\n����û ����. ���� ȭ������ ���ư��ϴ�.");
        } catch (NumberFormatException e) {
        	// A3. ����û���뿡 ��ȿ���� ���� ���� �Է��� ���
        	System.out.println("�Է°��� ��ȿ���� �ʽ��ϴ�.");
        } catch (DateTimeException dateTimeException) {
        	System.out.println("��¥�� �ùٸ��� �ʽ��ϴ�.");
        } catch (InvalidConsultDateTimeException invalidConsultDateTimeException) {
        	System.out.println(invalidConsultDateTimeException.getMessage());
        }
	}
	
	private void checkInvalidDate(LocalDateTime consultDateTime) throws InvalidConsultDateTimeException {
		// ��û��¥�� �ּ� ��û ���� ��¥(���� + 1��)���� ������ ��
		if(consultDateTime.isBefore(LocalDateTime.now().plusDays(1))) {
			throw new InvalidConsultDateTimeException("��û�� �� ���� ��¥�Դϴ�.");
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
