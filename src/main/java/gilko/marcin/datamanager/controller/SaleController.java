package gilko.marcin.datamanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gilko.marcin.datamanager.model.Sale;
import gilko.marcin.datamanager.service.SaleService;

@Controller
public class SaleController {
	@Autowired
	private SaleService service;
	
	@RequestMapping("/sale_list")
	public String viewSalePage(Model model) {
		List<Sale> listSale = service.list();
		model.addAttribute("listSale", listSale);
		return "sale_list";
	}
}
