package com.tvnsoftware.drcare.model.medicalrecord;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 7/26/2017.
 */

public class MedicalRecord implements Parcelable{

    private String patientCode;
    private String patientName;
    private String patientStatus;

    public MedicalRecord() {
    }

    public MedicalRecord(String patientCode, String patientName, String patientStatus) {
        this.patientCode = patientCode;
        this.patientName = patientName;
        this.patientStatus = patientStatus;
    }

    protected MedicalRecord(Parcel in) {
        patientCode = in.readString();
        patientName = in.readString();
        patientStatus = in.readString();
    }

    public static final Creator<MedicalRecord> CREATOR = new Creator<MedicalRecord>() {
        @Override
        public MedicalRecord createFromParcel(Parcel in) {
            return new MedicalRecord(in);
        }

        @Override
        public MedicalRecord[] newArray(int size) {
            return new MedicalRecord[size];
        }
    };

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(patientCode);
        dest.writeString(patientName);
        dest.writeString(patientStatus);
    }
}
