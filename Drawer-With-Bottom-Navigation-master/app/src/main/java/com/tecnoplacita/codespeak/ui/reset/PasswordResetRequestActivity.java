package com.tecnoplacita.codespeak.ui.reset;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.tecnoplacita.codespeak.R;
import com.tecnoplacita.codespeak.network.ApiService;
import com.tecnoplacita.codespeak.network.NetworkApiAdapter;
import com.tecnoplacita.codespeak.io.request.PasswordResetRequest;
import com.tecnoplacita.codespeak.io.responses.PasswordResetResponse;
import com.tecnoplacita.codespeak.ui.login.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordResetRequestActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button sendRequestButton;
    private TextView messageTextView;
    private TextView linkToLoginTextView;
    private TextView linktoTokenValidation;
    private ApiService apiService;
    private ProgressDialogHelper progressDialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_request);

        emailEditText = findViewById(R.id.editTextEmailAddress);
        sendRequestButton = findViewById(R.id.buttonSendResetRequest);
        messageTextView = findViewById(R.id.resetPasswordMessage);
        linkToLoginTextView = findViewById(R.id.link_to_login);
        linktoTokenValidation=findViewById(R.id.link_to_token_reset);
        apiService = NetworkApiAdapter.getApiService();
        progressDialogHelper = new ProgressDialogHelper(this);

        sendRequestButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (!email.isEmpty()) {
                sendResetPasswordRequest(email);
            } else {
                messageTextView.setText("Por favor ingrese su correo electrónico.");
            }
        });

        linkToLoginTextView.setOnClickListener(v -> {
            Intent intent = new Intent(PasswordResetRequestActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


        linktoTokenValidation.setOnClickListener(v -> {
            Intent intent = new Intent(PasswordResetRequestActivity.this, PasswordResetConfirmationActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void sendResetPasswordRequest(String email) {
        progressDialogHelper.show();

        PasswordResetRequest request = new PasswordResetRequest(email);
        apiService.resetPassword(request).enqueue(new Callback<PasswordResetResponse>() {
            @Override
            public void onResponse(Call<PasswordResetResponse> call, Response<PasswordResetResponse> response) {
                progressDialogHelper.hide();
                if (response.isSuccessful()) {
                    PasswordResetResponse resetResponse = response.body();
                    if (resetResponse != null && resetResponse.isSuccess()) {
                        messageTextView.setText("Se ha enviado un correo para restablecer la contraseña.");
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
                progressDialogHelper.hide();
                messageTextView.setText("Error de red. Intente nuevamente.");
            }
        });
    }
}
