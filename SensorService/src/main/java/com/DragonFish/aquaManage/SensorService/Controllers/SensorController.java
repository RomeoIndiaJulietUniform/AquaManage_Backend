package com.DragonFish.aquaManage.SensorService.Controllers;

import com.DragonFish.aquaManage.SensorService.Entity.SensorData;
import com.DragonFish.aquaManage.SensorService.Repository.SensorDataRepository;
import com.DragonFish.aquaManage.SensorService.Service.SensorModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorModelService sensorModelService;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @GetMapping("/greet")
    public String getGreeting() {
        return "Hello World";
    }

    @GetMapping("/data")
    public ResponseEntity<Map<String, Double>> getSensorData() {
        List<SensorData> sensorDataList = sensorDataRepository.findAll();


        if (sensorDataList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SensorData sensorData = sensorDataList.get(0);

        Map<String, Double> sensorResults = new HashMap<>();
        sensorResults.put("Temperature", sensorModelService.calculateTemperature(sensorData));
        sensorResults.put("FlowSpeed", sensorModelService.calculateFlowSpeed(sensorData));
        sensorResults.put("Pressure", sensorModelService.calculatePressure(sensorData));
        sensorResults.put("LightIntensity", sensorModelService.simulateLightIntensity());
        sensorResults.put("Nitrate", sensorModelService.simulateNitrate());
        sensorResults.put("Salinity", sensorModelService.simulateSalinity());
        sensorResults.put("pH", sensorModelService.simulatePh());
        sensorResults.put("Calcium", sensorModelService.simulateCalcium());
        sensorResults.put("Alkalinity", sensorModelService.simulateAlkalinity());
        sensorResults.put("Nitrite", sensorModelService.simulateNitrite());

        return ResponseEntity.ok(sensorResults);
    }
}
