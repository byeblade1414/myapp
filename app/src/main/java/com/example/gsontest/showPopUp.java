package com.example.gsontest;

import org.json.JSONObject;

public class showPopUp extends Commons{
    String method = methods.showPopup;
    paramClass params;

    public showPopUp(showPopUp.paramClass params) {
        this.params = params;
    }

    static class paramClass{
        String title;
        String message;

        public paramClass(String title, String message) {
            this.title = title;
            this.message = message;
        }
    }
}


