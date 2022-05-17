package com.example.krishi_network;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    ImageView imgView;
    Button button3;
    private static int PICK_IMAGE_REQUEST = 1;
    Uri imageUri;
    EditText etName, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        imgView = (ImageView) findViewById(R.id.imgSelect);
        button3 = (Button) findViewById(R.id.btnSubmit);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        start();
    }

    private void start() {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRequiredFields()) {
                    if (checkEmail()){
                        try {
                            Intent intent = new Intent(getApplicationContext(), DisplayActivityJava.class);
                            intent.putExtra("imageUri", imageUri.toString());
                            intent.putExtra("Name", etName.getText().toString());
                            intent.putExtra("email", etEmail.getText().toString());
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Please select an Image", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Please fill up the valid email-Id",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please Fill up the Name",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkEmail() {
        if(isValidEmail(etEmail.getText().toString())){
            return true;
        }else{
            return  false;
        }
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                // Get uri
                imageUri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);
                // Set image
                imgView.setImageBitmap(scaled);

            } else {
                Toast.makeText(this, "No.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Oops! Sorry", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }
    public boolean checkRequiredFields() {
        if (!etName.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void loadImagefromGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}