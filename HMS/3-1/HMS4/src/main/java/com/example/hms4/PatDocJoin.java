package com.example.hms4;

public class PatDocJoin {
    private int patId;
    private String patName;
    private int patAge;
    private int patDocId;
    private String docTimeOfApt;
    private int patActStat;
    private String patPhNo;

    public PatDocJoin(int patId, String patName, int patAge, int patDocId, String docTimeOfApt, int patActStat, String patPhNo) {
        this.patId = patId;
        this.patName = patName;
        this.patAge = patAge;
        this.patDocId = patDocId;
        this.docTimeOfApt = docTimeOfApt;
        this.patActStat = patActStat;
        this.patPhNo = patPhNo;
    }

    public int getPatId() {
        return patId;
    }

    public String getPatName() {
        return patName;
    }

    public int getPatAge() {
        return patAge;
    }

    public int getPatDocId() {
        return patDocId;
    }

    public String getDocTimeOfApt() {
        return docTimeOfApt;
    }

    public int getPatActStat() {
        return patActStat;
    }

    public String getPatPhNo() {
        return patPhNo;
    }
}
