package com.example.chatott2.Fragments;

import com.example.chatott2.Notification.MyResponse;
import com.example.chatott2.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA4uNOttU:APA91bHvc0cBQzVnNLmR9BrUkTiPzY-gpL9g3kUZuwKrfAXwgRRRXidecnecOKE4iyCWukQMtQ04YxgRwJQWqHHPh6DgNxlqpheYMHhDy-Eb5ZKZJXQkyFSaGh77wTidJAeoOsVplPT4"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
