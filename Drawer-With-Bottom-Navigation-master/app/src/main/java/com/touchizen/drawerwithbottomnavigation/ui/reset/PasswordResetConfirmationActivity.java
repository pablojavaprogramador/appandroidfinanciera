package com.touchizen.drawerwithbottomnavigation.ui.reset;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private EditText confirmacionPassword;
    private Button confirmButton;
    private TextView messageTextView;
    private TextView linkToLogin;
    private ApiService apiService;
    private ImageView eyeNewPassword;
    private ImageView eyeConfirmationPassword;
    private boolean isNewPasswordVisible = false;
    private boolean isConfirmationPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_confirmation);

        tokenEditText = findViewById(R.id.editTextToken);
        newPasswordEditText = findViewById(R.id.editTextNewPassword);
        confirmacionPassword = findViewById(R.id.editTextConfirmationPassword);
        confirmButton = findViewById(R.id.buttonConfirmReset);
        linkToLogin = findViewById(R.id.link_to_login);
        messageTextView = findViewById(R.id.resetConfirmationMessage);
        eyeNewPassword = findViewById(R.id.eye_new_password);
        eyeConfirmationPassword = findViewById(R.id.eye_confirmation_password);

        apiService = NetworkApiAdapter.getApiService();

        confirmButton.setOnClickListener(v -> {
            String token = tokenEditText.getText().toString().trim();
            String newPassword = newPasswordEditText.getText().toString().trim();
            String confirmPassword = confirmacionPassword.getText().toString().trim();

            if (token.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                messageTextView.setText("Por favor complete todos los campos.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            confirmResetPassword(token, newPassword);
        });

        linkToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(PasswordResetConfirmationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Lógica para alternar la visibilidad de la nueva contraseña
        eyeNewPassword.setOnClickListener(v -> {
            isNewPasswordVisible = !isNewPasswordVisible;
            newPasswordEditText.setInputType(isNewPasswordVisible ?
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD);
            eyeNewPassword.setImageResource(isNewPasswordVisible ?
                    R.drawable.ic_visibility : R.drawable.ic_visibility_off);
            newPasswordEditText.setSelection(newPasswordEditText.getText().length());
        });

        // Lógica para alternar la visibilidad de la confirmación de contraseña
        eyeConfirmationPassword.setOnClickListener(v -> {
            isConfirmationPasswordVisible = !isConfirmationPasswordVisible;
            confirmacionPassword.setInputType(isConfirmationPasswordVisible ?
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD);
            eyeConfirmationPassword.setImageResource(isConfirmationPasswordVisible ?
                    R.drawable.ic_visibility : R.drawable.ic_visibility_off);
            confirmacionPassword.setSelection(confirmacionPassword.getText().length());
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
                        Intent intent = new Intent(PasswordResetConfirmationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(PasswordResetConfirmationActivity.this, "Token invalido o expirado", Toast.LENGTH_SHORT).show();
                        messageTextView.setText("Token inválido o expirado.");
                    }
                } else {
                    Toast.makeText(PasswordResetConfirmationActivity.this, "Token invalido o expirado", Toast.LENGTH_SHORT).show();
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
