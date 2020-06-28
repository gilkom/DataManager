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
		return "index";
	}
}
