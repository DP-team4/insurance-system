package domain.Insurance.test;

import domain.Insurance.MarineInsurance;

import java.util.Scanner;

public class MarineInsuranceTest {
    public static MarineInsurance testCreation(Scanner scanner, String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        MarineInsurance insurance = MarineInsurance.create(name);
        InsuranceTest.testAddition(insurance);
        System.out.println(insurance);
        return insurance;
    }
}
