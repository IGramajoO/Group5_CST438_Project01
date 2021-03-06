package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityModifyAccountBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.User;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;

public class ModifyAccount extends AppCompatActivity {

    private static final String USER_ID_KEY = "userIdKey";

    private ActivityModifyAccountBinding binding;
    private UserDao userDao;

    private String username;
    private String password;
    private String passwordConfirm;
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_account);
        binding = ActivityModifyAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDatabase();
        int userId = getIntent().getIntExtra(USER_ID_KEY, -1);
        user = userDao.getUserById(userId);

        binding.submitUserChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = binding.newPassword.getText().toString();
                passwordConfirm = binding.confirmNewPassword.getText().toString();

                if (verifyAccount()){
                    updateUser();
                    //intent to kick user back to login once they change account info
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ModifyAccount.this);
                alert.setTitle("Delete Account?");
                alert.setMessage("Are you sure you want to delete your account?");

                alert.setNegativeButton("Yes, I'm sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDao.deleteUser(user);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                alert.setPositiveButton("No", null);
                alert.show();
            }
        });
    }

    /**
     * Checks to make password fields match. Nearly the same method as CreateAccount but only checks
     * the password fields against each other
     * @return boolean Returns if passwords match
     */
    private boolean verifyAccount(){
        if(!password.equals(passwordConfirm)){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void updateUser(){
        user.setPass(password);
        userDao.insert(user);
    }

    private void getDatabase(){
        userDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }
}