package com.me.crudmedico.model.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.ui.doctor.view.CreateDoctorActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdaptertextNameDoctors extends RecyclerView.Adapter<AdaptertextNameDoctors.ViewHolderNames> {
    private List<Doctor> doctors;

    public AdaptertextNameDoctors.launchACtivityDetailDoctor getLaunchACtivityDetailDoctor() {
        return launchACtivityDetailDoctor;
    }

    public void setLaunchACtivityDetailDoctor(AdaptertextNameDoctors.launchACtivityDetailDoctor launchACtivityDetailDoctor) {
        this.launchACtivityDetailDoctor = launchACtivityDetailDoctor;
    }

    private launchACtivityDetailDoctor launchACtivityDetailDoctor;
    public AdaptertextNameDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public ViewHolderNames onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);

        return new ViewHolderNames(v);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolderNames holder, final int position) {
        holder.textViewName.setText(doctors.get(position).getCode());
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 launchACtivityDetailDoctor.launchACtivityDetailDoctor(doctors.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
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

    public interface launchACtivityDetailDoctor{
        public void launchACtivityDetailDoctor(Doctor doctor);
    }
}
