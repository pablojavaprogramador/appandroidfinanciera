package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;
import com.touchizen.drawerwithbottomnavigation.network.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.ui.login.LoginActivity;

public class RegistroActivity extends ComponentActivity {

    private static final String CHANNEL_ID = "registroChannel";
    private static final int NOTIFICATION_PERMISSION_REQUEST_CODE = 1;
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

        // Crear canal de notificación
        createNotificationChannel();

        // Verificar el estado del registro
        registroViewModel.status.observe(this, status -> {
            if (status.equals("Registro exitoso")) {
                checkNotificationPermission();  // Verificar y solicitar permiso antes de enviar la notificación
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
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

    // Crear el canal de notificación (solo es necesario para Android 8.0 o superior)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Registro Notificaciones";
            String description = "Canal para notificaciones de registro exitoso";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Registrar el canal en el sistema
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Verificar si tenemos el permiso para mostrar notificaciones
    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {  // Android 13+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Si no tenemos el permiso, lo solicitamos
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_REQUEST_CODE);
            } else {
                // Si ya tenemos el permiso, enviamos la notificación
                sendRegistrationNotification();
            }
        } else {
            // Para versiones anteriores a Android 13, solo enviamos la notificación
            sendRegistrationNotification();
        }
    }

    // Manejar el resultado de la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NOTIFICATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, enviar la notificación
                sendRegistrationNotification();
            } else {
                // Permiso denegado, mostrar un mensaje al usuario
                Toast.makeText(this, "Permiso de notificaciones denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Enviar la notificación cuando el registro sea exitoso
    private void sendRegistrationNotification() {
        // Crear la intención que se ejecutará al tocar la notificación
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        Toast.makeText(this, "Token enviado. Verifica tu correo para continuar.", Toast.LENGTH_SHORT).show();
        // Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_account_balance_24) // Asegúrate de tener un icono en drawable
                .setContentTitle("Token enviado" )
                .setContentText("Token enviado. Verifica tu correo para continuar.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)  // La notificación desaparecerá al tocarla
                .setContentIntent(pendingIntent)  // Intención al tocar la notificación
                .setStyle(new NotificationCompat.BigTextStyle().bigText("La autenticación ha sido enviada a tu correo. Revisa tu bandeja de entrada o spam."));

        // Verifica que el permiso haya sido otorgado antes de intentar enviar la notificación
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Mostrar la notificación
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());
        } else {
            // Si no se ha otorgado el permiso, no hacer nada o mostrar un mensaje
            Toast.makeText(this, "Permiso de notificaciones no concedido", Toast.LENGTH_SHORT).show();
        }
    }
}
