package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;
import com.touchizen.drawerwithbottomnavigation.network.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.ui.login.LoginActivity;

public class RegistroActivity extends ComponentActivity {

    private RegistroViewModel registroViewModel;
    private TextView nombreCliente, correoElectronico, password, confirmarPassword, respuestaRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro);

        // Inicializar los campos de entrada
        nombreCliente = findViewById(R.id.id_Persona);
        correoElectronico = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        confirmarPassword = findViewById(R.id.editTextConfirmarPassword);
        respuestaRegistro = findViewById(R.id.respuestaRegistro);
        Button botonRegistrar = findViewById(R.id.buttonRegistrarUsuario);
        TextView inicioSesionLabel = findViewById(R.id.link_to_login);

        // Inicializar ViewModel con el Factory
        UserRepository userRepository = new UserRepository(NetworkApiAdapter.getApiService());
        RegistroViewModelFactory factory = new RegistroViewModelFactory(userRepository);
        registroViewModel = new ViewModelProvider(this, factory).get(RegistroViewModel.class);

        // Observar el estado del registro
        registroViewModel.status.observe(this, status -> {
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
            if (status.equals("Registro exitoso")) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Configurar el botón de registro
        botonRegistrar.setOnClickListener(v -> {
            String nombre = nombreCliente.getText().toString();
            String email = correoElectronico.getText().toString();
            String pass = password.getText().toString();
            String confirmPass = confirmarPassword.getText().toString();

            if (pass.equals(confirmPass)) {
                registroViewModel.registerUser(nombre, email, pass);
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        });

        // Redirigir a la actividad de inicio de sesión
        inicioSesionLabel.setOnClickListener(v -> {
            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
