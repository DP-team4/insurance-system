package domain.Insurance.test;

import domain.Insurance.FireInsurance;

import java.util.Scanner;

public class FireInsuranceTest {
    public static FireInsurance testCreation(Scanner scanner, String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        FireInsurance fireInsurance = FireInsurance.create(name);
        InsuranceTest.testAddition(fireInsurance);
        System.out.println(fireInsurance);
        return fireInsurance;
    }
}
