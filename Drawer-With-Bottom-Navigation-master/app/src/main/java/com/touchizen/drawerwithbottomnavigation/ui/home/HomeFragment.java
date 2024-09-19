// com/touchizen/drawerwithbottomnavigation/ui/home/HomeFragment.java
package com.touchizen.drawerwithbottomnavigation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.touchizen.drawerwithbottomnavigation.R;

public class HomeFragment extends Fragment {

    private TextView verbTitle;
    private TextView conjugationsContent;
    private TextView pronunciationContent;
    private TextView comparativesContent;
    private TextView pronounsContent;
    private TextView adverbsPrepositionsContent;
    private TextView connectorsContent;
    private TextView conversationContent;
    private Button exampleButton;
    private Button audioButton;
    private ImageButton backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize UI components
        verbTitle = view.findViewById(R.id.verbTitle);
        conjugationsContent = view.findViewById(R.id.conjugationsContent);
        pronunciationContent = view.findViewById(R.id.pronunciationContent);
        comparativesContent = view.findViewById(R.id.comparativesContent);
        pronounsContent = view.findViewById(R.id.pronounsContent);
        adverbsPrepositionsContent = view.findViewById(R.id.adverbsPrepositionsContent);
        connectorsContent = view.findViewById(R.id.connectorsContent);
        conversationContent = view.findViewById(R.id.conversationContent);
        exampleButton = view.findViewById(R.id.exampleButton);
        audioButton = view.findViewById(R.id.audioButton);
        backButton = view.findViewById(R.id.backButton);

        // Set data (this could be replaced with dynamic data fetching)
        setVerbData();

        // Set click listeners
        exampleButton.setOnClickListener(v -> showExampleUsage());
        audioButton.setOnClickListener(v -> playAudio());
        backButton.setOnClickListener(v -> getActivity().onBackPressed());

        return view;
    }

    private void setVerbData() {
        // This is where you set the data for the verb. For example:
        verbTitle.setText("To Speak");
        conjugationsContent.setText("I speak, you spoke, he will speak");
        pronunciationContent.setText("/spiːk/");
        comparativesContent.setText("Better, Best");
        pronounsContent.setText("My, your, himself, etc.");
        adverbsPrepositionsContent.setText("Quickly, on");
        connectorsContent.setText("However, therefore");
        conversationContent.setText("A: How often do you speak English?\nB: I speak English every day.");
    }

    private void showExampleUsage() {
        // Implement functionality to show example usage
    }

    private void playAudio() {
        // Implement functionality to play audio
    }
}
