package com.voidstudio.quickcashreg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;


public class WelcomeWindow extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.welcomewindow);

    Button registerButton = findViewById(R.id.welcomeRegisterButton);
    Button logInButton = findViewById(R.id.welcomeLogInButton);
    registerButton.setOnClickListener(WelcomeWindow.this);
    logInButton.setOnClickListener(WelcomeWindow.this);

  }

  protected void switch2RegisterWindow(){
    Intent registerSwitch = new Intent(WelcomeWindow.this, MainActivity.class);
    startActivity(registerSwitch);
  }

  protected void switch2LogInWindow(){
    //Need to create log in view
    Intent logInSwitch = new Intent(WelcomeWindow.this, LogIn.class);
    startActivity(logInSwitch);
  }

  @Override
  public void onClick(View view) {
    if(view.getId() == R.id.welcomeRegisterButton){
      switch2RegisterWindow();
    }
    if(view.getId() == R.id.welcomeLogInButton){
      switch2LogInWindow();
    }
  }

}