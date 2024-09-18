package com.touchizen.drawerwithbottomnavigation.ui.reset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.network.ApiService;
import com.touchizen.drawerwithbottomnavigation.network.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.PasswordResetConfirmationRequest;
import com.touchizen.drawerwithbottomnavigation.io.responses.PasswordResetConfirmationResponse;
import com.touchizen.drawerwithbottomnavigation.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordResetConfirmationActivity extends AppCompatActivity {

    private EditText tokenEditText;
    private EditText newPasswordEditText;
    private Button confirmButton;
    private TextView messageTextView;
    private TextView linkToLogin;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_confirmation);

        tokenEditText = findViewById(R.id.editTextToken);
        newPasswordEditText = findViewById(R.id.editTextNewPassword);
        confirmButton = findViewById(R.id.buttonConfirmReset);
        messageTextView = findViewById(R.id.resetConfirmationMessage);
        linkToLogin = findViewById(R.id.link_to_login);

        apiService = NetworkApiAdapter.getApiService();

        confirmButton.setOnClickListener(v -> {
            String token = tokenEditText.getText().toString().trim();
            String newPassword = newPasswordEditText.getText().toString().trim();
            if (!token.isEmpty() && !newPassword.isEmpty()) {
                confirmResetPassword(token, newPassword);
            } else {
                messageTextView.setText("Por favor complete todos los campos.");
            }
        });

        linkToLogin.setOnClickListener(v -> {
            // Navegar a la pantalla de inicio de sesión
            Intent intent = new Intent(PasswordResetConfirmationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Opcional: Cierra la actividad actual
        });
    }

    private void confirmResetPassword(String token, String newPassword) {
        PasswordResetConfirmationRequest request = new PasswordResetConfirmationRequest(token, newPassword);
        apiService.confirmReset(request).enqueue(new Callback<PasswordResetConfirmationResponse>() {
            @Override
            public void onResponse(Call<PasswordResetConfirmationResponse> call, Response<PasswordResetConfirmationResponse> response) {
                if (response.isSuccessful()) {
                    PasswordResetConfirmationResponse resetResponse = response.body();
                    if (resetResponse != null && resetResponse.isSuccess()) {
                        messageTextView.setText("Contraseña restablecida exitosamente.");
                        Toast.makeText(PasswordResetConfirmationActivity.this, "Token validado exitosamente", Toast.LENGTH_SHORT).show();
                        // Redirigir al login después de la validación exitosa
                        Intent intent = new Intent(PasswordResetConfirmationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        messageTextView.setText("Token inválido o expirado.");
                    }
                } else {
                    messageTextView.setText("Token inválido o expirado.");
                }
            }

            @Override
            public void onFailure(Call<PasswordResetConfirmationResponse> call, Throwable t) {
                messageTextView.setText("Error de red. Intente nuevamente.");
            }
        });
    }
}
