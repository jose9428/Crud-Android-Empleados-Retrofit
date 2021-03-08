package com.edu.pe.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.pe.R;
import com.edu.pe.models.Empleado;

import java.util.List;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.ViewHolder> implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(click !=null){
                click.onClick(view);
            }
        }
        public static class ViewHolder extends RecyclerView.ViewHolder{
            TextView nombre , apellidos,  sueldo;
            ImageView imagen;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                nombre = (TextView)itemView.findViewById(R.id.txtNombres);
                apellidos = (TextView)itemView.findViewById(R.id.txtApellidos);
                sueldo = (TextView) itemView.findViewById(R.id.txtSueldo);
                imagen = (ImageView)itemView.findViewById(R.id.imageView1);
            }
        }

        private List<Empleado> lista;
        private View.OnClickListener click;

        public EmpleadoAdapter(List<Empleado> lista) {
            this.lista = lista;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_empleado , parent , false);
            view.setOnClickListener(this);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        public void setOnClickListener(View.OnClickListener click){
            this.click = click;
        }

        //Realiza las modificaciones de cada item
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Empleado a = lista.get(position);

            holder.nombre.setText(a.getNombres()+" "+a.getApe_paterno()+" "+a.getApe_materno().charAt(0)+".");
            holder.apellidos.setText(a.getCorreo());
            holder.sueldo.setText(""+a.getSueldo());

            if(a.getGenero().equalsIgnoreCase("M")){
                holder.imagen.setImageResource(R.drawable.hombre);
            }else{
                holder.imagen.setImageResource(R.drawable.mujer);
            }
        }

        //Cantidad de elementos que se van a procesar
        @Override
        public int getItemCount() {
            return lista.size();
        }
    }
