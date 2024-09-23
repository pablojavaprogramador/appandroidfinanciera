package com.tecnoplacita.codespeak.ui.verbos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tecnoplacita.codespeak.R;
import com.tecnoplacita.codespeak.io.responses.VerbResponse;

import java.util.ArrayList;
import java.util.List;

public class VerbosFragment extends Fragment {

    private VerbosViewModel verbosViewModel;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> verbosList;
    private EditText editTextBuscar;
    private Button buttonBuscar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_verbos, container, false);
        listView = root.findViewById(R.id.listViewVerb);
        editTextBuscar = root.findViewById(R.id.editTextBuscar);
        buttonBuscar = root.findViewById(R.id.buttonBuscar);

        verbosList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, verbosList);
        listView.setAdapter(adapter);

        // Usar la factory para crear el ViewModel
        verbosViewModel = new ViewModelProvider(this, new VerbosViewModelFactory(getActivity()))
                .get(VerbosViewModel.class);

        // Observador para actualizar la lista cuando se reciben nuevos datos
        verbosViewModel.getVerbs().observe(getViewLifecycleOwner(), new Observer<List<VerbResponse>>() {
            @Override
            public void onChanged(List<VerbResponse> verbResponses) {
                if (verbResponses != null && !verbResponses.isEmpty()) {
                    verbosList.clear();
                    for (VerbResponse verbo : verbResponses) {
                        // Mostrar información detallada del verbo
                        StringBuilder verboInfo = new StringBuilder();
                        verboInfo.append("Verbo: ").append(verbo.getBaseForm())
                                .append("\nTraducción: ").append(verbo.getTranslation())
                                .append("\nTercera Persona: ").append(verbo.getThirdPerson())
                                .append("\nPasado: ").append(verbo.getPast())
                                .append("\nParticipio Pasado: ").append(verbo.getPastParticiple())
                                .append("\nGerundio: ").append(verbo.getGerund())
                                .append("\nOraciones:\n");
                        for (VerbResponse.Sentence sentence : verbo.getSentences()) {
                            verboInfo.append("- ").append(sentence.getContent())
                                    .append(" (").append(sentence.getTranslation()).append(")")
                                    .append(" [Tense: ").append(sentence.getTense())
                                    .append(", Type: ").append(sentence.getType())
                                    .append(", Pronunciation: ").append(sentence.getFigurativePronunciation()).append("]\n");
                        }
                        verbosList.add(verboInfo.toString());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busquedad = editTextBuscar.getText().toString().trim();
                if (!busquedad.isEmpty()) {
                    buscarVerbo(busquedad);
                } else {
                    Toast.makeText(getActivity(), "Por favor ingrese un verbo para buscar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    private void buscarVerbo(String busquedad) {
        verbosViewModel.buscarVerbos(busquedad);
    }
}
