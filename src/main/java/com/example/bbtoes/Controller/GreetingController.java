package com.example.bbtoes.Controller;

import com.example.bbtoes.Services.TestConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.bbtoes.Model.Test;

@Controller
public class GreetingController {

    @GetMapping("/TestEntry")
    public String greetingForm(Model model) {
        model.addAttribute("test", new Test());
        System.out.println("Hello");
        return "greeting";
    }



    @PostMapping("/TestPrepared")
    public String greetingSubmit(@ModelAttribute Test test, Model model, @Autowired TestConversionService conversionService) {

        System.out.println("POST mapping called from greeting html form");
        String data = test.getTestData();
//        System.out.println(data);
       String Returned = conversionService.testConvert(test);
        model.addAttribute("testFormatted", Returned);
        return "result";
    }

}