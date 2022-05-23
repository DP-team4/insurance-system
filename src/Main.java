import domain.Customer.test.CustomerTest;
import domain.Insurance.test.InsuranceTest;
import domain.UW.test.UWTest;
import domain.cancelApplication.test.CancelApplicationTest;
import domain.consultApplication.test.ConsultApplicationTest;
import domain.contract.test.ContractTest;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		InsuranceTest.test(scanner);
		UWTest.test(scanner);
		CustomerTest.test(scanner);
		ContractTest.test(scanner);
		CancelApplicationTest.test(scanner);
		ConsultApplicationTest.test(scanner);
	}

}
