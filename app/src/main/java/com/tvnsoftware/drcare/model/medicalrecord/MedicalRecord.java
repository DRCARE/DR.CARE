package com.tvnsoftware.drcare.model.medicalrecord;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 7/26/2017.
 */

class MedicalRecord {
    @SerializedName("MecRcDetailsID")
    private int id;
    @SerializedName("MecRcID")
    private int patientID;
    @SerializedName("DoctorID")
    private int DoctorID;
}
