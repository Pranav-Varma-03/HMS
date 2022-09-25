package com.example.hms4;

public class Doctor {
    private int docId;
    private String docPwd;
    private String docName;
    private String docGender;
    private String docSpec;
    private String docTime;
    private String docPhoneNum;
    private String docCat;
    private int docAge;
    private int docWorkStat;

    public Doctor(int docId, String docPwd, String docName, String docGender, String docSpec, String docTime, String docPhoneNum, String docCat, int docAge, int docWorkStat) {
        this.docId = docId;
        this.docPwd = docPwd;
        this.docName = docName;
        this.docGender = docGender;
        this.docSpec = docSpec;
        this.docTime = docTime;
        this.docPhoneNum = docPhoneNum;
        this.docCat = docCat;
        this.docAge = docAge;
        this.docWorkStat = docWorkStat;
    }

    public int getDocId() {
        return docId;
    }

    public String getDocPwd() {
        return docPwd;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocGender() {
        return docGender;
    }

    public String getDocSpec() {
        return docSpec;
    }

    public String getDocTime() {
        return docTime;
    }

    public String getDocPhoneNum() {
        return docPhoneNum;
    }

    public String getDocCat() {
        return docCat;
    }

    public int getDocAge() {
        return docAge;
    }

    public int getDocWorkStat() {
        return docWorkStat;
    }
}
