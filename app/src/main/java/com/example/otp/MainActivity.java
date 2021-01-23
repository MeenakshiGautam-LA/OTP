package com.example.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.database.DatabaseManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String JSON_URL = "https://demo5636362.mockable.io/stats";


    JSONObject objnew;
    List<JSONObject> monthList;

    DatabaseManager databaseManager;
    Long result;
    LinearLayout month_ll, stat_ll, linearChart, parent_ll;
    int color = Color.RED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseManager = new DatabaseManager(MainActivity.this);
        monthList = new ArrayList<>();
        month_ll = findViewById(R.id.month_list);
        stat_ll = findViewById(R.id.stat_list);
        parent_ll = findViewById(R.id.parent_ll);
        linearChart = findViewById(R.id.linearChart);

        loadMonthList();
    }

    private void loadMonthList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);
                        parent_ll.setVisibility(View.VISIBLE);
                        Log.d("response", response);

                        try {

                            JSONObject obj = new JSONObject(response);


                            JSONArray dataArray = obj.getJSONArray("data");


                            for (int i = 0; i < dataArray.length(); i++) {

                                JSONObject dataObject = dataArray.getJSONObject(i);


                                objnew = new JSONObject();
                                objnew.put("month", dataObject.getString("month"));
                                objnew.put("stat", dataObject.getString("stat"));


                                monthList.add(objnew);

                                TextView textView1 = new TextView(MainActivity.this);
                                textView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                                textView1.setText(dataObject.getString("month"));
                                textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
                                textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
                                month_ll.addView(textView1);


                                TextView textView2 = new TextView(MainActivity.this);
                                textView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                                textView2.setText(dataObject.getString("stat"));
                                textView2.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
                                textView2.setPadding(20, 20, 20, 10);// in pixels (left, top, right, bottom)
                                stat_ll.addView(textView2);


                                View view = new View(MainActivity.this);
                                view.setBackgroundColor(color);
                                view.setLayoutParams(new LinearLayout.LayoutParams(25, Integer.parseInt(dataObject.getString("stat"))));
                                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
                                        .getLayoutParams();
                                params.setMargins(3, 0, 0, 0); // substitute parameters for left,
                                // top, right, bottom
                                view.setLayoutParams(params);
                                linearChart.addView(view);


                            }


                            //Inserting data

                            result = databaseManager.insertData(monthList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
