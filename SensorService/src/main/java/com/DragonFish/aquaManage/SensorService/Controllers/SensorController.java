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

/**
 * REST controller for handling requests related to sensor data in the marine aquarium.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorModelService sensorModelService;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    /**
     * Greeting endpoint to check the service is up.
     *
     * @return a greeting message.
     */
    @GetMapping("/greet")
    public String getGreeting() {
        return "Hello World";
    }

    /**
     * Endpoint to retrieve sensor data for the marine aquarium.
     * If no data is found, it returns a 404 response. If data is available,
     * it retrieves various simulated or calculated sensor parameters.
     *
     * @return a ResponseEntity containing a map of sensor names and their values.
     */
    @GetMapping("/data")
    public ResponseEntity<Map<String, Double>> getSensorData() {
        List<SensorData> sensorDataList = sensorDataRepository.findAll();

        if (sensorDataList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SensorData sensorData = sensorDataList.get(0); // Get the first sensor data for processing

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
