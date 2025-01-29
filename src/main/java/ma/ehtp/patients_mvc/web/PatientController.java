package ma.ehtp.patients_mvc.web;


import org.springframework.data.domain.Page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ma.ehtp.patients_mvc.entities.Patient;
import ma.ehtp.patients_mvc.repositories.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String patients(Model model, 
    @RequestParam(name="page", defaultValue = "0") int page,
     @RequestParam(name="size", defaultValue = "5") int size,
     @RequestParam(name="keyword", defaultValue = "") String keyword){
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        

    
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id,String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    } 

     @GetMapping("/patients")
     @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
    
    

}
