package com.DragonFish.aquaManage.ActuatorService.Models;

public class MarineAquarium extends BasicAquarium {
    private final Regulator saltwaterMixer;
    private final Regulator calciumDoser;
    private final Regulator alkalinityDoser;
    private final Regulator skimmer;

    public MarineAquarium() {
        super();
        this.saltwaterMixer = new SaltwaterMixer();
        this.calciumDoser = new CalciumDoser();
        this.alkalinityDoser = new AlkalinityDoser();
        this.skimmer = new Skimmer();
    }

    public void setSaltwaterMixerRatio(double ratio) {
        saltwaterMixer.setValue(ratio);
    }

    public void setCalciumDosingRate(double rate) {
        calciumDoser.setValue(rate);
    }

    public void setAlkalinityDosingRate(double rate) {
        alkalinityDoser.setValue(rate);
    }

    public void setSkimmerEfficiency(double efficiency) {
        skimmer.setValue(efficiency);
    }
}

class SaltwaterMixer implements Regulator {
    @Override
    public void setValue(double ratio) {
        System.out.println("Saltwater Mixer ratio set to " + ratio);
    }
}

class CalciumDoser implements Regulator {
    @Override
    public void setValue(double rate) {
        System.out.println("Calcium Dosing rate set to " + rate + " mL/hour");
    }
}

class AlkalinityDoser implements Regulator {
    @Override
    public void setValue(double rate) {
        System.out.println("Alkalinity Dosing rate set to " + rate + " mL/hour");
    }
}

class Skimmer implements Regulator {
    @Override
    public void setValue(double efficiency) {
        System.out.println("Skimmer efficiency set to " + efficiency + "%");
    }
}
