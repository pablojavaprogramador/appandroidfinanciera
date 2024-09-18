package com.touchizen.drawerwithbottomnavigation.ui.reset;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.network.ApiService;
import com.touchizen.drawerwithbottomnavigation.network.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.PasswordResetRequest;
import com.touchizen.drawerwithbottomnavigation.io.responses.PasswordResetResponse;
import com.touchizen.drawerwithbottomnavigation.ui.login.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordResetRequestActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button sendRequestButton;
    private TextView messageTextView;
    private TextView linkToLoginTextView;  // TextView para el enlace a login
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_request);

        emailEditText = findViewById(R.id.editTextEmailAddress);
        sendRequestButton = findViewById(R.id.buttonSendResetRequest);
        messageTextView = findViewById(R.id.resetPasswordMessage);
        linkToLoginTextView = findViewById(R.id.link_to_login);  // Inicializar el TextView

        apiService = NetworkApiAdapter.getApiService();

        sendRequestButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (!email.isEmpty()) {
                sendResetPasswordRequest(email);
            } else {
                messageTextView.setText("Por favor ingrese su correo electrónico.");
            }
        });

        // Configurar la redirección a LoginActivity al hacer clic en el enlace
        linkToLoginTextView.setOnClickListener(v -> {
            Intent intent = new Intent(PasswordResetRequestActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();  // Cerrar la actividad actual
        });
    }

    private void sendResetPasswordRequest(String email) {
        PasswordResetRequest request = new PasswordResetRequest(email);
        apiService.resetPassword(request).enqueue(new Callback<PasswordResetResponse>() {
            @Override
            public void onResponse(Call<PasswordResetResponse> call, Response<PasswordResetResponse> response) {
                if (response.isSuccessful()) {
                    PasswordResetResponse resetResponse = response.body();
                    if (resetResponse != null && resetResponse.isSuccess()) {
                        messageTextView.setText("Se ha enviado un correo para restablecer la contraseña.");
                        // Navegar a la pantalla de confirmación de código
                        Intent intent = new Intent(PasswordResetRequestActivity.this, PasswordResetConfirmationActivity.class);
                        startActivity(intent);
                    } else {
                        messageTextView.setText("Error en la solicitud. Verifique el correo.");
                    }
                } else {
                    messageTextView.setText("Error en la solicitud. Verifique el correo.");
                }
            }

            @Override
            public void onFailure(Call<PasswordResetResponse> call, Throwable t) {
                messageTextView.setText("Error de red. Intente nuevamente.");
            }
        });
    }
}
