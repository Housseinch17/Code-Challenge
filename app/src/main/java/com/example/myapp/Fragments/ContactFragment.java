package com.example.myapp.Fragments;


import static android.content.Context.CONNECTIVITY_SERVICE;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp.ContactClasses.ContactClass;
import com.example.myapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class ContactFragment extends Fragment {
    View view;
    EditText contactEmail, contactPhone, contactMessage;
    TextInputEditText contactName;
    Button postButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference,reference1;
    ProgressBar progressBar;
    ArrayList<String> stringArrayList;
    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact2, container, false);

        contactName = view.findViewById(R.id.contactName);
        contactEmail = view.findViewById(R.id.contactEmail);
        contactPhone = view.findViewById(R.id.contactPhone);
        contactMessage = view.findViewById(R.id.contactMessage);
        postButton = view.findViewById(R.id.postButton);
        progressBar = view.findViewById(R.id.progress);

        stringArrayList=new ArrayList<>();

        postButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                stringArrayList.clear();
                String name = contactName.getText().toString();
                String email = contactEmail.getText().toString();
                String phone = contactPhone.getText().toString();
                String message = contactMessage.getText().toString();
                if (TextUtils.isEmpty(name))
                    contactName.setError("Please enter your name!");
                else {
                    if (TextUtils.isEmpty(email))
                        contactEmail.setError("Please enter your email!");
                    else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                        contactEmail.setError("Please enter valid email");
                    else {
                        if (TextUtils.isEmpty(phone))
                            contactPhone.setError("Please enter your phone!");
                        else {
                            if (TextUtils.isEmpty(message))
                                contactMessage.setError("Please enter your message!");
                            else {

                                ConnectivityManager connManager = (ConnectivityManager) getContext().getSystemService(CONNECTIVITY_SERVICE);
                                NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                                NetworkInfo mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                                //can contact us only once using the same phone and email.
                                if (wifi.isConnected() || mobile.isConnected()) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    ContactClass contactClass = new ContactClass(name, message, email);
                                    reference=database.getReference().child("PostContacts/"+phone);
                                    reference1=database.getReference().child("PostContacts");
                                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.hasChild(phone)){
                                                Toast.makeText(getContext(), "Phone number already in use", Toast.LENGTH_LONG).show();
                                                contactPhone.setError("Phone number already in use!");
                                                progressBar.setVisibility(View.INVISIBLE);
                                            }
                                            else {
                                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                    stringArrayList.add(String.valueOf(dataSnapshot.child("email").getValue()));
                                                }
                                                for(int i=0;i<stringArrayList.size();i++){
                                                    Log.d("hey",stringArrayList.get(i)+"n");
                                                }
                                                if (stringArrayList.contains(email)) {
                                                    Toast.makeText(getContext(), "Email already used!", Toast.LENGTH_LONG).show();
                                                    contactEmail.setError("Email already used!");
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                } else {
                                                    reference.setValue(contactClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            progressBar.setVisibility(View.INVISIBLE);
                                                            Toast.makeText(getContext(), "Data inserted successfully", Toast.LENGTH_LONG).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            progressBar.setVisibility(View.INVISIBLE);
                                                            Toast.makeText(getContext(), "No data inserted " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                                        }
                                                    });

                                                    contactName.setText("");
                                                    contactEmail.setText("");
                                                    contactPhone.setText("");
                                                    contactMessage.setText("");

                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(getContext(), "Data canceled "+error.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                                //Can contact us multiple times using same phone and email.

                                /*if (wifi.isConnected() || mobile.isConnected()) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    ContactClass contactClass = new ContactClass(name, message,email);
                                    reference=database.getReference().child("PostContacts/"+phone);
                                    reference.push().setValue(contactClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(getContext(), "Data inserted successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(getContext(), "No data inserted " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    contactName.setText("");
                                    contactEmail.setText("");
                                    contactPhone.setText("");
                                    contactMessage.setText("");

                                }
*/

                                else{
                                    Toast.makeText(getContext(), "Please connect to wifi", Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    }
                }




            }
        });


        return view;
    }
}