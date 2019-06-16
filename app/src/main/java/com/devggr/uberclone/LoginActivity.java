package com.devggr.uberclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devggr.uberclone.Pojos.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase db;
    DatabaseReference users;

    ConstraintLayout rootLayout;
    Button entrar, registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        entrar = findViewById(R.id.btnIniciarSesion);
        registrarse = findViewById(R.id.btnRegistrarse);
        rootLayout = findViewById(R.id.rootLayout);

        //inicializacion firebase
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("users");

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showRegisterDialog();

            }
        });

    }
    private void showLoginDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Entrar");
        dialog.setMessage("Captura todos los datos");
        LayoutInflater inflater = LayoutInflater.from(this);

        View login_layout = inflater.inflate(R.layout.layout_login, null);

        final EditText email = login_layout.findViewById(R.id.edtEmail);
        final EditText pass = login_layout.findViewById(R.id.edtPassword);

        dialog.setView(login_layout);

        //setbuttons

        dialog.setPositiveButton("Entrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        entrar.setEnabled(false);


                        //Validation Editexts
                        if (TextUtils.isEmpty(email.getText().toString())) {
                            Snackbar.make(rootLayout, "Por favor, ingrese su email", Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        if (pass.getText().toString().length() < 8) {
                            Snackbar.make(rootLayout, "Password demasiado corto", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        final SpotsDialog waitingDialog = new SpotsDialog(LoginActivity.this);
                        waitingDialog.show();

                        //login
                        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                waitingDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MapaActivity.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                waitingDialog.dismiss();
                                Snackbar.make(rootLayout, "Error: "+e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                entrar.setEnabled(true);
                            }
                        });

                    }
                });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void showRegisterDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Registrate");
        dialog.setMessage("Captura todos los datos");
        LayoutInflater inflater = LayoutInflater.from(this);

        View register_layout = inflater.inflate(R.layout.layout_register, null);

        final EditText nombre = register_layout.findViewById(R.id.edtNombre);
        final EditText email = register_layout.findViewById(R.id.edtEmail);
        final EditText pass = register_layout.findViewById(R.id.edtPassword);

        dialog.setView(register_layout);

        //setbuttons

        dialog.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                //Validation Editexts
                if(TextUtils.isEmpty(nombre.getText().toString())){
                    Snackbar.make(rootLayout, "Por favor, ingrese su nombre", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(rootLayout, "Por favor, ingrese su email", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(pass.getText().toString().length() < 8){
                    Snackbar.make(rootLayout, "Password demasiado corto", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //saveUsertodb

                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setNombre(nombre.getText().toString());
                        user.setPassword(pass.getText().toString());

                        //use email to key
                        users.child(firebaseAuth.getUid())
                                .setValue(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(rootLayout, "Registrado correctamente", Snackbar.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(rootLayout, "Error en el registro: "+e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(rootLayout, "Error en el registro: "+e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
