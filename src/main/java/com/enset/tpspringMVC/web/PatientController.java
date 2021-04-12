package com.enset.tpspringMVC.web;

import com.enset.tpspringMVC.entities.Patient;
import com.enset.tpspringMVC.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class PatientController {
	@Autowired
	PatientRepository patientRepository;
	
	@GetMapping(path="/index")
	public String listPatients(Model model
			,@RequestParam(name="motCle",defaultValue="")String mc
			,@RequestParam(name="page",defaultValue="0")int page
			,@RequestParam(name="size",defaultValue="5")int size
			) {
		Page<Patient> patients = patientRepository.findByNomContains(mc,PageRequest.of(page,size));
		model.addAttribute("listePatients", patients.getContent());
		model.addAttribute("motCle", mc);
		//System.out.println(patients.getTotalPages());
		model.addAttribute("pages", new  int[patients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		return "PatientsView";
	}
	
	@GetMapping(path="/deletePatient")
	public String delete(Long id, @RequestParam(name="page",defaultValue="0")int page, @RequestParam(name="motCle",defaultValue="")String  key) {
		patientRepository.deleteById(id);
		return "redirect:/index?page="+page+"&motCle="+key;
	}


	@GetMapping(path="/updatePatient")
	public String update(Long id,Model model) {
		//patientRepository;
		Patient p = patientRepository.findById(id).get();
		model.addAttribute("patient",p);
		model.addAttribute("type","Update");
		return "formPatient";
	}
	
	@GetMapping(path="/formPatient")
	public String insertPatient(Model model) {
		model.addAttribute("patient",new Patient(null,"",new Date(),10,true));
		model.addAttribute("type","Save");
		return "formPatient";
	}
	@PostMapping(path="/savePatient")
	public String insertPatient(@Valid Patient p, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) return "formPatient";
		model.addAttribute("patient",p);
		if(p.getId() == null)
			model.addAttribute("type","Save");
		else
			model.addAttribute("type","Update");
		patientRepository.save(p);
		return "cofirme";
	}
	@GetMapping("/notAutorized")
	public String error(){
		return "notAutorized";
	}


}
