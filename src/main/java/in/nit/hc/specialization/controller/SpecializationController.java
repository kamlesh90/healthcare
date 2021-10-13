package in.nit.hc.specialization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.specialization.entity.Specialization;
import in.nit.hc.specialization.exception.SpecializationNotFoundException;
import in.nit.hc.specialization.service.ISpecializationService;
import in.nit.hc.specialization.view.SpecializationExcelView;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService specService;
	
	/***
	 * 
	 * 	1. show register 
	 */
	
	@GetMapping("/spec-register")  																		// spec/spec-register
	public String showSpecRegister() {
		
		return "SpecializationRegister";
	}
	
	/**
	 * 
	 * 	2. save 
	 */
	
	@PostMapping("/save")														    					// spec/save
	public String saveSpecialization(@ModelAttribute Specialization spec, Model model) {
		
		Long id = specService.saveSpecialization(spec);
		String message = "Specialization record with ID ("+id+") has been created remember ID for future refference";
		model.addAttribute("message", message);
		
		return "redirect:spec-register";
	}
	
	/**
	 * 
	 * 
	 *  3. show all Specialization
	 */
	
	@GetMapping("/all")																					// spec/all
	public String getAllSpecialization(Model model) {
		
		List<Specialization> specList = specService.getAllSpecializations();
		model.addAttribute("specList", specList);
		
		return "SpecializationData";
	}
	
	/***
	 * 	4. remove Specialization based on id 
	 *     but here first we need to check with in DB if Specialization 
	 *     found the delete it or else   throw Error not found
	 * 	   and redirect to all page to fetch latest data after delete
	 */
	
	@GetMapping("/delete")																			   			// spec/delete
	public String removeSpecialization(@RequestParam(required = true) Long id, RedirectAttributes attributes) {
		
		try {
			specService.removeSpecialization(id);
			attributes.addAttribute("delMsg", "Specialization (\"+id+\") has been deleted successfully!!");
		}catch (SpecializationNotFoundException snfe) {
			snfe.printStackTrace();
			attributes.addAttribute("errorDelMsg", snfe.getMessage());
		}
		
		return "redirect:all";
	}
	
	/***
	 * 	5. update form data and redirect to all 
	 * 
	 */
	
	@PostMapping("/update")
	public String updateSpecialization(@ModelAttribute Specialization spec, RedirectAttributes attributes) {
		
		specService.updateSpecialization(spec);
		attributes.addFlashAttribute("message", "Specialization ("+spec.getId()+") is updated!!");
		
		return "redirect:all";
	}
	
	/****
	 * 	6. to edit respective record click on edit button 
	 * 	   so it will load data to edit page if it is there in 
	 * 	   DB if not then show Error message (Not found)   
	 */
	
	@GetMapping("/edit")
	public String showEditpage(@RequestParam Long id, Model model, RedirectAttributes attributes){
		
		String page = null;
		
		try {
			Specialization spec = specService.getOneSpecialization(id);
			model.addAttribute("spec", spec);
			page = "SpecializationEdit";
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		
		return page;
	}
	
	/****
	 * 	7. To check where the entered specCode is exist in DB Duplicate check
	 *  here when we enter specCode in code field then in background ajax call trigger to backend
	 */
	
	@GetMapping("/checkcode")  												
	@ResponseBody
	public String validateSpecCode(@RequestParam String specCode, @RequestParam Long id) {
		System.out.println("Long id "+id);
		String message = "";
		
		if(id==0 && specService.isSpecCodeExist(specCode)) {	// register check
			message = specCode + " Already Exist !!";
		}else if(id!=0 && specService.isSpecCodeExist(specCode, id)) {	// edit check
			message = specCode + "Already Exist !!";
		}
		
		return message;	//this is not viewName(it is message) that's why we need to annotate this method with @ResponseBody
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		
		ModelAndView mv = new ModelAndView();
		mv.setView(new SpecializationExcelView());

		List<Specialization> list = specService.getAllSpecializations();
		mv.addObject("list", list);
		
		return mv;
	}
	
}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
