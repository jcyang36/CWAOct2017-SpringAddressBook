package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/list")
    public String listPersons(Model model){
        model.addAttribute("people", personRepository.findAll());
    return "list";}

    @RequestMapping("/")
    public String goHomePage(Model model){
        model.addAttribute("people", personRepository.findAll());
        return "main";}

    @RequestMapping("/main")
    public String goMainPage(Model model){
        model.addAttribute("people", personRepository.findAll());
        return "main";}

    @GetMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("person", new Person());
        return "personform";}

    @PostMapping("/process")
    public String processForm(@Valid Person person, BindingResult result){
    if (result.hasErrors()){
        return "personform";
    }
    personRepository.save(person);
    return "redirect:/list";
    }
    @RequestMapping("/detail/{id}")
    public String showPerson(@PathVariable("id") long id, Model model){model.addAttribute("person", personRepository.findOne(id));
    return "show";}

    @RequestMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personRepository.findOne(id));
        return "personform";
    }

    @RequestMapping("/delete/{id}")
    public String delPerson(@PathVariable("id") long id, Model model){
       personRepository.delete(id);
        return "redirect:/";
    }
}
