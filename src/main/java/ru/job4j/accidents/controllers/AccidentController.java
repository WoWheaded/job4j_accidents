package ru.job4j.accidents.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.SimpleAccidentRuleService;
import ru.job4j.accidents.service.SimpleAccidentService;
import ru.job4j.accidents.service.SimpleAccidentTypeService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {

    private final SimpleAccidentService accidentsService;

    private final SimpleAccidentTypeService accidentTypeService;

    private final SimpleAccidentRuleService accidentRuleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAllAccidentTypes());
        model.addAttribute("rules", accidentRuleService.findAllAccidentRules());
        return "accidents/createAccident";
    }

    @GetMapping("/{id}")
    public String getAccidentById(@PathVariable int id, Model model) {
        Optional<Accident> accidentById = accidentsService.findAccidentById(id);
        if (accidentById.isEmpty()) {
            model.addAttribute("message", "Инцедент с идентификатором id: " + id + " не найден");
            return "errors/404";
        }
        model.addAttribute("accident", accidentById.get());
        model.addAttribute("types", accidentTypeService.findAllAccidentTypes());
        model.addAttribute("rules", accidentRuleService.findAllAccidentRules());
        return "accidents/oneAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("rIds") String[] ids) {
        accidentRuleService.setRuleById(ids, accident);
        accidentsService.createAccident(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/updateAccident/{id}")
    public String getUpdateAccidentPage(@PathVariable int id, Model model) {
        Optional<Accident> accident = accidentsService.findAccidentById(id);
        model.addAttribute("accident", accident.get());
        model.addAttribute("types", accidentTypeService.findAllAccidentTypes());
        model.addAttribute("rules", accidentRuleService.findAllAccidentRules());
        return "accidents/updateAccident";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, @RequestParam("rIds") String[] ids) {
        accidentRuleService.setRuleById(ids, accident);
        accidentsService.updateAccident(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/deleteAccident/{id}")
    public String deleteAccidentById(@PathVariable int id, Model model) {
        boolean isDeleted = accidentsService.deleteAccidentById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Инцедент с идентификатором id: " + id + " не удален");
            return "errors/404";
        }
        return "redirect:/";
    }
}
