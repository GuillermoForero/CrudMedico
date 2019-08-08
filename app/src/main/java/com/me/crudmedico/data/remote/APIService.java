package com.me.crudmedico.data.remote;

import com.me.crudmedico.model.MedicalAppointment;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Call<MedicalAppointment> savePost(@Field("doctorCode") String doctorCode,
                                      @Field("patientId") String patientId,
                                      @Field("date") Date date,
                                      @Field("attended") Date attended,
                                      @Field("imageFirmSVG") String imageFirmSVG);
}
