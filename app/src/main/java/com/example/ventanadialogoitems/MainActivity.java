package com.example.ventanadialogoitems;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnV_Lista, btnV_Radio, btnV_Check;
    private static final int DIALOG_LISTA = 1, DIALOG_RADIO = 2, DIALOG_CHECK = 3;
    private String[] arrayColores;
    private List<String> elecciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayColores = getResources().getStringArray(R.array.colores);

        btnV_Lista = findViewById(R.id.btnV_Lista);
        btnV_Radio = findViewById(R.id.btnV_Radio);
        btnV_Check = findViewById(R.id.btnV_Check);

        btnV_Lista.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                showDialog(DIALOG_LISTA);
            }
        });

        btnV_Radio.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                showDialog(DIALOG_RADIO);
            }
        });

        btnV_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                showDialog(DIALOG_CHECK);
            }
        });
    }

    @Override
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_LISTA:
                ventanaLista(ad);
                break;
            case DIALOG_RADIO:
                ventanaRadio(ad);
                break;
            case DIALOG_CHECK:
                ventanaCheck(ad);
                break;
        }
        return ad.create();
    }

    public AlertDialog.Builder ventanaLista(AlertDialog.Builder ad) {
        ad.setTitle("Elige un color");
        ad.setItems(arrayColores, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Opción elegida: " + arrayColores[which],
                        Toast.LENGTH_SHORT).show();
            }
        });
        return ad;
    }

    public AlertDialog.Builder ventanaRadio(AlertDialog.Builder ad) {
        ad.setTitle("Elige un color");
        ad.setSingleChoiceItems(arrayColores, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Opción elegida: " + arrayColores[which],
                        Toast.LENGTH_SHORT).show();
            }
        });
        return ad;
    }

    public AlertDialog.Builder ventanaCheck(AlertDialog.Builder ad) {
        ad.setTitle("Elige un color");
        ad.setMultiChoiceItems(arrayColores, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    elecciones.add(arrayColores[which]);
                } else {
                    elecciones.remove(arrayColores[which]);
                }
            }
        });
        ad.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onClick(DialogInterface dialog, int which) {
                if (!elecciones.isEmpty()) {
                    for (String eleccion : elecciones) {
                        Toast.makeText(getApplicationContext(),
                                "Opción elegida: " + eleccion,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Ninguna opción elegida",
                            Toast.LENGTH_SHORT).show();
                }
                removeDialog(DIALOG_CHECK);
                elecciones.clear();
            }
        });
        return ad;
    }
}
