package gilko.marcin.datamanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gilko.marcin.datamanager.model.Product;
import gilko.marcin.datamanager.service.ProductService;

@Controller
@RequestMapping("/product_list")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@RequestMapping
	public String viewProductPage(Model model) {
		//List<Product> listProducts = service.listAll();
		//model.addAttribute("listProducts", listProducts);
		//return "product_list";
		return viewPage(model, 1, "name", "asc", "");
	}
	@RequestMapping("/page/{pageNum}")
	public String viewPage(Model model, 
			@PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		
		Page<Product> page = service.listAll(pageNum, sortField, sortDir, keyword);
		
		List<Product> listProducts = page.getContent();
		
		
		model.addAttribute("currentPage", pageNum);		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("listProducts", listProducts);
		return "product_list";
	}
	
	@RequestMapping("/new_product")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "new_product";
		}
		service.save(product);	
		return "redirect:/product_list";
	}
	@RequestMapping("/edit/{id}")
	public ModelAndView  showEditProductPage(@PathVariable(name = "id") long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		return mav;
	}
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") long id) {
		service.delete(id);
		return "redirect:/product_list";
	}
}
