package com.DragonFish.aquaManage.ActuatorService.Models;

interface Aquarium {
    void setHeaterTemperature(double temperature);
    void setLightTimerDuration(int duration);
    void setWaterPumpFlowRate(double flowRate);
    void setAutoWaterAddingVolume(double volume);
}

public class BasicAquarium implements Aquarium {
    private double heaterTemperature;
    private int lightTimerDuration;
    private double waterPumpFlowRate;
    private double autoWaterAddingVolume;

    @Override
    public void setHeaterTemperature(double temperature) {
        this.heaterTemperature = temperature;
        System.out.println("Heater temperature set to " + temperature + " °C");
    }

    @Override
    public void setLightTimerDuration(int duration) {
        this.lightTimerDuration = duration;
        System.out.println("Light timer duration set to " + duration + " minutes");
    }

    @Override
    public void setWaterPumpFlowRate(double flowRate) {
        this.waterPumpFlowRate = flowRate;
        System.out.println("Water Pump flow rate set to " + flowRate + " L/h");
    }

    @Override
    public void setAutoWaterAddingVolume(double volume) {
        this.autoWaterAddingVolume = volume;
        System.out.println("Auto water adding volume set to " + volume + " L");
    }
}
