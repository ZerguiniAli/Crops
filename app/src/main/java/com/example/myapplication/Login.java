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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button bt;
    EditText ps , na;
    TextView forget;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        bt = view.findViewById(R.id.button);
        na = view.findViewById(R.id.editusername);
        ps = view.findViewById(R.id.editpassword);
        forget = view.findViewById(R.id.forgetclick);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = na.getText().toString();
                String password = ps.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,endpoint.login_url, response -> {
                    if (response.equals("Connected login success"))
                    {
                        Intent intent = new Intent(getActivity(), MAIN.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "login failed", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
                    Toast.makeText(getActivity(), "please check your internet connection", Toast.LENGTH_SHORT).show();
                }){
                    protected Map<String , String> getParams(){
                        Map<String , String> params= new HashMap<>();
                        params.put("username",username);
                        params.put("password",password);
                        return params;
                    }
                };
                VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new email_forget() ;

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                                R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.Login, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }





}