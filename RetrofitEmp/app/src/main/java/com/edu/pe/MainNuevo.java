package com.edu.pe;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.edu.pe.api.EmpleadoApi;
import com.edu.pe.connection.ConexionAPI;
import com.edu.pe.models.Empleado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainNuevo extends AppCompatActivity {
    private int dia,mes,anio,hora,minutos;
    EditText fechaNac , txtCorreo , txtSueldo;
    EditText txtNombre , txtApellidoP , txtApellidoM;
    Spinner spGenero;

    EmpleadoApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuevo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enviar = new Intent(MainNuevo.this , MainActivity.class);
                startActivity(enviar);
            }
        });

        api = ConexionAPI.getConnection().create(EmpleadoApi.class);

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellidoP = (EditText)findViewById(R.id.txtApellidoP);
        txtApellidoM = (EditText)findViewById(R.id.txtApellidoM);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtSueldo = (EditText)findViewById(R.id.txtSueldo);
        spGenero = (Spinner)findViewById(R.id.spGenero);
        fechaNac = (EditText) findViewById(R.id.txtFechaNac);

        fechaNac.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 CargarCalendar();
             }
        });
    }

    public void Guardar(View view){
        Empleado e = new Empleado();
        e.setNombres(txtNombre.getText().toString().trim());
        e.setApe_paterno(txtApellidoP.getText().toString().trim());
        e.setApe_materno(txtApellidoM.getText().toString().trim());
        e.setCorreo(txtCorreo.getText().toString().trim());
        e.setSueldo(Double.parseDouble(txtSueldo.getText().toString()));
        e.setGenero(spGenero.getSelectedItem().toString().equals("Masculino")?"M":"F");
        e.setFecha_nacimiento(fechaNac.getText().toString());

        Call call = api.Guardar(e);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
               // Log.w("Guardar" , response.toString());
                if(response.isSuccessful()){
                    LimpiarCasillas();
                    Toast.makeText(getApplicationContext() , "Empleado Grabado Correctamente" , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext() , "No se ha podido guardar datos" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext() , "No se ha podido guardar datos" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Nuevo(View view){
        LimpiarCasillas();
    }

    public void LimpiarCasillas(){
        txtSueldo.setText("");
        txtCorreo.setText("");
        txtApellidoM.setText("");
        txtApellidoP.setText("");
        fechaNac.setText("");
        txtNombre.setText("");
    }

    public void CargarCalendar(){
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anio=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,  AlertDialog.THEME_HOLO_LIGHT,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               // fechaNac.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                fechaNac.setText(year+"-"+String.format("%02d" , (monthOfYear+1))+"-"+String.format("%02d" , (dayOfMonth)) );
            }
        },dia,mes,anio);
        datePickerDialog.show();
    }


}