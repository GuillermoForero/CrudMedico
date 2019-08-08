package com.me.crudmedico.data.remote;

import com.me.crudmedico.model.MedicalAppointment;

import java.util.Date;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Observable<MedicalAppointment> savePost(@Field("doctorCode") String doctorCode,
                                      @Field("patientId") String patientId,
                                      @Field("date") Date date,
                                      @Field("attended") boolean attended,
                                      @Field("imageFirmSVG") String imageFirmSVG);
}
