package com.tecnoplacita.codespeak.ui.chat;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tecnoplacita.codespeak.network.ApiService;
import com.tecnoplacita.codespeak.network.NetworkApiAdapter;
import com.tecnoplacita.codespeak.io.request.SearchRequest;
import com.tecnoplacita.codespeak.io.responses.VerbResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends ViewModel {
    private MutableLiveData<List<ChatMessage>> messages;
    private Context context;

    public ChatViewModel() {
        messages = new MutableLiveData<>(new ArrayList<>());
    }

    public ChatViewModel(Context context) {
        this.context = context;
        messages = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<ChatMessage>> getMessages() {
        return messages;
    }

    public void sendMessage(String input) {
        List<ChatMessage> currentMessages = messages.getValue();
        currentMessages.add(new ChatMessage(input, true)); // Mensaje del usuario

        // Procesar la respuesta
        if (input.toLowerCase().contains("chagpt")) {
            consumeApi(input);
        } else {
            String response = getResponse(input);
            currentMessages.add(new ChatMessage(response, false)); // Respuesta del bot
            messages.setValue(currentMessages);
        }
    }

    private void consumeApi(String input) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("JWT_TOKEN", "");

        SearchRequest searchRequest = new SearchRequest(input); // Asume que input es el verbo a buscar
        ApiService apiService = NetworkApiAdapter.getApiService();

        Call<List<VerbResponse>> call = apiService.searchVerbosWithAuth("Bearer " + token, searchRequest);
        call.enqueue(new Callback<List<VerbResponse>>() {
            @Override
            public void onResponse(Call<List<VerbResponse>> call, Response<List<VerbResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ChatMessage> currentMessages = messages.getValue();
                    for (VerbResponse verb : response.body()) {
                        // Mensaje inicial indicando el verbo encontrado
                        currentMessages.add(new ChatMessage("Verbo : " + verb.getBaseForm(), false));
                        currentMessages.add(new ChatMessage("Traduccion: " + verb.getTranslation(), false));
                        currentMessages.add(new ChatMessage("3° Persona: " + verb.getThirdPerson(), false));
                        currentMessages.add(new ChatMessage("Pasado: " + verb.getPast(), false));
                        currentMessages.add(new ChatMessage("Pasado Participio: " + verb.getPastParticiple(), false));
                        currentMessages.add(new ChatMessage("Gerundio: " + verb.getBaseForm(), false));


                        // Iteramos sobre las oraciones de ejemplo del verbo
                        List<VerbResponse.Sentence> sentences = verb.getSentences();
                        if (sentences != null && !sentences.isEmpty()) {
                            for (VerbResponse.Sentence sentence : sentences) {
                                // Añadir cada oración como un mensaje separado

                                currentMessages.add(new ChatMessage("Tiempo verbal: " + sentence.getTense(), false));
                                currentMessages.add(new ChatMessage("Tipo: " + sentence.getType(), false));
                                currentMessages.add(new ChatMessage("Ejemplo: " + sentence.getContent(), false));
                                currentMessages.add(new ChatMessage("Pronunciación : " + sentence.getFigurativePronunciation(), false));
                                currentMessages.add(new ChatMessage("Traducción: " + sentence.getTranslation(), false));
                            }
                        } else {
                            // Si no hay oraciones, indicamos que no se encontraron ejemplos
                            currentMessages.add(new ChatMessage("No hay ejemplos disponibles para este verbo.", false));
                        }
                    }
                    messages.setValue(currentMessages);

                } else {
                    // handleApiError(currentMessages);
                }
            }


            @Override
            public void onFailure(Call<List<VerbResponse>> call, Throwable t) {
                List<ChatMessage> currentMessages = messages.getValue();
                currentMessages.add(new ChatMessage("Error de conexión: " + t.getMessage(), false));
                messages.setValue(currentMessages);
            }
        });

    }

    private void handleApiError(List<ChatMessage> currentMessages) {
        currentMessages.add(new ChatMessage("No se encontraron resultados para ese verbo.", false));
        messages.setValue(currentMessages);
    }

    private String getResponse(String input) {
        if (input.toLowerCase().contains("hola")) {
            return "¡Hola! ¿Por fa introduce el verbo?";
        } else if (input.toLowerCase().contains("what is your name")) {
            return "Soy un CodeSpeak. ¿Introduce el Verbo para su análisis?";
        } else {
          consumeApi(input);

            //return "No estoy seguro de cómo responder a eso. ¡Porfa intenta ingresar un verbo!";
        }
        return "";
    }
}
