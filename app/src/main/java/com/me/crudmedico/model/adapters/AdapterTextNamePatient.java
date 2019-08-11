package com.me.crudmedico.model.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Patient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterTextNamePatient extends RecyclerView.Adapter<AdaptertextNameDoctors.ViewHolderNames> {
    private List<Patient> patients;
    private launchActivityDetailPatient launchActivityDetailPatient;

    public AdapterTextNamePatient(List<Patient> patients) {
        this.patients = patients;
    }

    public AdapterTextNamePatient.launchActivityDetailPatient getLaunchActivityDetailPatient() {
        return launchActivityDetailPatient;
    }

    public void setLaunchActivityDetailPatient(AdapterTextNamePatient.launchActivityDetailPatient launchActivityDetailPatient) {
        this.launchActivityDetailPatient = launchActivityDetailPatient;
    }

    @Override
    public AdaptertextNameDoctors.ViewHolderNames onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);

        return new AdaptertextNameDoctors.ViewHolderNames(v);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final AdaptertextNameDoctors.ViewHolderNames holder, final int position) {
        holder.textViewName.setText(patients.get(position).getId());
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivityDetailPatient.launchACtivityDetailPatient(patients.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }


    public interface launchActivityDetailPatient {
        void launchACtivityDetailPatient(Patient patient);
    }

    public static class ViewHolderNames extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_name_item)
        TextView textViewName;
        private View view;

        public ViewHolderNames(View v) {
            super(v);
            view = v;
            ButterKnife.bind(this, view);
        }

    }
}