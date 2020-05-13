package gilko.marcin.datamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gilko.marcin.datamanager.service.ProductService;

@Controller
public class AppController {
	@Autowired
	private ProductService service;
}
