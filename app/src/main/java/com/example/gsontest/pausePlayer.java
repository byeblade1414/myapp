package com.example.gsontest;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;

public class pausePlayer extends Commons{
    String method = methods.pausePlayer;
    params params = new params();



    static class params{
        int playerid = 1;
        boolean play = false;
    }
}
