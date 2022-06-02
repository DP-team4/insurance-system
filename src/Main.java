import view.CustomerView;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이용할 서비스를 선택해주세요 - 고객(1), 직원(2) >> "); String input = scanner.nextLine().trim();
		if(input.equals("1")) new CustomerView(scanner).showView();
		else if(input.equals("2")) System.out.println("직원 화면 미구현"); // new EmployeeView().show();
		else System.out.println("Invalid Option");
	}

}
