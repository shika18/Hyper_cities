package com.example.shika.hyperlist;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Data_Model;

public class MainActivity extends AppCompatActivity {

    RecyclerView.Adapter recyclerViewadapter,recyclerViewadapter2;
    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    List<Data_Model> rewardList;
    RecyclerView recyclerView,recyclerView2;
    RequestQueue requestQueue ;
    private static  final String listOfCitiesURL2 = "http://grapesnberries.getsandbox.com/products?count=10&from=1";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        layoutManagerOfrecyclerView = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rewardList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, listOfCitiesURL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray branches3 = new JSONArray(response);

                            for (int i = 0; i < branches3.length(); i++) {
                                JSONObject obj = branches3.getJSONObject(i).getJSONObject("image");
                                String obj2 = branches3.getJSONObject(i).getString("productDescription");
                                int obj3 = branches3.getJSONObject(i).getInt("price");

                                rewardList.add(new Data_Model(

                                        obj2,
                                        obj.getString("url"),
                                        obj.getInt("width"),
                                        obj.getInt("height"),
                                        obj3

                                ));


                            }

                            recyclerViewadapter2 = new RecyclerView_Adapter(MainActivity.this, rewardList);
                            recyclerView.setAdapter(recyclerViewadapter2);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }
}

