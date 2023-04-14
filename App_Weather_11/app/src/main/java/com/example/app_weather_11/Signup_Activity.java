package com.example.app_weather_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.example.app_weather_11.databinding.ActivitySignupBinding;

public class Signup_Activity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if (email.equals("")|| password.equals("")|| confirmPassword.equals(""))
                    Toast.makeText(Signup_Activity.this, "Bạn cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);
                        if (checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email,password);

                            if(insert == true){
                                Toast.makeText(Signup_Activity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(Signup_Activity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Signup_Activity.this, "Người dùng đã tồn taị, Đăng nhập", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Signup_Activity.this, "Hãy kiểm tra lại mật khẩu.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);

            }
        });

    }
}