package com.touchizen.drawerwithbottomnavigation.ui.chatbot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.ui.tools.AyudaFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChatbotFragment extends Fragment {

    private ChatbotViewModel sendViewModel;
    //creating variables for our widgets in xml file.
    private RecyclerView chatsRV;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;

    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    //creating a variable for our volley request queue.
    private RequestQueue mRequestQueue;
    //creating a variable for array list and adapter class.
    private ArrayList<MessageModal> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatbotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AyudaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatbotFragment newInstance(String param1, String param2) {
        ChatbotFragment fragment = new ChatbotFragment();
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



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel = ViewModelProviders.of(this).get(ChatbotViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chatbot, container, false);
        final TextView textView = root.findViewById(R.id.text_send);

        chatsRV = root.findViewById(R.id.idRVChats);
        sendMsgIB = root.findViewById(R.id.idIBSend);
        userMsgEdt = root.findViewById(R.id.idEdtMessage);

        //creating a new array list
        messageModalArrayList = new ArrayList<>();
          mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
         mRequestQueue.getCache().clear();
        //adding on click listener for send message button.
        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking if the message entered by user is empty or not.
                if (userMsgEdt.getText().toString().isEmpty()) {
                    //if the edit text is empty display a toast message.
                    Toast.makeText(getActivity(), "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }
                //calling a method to send message to our bot to get response.
                sendMessage(userMsgEdt.getText().toString());
                //below line we are setting text in our edit text as empty
                userMsgEdt.setText("");

            }
        });

        sendViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
      //  userMsgEdt.setText("gdfgdfgd");
           //return inflater.inflate(R.layout.fragment_chatbot, container, false);
         return root;
    }


    private void sendMessage(String userMsg) {
        //below line is to pass message to our array list which is entered by the user.
        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
      //  messageRVAdapter.notifyDataSetChanged();
        //url for our brain
        //make sure to add mshape for uid.
        String url = "Enter your url" + userMsg;
        //creating a variable for our request queue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        //on below line we are making a json object request for a get request and passing our url .
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //in on response method we are extracting data from json response and adding this response to our array list.
                    String botResponse = response.getString("cnt");
                    messageModalArrayList.add(new MessageModal(botResponse, BOT_KEY));
                    //notifying our adapter as data changed.
                  //  messageRVAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    //handling error response from bot.
                    messageModalArrayList.add(new MessageModal("No response", BOT_KEY));
                   // messageRVAdapter.notifyDataSetChanged();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error handling.
                messageModalArrayList.add(new MessageModal("Sorry no response found", BOT_KEY));
                Toast.makeText(getActivity(), "No response from the bot..", Toast.LENGTH_SHORT).show();
            }
        });
        //at last adding json object request to our queue.
        queue.add(jsonObjectRequest);


    }
}