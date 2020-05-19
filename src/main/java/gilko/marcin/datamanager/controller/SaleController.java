package gilko.marcin.datamanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.datamanager.model.Sale;
import gilko.marcin.datamanager.service.SaleService;

@Controller
@RequestMapping("/sale_list")
public class SaleController {
	@Autowired
	private SaleService service;
	
	@RequestMapping
	public String viewSalePage(Model model) {
		List<Sale> listSale = service.list();
		model.addAttribute("listSale", listSale);
		return "sale_list";
	}
	@RequestMapping("/new_sale")
	public String showNewForm(Model model) {
		Sale sale = new Sale();
		model.addAttribute("sale", sale);
		return "new_sale";
	}
	@RequestMapping(value = "/save_sale",method = RequestMethod.POST)
	public String saveSale(@ModelAttribute("product") Sale sale) {
		service.save(sale);
		return "redirect:/sale_list";
	}
	@RequestMapping("/edit_sale/{id}")
	public ModelAndView showEditSalePage(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView("edit_sale");
		Sale sale = service.get(id);
		mav.addObject("sale", sale);
		return mav;
	}
	@RequestMapping("/delete_sale/{id}")
	public String deleteSale(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/sale_list";
	}
}
