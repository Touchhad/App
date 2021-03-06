package com.example.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.API.ApiClient;
import com.example.app.R;
import com.example.app.Request.RegisterRequest;
import com.example.app.Response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText edName, edFirstName, edEmail, edPass, edPassCon;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edName = findViewById(R.id.edName);
        edFirstName = findViewById(R.id.edFirstName);
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPassword);
        edPassCon = findViewById(R.id.edPasswordConfirm);
        btnRegister = findViewById(R.id.btnSignUp);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edEmail.getText().toString())||TextUtils.isEmpty(edPass.getText().toString())||TextUtils.isEmpty(edName.getText().toString())||TextUtils.isEmpty(edFirstName.getText().toString())){
                 String message = "FWAFAWF";
                 ShowAlertDialogWindow(message);
                }
                else {

                    registerUser();
                }

            }
        });
    }
public void ShowAlertDialogWindow(String s){
        AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).setMessage(s).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();
        alertDialog.show();
}


    public void onClickSignIn (View view)
    {
        Intent signUp = new Intent(SignUp.this, SignIn.class);
        startActivity(signUp);
    }


    public void registerUser(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(edEmail.getText().toString());
        registerRequest.setPassword(edPass.getText().toString());
        registerRequest.setFirstName(edName.getText().toString());
        registerRequest.setLastName(edFirstName.getText().toString());
        Call<RegisterResponse> registerResponseCall = ApiClient.getRegister().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    String message = "?????? ????...";
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();

                    finish();
                }else {

                    String message = "??????-???? ?????????? ???? ??????";
                    Toast.makeText(SignUp.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = "?????????????????????? ???????????? ??????????????";
                Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignUp.this,SignIn.class));

            }
        });
    }
}