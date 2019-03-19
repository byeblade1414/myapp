package com.example.gsontest;

public class Commons {
    String jsonrpc = "2.0";
    int id = 1;

    static class methods {
        static String pausePlayer = "Player.PlayPause";
        static String showPopup = "GUI.ShowNotification";
        static String select = "Input.Select";
        static String navigateDown = "Input.down";
        static String navigateUp = "Input.Up";
        static String openMovie = "Player.Open";
        static String getMovies = "VideoLibrary.GetMovies";
        static String playPlayer = "Player.PlayPause";
    }
    static class urldef{
        static String url = "http://192.168.43.121/jsonrpc"; // default url.

        public static void setUrl(String url) {
            Commons.urldef.url = url;
        }
    }

    static class otherDefinitions{
        static int movieNotFound = -1;
    }
    static class volumeDef{
        private static int volume = 50;

        public static int getVolume() {
            return volume;
        }

        public static void setVolume(int volum) {
            volume = volum;
        }
    }
}
