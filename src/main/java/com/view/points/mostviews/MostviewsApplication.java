package com.view.points.mostviews;

import com.view.points.mostviews.services.TripAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableFeignClients
public class MostviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MostviewsApplication.class, args);
	}

}
