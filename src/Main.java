import domain.customer.test.CustomerTest;
import domain.insurance.test.InsuranceTest;
import domain.uw.test.UWTest;
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
