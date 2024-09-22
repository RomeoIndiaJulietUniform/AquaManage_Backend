package com.DragonFish.aquaManage.ActuatorService.Models;

public class PlantedAquarium extends BasicAquarium {
    private final Regulator co2Injector;
    private final Regulator nutrientDoser;

    public PlantedAquarium() {
        super();
        this.co2Injector = new CO2Injector();
        this.nutrientDoser = new NutrientDoser();
    }

    public void setCO2InjectionRate(double rate) {
        co2Injector.setValue(rate);
    }

    public void setNutrientDosingRate(double rate) {
        nutrientDoser.setValue(rate);
    }
}

interface Regulator {
    void setValue(double rate);
}

class CO2Injector implements Regulator {
    @Override
    public void setValue(double rate) {
        System.out.println("CO2 Injection rate set to " + rate + " bubbles/minute");
    }
}

class NutrientDoser implements Regulator {
    @Override
    public void setValue(double rate) {
        System.out.println("Nutrient Dosing rate set to " + rate + " mL/hour");
    }
}
