package domain.insurance.test;

import domain.insurance.NonLifeInsurance;

import java.util.Scanner;

public class NonLifeInsuranceTest {
    public static NonLifeInsurance testCreation(Scanner scanner, String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        NonLifeInsurance insurance = new NonLifeInsurance();
        insurance.setName(name);
        InsuranceTest.testAddition(insurance);
        System.out.println(insurance);
        return insurance;
    }
}
