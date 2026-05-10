package it.polito.cs.soapclient;

import it.polito.cs.soapclient.countryinfo.CountryInfoService;
import it.polito.cs.soapclient.countryinfo.CountryInfoServiceSoapType;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    public String getCountryCapital(String countryISOCode) {
        CountryInfoService service = new CountryInfoService();

        CountryInfoServiceSoapType port = service.getCountryInfoServiceSoap();

        return port.capitalCity(countryISOCode);
    }
}