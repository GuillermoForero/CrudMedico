package com.me.crudmedico.model.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.me.crudmedico.R;
import com.me.crudmedico.model.Doctor;
import com.me.crudmedico.model.MedicalAppointment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolderNames> {
    private List<MedicalAppointment> medicalAppointments;
    private LaunchFirmActivity launchFirmActivity;

    public LaunchFirmActivity getLaunchFirmActivity() {
        return launchFirmActivity;
    }

    public void setLaunchFirmActivity(LaunchFirmActivity launchFirmActivity) {
        this.launchFirmActivity = launchFirmActivity;
    }

    public List<MedicalAppointment> getMedicalAppointments() {
        return medicalAppointments;
    }

    public void setMedicalAppointments(List<MedicalAppointment> medicalAppointments) {
        this.medicalAppointments = medicalAppointments;
    }

    @Override
    public AdapterHistory.ViewHolderNames onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);

        return new AdapterHistory.ViewHolderNames(v);

    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final AdapterHistory.ViewHolderNames holder, final int position) {
        holder.textViewDate.setText(medicalAppointments.get(position).getDate().toString());
        if(medicalAppointments.get(position).isAttended()){
            holder.textViewAttended.setText("asistió");
        }else{
            holder.textViewAttended.setText("No asistió");
        }

        holder.containerItemHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFirmActivity.launchFirmActivity();
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicalAppointments.size();
    }


    public static class ViewHolderNames  extends RecyclerView.ViewHolder {

        private View view;
        @BindView(R.id.date_of_appointment)
        TextView textViewDate;
        @BindView(R.id.attended)
        TextView textViewAttended;
        @BindView(R.id.container_item_history)
        LinearLayout containerItemHistory;

        public ViewHolderNames(View v) {
            super(v);
            view = v;
            ButterKnife.bind(this, view);
        }

    }
    public interface LaunchFirmActivity{
        public void launchFirmActivity();
    }

}
