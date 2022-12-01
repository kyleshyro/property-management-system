package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator/") // Class level mappping of url to a controller class
public class CalculatorController {

    //http://localhost:8080/api/v1/calculator/add

    //http://localhost:8080/api/v1/calculator/add?num1=6.5&num2=8.8
    @GetMapping("/add/{num5}") //MEthod level mapping of an url to a controller function
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2")Double num2,
                      @PathVariable("num5") Double num5){

        return num1+num2+num5;

    }
    @GetMapping("/sub/{num1}/{num2}") //Map the values of url to Java Variables by Path Variable method
    public Double substract(@PathVariable("num1") Double num1, @PathVariable("num2")Double num2){
        Double result=null;

        if(num1>num2){
            result=num1-num2;
        }else{
            result =num2-num1;
        }
        return result;

    }

    @PostMapping("/mul")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO){

        Double result =null;
        result = (calculatorDTO.getNum1()*calculatorDTO.getNum2())+calculatorDTO.getNum3()+calculatorDTO.getNum4();

        ResponseEntity<Double> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED);
        return responseEntity;

    }
}
