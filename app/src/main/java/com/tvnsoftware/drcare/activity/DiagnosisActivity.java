package com.tvnsoftware.drcare.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tvnsoftware.drcare.R;
import com.tvnsoftware.drcare.adapter.DiagnosisAdapter;
import com.tvnsoftware.drcare.model.users.Medicine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 7/26/2017.
 */

public class DiagnosisActivity extends AppCompatActivity {

    private List<Medicine> medicines = new ArrayList<>();
    private RecyclerView recyclerView;
    private DiagnosisAdapter diagnosisAdapter;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.et_medicine)
    EditText etMedicine;
    @BindView(R.id.et_times_taken)
    EditText etTimes;
    @BindView(R.id.et_quantity)
    EditText etQuantity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_diagnosis_layout);
        ButterKnife.bind(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        diagnosisAdapter = new DiagnosisAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(diagnosisAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                addPrescription();
            }
        });
    }

    private void addPrescription(){
        Medicine medicine = new Medicine(etMedicine.getText().toString(), etQuantity.getText().toString(),
                etTimes.getText().toString());
        diagnosisAdapter.add(medicine);
        Toast.makeText(getBaseContext(), "Added successfully", Toast.LENGTH_SHORT).show();
    }
}
