package gilko.marcin.datamanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gilko.marcin.datamanager.model.Customer;
import gilko.marcin.datamanager.service.CustomerService;

@Controller
@RequestMapping("/customer_list")
public class CustomerController {
@Autowired
private CustomerService service;


public String viewCustomerPage(Model model) {
	List<Customer> listCustomers = service.listAll();
	model.addAttribute("listCustomers", listCustomers);
	return "customer_list";
}
}
