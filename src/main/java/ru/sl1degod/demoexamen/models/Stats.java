package ru.sl1degod.demoexamen.models;

public class Stats {
    private String countApp_form;
    private String avgTime;
    private String causes;

    public Stats(String countApp_form, String avgTime, String causes) {
        this.countApp_form = countApp_form;
        this.avgTime = avgTime;
        this.causes = causes;
    }

    public String getCountApp_form() {
        return countApp_form;
    }

    public void setCountApp_form(String countApp_form) {
        this.countApp_form = countApp_form;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }
}
