package ru.job4j.accidents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.SimpleAccidentService;

@Controller
public class IndexController {
    private final SimpleAccidentService simpleAccidentService;

    @Autowired
    public IndexController(SimpleAccidentService simpleAccidentService) {
        this.simpleAccidentService = simpleAccidentService;
    }

    @GetMapping("/")
    public String getAllAccidents(Model model) {
        model.addAttribute("user", "Dmitry Arinin");
        model.addAttribute("accidents", simpleAccidentService.findAllAccidents());
        return "index";
    }
}