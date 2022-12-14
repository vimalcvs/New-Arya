package com.bindesh.aryaveerdalchattisgarh.anime;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bindesh.aryaveerdalchattisgarh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecondActivity2 extends AppCompatActivity {

    private final String JSON_URL = "https://aryaveerdal.in/anime.json" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;

    private List<com.bindesh.aryaveerdalchattisgarh.anime.Anime> lstAnime ;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);


        lstAnime = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);


        jsonrequest();


        com.bindesh.aryaveerdalchattisgarh.anime.RecyclerViewAdapter myadapter = new com.bindesh.aryaveerdalchattisgarh.anime.RecyclerViewAdapter(this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }



    //online use json file

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;

                        com.bindesh.aryaveerdalchattisgarh.anime.Anime anime = new com.bindesh.aryaveerdalchattisgarh.anime.Anime() ;
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));

                        anime.setCategorie(jsonObject.getString("categorie"));

                        anime.setImage_url(jsonObject.getString("img"));
                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstAnime);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(SecondActivity2.this);
        requestQueue.add(request) ;


    }


    private void setuprecyclerview(List<com.bindesh.aryaveerdalchattisgarh.anime.Anime> lstAnime) {
        com.bindesh.aryaveerdalchattisgarh.anime.RecyclerViewAdapter myadapter = new com.bindesh.aryaveerdalchattisgarh.anime.RecyclerViewAdapter(this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }


}

