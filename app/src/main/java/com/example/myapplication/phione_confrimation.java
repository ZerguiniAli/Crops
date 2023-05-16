package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link phione_confrimation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class phione_confrimation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText c1,c2,c3,c4,c5;

    Button btn;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public phione_confrimation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment phione_confrimation.
     */
    // TODO: Rename and change types and number of parameters
    public static phione_confrimation newInstance(String param1, String param2) {
        phione_confrimation fragment = new phione_confrimation();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_phione_confrimation, container, false);

        c1 = view.findViewById(R.id.c1);
        c2 = view.findViewById(R.id.c2);
        c3 = view.findViewById(R.id.c3);
        c4 = view.findViewById(R.id.c4);
        c5 = view.findViewById(R.id.c5);
        btn = view.findViewById(R.id.btn);

        String code = getArguments().getString("key");
        String email = getArguments().getString("email");
        String password = getArguments().getString("password");
        String username = getArguments().getString("username");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vcode = c1.getText().toString() +
                        c2.getText().toString()+
                        c3.getText().toString()+
                        c4.getText().toString()+
                        c5.getText().toString();

                if(vcode.equals(code))
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,endpoint.registre_url, response -> {
                        if (response.equals("Connected success"))
                        {
                            Intent intent = new Intent(getActivity(), MAIN.class);
                            startActivity(intent);
                        }
                        else
                        {

                        }
                    }, error -> {
                        Toast.makeText(getActivity(), "please check your internet connection", Toast.LENGTH_SHORT).show();
                    }){
                        protected Map<String , String> getParams(){
                            Map<String , String> params= new HashMap<>();
                            params.put("username",username);
                            params.put("email",email);
                            params.put("password",password);
                            return params;
                        }
                    };
                    VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
                }
                else
                {
                    Toast.makeText(getActivity(), vcode+"make sure", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}