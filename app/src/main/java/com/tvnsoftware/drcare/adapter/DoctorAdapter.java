package com.tvnsoftware.drcare.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tvnsoftware.drcare.R;
import com.tvnsoftware.drcare.Utils.GlideCircleTransformation;
import com.tvnsoftware.drcare.activity.AlarmActivity;
import com.tvnsoftware.drcare.activity.DiagnosisActivity;
import com.tvnsoftware.drcare.model.medicalrecord.MedicalRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tvnsoftware.drcare.adapter.ROLE_STATE.PATIENT;

/**
 * Created by Admin on 7/24/2017.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private final List<MedicalRecord> medicalRecords;
    private Context context;
    private ArrayList<MedicalRecord> arrayList;
    private int mExpandedPosition = -1;

    private static ROLE_STATE stateByRole;

    public DoctorAdapter(Context context) {
        this.medicalRecords = new ArrayList<>();
        this.context = context;
    }

    /**
     * by Samn at 2:11AM 28-Jul-2017
     */
    public void setData(List<MedicalRecord> medical_records){
        medicalRecords.clear();
        medicalRecords.addAll(medical_records);
        notifyDataSetChanged();
    }

    public void setState(int roleID) {
        stateByRole = roleID == 1 ? PATIENT : ROLE_STATE.DOCTOR;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MedicalRecord medicalRecord = medicalRecords.get(position);

        if(stateByRole == ROLE_STATE.DOCTOR){
            BindView_DoctorScreen(holder, medicalRecord);
            expandCardView(holder, position);
        }
        else //PATIENT
        {
            BindView_PatientScreen(holder, medicalRecord);
        }
    }

    private void BindView_PatientScreen(ViewHolder holder, final MedicalRecord medicalRecord) {
        holder.tvPatientName.setText(medicalRecord.getDiseaseName());
        holder.tvPatientCode.setText("Doctor: " + medicalRecord.getDoctorName());
        holder.tvPatientStatus.setText("Date: " + medicalRecord.getDayCreated());
        Glide.with(context).load(medicalRecord.getDoctorPhoto())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivCover);
        holder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DiagnosisActivity.class);
                intent.putExtra("patient", medicalRecord);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void BindView_DoctorScreen(ViewHolder holder, final MedicalRecord medicalRecord) {
        holder.tvPatientName.setText(medicalRecord.getPatientName());
        holder.tvPatientCode.setText("ID: " + medicalRecord.getPatientCode());
        holder.tvPatientStatus.setText("Status: " + medicalRecord.getPatientStatus());
        Glide.with(context).load(medicalRecord.getPatientPhoto())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .bitmapTransform(new GlideCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivCover);
        holder.btnAdmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DiagnosisActivity.class);
                intent.putExtra("patient", medicalRecord);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void expandCardView(final ViewHolder holder, final int position){
        final boolean isExpanded = position == mExpandedPosition;
        holder.tvPatientBlood.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.tvPatientPressure.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.tvPatientGender.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.btnAdmit.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyDataSetChanged();
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
                if (charText.length() != 0 &&
                        medicalRecord.getPatientName()
                            .toLowerCase(Locale.getDefault()).contains(charText)) {
                    medicalRecords.add(medicalRecord);
                } else if (charText.length() != 0 &&
                        medicalRecord.getPatientCode().contains(charText)) {
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
        @BindView(R.id.tv_patient_gender)
        TextView tvPatientGender;
        @BindView(R.id.tv_patient_blood)
        TextView tvPatientBlood;
        @BindView(R.id.tv_patient_pressure)
        TextView tvPatientPressure;
        @BindView(R.id.btnAdmit)
        Button btnAdmit;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(stateByRole == PATIENT){
                        int pos = getAdapterPosition();
                        onClick_startIntent(pos);
                    }
                }
            });

            btnAdmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int pos = getAdapterPosition();
//                    onClick_startIntent(pos);
                }
            });
        }
    }

    private void onClick_startIntent(int position){
        MedicalRecord medRec = medicalRecords.get(position);
        Intent intent = new Intent(context, AlarmActivity.class);
        if(stateByRole == ROLE_STATE.PATIENT){
            intent.putExtra(DiagnosisActivity.EXTRA_PATIENT, medRec);
        } else{
            intent.putExtra(DiagnosisActivity.EXTRA_DOCTOR, medRec);
        }
        context.startActivity(intent);
    }
}
