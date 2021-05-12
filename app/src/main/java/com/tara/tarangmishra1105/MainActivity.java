package com.tara.tarangmishra1105;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    Adapter adapter;
    GridLayoutManager gridLayoutManager;
    private EditText edittext;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView)findViewById(R.id.recyclerview);
        edittext = (EditText)findViewById(R.id.edittext);
        button = (Button)findViewById(R.id.button);
        getClickOnButton();
    }

    private void getClickOnButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = edittext.getText().toString();
                int num = Integer.parseInt(number);
                if(num == 0){
                    Toast.makeText(MainActivity.this, "number required", Toast.LENGTH_SHORT).show();
                }else{
                 Root root = new Root(num);
                    ApiInterface apiService = RetroClient.getClient().create(ApiInterface.class);

                    Call<Root> call = apiService.getData(num);
                    call.enqueue(new Callback<Root>() {
                        @Override
                        public void onResponse(Call<Root> call, Response<Root> response) {
                            if(response.isSuccessful()){
                                List<Datum> datumList = response.body().getData();
                                gridLayoutManager = new GridLayoutManager(MainActivity.this,3);
                                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                         int module = position %6;

                                          if(module == 0)
                                          {
                                              return 2;
                                          }
                                          else{
                                                return  1;
                                            }
                                    }
                                });
                                recyclerview.setLayoutManager(gridLayoutManager);
                                adapter = new Adapter(MainActivity.this,datumList,gridLayoutManager);
                                recyclerview.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Root> call, Throwable t) {
                            Log.e("Tarang", "onFailure: "+t.getMessage());
                            Toast.makeText(MainActivity.this, "failed"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}