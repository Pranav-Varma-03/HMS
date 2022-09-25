package com.example.hms4;

public class Emergency {
    private int emerID;
    private String emerName;
    private int emerAge;
    private String emerGender;
    private String emerPhNo;
    private String emerRel;
    private String emerRelPhNo;
    private String emerPriorHealth;
    private int emerDocId;
    private String emerDisease;
    private String emerTestRep;
    private String emerMedRep;


    public Emergency(int emerID, String emerName, int emerAge, String emerGender, String emerPhNo, String emerRel, String emerRelPhNo, String emerPriorHealth, int emerDocId, String emerDisease, String emerTestRep, String emerMedRep) {
        this.emerID = emerID;
        this.emerName = emerName;
        this.emerAge = emerAge;
        this.emerGender = emerGender;
        this.emerPhNo = emerPhNo;
        this.emerRel = emerRel;
        this.emerRelPhNo = emerRelPhNo;
        this.emerPriorHealth = emerPriorHealth;
        this.emerDocId = emerDocId;
        this.emerDisease = emerDisease;
        this.emerTestRep = emerTestRep;
        this.emerMedRep = emerMedRep;
    }
    public int getEmerID() {
        return emerID;
    }

    public String getEmerName() {
        return emerName;
    }

    public int getEmerAge() {
        return emerAge;
    }

    public String getEmerGender() {
        return emerGender;
    }

    public String getEmerPhNo() {
        return emerPhNo;
    }

    public String getEmerRel() {
        return emerRel;
    }

    public String getEmerRelPhNo() {
        return emerRelPhNo;
    }

    public String getEmerPriorHealth() {
        return emerPriorHealth;
    }

    public int getEmerDocId() {
        return emerDocId;
    }

    public String getEmerDisease() {
        return emerDisease;
    }

    public String getEmerTestRep() {
        return emerTestRep;
    }

    public String getEmerMedRep() {
        return emerMedRep;
    }
}
