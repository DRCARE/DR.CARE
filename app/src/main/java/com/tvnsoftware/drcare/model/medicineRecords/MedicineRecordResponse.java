package com.tvnsoftware.drcare.model.medicineRecords;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Thieusike on 7/24/2017.
 */

public class MedicineRecordResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("response")
    private List<Medicine> medicines;

    public MedicineRecordResponse() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
