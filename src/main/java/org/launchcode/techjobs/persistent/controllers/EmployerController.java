package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {


    @Autowired
   // private field of ER type @autoWired allows employerRepository to get rows of a table in the database
    private EmployerRepository employerRepository;
// handles request at the root
@GetMapping("")
public String index (Model model) {
    //if title is not null first model renders all employers in fragments
   model.addAttribute("title", "All Employers");
   // uses name provided by index, finds all employers in repo
   model.addAttribute("employers", employerRepository.findAll());
   return "employers/index";
}

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }
      // if information is valid it is saved to the repo
        employerRepository.save(newEmployer);
        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
            //uses employers id field to get correct info from repo had to set repo to find by id variable provided by parameters
        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            model.addAttribute("title", "Edit Employer " +
                    employer.getName() + "(id="+ employer.getId() + ")");
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
