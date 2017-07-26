package com.tvnsoftware.drcare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tvnsoftware.drcare.R;
import com.tvnsoftware.drcare.model.users.Medicine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 7/26/2017.
 */

public class DiagnosisAdapter extends RecyclerView.Adapter<DiagnosisAdapter.ViewHolder> {

    private List<Medicine> medicines;


    public DiagnosisAdapter() {
        this.medicines = new ArrayList<>();
//        this.medicines = medicines;
    }
    public void add(Medicine medicine){
        this.medicines.add(medicine);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_prescription, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Medicine medicine = medicines.get(position);
        holder.tvPrescriptionMedicine.setText(medicine.getMedicineName());
        holder.tvPrescriptionQuantity.setText(medicine.getMedicineQuantity());
        holder.tvPrescriptionTimes.setText(medicine.getMedicineTimesTaken());
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_prescription_medicine)
        TextView tvPrescriptionMedicine;
        @BindView(R.id.tv_prescription_quantity)
        TextView tvPrescriptionQuantity;
        @BindView(R.id.tv_prescription_times)
        TextView tvPrescriptionTimes;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
