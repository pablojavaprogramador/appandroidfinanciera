package com.touchizen.drawerwithbottomnavigation.ui.tools;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.touchizen.drawerwithbottomnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TerminosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TerminosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TerminosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TerminosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TerminosFragment newInstance(String param1, String param2) {
        TerminosFragment fragment = new TerminosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_terminos, container, false);
        final WebView webView = root.findViewById(R.id.webview_terminos);
        String url = "https://www.storicard.com/terminos-y-condiciones";

        final WebSettings ajustesVisorWeb = webView.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/myfile.html");
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_terminos, container, false);
    return root;
    }

}