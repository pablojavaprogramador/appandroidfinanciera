package com.tecnoplacita.codespeak.ui.verbos;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tecnoplacita.codespeak.io.request.SearchRequest;
import com.tecnoplacita.codespeak.io.responses.VerbResponse;
import com.tecnoplacita.codespeak.network.ApiService;
import com.tecnoplacita.codespeak.network.NetworkApiAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerbosViewModel extends ViewModel {

    private final MutableLiveData<List<VerbResponse>> verbsLiveData = new MutableLiveData<>();
    private Context context;

    public VerbosViewModel(Context context) {
        this.context = context;
    }

    public LiveData<List<VerbResponse>> getVerbs() {
        return verbsLiveData;
    }

    private String getToken() {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("JWT_TOKEN", null);
    }

    public void buscarVerbos(String busquedad) {
        String token = getToken();
        ApiService apiService = NetworkApiAdapter.getApiService();
        SearchRequest searchRequest = new SearchRequest(busquedad);

        Call<List<VerbResponse>> call;

        if (token != null) {
            call = apiService.searchVerbosWithAuth("Bearer " + token, searchRequest);
        } else {
            call = apiService.searchVerbosWithAuth("null",searchRequest); // Llama sin autenticación si no hay token
        }

        call.enqueue(new Callback<List<VerbResponse>>() {
            @Override
            public void onResponse(Call<List<VerbResponse>> call, Response<List<VerbResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("VerbosViewModel", "Resultados: " + response.body().toString());
                    verbsLiveData.setValue(response.body());
                } else {
                    Log.d("VerbosViewModel", "Código de estado: " + response.code());
                    verbsLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<VerbResponse>> call, Throwable t) {
                verbsLiveData.setValue(null);
            }
        });
    }
}
