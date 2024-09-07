package com.DragonFish.aquaManage.TankService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TankServiceController {

    @GetMapping("/v1/")
    String heyBro(){
        return "happy ganesh chaturthi";
    }
}
