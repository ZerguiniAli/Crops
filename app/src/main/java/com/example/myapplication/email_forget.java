package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
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
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link email_forget#newInstance} factory method to
 * create an instance of this fragment.
 */
public class email_forget extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText em ;
    Button btn ;

    Random random = new Random();
    int randomNum = random.nextInt(90000) + 10000;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public email_forget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment email_forget.
     */
    // TODO: Rename and change types and number of parameters
    public static email_forget newInstance(String param1, String param2) {
        email_forget fragment = new email_forget();
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
        View view = inflater.inflate(R.layout.fragment_email_forget, container, false);

        em = view.findViewById(R.id.email_edit);

        btn = view.findViewById(R.id.button);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_confirmail_forget fragment = new email_confirmail_forget();
                String email = em.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("key", String.valueOf(randomNum));
                bundle.putString("email", email);
                StringRequest stringRequest = new StringRequest(Request.Method.POST,endpoint.forget_url, response -> {
                    if (response.equals("Connected login success"))
                    {
                        sendEmail();
                        // Set the Bundle as arguments for the phione_confrimation fragment
                        fragment.setArguments(bundle);

                        // Navigate to the phione_confrimation fragment
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                                        R.anim.slide_in_right, R.anim.slide_out_left)
                                .replace(R.id.email_forget, fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "make sure you enterd the right email", Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
                    Toast.makeText(getActivity(), "please check your internet connection", Toast.LENGTH_SHORT).show();
                }){
                    protected Map<String , String> getParams(){
                        Map<String , String> params= new HashMap<>();
                        params.put("email",email);
                        return params;
                    }
                };
                VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
            }
        });




        return view;
    }

    private class SendEmailTask extends AsyncTask<Void, Void, Boolean> {
        private String recipient;
        private String subject;
        private String message;
        String error ;


        public SendEmailTask(String recipient, String subject, String message) {
            this.recipient = recipient;
            this.subject = subject;
            this.message = message;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // Set up the JavaMail properties.
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Set up the email authentication credentials.
            String username = "crops.service@gmail.com";
            String password = "jbiwgxtgpkklgpxo";
            javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create a new email message.
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(username));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                msg.setSubject(subject);
                msg.setText(message);

                // Send the email message.
                Transport.send(msg);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                error = e.toString();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getActivity(), "Email sent", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Error sending email"+error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void sendEmail() {
        // Get the recipient, subject, and message from the EditTexts.
        String recipient = em.getText().toString();
        String subject = "CROPS";
        String message = "Your verication code to reset your password for CROPS is : "+ randomNum;

        // Start the AsyncTask to send the email in the background.
        new email_forget.SendEmailTask(recipient, subject, message).execute();
    }
}