package com.daclink.drew.sp22.cst438_project01_starter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMainBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public UserDao db;
    public User user;

    Button createAccBtn;
    Button loginBtn;

    EditText pass;
    EditText name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        createAccBtn = view.findViewById(R.id.create_acc);
        name = view.findViewById(R.id.user);
        pass = view.findViewById((R.id.pass));
        loginBtn = view.findViewById(R.id.login);

    }

//    public void login(View v){
//
//        user.setUsername(name.getText().toString());
//
//    }

    public void login(View v){

        Toast.makeText(this, "Testing", Toast.LENGTH_SHORT).show();
//        user.setUsername((name.getText().toString()));
//        user.setPass(pass.getText().toString());

        user.setUsername("Testing");
        user.setPass("Purposes");

        Toast.makeText(this, user.getUsername() + " " + user.getPass(), Toast.LENGTH_SHORT).show();
    }

}
