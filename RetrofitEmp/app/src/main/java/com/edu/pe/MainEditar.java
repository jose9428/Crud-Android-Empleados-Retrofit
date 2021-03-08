package com.edu.pe;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.edu.pe.api.EmpleadoApi;
import com.edu.pe.connection.ConexionAPI;
import com.edu.pe.models.Empleado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainEditar extends AppCompatActivity {
    private int dia,mes,anio,hora,minutos;
    EditText fechaNac , txtCorreo , txtSueldo;
    EditText txtNombre , txtApellidoP , txtApellidoM;
    Spinner spGenero;

    EmpleadoApi api;
    int idEmpleado = 0;
    String fechaRegistro = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_editar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enviar = new Intent(MainEditar.this , MainActivity.class);
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

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            idEmpleado =  parametros.getInt("codigo");
            CargarDatos();
        }

        fechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarCalendar();
            }
        });
    }

    public void CargarDatos(){
        Call<Empleado> call = api.BuscarPorId(idEmpleado);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Empleado e = response.body();
                    txtNombre.setText(e.getNombres());
                    txtApellidoP.setText(e.getApe_paterno());
                    txtApellidoM.setText(e.getApe_materno());
                    txtCorreo.setText(e.getCorreo());
                    txtSueldo.setText(""+e.getSueldo());
                    fechaNac.setText(e.getFecha_nacimiento());
                    fechaRegistro = e.getFecha_registro();
                    spGenero.setSelection(obtenerPosicionItem(spGenero , e.NomGenero()));
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(getApplicationContext() , "No se ha podido cargar datos" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void CargarCalendar(){
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anio=c.get(Calendar.YEAR );

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                AlertDialog.THEME_HOLO_LIGHT,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                fechaNac.setText(year+"-"+String.format("%02d" , (monthOfYear+1))+"-"+String.format("%02d" , (dayOfMonth)) );
            }
        },dia,mes,anio);
        datePickerDialog.show();

    }

    public static int obtenerPosicionItem(Spinner spinner, String buscar) {
        int posicion = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(buscar)) {
                posicion = i;
            }
        }
        return posicion;
    }

    public void Guardar(View view){
        Empleado e = new Empleado();
        e.setId_empleado(idEmpleado);
        e.setNombres(txtNombre.getText().toString().trim());
        e.setApe_paterno(txtApellidoP.getText().toString().trim());
        e.setApe_materno(txtApellidoM.getText().toString().trim());
        e.setCorreo(txtCorreo.getText().toString().trim());
        e.setSueldo(Double.parseDouble(txtSueldo.getText().toString()));
        e.setGenero(spGenero.getSelectedItem().toString().equals("Masculino")?"M":"F");
        e.setFecha_nacimiento(fechaNac.getText().toString());
        e.setFecha_registro(fechaRegistro);
        Call call = api.Editar(e , e.getId_empleado());

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext() , "Empleado actualizado correctamente!!" , Toast.LENGTH_LONG).show();

                    Intent enviar = new Intent(getApplicationContext() , MainActivity.class);
                    startActivity(enviar);
                }else{
                    Toast.makeText(getApplicationContext() , "No se ha podido actualizar datos" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext() , "No se ha podido actualizar datos" , Toast.LENGTH_LONG).show();
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
}