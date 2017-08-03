package com.tvnsoftware.drcare.model.medicalrecord;

import android.os.Parcel;
import android.os.Parcelable;

import com.tvnsoftware.drcare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 7/26/2017.
 */

public class MedicalRecord implements Parcelable {

    private String patientCode;
    private String patientName;
    private String patientStatus;
    private int patientPhoto;
    private List<Prescription> prescriptionList;

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    public MedicalRecord() {
    }

    public MedicalRecord(String patientCode, String patientName, String patientStatus, int patientPhoto) {
        this.patientCode = patientCode;
        this.patientName = patientName;
        this.patientStatus = patientStatus;
        this.patientPhoto = patientPhoto;
    }

    /**
     * SAMN .. 27-Jul-2017
     */
    private int isTaken; ///for STATUS of Medical Record -- DOCTOR
    private int DoctorID;
    private String DoctorName;
    private String DiseaseName;
    private String DayCreated;
    private int doctorPhoto;

    private static List<MedicalRecord> patientList;
    private static List<MedicalRecord> medicalList;

    public String getPatientCode() {
        return patientCode;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientStatus() {
        if (isTaken == 0)
            this.patientStatus = "Pending";
        else
            this.patientStatus = "Taken";
        return patientStatus;
    }

    public int getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(int isTaken) {
        this.isTaken = isTaken;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public String getDiseaseName() {
        return DiseaseName;
    }

    public String getDayCreated() {
        return DayCreated;
    }

    public int getPatientPhoto() {
        return patientPhoto;
    }

    public void setPatientPhoto(int patientPhoto) {
        this.patientPhoto = patientPhoto;
    }

    public int getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(int doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }

    public MedicalRecord(String patientCode, String patientName, int isTaken) {
        this.patientCode = patientCode;
        this.patientName = patientName;
        this.isTaken = isTaken;
    }

    public MedicalRecord(int doctorID, String doctorName, String diseaseName,
                         String dayCreated, int doctorPhoto, List<Prescription> prescriptions) {
        DoctorID = doctorID;
        DoctorName = doctorName;
        DiseaseName = diseaseName;
        DayCreated = dayCreated;
        this.doctorPhoto = doctorPhoto;
        this.prescriptionList = prescriptions;
    }

    private static void dataForDoctor() {
        patientList = new ArrayList<>();
        patientList.add(new MedicalRecord("ABC1234", "John Cena", 0));
        patientList.add(new MedicalRecord("SAM6969", "Samn Nguyen", 0));
        patientList.add(new MedicalRecord("WSH0933", "Will Smith", 0));
        patientList.add(new MedicalRecord("SGZ1065", "Selena Gomez", 0));
        patientList.add(new MedicalRecord("HCC8345", "Hoàn Châu Cách Cách", 0));
        patientList.add(new MedicalRecord("NTT3087", "Nguyễn Trần Tuyết Nghi", 0));
        patientList.add(new MedicalRecord("THB5763", "Trịnh Hoàng Huy Bảo", 0));
        patientList.add(new MedicalRecord("TTN9240", "Trần Phan Tố Nguyệt", 1));
        patientList.add(new MedicalRecord("PTN8372", "Phạm Kiều Thảo Nguyên", 1));
    }

    public static List<MedicalRecord> getPatientList() {
        dataForDoctor();
        return patientList;
    }

    private static List<Prescription> pre() {
        List<Prescription> res = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Prescription prescription = new Prescription();
            prescription.setName("Paracetamol " + i);
            prescription.setQuantity(10);
            prescription.setUnit("Gói");
            prescription.setUsage("Ngày dùng 2 lần mỗi lần 10 viên");
            res.add(prescription);
        }
        return res;
    }

    private static void dataForPatient() {
        medicalList = new ArrayList<>();
        medicalList.add(new MedicalRecord(5, "Allison Scott", "Sốt phát ban", "24/07/2014", R.drawable.res_doctor4, pre()));
        medicalList.add(new MedicalRecord(1, "Gwen Seaver", "Viêm họng cấp", "5/9/2014", R.drawable.res_doctor2, pre()));
        medicalList.add(new MedicalRecord(2, "Brett Vandenberg", "Thấp khớp", "30/10/2015", R.drawable.res_doctor3, pre()));
        medicalList.add(new MedicalRecord(3, "Fiona McConnell", "Viêm mũi dị ứng", "16/11/2015", R.drawable.res_doctor1, pre()));
        medicalList.add(new MedicalRecord(5, "Allison Scott", "Viêm phế quản", "21/3/2016", R.drawable.res_doctor4, pre()));
        medicalList.add(new MedicalRecord(2, "Brett Vandenberg", "Thoái hóa cột sống cổ", "4/12/1016", R.drawable.res_doctor3, pre()));
        medicalList.add(new MedicalRecord(1, "Gwen Seaver", "Rối loạn tiêu hóa", "28/6/2017", R.drawable.res_doctor2, pre()));
    }

    public static List<MedicalRecord> getMRHistoryList() {
        dataForPatient();
        return medicalList;
    }

    protected MedicalRecord(Parcel in) {
        patientCode = in.readString();
        patientName = in.readString();
        patientStatus = in.readString();
        patientPhoto = in.readInt();
        isTaken = in.readInt();
        DoctorID = in.readInt();
        DoctorName = in.readString();
        DiseaseName = in.readString();
        DayCreated = in.readString();
        prescriptionList = new ArrayList<>();
        in.readList(prescriptionList, getClass().getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(patientCode);
        dest.writeString(patientName);
        dest.writeString(patientStatus);
        dest.writeInt(patientPhoto);
        dest.writeInt(isTaken);
        dest.writeInt(DoctorID);
        dest.writeString(DoctorName);
        dest.writeString(DiseaseName);
        dest.writeString(DayCreated);
        dest.writeList(prescriptionList);
    }

    @Override
    public int describeContents() {
        return 0;
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

}
