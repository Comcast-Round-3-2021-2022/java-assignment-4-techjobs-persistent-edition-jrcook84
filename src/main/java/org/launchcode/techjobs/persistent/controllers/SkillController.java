package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
// handles request @ jobs jobs provided by skills/index.html
@RequestMapping("skills")
public class SkillController {
   // links to skills repo
    @Autowired
    private SkillRepository skillRepository;
// index
@GetMapping("")
public String displayAllSkills (Model model) {
    //if title is not null first model renders all jobs in fragments
   model.addAttribute("title", "skills");
    // uses name provided by index, finds all jobs in repo
   model.addAttribute("skills", skillRepository.findAll());
    return "skills/index";
}

// displayAddSkillForm
 @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

// processAddSkillForm
@PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

    if (errors.hasErrors()) {
        return "skills/add";
    }
    skillRepository.save(newSkill);
    return "redirect:";
}
    /* displayViewSkill */
//  @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {
            //uses employers id field to get correct info from repo had to set repo to find by id variable provided by parameters
        Optional optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("employer", skill);
            return "skill/view";
        } else {
            return "redirect:../";
        }
    }


}
