package com.jpa.demo.wep;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpa.demo.entities.Patient;
import com.jpa.demo.reposotory.PatientRepository;

@Controller
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
  @GetMapping(path = "/index")
	public String index() {
	return "index";
}
  
  @GetMapping(path = "/patients")
	public String listPatient(Model model ,@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="keyword",defaultValue = "")String keyword) {
	Page<Patient> pagesPatients=patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
	model.addAttribute("patients",pagesPatients.getContent());
	model.addAttribute("pages", new int[pagesPatients.getTotalPages()]);
	model.addAttribute("currentPage",page);
	model.addAttribute("size",size);
	model.addAttribute("keyword",keyword);
	return "patients";
}
  @GetMapping(path="/deletePatient")
  public String delete(Long id,String keyword,int page,int size) {
	  patientRepository.deleteById(id);
	  return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
  }
  @GetMapping(path="/formPatient")
  public String formPatient(Model model) {
	  model.addAttribute("patient",new Patient());
	  model.addAttribute("mode","new");
	  return "formPatient";
	  
  }

  
  @PostMapping(path="/savePatient")
  public String savePatient(@Valid Patient patient,BindingResult bindingResult,Model model) {
	if(bindingResult.hasErrors())  return "formPatient";
	  patientRepository.save(patient);
	  model.addAttribute("message","Enregistrement a ete effectue avec sucess pour le patient "+patient.getNom());
	  return "formPatient";
	  
  }
  @GetMapping(path="/editPatient")
  public String editPatient(Model model,Long id) {
	  Patient p=patientRepository.findById(id).get();
	  model.addAttribute("mode","edit");
	  model.addAttribute("patient",p);
	  return "formPatient";
	  
  }
  
  @InitBinder     
  public void initBinder(WebDataBinder binder){
       binder.registerCustomEditor(       Date.class,     
                           new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
  }
  
}
