package com.datasoft.postandget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.datasoft.postandget.models.Datum;
import com.datasoft.postandget.models.Response;
import com.datasoft.postandget.models.User;
import com.datasoft.postandget.response.Methods;
import com.datasoft.postandget.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
   private Button button,btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        btn = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Methods methods = RetrofitClient.GetRetrofitInstance().create(Methods.class);
                Call<Response> call = methods.getAllData();

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Log.e(TAG,"on response call"+response.code());
                        List<Datum> data = response.body().getData();

                        for (Datum datum1:data){

                            Log.e(TAG, "onResponse: "+datum1.getEmail() );
                        }

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.getMessage() );
                    }
                });
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               btnPostReqClick();

            }
        });
    }

    private void btnPostReqClick() {
        Methods methods = RetrofitClient.GetRetrofitInstance().create(Methods.class);
        Call<User> call =methods.getUserInformation("ARY","Android Developer");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Log.e(TAG,"on Response:"+response.code());
                Log.e(TAG,"on Response: name "+response.body().getName());
                Log.e(TAG,"on Response:createdAt :"+response.body().getCreatedAt());
                Log.e(TAG,"on Response:job :"+response.body().getJob());
                Log.e(TAG,"on Response:id :"+response.body().getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG,"onFailure: "+t.getMessage());

            }
        });
    }
}