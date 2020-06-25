package gilko.marcin.datamanager.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

	@GetMapping("/")
	public String viewMainPage(Model model, HttpServletRequest request) {
		model.addAttribute("pageTitle", "Home");
		
		Locale currentLocale= request.getLocale();
		String countryCode = currentLocale.getCountry();
		String countryName = currentLocale.getDisplayCountry();
		
		String langCode = currentLocale.getLanguage();
		String langName = currentLocale.getDisplayLanguage();
		
		System.out.println(countryCode + ": " + countryName);
		System.out.println(langCode + ": " + langName);
		
		return "index";
	}
}
