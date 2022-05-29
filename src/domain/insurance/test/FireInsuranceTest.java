package domain.insurance.test;

import domain.insurance.FireInsurance;

import java.util.Scanner;

public class FireInsuranceTest {
    public static FireInsurance testCreation(Scanner scanner, String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        FireInsurance fireInsurance = new FireInsurance();
        fireInsurance.setName(name);
        InsuranceTest.testAddition(fireInsurance);
        System.out.println(fireInsurance);
        return fireInsurance;
    }
}