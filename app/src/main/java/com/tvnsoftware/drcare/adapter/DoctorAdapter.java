package com.tvnsoftware.drcare.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tvnsoftware.drcare.R;
import com.tvnsoftware.drcare.activity.DiagnosisActivity;
import com.tvnsoftware.drcare.activity.MainActivity;
import com.tvnsoftware.drcare.model.medicalrecord.MedicalRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 7/24/2017.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private final List<MedicalRecord> medicalRecords;
    private Context context;
    private ArrayList<MedicalRecord> arrayList;

    public DoctorAdapter(Context context) {
        this.medicalRecords = new ArrayList<>();
        this.context = context;
    }

    public void addPatient(MedicalRecord medicalRecord){
        this.medicalRecords.add(medicalRecord);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_layout, parent, false);
        return new DoctorAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MedicalRecord medicalRecord = medicalRecords.get(position);
        holder.tvPatientName.setText(medicalRecord.getPatientName());
        holder.tvPatientCode.setText(medicalRecord.getPatientCode());
        holder.tvPatientStatus.setText(medicalRecord.getPatientStatus());
        holder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DiagnosisActivity.class);
                intent.putExtra("patient", medicalRecord);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void filter(String charText) {
        arrayList = new ArrayList<>();
        arrayList.addAll(medicalRecords);
        charText = charText.toLowerCase(Locale.getDefault());

        medicalRecords.clear();
        if (charText.length() == 0) {
            medicalRecords.addAll(arrayList);

        } else {
            for (MedicalRecord medicalRecord : arrayList) {
                if (charText.length() != 0 && medicalRecord.getPatientName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    medicalRecords.add(medicalRecord);
                } else if (charText.length() != 0 && medicalRecord.getPatientCode().contains(charText)) {
                    medicalRecords.add(medicalRecord);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return medicalRecords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_patient_name)
        TextView tvPatientName;
        @BindView(R.id.tv_patient_status)
        TextView tvPatientStatus;
        @BindView(R.id.tv_patient_code)
        TextView tvPatientCode;
        @BindView(R.id.iv_cover)
        ImageView ivCover;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
