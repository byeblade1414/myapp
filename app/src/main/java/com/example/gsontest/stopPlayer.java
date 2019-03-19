package com.example.gsontest;

public class stopPlayer extends Commons{
    String method = "Player.Stop";
    params params; // with construction initialization. Please compare with pausePlayer class.

    public stopPlayer(stopPlayer.params params) {
        this.params = params;
    }

    static class params{
        int playerid = 1;
    }
}
