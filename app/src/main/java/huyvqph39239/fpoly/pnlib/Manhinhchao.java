package huyvqph39239.fpoly.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Manhinhchao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);
        ImageView ivlogo = findViewById(R.id.ivlogo);
        Glide.with(this).load(R.drawable.logo).into(ivlogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Manhinhchao.this, dangnhap.class));
            }
        }, 3000);
    }
    }
