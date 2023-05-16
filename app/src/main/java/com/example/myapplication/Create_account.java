package com.example.myapplication;

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

import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Create_account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Create_account extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Button creat ;

    Random random = new Random();
    int randomNum = random.nextInt(90000) + 10000;

    EditText email ,username , password;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Create_account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Create_account.
     */
    // TODO: Rename and change types and number of parameters
    public static Create_account newInstance(String param1, String param2) {
        Create_account fragment = new Create_account();
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
    
    private boolean isValidEmail(String email) {
        // Regex pattern for email validation
        String emailRegex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_create_account, container, false);

       

         email = view.findViewById(R.id.editemail);

         username = view.findViewById(R.id.editusername);

         password = view.findViewById(R.id.editpassword);

         creat = view.findViewById(R.id.button);

         creat.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 phione_confrimation fragment = new phione_confrimation();
                 

                 // Send an email (assuming this method exists in your code)
                 

                 // Create a Bundle and put the String variable value into it
                 Bundle bundle = new Bundle();
                 bundle.putString("key", String.valueOf(randomNum));

                 String email1 = email.getText().toString();
                 bundle.putString("email", email1);

                 String username1 = username.getText().toString();
                 bundle.putString("username", username1);

                 String password1 = password.getText().toString();
                 bundle.putString("password", password1);
                 
                 if(username1.isEmpty() || password1.isEmpty() || email1.isEmpty()){
                     Toast.makeText(getActivity(), "make you sure you entered everything", Toast.LENGTH_SHORT).show();
                 }
                 else if (isValidEmail(email1)){
                     
                     sendEmail();
                     // Set the Bundle as arguments for the phione_confrimation fragment
                     fragment.setArguments(bundle);

                     // Navigate to the phione_confrimation fragment
                     getActivity().getSupportFragmentManager().beginTransaction()
                             .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                                     R.anim.slide_in_left, R.anim.slide_out_right)
                             .replace(R.id.create_account_fragment, fragment)
                             .addToBackStack(null)
                             .commit();
                 }
                 else
                 {
                     Toast.makeText(getActivity(), "your email is not valid", Toast.LENGTH_SHORT).show();
                 }
                 


                
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
        String recipient = email.getText().toString();
        String subject = "CROPS";
        String message = "Your verication code for CROPS is : "+ randomNum;

        // Start the AsyncTask to send the email in the background.
        new SendEmailTask(recipient, subject, message).execute();
    }
    

}