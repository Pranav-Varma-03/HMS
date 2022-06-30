-- CREATE database HMSDB;
-- USE HMSDB;

-- CREATE TABLE LOGINCRED(
-- SNO INT UNIQUE auto_increment primary KEY,
-- USER_ID INT NOT NULL, 
-- PASSWORD VARCHAR(20), -- #(look at this whether to auto gen or enter new password manually)
-- ROLE VARCHAR(20)
-- );

-- CREATE TABLE PATIENT(
-- PATIENT_ID INT UNIQUE auto_increment primary KEY, --(format : 111%)
-- PASSWORD VARCHAR(20),
-- NAME VARCHAR(20), 
-- AGE INT NOT NULL,
-- GENDER VARCHAR(1),
-- PHONE_NUM VARCHAR(10),
-- AADHAAR VARCHAR(12) UNIQUE,
-- ACTIVITY_STAT INT,
-- DOC_ID INT DEFAULT NULL -- #(FOREGIN KEY AND TRIGGERS ?? CHECK AND UPDATE)
-- DISEASE VARCHAR(50) DEFAULT NULL,
-- TESTREP_FILE VARCHAR(50) DEFAULT NULL,
-- MEDREP_FILE VARCHAR(50) DEFAULT NULL
-- );

-- CREATE TABLE EMG_PATIENT(
-- PATIENT_ID INT UNIQUE auto_increment primary KEY -- (format : 554%)
-- -- NO PASSWORD VARCHAR(20),
-- NAME VARCHAR(20), 
-- AGE INT NOT NULL,
-- GENDER VARCHAR(1),
-- PHONE_NUM VARCHAR(10),
-- AADHAAR VARCHAR(12) UNIQUE,
-- -- ACTIVITY_STAT INT DEFAULT 1,
-- DOC_ID INT DEFAULT NULL -- #(FOREGIN KEY AND TRIGGERS ?? CHECK AND UPDATE)
-- DISEASE VARCHAR(50) DEFAULT NULL, -- (Emergency)
-- TESTREP_FILE VARCHAR(50) DEFAULT NULL,
-- MEDREP_FILE VARCHAR(50) DEFAULT NULL
-- );

-- CREATE TABLE DOCTOR(
-- DOC_ID INT UNIQUE auto_increment primary KEY -- (format : 221%)
-- PASSWORD VARCHAR(20),
-- NAME VARCHAR(20), 
-- AGE INT NOT NULL,
-- GENDER VARCHAR(1),
-- PHONE_NUM VARCHAR(10),
-- SPECIALIZATION VARCHAR(20),
-- TIME_SLOT VARCHAR(20),
-- CATEGORY VARCHAR(20), --(GEN OR NON-GEN{SPCL})
-- );

-- CREATE TABLE MEDICINE(
-- MED_ID INT UNIQUE auto_increment primary KEY -- (format : 332%)
-- NAME VARCHAR(20), 
-- EXPIRY DATE,
-- ACTIVITY_STAT INT,
-- );

-- DESCRIBE <TABLE_NAME>

-- ALTER TABLE <TABLE_NAME> ADD <COLUMN_NAME>;
-- ALTER TABLE <TABLE_NAME> DROP COLUMN <COLUMN_NAME>;

-- initiallising patient table to follow 111% code to patient IDs 
-- INSERT INTO PATIENT VALUES(11100,'11101','Pranav',19,'M','8977007588','123443211234',0,NULL,NULL,NULL,NULL);

-- INSERT INTO PATIENT(PASSWORD,NAME,AGE,GENDER,PHONE_NUM,AADHAAR,ACTIVITY_STAT) VALUES('11101','HARI',29,'M','9977007588','223443211234',0);

-- initiallising EMERGENCY patient table to follow 554% code to patient IDs 
-- INSERT INTO EMG_PATIENT VALUES(55400,'SIVA',19,'M','8877007588','123543211234',1,NULL,NULL,NULL,NULL);

-- ACTIVITY STATUS FOR EMERGENCY PAT IS ALWAYS 1 UNTILL HE LOGS OUT.
-- INSERT INTO EMG_PATIENT(NAME,AGE,GENDER,PHONE_NUM,AADHAAR) VALUES('HARI',29,'M','9977007588','223443211234');

-- initiallising DOCTOR table to follow 221% code to patient IDs 
-- INSERT INTO DOCTOR VALUES(22100,'11101','RAMA',39,'M','8997007588',GENERAL,6 TO 18,GEN);	

-- INSERT INTO DOCTOR(PASSWORD,NAME,AGE,GENDER,PHONE_NUM,SPECIALIZATION,TIME_SLOT,CATEGORY) VALUES('11101','SITA',29,'F','7997007588',NEURO,6 TO 18,NONGEN);

-- initiallising MEDICINE table to follow 332% code to patient IDs 
-- INSERT INTO MEDICINE VALUES(33200,'RA12X',23-12-2024,1);	

-- INSERT INTO MEDICINE(NAME,EXPIRY,ACTIVITY_STAT) VALUES('RA12X',23-12-2024,1);

-- UPDATE / EDIT CREDENTIALS : PHONE_NUM , ACITIVITY_STAT (ON OFF BUTTON)
-- UPDATE <TABLE_NAME>
-- SET <COLUMN_NAME> = " INPUT ", <COLUMN2_NAME> = " INPUT2 " ................
-- WHERE <ROLE>_ID = 'WHO LOGGED IN';

-- TRIIGERS USED
-- 1) WHEN PAT,DOC CREATED VIA ADMIN : IT MUST AUTO ADD TO LOGIN TABLE
-- 2) WHEN DATE CROSSED EXPIRY DATE : IT MUST AUTO TURN TO ACTIVITY OFF AND DISPLAY ALL LIST OF OFF ACTIVITY MEDICINES

-- 1) {
-- DELIMITER $$
-- CREATE TRIGGER ADD_USER
-- AFTER INSERT ON PATIENT
-- FOR EACH ROW 
-- BEGIN
-- INSERT INTO LOGINCRED VALUES(new.PATIENT_ID,new.PASSWORD,'PATIENT');
-- END $$
-- DELIMITER ;
-- }

-- 2) {
-- DELIMITER $$
-- CREATE TRIGGER ADD_USER
-- AFTER INSERT ON DOCTOR
-- FOR EACH ROW 
-- BEGIN
-- INSERT INTO LOGINCRED VALUES(new.DOC_ID,new.PASSWORD,'DOCTOR');
-- END $$
-- DELIMITER ;
-- }

-- 3) {
-- DELIMITER $$
-- CREATE TRIGGER MED_INFO
-- BEFORE UPDATE ON MEDICINE	
-- FOR EACH ROW 
-- BEGIN
-- IF DATE == EXPIRYDATE THEN
-- 		SET new.ACTIVITY_STAT = 0;
-- END IF;
-- END $$
-- DELIMITER ;
-- }










