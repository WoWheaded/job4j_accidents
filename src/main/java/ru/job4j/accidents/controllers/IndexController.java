package ru.job4j.accidents.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.SimpleAccidentService;

@Controller
@AllArgsConstructor
public class IndexController {

    private final SimpleAccidentService simpleAccidentService;

    @GetMapping({"/", "/accidents"})
    public String getAllAccidents(Model model) {
        model.addAttribute("user", "Dmitry Arinin");
        model.addAttribute("accidents", simpleAccidentService.findAllAccidents());
        return "index";
    }
}