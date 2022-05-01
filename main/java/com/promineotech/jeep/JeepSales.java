package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class JeepSales {

	public static void main(String[] args) {
		SpringApplication.run(JeepSales.class, args);
		System.out.println();
	}


}
