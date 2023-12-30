package com.example.bbtoes.Controller;

import com.example.bbtoes.Model.Test;

import com.example.bbtoes.Services.TestConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.lang.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
//        System.out.println(test.getTestData());
           String testDataTest = """
Question 1 

1 

points 

Which of the following actions would BEST assess the strength of the supraspinatus? 

 

Answer 1 

Have the patient abduct the arm from the fully adducted position against resistance 

 

Correct answer 

Answer 2 

Have the patient adduct the arm from the fully abducted position against resistance 

 

Answer 3 

Have the patient extend the elbow, abduct the arm and medially rotate against resistance 

 

Answer 4 

Have the patient flex the elbow, abduct the arm and medially rotate against resistance 

 

Question 2 

1 

points 

Compression of which of the following nerves may result in weak thumb flexion? 

 

Answer 1 

Deep Ulnar N 

 

Answer 2 

Posterior Interosseus N 

 

Answer 3 

Recurrent branch of Median N 

 

Correct answer 

Answer 4 

Superficial Radial N 

 

Question 3 

1 

points 

A patient with suspected nerve entrapment experiences weakness with elbow extension and numbness over the dorsum of the wrist and hand. Where is the MOST LIKELY location of this entrapment? 

 

Answer 1 

Cubital Fossa 

 

Answer 2 

Cubital Tunnel 

 

Answer 3 

Quadrangular Space 

 

Answer 4 

Triangular Interval 

 

Correct answer 

Question 4 

1 

points 

A patient with a complete injury to the musculocutaneous nerve at the level of coracobrachialis would have some ability to flex at the elbow because of the action of which of the following muscles? 

 

Answer 1 

Bicep brachii m. 

 

Answer 2 

Brachialis m. 

 

Answer 3 

Brachioradialis m. 

 

Correct answer 

Answer 4 

Ulnar head of flexor carpi ulnaris m. 

 

Question 5 

1 

points 

With the origin fixed, what are the primary actions of the pectoralis major muscle on the humerus? 

 

Answer 1 

Abduction and lateral rotation 

 

Answer 2 

Abduction and medial rotation 

 

Answer 3 

Adduction and lateral rotation 

 

Answer 4 

Adduction and medial rotation 

 

Correct answer 
                   """.replaceAll("\n\n"," \r\n\r\n").replaceAll("\r\n\r\n\r\n\r\n", "\r\n\r\n \r\n\r\n").trim();
           if(test.getTestData().contains(testDataTest)){
               System.out.println("HELLLOOOO");
           }else{
               System.out.println("nonononono");
           }
        System.out.println("----DIFF-----");
        System.out.println(StringUtils.difference(test.getTestData(),testDataTest));
        System.out.println("-----DIFF-----");
//        System.out.println(data);
       String Returned = conversionService.testConvert(test);
        model.addAttribute("testFormatted", Returned);
        return "result";
    }

}