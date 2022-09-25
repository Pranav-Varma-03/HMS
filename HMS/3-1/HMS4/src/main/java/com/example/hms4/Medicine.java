package com.example.hms4;

public class Medicine {
    private int medId;
    private String medName;
    private String medExpDate;
    private int medActStat;

    public Medicine(int medId,String medName, String medExpDate, int medActStat) {
        this.medId = medId;
        this.medName = medName;
        this.medExpDate = medExpDate;
        this.medActStat = medActStat;

    }

    public int getMedId() {
        return medId;
    }

    public String getMedName() {
        return medName;
    }

    public String getMedExpDate() {
        return medExpDate;
    }

    public int getMedActStat() {
        return medActStat;
    }

}
