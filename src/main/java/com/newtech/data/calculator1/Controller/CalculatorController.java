package com.newtech.data.calculator1.Controller;

import com.newtech.data.calculator1.Service.Calculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String calculatingForm(Model model) {
        model.addAttribute("calculator", new Calculator());
        return "calculator";
    }

    @PostMapping("/calculator")
    public String calculatingSubmit(@ModelAttribute Calculator calculator, Map<String, Object> map) {
        String expr = calculator.getExpr();
        String answer = calculator.calculate(expr);
        map.put("answer", answer);
        return "calculator";
    }

}
