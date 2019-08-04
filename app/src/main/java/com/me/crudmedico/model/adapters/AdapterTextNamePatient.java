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

    public AdapterTextNamePatient(List<Patient> patients) {
        this.patients = patients;
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
        public void onBindViewHolder(final AdaptertextNameDoctors.ViewHolderNames holder, int position) {
            holder.textViewName.setText(patients.get(position).getId());
        }

        @Override
        public int getItemCount() {
            return patients.size();
        }


        public static class ViewHolderNames  extends RecyclerView.ViewHolder {

            private View view;
            @BindView(R.id.textview_name_item)
            TextView textViewName;

            public ViewHolderNames(View v) {
                super(v);
                view = v;
                ButterKnife.bind(this, view);
            }

        }
    }

