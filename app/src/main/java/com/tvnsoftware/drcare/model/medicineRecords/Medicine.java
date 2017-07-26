package com.tvnsoftware.drcare.model.medicineRecords;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thieusike on 7/24/2017.
 */

public class Medicine {
    @SerializedName("MecRcDetailsID")
    public int MedicalCode;
    @SerializedName("DoctorID")
    public int UserCode;
    @SerializedName("MecRcID")
    public int UserPatientId;
    @SerializedName("DiseaseID")
    public int DiseaseCode;
    @SerializedName("Symptoms")
    public String MedicalSymptoms;
    @SerializedName("DayCreated")
    public String MedicalDayCreated;
    @SerializedName("isTaken")
    public int isTaken;

    public Medicine() {
    }

    public int getMedicalCode() {
        return MedicalCode;
    }

    public void setMedicalCode(int medicalCode) {
        MedicalCode = medicalCode;
    }

    public int getUserCode() {
        return UserCode;
    }

    public void setUserCode(int userCode) {
        UserCode = userCode;
    }

    public int getDiseaseCode() {
        return DiseaseCode;
    }

    public void setDiseaseCode(int diseaseCode) {
        DiseaseCode = diseaseCode;
    }

    public String getMedicalSymptoms() {
        return MedicalSymptoms;
    }

    public void setMedicalSymptoms(String medicalSymptoms) {
        MedicalSymptoms = medicalSymptoms;
    }

    public String getMedicalDayCreated() {
        return MedicalDayCreated;
    }

    public void setMedicalDayCreated(String medicalDayCreated) {
        MedicalDayCreated = medicalDayCreated;
    }

    public int getUserPatientId() {
        return UserPatientId;
    }

    public void setUserPatientId(int userPatientId) {
        UserPatientId = userPatientId;
    }

    public int getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(int isTaken) {
        this.isTaken = isTaken;
    }
}
