import view.CustomerView;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("�̿��� ���񽺸� �������ּ��� - ��(1), ����(2) >> "); String input = scanner.nextLine().trim();
		if(input.equals("1")) new CustomerView(scanner).showView();
		else if(input.equals("2")) System.out.println("���� ȭ�� �̱���"); // new EmployeeView().show();
		else System.out.println("Invalid Option");
	}

}
