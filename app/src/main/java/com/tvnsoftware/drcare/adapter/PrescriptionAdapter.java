package com.tvnsoftware.drcare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tvnsoftware.drcare.R;
import com.tvnsoftware.drcare.model.medicalrecord.Prescription;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Thieusike on 8/3/2017.
 */

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.ViewHolder> {
    private Context mContext;
    private List<Prescription> prescriptionList;

    public PrescriptionAdapter(Context c, List<Prescription> prescription) {
        this.mContext = c;
        this.prescriptionList = prescription;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_prescription, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Prescription prescription = prescriptionList.get(position);
        holder.txtName.setText(prescription.getName());
        holder.txtQu.setText(prescription.getQuantity() + " ");
        holder.txtUnit.setText(prescription.getUnit());
        holder.txtUsage.setText(prescription.getUsage());
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView txtName;
        @BindView(R.id.qu)
        TextView txtQu;
        @BindView(R.id.unit)
        TextView txtUnit;
        @BindView(R.id.usage)
        TextView txtUsage;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public Context getContext() {
        return mContext;
    }
}
