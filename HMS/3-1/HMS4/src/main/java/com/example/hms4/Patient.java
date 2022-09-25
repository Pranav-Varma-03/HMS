package com.example.hms4;

public class Patient {
private int patId;
private String patPwd;
private String patName;
private int patAge;
private String patGender;
private String patPhoneNum;
private String patAadhaar;
private int patActStat;
private int patDocId;
private String patDisease;
private String patTestRep;
private String patMedRep;

    public String getPatPhoneNum() {
        return patPhoneNum;
    }

    public Patient(int patId, String patPwd, String patName, int patAge, String patGender, String patPhoneNum, String patAadhaar, int patActStat, int patDocId, String patDisease, String patTestRep, String patMedRep) {
        this.patId = patId;
        this.patPwd = patPwd;
        this.patName = patName;
        this.patAge = patAge;
        this.patGender = patGender;
        this.patPhoneNum = patPhoneNum;
        this.patAadhaar = patAadhaar;
        this.patActStat = patActStat;
        this.patDocId = patDocId;
        this.patDisease = patDisease;
        this.patTestRep = patTestRep;
        this.patMedRep = patMedRep;
    }

    public int getPatId() {
        return patId;
    }

    public String getPatPwd() {
        return patPwd;
    }

    public String getPatName() {
        return patName;
    }

    public int getPatAge() {
        return patAge;
    }

    public String getPatGender() {
        return patGender;
    }

    public String getPatAadhaar() {
        return patAadhaar;
    }

    public int getPatActStat() {
        return patActStat;
    }

    public int getPatDocId() {
        return patDocId;
    }

    public String getPatDisease() {
        return patDisease;
    }

    public String getPatTestRep() {
        return patTestRep;
    }

    public String getPatMedRep() {
        return patMedRep;
    }
}
