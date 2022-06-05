package domain.carAccidentHandling.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.carAccidentHandling.CarAccidentHandling;

public class CarAccidentHandlingTest {
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		test(scanner);
//		scanner.close();
//	}
//
//	public static void test(Scanner scanner) {
//		System.out.println("///// 사고처리접수 Test /////");
//
//		System.out.println("테스트 데이터 생성");
//		// 테스트 데이터 생성
//		// Customer에 관련 내용이 아직 없는 관계로 자체 생성
//		// Insurance
//		for (int i = 0; i < 3; i++) {
//			CarAccidentHandling e = testAdd(scanner);
//			claims.add(e);
//			claimRepository.add(e);
//		}
//		claimRepository.printAll();
//	}
//
//	public static CarAccidentHandling testAdd(Scanner scanner) {
//		CarAccidentHandling claim = new CarAccidentHandling();
////		System.out.println("접수자 본인 고객ID 입력: ");
////		claim.setAppliedCustomerId(scanner.nextLine().trim());
////		System.out.println("사고 경위 입력: ");
////		claim.setAccidentCause(scanner.nextLine().trim());
////		System.out.println("사고 지역 입력: ");
////		claim.setAccidentLocation(scanner.nextLine().trim());
////		System.out.println("사고 시각 입력: ");
////		//    	String year = scanner.nextLine().trim();
////		//    	String month = scanner.nextLine().trim();
////		//    	String day = scanner.nextLine().trim();
////		//    	String hour = scanner.nextLine().trim();
////		//    	String minute = scanner.nextLine().trim();
////		int timeElementCounts = 5;
////		int[] timeElement = new int[timeElementCounts];
////		for(int i = 0; i < timeElementCounts; i++) {
////			String temp = scanner.nextLine().trim();
////			if(validateNumInput(temp))
////				timeElement[i] =  Integer.parseInt(temp);
////			else {
////				System.out.println("시간을 입력해주시기 바랍니다.");
////				i--;
////			}
////		}
////		LocalDate accidentDate = LocalDate.of(timeElement[0], timeElement[1], timeElement[2]);
////		LocalTime accidentTime = LocalTime.of(timeElement[3], timeElement[4]);
////		claim.setAccidentDate(LocalDateTime.of(accidentDate, accidentTime));
//		return claim;
//	}
//
//	// 숫자 여부만 체크. 자릿수 넘기는 등은 일단 제외
//	private static boolean validateNumInput(String input) {
//		try {
//			Integer.parseInt(input);
//			return true;
//		}catch(NumberFormatException e1) {
//			return false;
//		}
//	}
}
