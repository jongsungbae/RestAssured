package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.javafaker.Faker;

public class RestUtils {
	
	
	public static String empName() {
		Faker faker = new Faker();
		String name = faker.name().firstName();
		return name;
	}
	
	public static String empSal() {
		String salary = RandomStringUtils.randomNumeric(5);
		return salary;
	}
	
	public static String empAge() {
		String age = RandomStringUtils.randomNumeric(2);
		return age;
	}

}
