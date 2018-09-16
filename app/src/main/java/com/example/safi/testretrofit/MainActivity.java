package com.example.safi.testretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safi.testretrofit.user_api.UserName;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private ImageView mImageview;
    private TextView mTextView1,mTextView2,mTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new ProgressDialog(this);
        dialog.setMessage("please wait...");

        mImageview=findViewById(R.id.imageview);
        mTextView1=findViewById(R.id.textview1);
        mTextView2=findViewById(R.id.textview2);
        mTextView3=findViewById(R.id.textview3);
    }
    public void getUser(View view){

        dialog.show();
        ApiEndUser apiEndUser=ApiClient.getClient().create(ApiEndUser.class);
        Call<UserName> call=apiEndUser.getUser();
        call.enqueue(new Callback<UserName>() {
            @Override
            public void onResponse(Call<UserName> call, Response<UserName> response) {
                if(response.isSuccessful()){
                    dialog.dismiss();
                    UserName userName=response.body();
                    if(userName !=null){
                        Toast.makeText(MainActivity.this,userName.getResults().get(0).getEmail(), Toast.LENGTH_SHORT).show();

                        mTextView1.setText(userName.getResults().get(0).getName().getTitle());
                        mTextView1.setText(userName.getResults().get(0).getName().getFirst());
                        mTextView1.setText(userName.getResults().get(0).getName().getLast());
                        mTextView2.setText(userName.getResults().get(0).getEmail());
                        mTextView3.setText(userName.getResults().get(0).getLocation().getCity());
                        Picasso.get()
                                .load(userName.getResults().get(0).getPicture().getLarge())
                                .into(mImageview);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserName> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
