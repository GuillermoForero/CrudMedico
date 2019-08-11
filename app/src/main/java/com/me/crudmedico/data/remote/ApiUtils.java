package com.me.crudmedico.data.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private ApiUtils() {
    }

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}