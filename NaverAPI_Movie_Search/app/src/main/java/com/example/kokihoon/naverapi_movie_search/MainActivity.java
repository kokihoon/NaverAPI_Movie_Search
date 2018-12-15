package com.example.kokihoon.naverapi_movie_search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kokihoon.naverapi_movie_search.data.MovieItem;
import com.example.kokihoon.naverapi_movie_search.data.MovieList;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String search;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = editText.getText().toString();
                if(search.getBytes().length <= 0) {
                    Toast.makeText(getApplicationContext(), "검색어를 입력하세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    sendRequest();
                }
            }
        });


        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());

        }
    }

    public void sendRequest() {
        try {
            String text = URLEncoder.encode(search, "UTF-8");
            String apiURL = "https://"+ AppHelper.host+ text+"&display=100"; // json 결과
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    apiURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("응답  -> " + response);
                            processResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("에러 -> " + error.getMessage());
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("X-Naver-Client-Id", NaverAPI_key.clientId);
                    params.put("X-Naver-Client-Secret", NaverAPI_key.clientSecret);
                    Log.d("getHeader =>", "" + params);

                    return params;
                }
            };

            request.setShouldCache(false);
            AppHelper.requestQueue.add(request);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processResponse(String response) {
        Gson gson = new Gson();

        ArrayList<MovieItem> movieLists = new ArrayList<>();

        final MovieList movieList = gson.fromJson(response, MovieList.class);
        if(movieList.items.size() == 0) {
            Toast.makeText(getApplicationContext(), "'" + search + "' 에 대한 검색이 없습니다.", Toast.LENGTH_LONG).show();
        }
        for(int i = 0; i < movieList.items.size(); i++) {
            MovieItem movieItem = new MovieItem(movieList.items.get(i).link, movieList.items.get(i).image, movieList.items.get(i).subtitle, movieList.items.get(i).title, movieList.items.get(i).pubDate, movieList.items.get(i).director, movieList.items.get(i).actor, movieList.items.get(i).userRating);

            movieLists.add(movieItem);
        }

        RecyclerDataAdapter recyclerDataAdapter = new RecyclerDataAdapter(movieLists);

        recyclerDataAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(recyclerDataAdapter);
    }
}
