package com.edu.pe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.edu.pe.adaptador.EmpleadoAdapter;
import com.edu.pe.api.EmpleadoApi;
import com.edu.pe.connection.ConexionAPI;
import com.edu.pe.models.Empleado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EmpleadoApi api;
    RecyclerView recy1;
    String nomEmpleado = "";
    Empleado e = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enviar = new Intent(MainActivity.this , MainNuevo.class);
                startActivity(enviar);
            }
        });
        recy1 = (RecyclerView)findViewById(R.id.recyview1);
        recy1.setLayoutManager(new LinearLayoutManager(this));

        api = ConexionAPI.getConnection().create(EmpleadoApi.class);
        CargarListado();
    }

    public void CargarListado(){

        Call<List<Empleado>> call =  api.listaEmpleados();

        call.enqueue(new Callback<List<Empleado>>() {
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(response.isSuccessful()){
                    List<Empleado> lista = response.body();

                    EmpleadoAdapter adapta = new EmpleadoAdapter(lista);
                    recy1.setAdapter(adapta);
                    adapta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int posicion = recy1.getChildAdapterPosition(view);
                            e = lista.get(posicion);
                            nomEmpleado = e.getNombres()+" "+e.getApe_paterno()+" "+e.getApe_materno().charAt(0)+".";
                            Cuadro();
                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext() , "No hay data" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Toast.makeText(getApplicationContext() , "Error : "+t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void Cuadro(){
        final CharSequence[] opciones = {"Editar" , "Eliminar" , "Ver Detalle"};
        final AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("Seleccione una opcion");
        //alerta.setIcon(R.drawable.hombre);
        alerta.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (opciones[i].toString().toLowerCase()){
                    case "editar":
                        EnviarDatosModif();
                        break;
                    case "eliminar":
                        Confirmacion();
                        break;
                    case "ver detalle":
                        Detalle();
                        break;
                }

            }
        });
        alerta.show();
    }

    public void EnviarDatosModif(){
        Intent enviar = new Intent(MainActivity.this , MainEditar.class);
        enviar.putExtra("codigo" , e.getId_empleado());
        startActivity(enviar);
    }

    public void Confirmacion(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("Confirmación");
        alerta.setMessage("¿Esta seguro que desea eliminar al empleado "+nomEmpleado+"?");
        //alerta.setNegativeButton("Cancelar" , null);
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // Toast.makeText(getApplicationContext() , "Cancelado : "+i,Toast.LENGTH_LONG).show();
            }
        });
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Eliminar();

            }
        });
        alerta.show();
    }

    public void Eliminar(){
        Call<Boolean> call = api.Eliminar(e.getId_empleado());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    CargarListado();
                    Toast.makeText(getApplicationContext() , "Empleado eliminado correctamente!!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getApplicationContext() , "No se ha podido eliminar al empleado!!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Detalle(){
        String cadena = "";

        cadena+="Empleado: "+e.getNombres()+" "+e.getApe_paterno()+" "+e.getApe_materno().charAt(0)+"."+"\n\n";
        cadena+="Genero : "+e.NomGenero()+"\n\n";
        cadena+="Fecha Nacimiento: "+e.getFecha_nacimiento()+"\n\n";
        cadena+="Fecha Registro : " +e.getFecha_registro()+"\n\n";
        cadena+="Correo : " +e.getCorreo()+"\n\n";
        cadena+="Sueldo : " +e.getSueldo()+"\n";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalle");
        builder.setMessage(cadena);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}