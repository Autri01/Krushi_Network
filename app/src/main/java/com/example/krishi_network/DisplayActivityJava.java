package com.example.krishi_network;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivityJava extends AppCompatActivity {

    ImageView imageView;
    Uri imageUri;
    String name, email;
    TextView tvName,tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_java);


        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        imageView = (ImageView) findViewById(R.id.imgShow);

        if (getIntent().getExtras() != null) {
            imageUri = Uri.parse(getIntent().getStringExtra("imageUri"));
            name = getIntent().getStringExtra("Name");
            email = getIntent().getStringExtra("email");

            imageView.setImageURI(imageUri);
            tvName.setText(name);
            tvEmail.setText(email);
        }
    }
}