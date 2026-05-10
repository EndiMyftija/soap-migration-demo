package it.polito.cs.soapclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RequestInitializer implements CommandLineRunner {

    private final CountryService countryService;

    public RequestInitializer(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(String... args) throws Exception {
        String capitalName = countryService.getCountryCapital("ALB");
        System.out.printf("Capital of %s is %s%n", "ALB", capitalName);
    }
}
