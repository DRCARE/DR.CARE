package com.tvnsoftware.drcare.model.medicineRecords;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thieusike on 7/24/2017.
 */

public class Medicine {
    @SerializedName("MEDICAL_CODE")
    public int MedicalCode;
    @SerializedName("USER_CODE")
    public int UserCode;
    @SerializedName("USER_PATIENT_ID")
    public int UserPatientId;
    @SerializedName("DISEASE_CODE")
    public int DiseaseCode;
    @SerializedName("MEDICAL_SYMPTOMS")
    public String MedicalSymptoms;
    @SerializedName("MEDICAL_DAY_CREATED")
    public String MedicalDayCreated;

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
}
