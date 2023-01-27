package com.example.encriptado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button generarClave;
    Button cifrarDescifrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generarClave = (Button)findViewById(R.id.btnGenerar);
        cifrarDescifrar = (Button)findViewById(R.id.btnCifrar);
        generarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,GenerarClave.class);
                startActivity(i);
            }
        });

        cifrarDescifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,CifrarDescifrar.class);
                startActivity(i);
            }
        });
    }
}