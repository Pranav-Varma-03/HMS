package com.example.hms4;

public class loginCred {
    private int USER_ID;
    private String PASSWORD;
    private String ROLE;

    public loginCred(int USER_ID, String PASSWORD, String ROLE) {
        this.USER_ID = USER_ID;
        this.PASSWORD = PASSWORD;
        this.ROLE = ROLE;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getROLE() {
        return ROLE;
    }
}
