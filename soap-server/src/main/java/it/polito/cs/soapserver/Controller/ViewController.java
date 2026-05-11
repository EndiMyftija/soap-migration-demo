package it.polito.cs.soapserver.Controller;

import it.polito.cs.soapserver.CountryRepository;
import it.polito.cs.soapserver.generated.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    private final CountryRepository countryRepository;

    @Autowired
    public ViewController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("message", "Welcome to the Monolith");
        return "dashboard"; // Points to src/main/resources/templates/dashboard.html
    }

    @GetMapping("/countries")
    public String countryPage(@RequestParam(required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            try {
                Country country = countryRepository.findCountryByName(name);
                model.addAttribute("country", country);
            } catch (Exception e) {
                model.addAttribute("error", "Country not found in legacy database.");
            }
        }
        return "countries";
    }
}
