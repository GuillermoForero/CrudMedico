package com.me.crudmedico.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.me.crudmedico.R;
import com.me.crudmedico.data.remote.APIService;
import com.me.crudmedico.data.remote.ApiUtils;
import com.me.crudmedico.model.MedicalAppointment;
import com.me.crudmedico.model.MyTimerTask;
import com.me.crudmedico.ui.doctor.view.DoctorActivity;
import com.me.crudmedico.ui.patient.view.PatientActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button patientsButton;
    @BindView(R.id.button2)
    Button doctorsButton;

    private APIService mAPIService;
    MyTimerTask myTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();
        myTimerTask = new MyTimerTask();
        myTimerTask.run();
    }

    @OnClick(R.id.button2)
    public void goToDoctors(){
        startActivity(new Intent(this, DoctorActivity.class));
    }

    @OnClick(R.id.button)
    public void goToPatients(){
        startActivity(new Intent(this, PatientActivity.class));
    }

    public void showResponse(String response) {

    }
    public void sendPost() {
        // RxJava
        mAPIService.savePost("xd", "xd", new Date(), true, "xd").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MedicalAppointment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MedicalAppointment post) {
                        showResponse(post.toString());
                    }
                });
    }


}
