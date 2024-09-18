package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;
import com.touchizen.drawerwithbottomnavigation.network.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.ui.login.LoginActivity;

public class TokenValidationActivity extends AppCompatActivity {

    private TokenValidationViewModel tokenValidationViewModel;
    private EditText tokenEditText;
    private Button verifyButton;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_validation);

        // Inicializar los campos
        tokenEditText = findViewById(R.id.editTextToken);
        verifyButton = findViewById(R.id.buttonVerifyToken);
        statusTextView = findViewById(R.id.statusTextView);

        // Inicializar ViewModel con el Factory
        UserRepository userRepository = new UserRepository(NetworkApiAdapter.getApiService());
        TokenValidationViewModelFactory factory = new TokenValidationViewModelFactory(userRepository);
        tokenValidationViewModel = new ViewModelProvider(this, factory).get(TokenValidationViewModel.class);

        // Observa el estado de la validación del token
        tokenValidationViewModel.getStatus().observe(this, status -> {
            statusTextView.setText(status);
            if (status.equals("Validación exitosa")) {
                Toast.makeText(this, "Token validado exitosamente", Toast.LENGTH_SHORT).show();
                // Redirigir al login después de la validación exitosa
                Intent intent = new Intent(TokenValidationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Configura el botón para verificar el token
        verifyButton.setOnClickListener(v -> {
            String token = tokenEditText.getText().toString();
            if (token.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese el token", Toast.LENGTH_SHORT).show();
            } else {
                tokenValidationViewModel.validateToken(token);
            }
        });
    }
}
