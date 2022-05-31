package domain.carAccidentHandling.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.carAccidentHandling.CarAccidentHandling;
import repository.claim.ClaimListImpl;

public class CarAccidentHandlingTest {
	private static final ClaimListImpl claimRepository = ClaimListImpl.getInstance();
	private static final ArrayList<CarAccidentHandling> claims = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		test(scanner);
		scanner.close();
	}

	public static void test(Scanner scanner) {
		System.out.println("///// ���ó������ Test /////");

		System.out.println("�׽�Ʈ ������ ����");
		// �׽�Ʈ ������ ����
		// Customer�� ���� ������ ���� ���� ����� ��ü ����
		// Insurance
		for (int i = 0; i < 3; i++) {
			CarAccidentHandling e = testAdd(scanner);
			claims.add(e);
			claimRepository.add(e);
		}	
		claimRepository.printAll();
	}

	public static CarAccidentHandling testAdd(Scanner scanner) {
		CarAccidentHandling claim = new CarAccidentHandling();
		System.out.println("������ ���� ��ID �Է�: ");
		claim.setAppliedCustomerId(scanner.nextLine().trim());
		System.out.println("��� ���� �Է�: ");
		claim.setAccidentCause(scanner.nextLine().trim());
		System.out.println("��� ���� �Է�: ");
		claim.setAccidentLocation(scanner.nextLine().trim());
		System.out.println("��� �ð� �Է�: ");
		//    	String year = scanner.nextLine().trim();
		//    	String month = scanner.nextLine().trim();
		//    	String day = scanner.nextLine().trim();
		//    	String hour = scanner.nextLine().trim();
		//    	String minute = scanner.nextLine().trim();
		int timeElementCounts = 5;
		int[] timeElement = new int[timeElementCounts];
		for(int i = 0; i < timeElementCounts; i++) {
			String temp = scanner.nextLine().trim();
			if(validateNumInput(temp)) 
				timeElement[i] =  Integer.parseInt(temp);	
			else {
				System.out.println("�ð��� �Է����ֽñ� �ٶ��ϴ�.");
				i--;
			}
		}
		LocalDate accidentDate = LocalDate.of(timeElement[0], timeElement[1], timeElement[2]);
		LocalTime accidentTime = LocalTime.of(timeElement[3], timeElement[4]);
		claim.setAccidentDate(LocalDateTime.of(accidentDate, accidentTime));
		return claim;
	}

	// ���� ���θ� üũ. �ڸ��� �ѱ�� ���� �ϴ� ����
	private static boolean validateNumInput(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}catch(NumberFormatException e1) {
			return false;
		}
	}
}
