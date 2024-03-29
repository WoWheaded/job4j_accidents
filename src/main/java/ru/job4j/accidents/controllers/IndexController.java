package ru.job4j.accidents.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.DataAccidentService;

@Controller
@AllArgsConstructor
public class IndexController {

    private final DataAccidentService simpleAccidentService;

    @GetMapping({"/", "/accidents"})
    public String getAllAccidents(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", simpleAccidentService.findAllAccidents());
        return "index";
    }
}