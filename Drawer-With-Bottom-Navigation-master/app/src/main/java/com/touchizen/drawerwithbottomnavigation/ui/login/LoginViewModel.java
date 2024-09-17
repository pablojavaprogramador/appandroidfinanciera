package com.touchizen.drawerwithbottomnavigation.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.data.LoginDataSource;
import com.touchizen.drawerwithbottomnavigation.io.request.LoginRequest;
import com.touchizen.drawerwithbottomnavigation.io.responses.LoginResponse;
import com.touchizen.drawerwithbottomnavigation.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private ApiService loginService;

    public LoginViewModel(LoginDataSource loginDataSource) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.11:9090/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginService = retrofit.create(ApiService.class);
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);
        loginService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    loginResult.setValue(new LoginResult(new LoggedInUserView(email, "User")));
                } else {
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    public void loginDataChanged(String email, String password) {
        if (!isEmailValid(email)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    private boolean isEmailValid(String email) {
        return email != null && email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
