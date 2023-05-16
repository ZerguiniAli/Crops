package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link email_confirmail_forget#newInstance} factory method to
 * create an instance of this fragment.
 */
public class email_confirmail_forget extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn ;

    EditText c1,c2,c3,c4,c5;


    public email_confirmail_forget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment email_confirmail_forget.
     */
    // TODO: Rename and change types and number of parameters
    public static email_confirmail_forget newInstance(String param1, String param2) {
        email_confirmail_forget fragment = new email_confirmail_forget();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email_confirmail_forget, container, false);

        btn = view.findViewById(R.id.btn);

        c1 = view.findViewById(R.id.c1);
        c2 = view.findViewById(R.id.c2);
        c3 = view.findViewById(R.id.c3);
        c4 = view.findViewById(R.id.c4);
        c5 = view.findViewById(R.id.c5);

        String code = getArguments().getString("key");
        String email = getArguments().getString("email");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vcode = c1.getText().toString() +
                        c2.getText().toString()+
                        c3.getText().toString()+
                        c4.getText().toString()+
                        c5.getText().toString();
                if (vcode.equals(code))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    password_reset fragment = new password_reset();

                    fragment.setArguments(bundle);

                    // Navigate to the phione_confrimation fragment
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                                    R.anim.slide_in_right, R.anim.slide_out_left)
                            .replace(R.id.email_conf_forget, fragment)
                            .addToBackStack(null)
                            .commit();

                }
                else
                {
                    Toast.makeText(getActivity(), "Make sure you entered the right value", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}