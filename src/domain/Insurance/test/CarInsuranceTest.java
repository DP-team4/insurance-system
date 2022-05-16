package domain.Insurance.test;

import domain.Insurance.CarInsurance;

import java.util.Scanner;

public class CarInsuranceTest {
    public static CarInsurance testCreation(Scanner scanner, String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        CarInsurance insurance = CarInsurance.create(name);
        InsuranceTest.testAddition(insurance);
        System.out.println(insurance);
        return insurance;
    }
}
