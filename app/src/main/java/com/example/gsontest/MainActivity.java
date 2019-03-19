package com.example.gsontest;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button b1;
    TextView t1;
    EditText t2;
    Button b2;

    private final int REQ_CODE_SPEECH_INPUT = 100;
    static String Result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button2);
        t1 = (TextView) findViewById(R.id.textView2);
        t2 = (EditText) findViewById(R.id.editText);
        b2 = (Button) findViewById(R.id.button);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //makeVoiceCommand();
                //openMovieByname(t2.getText().toString());
                //makeVoiceCommand();
                //stopPlayer();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volumeDown();
            }
        });


    }


    void volumeDown(){
        Gson gson = new Gson();
        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        if(Commons.volumeDef.getVolume() != 0) Commons.volumeDef.setVolume(Commons.volumeDef.getVolume()-10);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new volumeDown()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                t1.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });



        requestQueue.add(jsonObjectRequest);

    }

    void volumeUp(){
        Gson gson = new Gson();
        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        if(Commons.volumeDef.getVolume() != 100) Commons.volumeDef.setVolume(Commons.volumeDef.getVolume()+10);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new volumeUp()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                t1.setText(gson.toJson(new volumeUp()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    void seekForward(){
        Gson gson = new Gson();
        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new seekForward()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do nothing
                //t1.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do nothing
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    void openMovieByname(String name) {
        final RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        final Gson gson = new Gson();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new getMovies(new getMovies.params(new getMovies.filter(name)))), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //t1.setText(response.toString()); make visible in oder to see the response
                try {
                    int id = response.getJSONObject("result").getJSONArray("movies").getJSONObject(0).getInt("movieid");
                    //t1.setText(String.format("movie's id = %d",id)); make visible in oder to see the response
                    //new request
                    RequestQueue r2 = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
                    JsonObjectRequest r1 = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new openMovie(new openMovie.params(new openMovie.params.movieid(id)))), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject responsex) {
                            //t1.setText(responsex.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(r1);
                    //new request
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    void pausePlayer(){
        Gson gson = new Gson();

        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new pausePlayer()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               t1.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do nothing.
            }
        });


        requestQueue.add(jsonObjectRequest);
    }

    void stopPlayer(){
        Gson gson = new Gson();

        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new stopPlayer(new stopPlayer.params())), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                t1.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do nothing.
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    void startPlayer(){
        Gson gson = new Gson();

        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new playPlayer()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                t1.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do nothing.
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    void VolumeUp(){
        Gson gson = new Gson();

        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new volumeUp()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                t1.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do nothing.
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    void VolumeDown(){
        Gson gson = new Gson();

        RequestQueue requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Commons.urldef.url, gson.toJson(new volumeDown()), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                t1.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do nothing.
            }
        });

        requestQueue.add(jsonObjectRequest);
    }




















    // Voice Recognition Related Part.
    // Voice Input Result is Stored at globally (Static) Defined variable, which is initialized to null.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    if(result.get(0).toLowerCase().indexOf("open") != -1 && result.get(0).toLowerCase().indexOf("movie") != -1)
                    {
                        List<String> list = new ArrayList<String>(Arrays.asList(result.get(0).toLowerCase().split(" ")));
                        list.remove("open");
                        list.remove("movie");

                        String[] sArr = list.toArray(new String[0]);

                        String movie = TextUtils.join(" ", sArr);

                        openMovieByname(movie);
                        t1.setText(movie);

                    }

                    if(result.get(0).toLowerCase().indexOf("player") != -1){

                        if(result.get(0).toLowerCase().indexOf("pause") != -1)
                        {
                            pausePlayer();
                        }
                        if(result.get(0).toLowerCase().indexOf("stop") != -1){
                            stopPlayer();
                        }
                        if(result.get(0).toLowerCase().indexOf("start") != -1 || result.get(0).toLowerCase().indexOf("play")!= -1){
                            startPlayer();
                        }
                    }

                    if(result.get(0).toLowerCase().indexOf("volume") != -1){
                        if(result.get(0).toLowerCase().indexOf("down") != -1){
                            volumeDown();
                        }
                        if(result.get(0).toLowerCase().indexOf("up") != -1){
                            volumeUp();
                        }
                    }
                }
                break;
            }

        }
    }

    private void makeVoiceCommand() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); //"en-US"
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (Exception a) {

        }
    }
}




