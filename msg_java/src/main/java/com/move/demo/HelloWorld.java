package com.move.demo;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/hello")
public class HelloWorld {


    @GetMapping(value = "/say" )
    public String say(@RequestParam(value = "id",required = false,defaultValue = "0") Integer id){
        return  "id:"+id;
    }

}
