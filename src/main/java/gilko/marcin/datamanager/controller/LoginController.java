package gilko.marcin.datamanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping
	public String login() {
		return "login.html";
	}
}