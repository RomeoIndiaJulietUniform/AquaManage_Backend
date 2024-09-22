package com.DragonFish.aquaManage.ActuatorService.Controllers;

import com.DragonFish.aquaManage.ActuatorService.Services.PlantedService;
import jdk.jfr.Enabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuators/Planted")
public class PlantedServiceController {

    private final PlantedService plantedService;

    @Autowired
    public PlantedServiceController(PlantedService plantedService) {
        this.plantedService = plantedService;
    }

    @PostMapping("/set-co2-rate")
    public String setCO2InjectionRate(@RequestParam double rate) {
        plantedService.setCO2InjectionRate(rate);
        return "CO2 Injection rate set to " + rate + " bubbles/minute";
    }

    @PostMapping("/set-nutrient-rate")
    public String setNutrientDosingRate(@RequestParam double rate) {
        plantedService.setNutrientDosingRate(rate);
        return "Nutrient dosing rate set to " + rate + " mL/hour";
    }

    @PostMapping("/set-heater-temp")
    public String setHeaterTemperature(@RequestParam double temperature) {
        plantedService.setHeaterTemperature(temperature);
        return "Heater temperature set to " + temperature + "°C";
    }

    @PostMapping("/set-light-duration")
    public String setLightTimeDuration(@RequestParam int duration) {
        plantedService.setLightTimeDuration(duration);
        return "Light duration set to " + duration + " minutes";
    }

    @PostMapping("/set-water-pump-rate")
    public String setWaterPumpFlowRate(@RequestParam double flowRate) {
        plantedService.setWaterPumpFlowRate(flowRate);
        return "Water pump flow rate set to " + flowRate + " L/h";
    }

    @PostMapping("/set-auto-water-change")
    public String setAutoWaterChange(@RequestParam double volume) {
        plantedService.setAutoWaterChange(volume);
        return "Auto water change volume set to " + volume + " liters";
    }
}
