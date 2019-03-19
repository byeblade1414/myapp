package com.example.gsontest;

public class openMovie extends  Commons {
    String method = methods.openMovie;
    public params params;

    public openMovie(params p) {
        this.params = p;
    }

    static class params{
        movieid item;

        public params(movieid item) {
            this.item = item;
        }

        static class movieid{
            int movieid;

            public movieid(int movieid) {
                this.movieid = movieid;
            }
        }
    }

}
