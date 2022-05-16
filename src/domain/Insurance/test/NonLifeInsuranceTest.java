package domain.Insurance.test;

import domain.Insurance.NonLifeInsurance;

import java.util.Scanner;

public class NonLifeInsuranceTest {
    public static NonLifeInsurance testCreation(Scanner scanner, String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        NonLifeInsurance insurance = NonLifeInsurance.create(name);
        InsuranceTest.testAddition(insurance);
        System.out.println(insurance);
        return insurance;
    }
}
