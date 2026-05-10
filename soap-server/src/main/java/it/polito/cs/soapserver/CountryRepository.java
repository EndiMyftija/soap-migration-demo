package it.polito.cs.soapserver;

import it.polito.cs.soapserver.generated.Country;
import it.polito.cs.soapserver.generated.Currency;
import it.polito.cs.soapserver.system.ObjectNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country albania = new Country();
        albania.setCapital("Tirana");
        albania.setCurrency(Currency.EUR);
        albania.setName("Albania");
        albania.setPopulation(260_000_0);

        countries.put(albania.getName(), albania);
    }

    public Country findCountryByName(String countryName) throws ObjectNotFoundException {
        System.out.println(countryName);
        System.out.println(countries.get(countryName));
        if (countries.get(countryName) == null) {
            throw new ObjectNotFoundException("country", countryName);
        }

        return countries.get(countryName);
    }
}
