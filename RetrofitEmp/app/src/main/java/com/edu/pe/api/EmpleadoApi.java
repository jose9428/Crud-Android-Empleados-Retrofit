package com.edu.pe.api;

import com.edu.pe.models.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface  EmpleadoApi {
    @GET("empleados")
    public Call<List<Empleado>> listaEmpleados();

    @POST("empleados")
    public Call<Void> Guardar(@Body Empleado e);

    @GET("empleados/{id}")
    public Call<Empleado> BuscarPorId(@Path("id") int id);

    @DELETE("empleados/{id}")
    public Call<Boolean> Eliminar(@Path("id") int id);

    @PUT("empleados/{id}")
    public Call<Void> Editar(@Body Empleado e , @Path("id") int id);
}
