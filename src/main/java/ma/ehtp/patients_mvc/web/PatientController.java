package ma.ehtp.patients_mvc.web;


import org.springframework.data.domain.Page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import ma.ehtp.patients_mvc.entities.Patient;
import ma.ehtp.patients_mvc.repositories.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/user/index")
    public String patients(Model model, 
    @RequestParam(defaultValue = "0") int page,
     @RequestParam(defaultValue = "5") int size,
     @RequestParam(defaultValue = "") String keyword){
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        

    
        return "patients";
    }
    @GetMapping("/admin/delete")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id,String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    } 

     @GetMapping("/patients")
     @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
    
    @GetMapping("/admin/formPatients")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }


    @PostMapping(path = "/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult,@RequestParam(defaultValue = "") String keyword,@RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/admin/editPatient")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient p = patientRepository.findById(id).orElse(null);
        if(p == null) throw new RuntimeException("Patient not found");
        model.addAttribute("patient", p);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "editPatient";
    }
    

   
    
    

}
