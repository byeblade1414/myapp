package com.example.gsontest;

public class volumeDown extends Commons {
    String method = "Application.SetVolume";
    params params = new params();


    static class params{
        int volume = Commons.volumeDef.getVolume();
    }
}
