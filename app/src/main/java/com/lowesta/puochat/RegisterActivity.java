package com.lowesta.puochat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar actionbarRegister;
    private EditText txtUsername,txtEmail,txtPassword;
    private Button btnRegister;
    private FirebaseAuth auth;

    @SuppressLint("WrongViewCast")
    public void init(){
        actionbarRegister=(Toolbar)findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Oluştur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth=FirebaseAuth.getInstance();
        txtUsername = (EditText) findViewById(R.id.txtusername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }
    private void createNewAccount() {

        String username = txtUsername.getText().toString();
        String email =txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)){

            Toast.makeText(this,"Email Alanı Boş Bırakılamaz!",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Şifre Alanı Boş Bırakılamaz!",Toast.LENGTH_LONG).show();
        }
        else{
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Hesabınız Başarılı Bir Şekilde Oluşturuldu",Toast.LENGTH_LONG).show();
                        Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(LoginIntent);
                        finish();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Bir Hata Oluştu.Lütfen Tekrar Deneyin",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
