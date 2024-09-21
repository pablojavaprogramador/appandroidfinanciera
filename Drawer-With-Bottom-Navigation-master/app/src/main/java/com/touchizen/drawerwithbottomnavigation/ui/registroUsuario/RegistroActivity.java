package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;
import com.touchizen.drawerwithbottomnavigation.network.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.ui.login.LoginActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText nombreCliente, correoElectronico, password, confirmarPassword;
    private CheckBox avisoPrivacidad;
    private RegistroViewModel registroViewModel;
    private AlertDialog progressDialog;
    private ImageView eyePassword, eyeConfirmarPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro);

        // Inicializar los campos de entrada
        nombreCliente = findViewById(R.id.editTextNombre);
        correoElectronico = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        confirmarPassword = findViewById(R.id.editTextConfirmarPassword);
        avisoPrivacidad = findViewById(R.id.checkBoxAvisoPrivacidad);
        Button botonRegistrar = findViewById(R.id.buttonRegistrarUsuario);
        TextView inicioSesionLabel = findViewById(R.id.link_to_login);
        TextView validacionSesionLabel=findViewById(R.id.link_to_token);
        eyePassword = findViewById(R.id.eyePassword);
        eyeConfirmarPassword = findViewById(R.id.eyeConfirmarPassword); // Nuevo campo

        // Inicializar ViewModel con el Factory
        UserRepository userRepository = new UserRepository(NetworkApiAdapter.getApiService());
        RegistroViewModelFactory factory = new RegistroViewModelFactory(userRepository);
        registroViewModel = new ViewModelProvider(this, factory).get(RegistroViewModel.class);

        // Configurar el progreso en la vista
        registroViewModel.status.observe(this, status -> {
            hideProgressDialog(); // Ocultar el diálogo de progreso

            if (status.equals("Registro exitoso, Validacion Pendiente")) {
                Toast.makeText(this, "Registro exitoso, por favor verifica tu correo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistroActivity.this, TokenValidationActivity.class);
                intent.putExtra("TOKEN", "b6f775d2-b365-47b3-8b7e-d8473e161c67");
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón de registro
        botonRegistrar.setOnClickListener(v -> {
            String nombre = nombreCliente.getText().toString();
            String email = correoElectronico.getText().toString();
            String pass = password.getText().toString();
            String confirmPass = confirmarPassword.getText().toString();
            boolean aceptoAvisoPrivacidad = avisoPrivacidad.isChecked();

            if (!pass.equals(confirmPass)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }else if (email.isEmpty() ){

                Toast.makeText(this, "El correo no puede ir vacio", Toast.LENGTH_SHORT).show();
            }

            else if (nombre.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "El usuario y pass no debe estar vacio", Toast.LENGTH_SHORT).show();
            } else if (password==null||password.length()<5) {
                Toast.makeText(this, "Las contraseñas tienen que tener 6 digitos ", Toast.LENGTH_SHORT).show();
            }else if (!aceptoAvisoPrivacidad) {
                Toast.makeText(this, "Debes aceptar el aviso de privacidad", Toast.LENGTH_SHORT).show();
            } else {
                showProgressDialog(); // Mostrar el diálogo de progreso

                registroViewModel.registerUser(nombre, email, pass, aceptoAvisoPrivacidad);
            }
        });

        // Redirigir a la actividad de inicio de sesión
        inicioSesionLabel.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        //Redirigir a la actividad de inicio de sesion
        validacionSesionLabel.setOnClickListener(v->{
            Intent intent = new Intent(RegistroActivity.this, TokenValidationActivity.class);
            startActivity(intent);
            finish();
        });


        // Configurar el ícono para mostrar/ocultar la contraseña
        eyePassword.setOnClickListener(v -> {
            if (password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyePassword.setImageResource(R.drawable.ic_visibility_off); // Cambiar a ícono de ojo cerrado
            } else {
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyePassword.setImageResource(R.drawable.ic_visibility); // Cambiar a ícono de ojo abierto
            }
            password.setSelection(password.getText().length()); // Mantener el cursor al final
        });

        // Configurar el ícono para mostrar/ocultar la confirmación de contraseña
        eyeConfirmarPassword.setOnClickListener(v -> {
            if (confirmarPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                confirmarPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                eyeConfirmarPassword.setImageResource(R.drawable.ic_visibility_off); // Cambiar a ícono de ojo cerrado
            } else {
                confirmarPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                eyeConfirmarPassword.setImageResource(R.drawable.ic_visibility); // Cambiar a ícono de ojo abierto
            }
            confirmarPassword.setSelection(confirmarPassword.getText().length()); // Mantener el cursor al final
        });
    }

    private void showProgressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Por favor espera");
        builder.setMessage("Enviando Token al Correo...");
        builder.setCancelable(false);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        ProgressBar progressBar = dialogView.findViewById(R.id.progressBar);
        builder.setView(dialogView);

        progressDialog = builder.create();
        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
