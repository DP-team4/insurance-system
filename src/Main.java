import domain.Insurance.test.InsuranceTest;
import domain.UW.test.UWTest;
import domain.contract.test.ContractTest;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		InsuranceTest.test(scanner);
		UWTest.test(scanner);
		ContractTest.test(scanner);
	}

}
