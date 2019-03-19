package com.example.gsontest;


import org.json.JSONObject;

import java.sql.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

// call with param parameter in order to make title search.
// pass no parameter to see get all movies.




public class getMovies extends Commons {

    String method = Commons.methods.getMovies;
    params params;

    public getMovies() {
        params = new params();
    }

    public getMovies(getMovies.params params) {
        this.params = params;
    }

    static class params{
        String[] properties = {"title"};
        sort sort = new sort();
        limits limits = new limits();
        filter filter;

        public params() {
            // do nothing;
        }

        public params(getMovies.filter filter) {
            this.filter = filter;

        }
    }
    static class limits{
        int start = 0;
        int end = 100;
    }

    static class sort{
        String method = "title";
        String order = "ascending";
        boolean ignorearticle = true;
    }
    static class filter{
        String operator = "contains";
        String field = "title";
        String value;

        public filter(String value) {
            this.value = value;
        }
    }
}
