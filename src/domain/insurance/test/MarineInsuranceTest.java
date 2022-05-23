package domain.insurance.test;

import domain.insurance.MarineInsurance;

import java.util.Scanner;

public class MarineInsuranceTest {
    public static MarineInsurance testCreation(Scanner scanner, String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        MarineInsurance insurance = new MarineInsurance();
        insurance.setName(name);
        InsuranceTest.testAddition(insurance);
        System.out.println(insurance);
        return insurance;
    }
}
