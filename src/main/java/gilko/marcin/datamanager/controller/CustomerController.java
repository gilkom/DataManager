package gilko.marcin.datamanager.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import gilko.marcin.datamanager.FileUploadUtil;
import gilko.marcin.datamanager.model.Customer;
import gilko.marcin.datamanager.model.Sale;
import gilko.marcin.datamanager.service.CustomerService;

@Controller
@RequestMapping("/customer_list")
public class CustomerController {
@Autowired
private CustomerService service;

@RequestMapping
public String viewCustomerPage(Model model) {
	List<Customer> listCustomers = service.listAll();
	model.addAttribute("listCustomers", listCustomers);
	return "customer_list";
}
@RequestMapping("/new_customer")
public String showNewCustomer(Model model) {
	Customer customer = new Customer();
	model.addAttribute("customer", customer);
	return "new_customer";
}
@RequestMapping(value= "/save_customer", method = RequestMethod.POST)
public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,
			BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile ) {
	if(bindingResult.hasErrors()) {
		return "new_customer";
	}
	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	if(!fileName.isEmpty()) {
		System.out.println("customer.setPhoto(" + fileName + ")");
		customer.setPhoto(fileName);
	}
	System.out.println("getId:" + customer.getId()+ ".");
	System.out.println("getPhoto:" + customer.getPhoto()+ ".");
	service.save(customer);
	
	String uploadDir = "user-photos/" + customer.getId();
	if(fileName.isEmpty()) {
		System.out.println("return 'redirect:/customer_list'");
		return "redirect:/customer_list";
	}
	try {
		System.out.println("save file");
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return  "redirect:/customer_list";
}
@RequestMapping("/edit_customer/{id}")
public ModelAndView showEditCustomerPage(@PathVariable(name="id") long id) {
	ModelAndView mav = new ModelAndView("edit_customer");
	Customer customer = service.get(id);
	mav.addObject("customer", customer);
	return mav;
}
@RequestMapping("/delete_customer/{id}")
public String deleteCustomer(@PathVariable(name = "id") long id) {
	service.delete(id);
	return "redirect:/customer_list";
}
}
