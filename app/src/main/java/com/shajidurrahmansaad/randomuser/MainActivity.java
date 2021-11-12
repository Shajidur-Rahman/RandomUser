package com.shajidurrahmansaad.randomuser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "ShajidurRahman";

    // TODO: TextViews and ImageViews
    TextView nameText;
    TextView emailTExt;
    TextView genderText;
    TextView ageText;
    TextView countryText;
    TextView phoneText;

    Button button;

    ImageView imageView;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.name);
        emailTExt = findViewById(R.id.email);
        genderText = findViewById(R.id.gender);
        ageText = findViewById(R.id.age);
        countryText = findViewById(R.id.country);
        phoneText = findViewById(R.id.phone);

        button = findViewById(R.id.button);

        imageView = findViewById(R.id.imageView);

        // TODO: creating request
        requestQueue = Volley.newRequestQueue(this);
        random();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Getting new ", Toast.LENGTH_SHORT).show();
                random();
            }
        });


    }

    public void random(){

        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, "https://randomuser.me/api", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray features = response.getJSONArray("results");
                    JSONObject info1 = features.getJSONObject(0);

                    // TODO: gender
                    String gender = info1.getString("gender");
                    genderText.setText(gender + "");

                    // TODO: name
                    JSONObject name = info1.getJSONObject("name");
                    String title = name.getString("title");
                    String first = name.getString("first");
                    String last = name.getString("last");
                    nameText.setText(title + " " + first +" "+ last + "");

                    // TODO: email
                    String email = info1.getString("email");
                    emailTExt.setText(email + "");

                    // TODO: age
                    JSONObject age = info1.getJSONObject("dob");
                    String ageText2 = age.getString("age");
                    ageText.setText(ageText2);

                    // TODO: country
                    JSONObject country = info1.getJSONObject("location");
                    String coun = country.getString("country");
                    countryText.setText(coun);

                    // TODO: phone
                    String pho = info1.getString("phone");
                    phoneText.setText(pho);

                    // TODO: picture
                    JSONObject pic = info1.getJSONObject("picture");
                    String url = pic.getString("medium");

//                    URL url1 = new URL("" + url);
//                    Bitmap bmp = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
//                    imageView.setImageBitmap(bmp);
                    Log.d(TAG, "onResponse: " + url);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObject);
    }

}