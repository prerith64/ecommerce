package com.example.bottomnavigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AccountFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

}


//package com.example.bottomnavigation;
//
//import static android.content.Intent.getIntent;
//import static android.content.Intent.getIntentOld;
//
//import android.content.Intent;
//import android.os.Bundle;
//import androidx.fragment.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class AccountFragment extends Fragment {
//    EditText Name, Email, UserName, Password;
//    Button Btn_update;
//    String name, email, username, password;
//    DatabaseReference reference;
//
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////        View view = inflater.inflate(R.layout.fragment_account, container, false);
////
////        reference = FirebaseDatabase.getInstance().getReference("users");
////
////        Name = view.findViewById(R.id.EditTextName); // Replace 'editTextName' with the actual ID of your EditText in your XML layout
////
////        // Initialize other EditText fields, buttons, etc. similarly
////        Email = view.findViewById(R.id.EditTextEmail);
////         UserName = view.findViewById(R.id.EditTextPhoneno);
////         Password = view.findViewById(R.id.EditTextpassword);
////         Btn_update = view.findViewById(R.id.btn_update);
////
////        showData();
////
////
////        // Set a click listener for the update button
////        Btn_update.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////               if(isNameChanged() || isEmailChanged() || isPasswordChanged()){
////                   Toast.makeText(AccountFragment.this, "Saved", Toast.LENGTH_SHORT).show();
////               }else{
////                   Toast.makeText(AccountFragment.this, "Changes failed", Toast.LENGTH_SHORT).show();
////               }
////
////                // Now you can use the 'name', 'email', 'phone', and 'password' values for Firebase database operations
////            }
////        });
////
////        return view;
////    }
////    public boolean isNameChanged(){
////        if(!name.equals(Name.getText().toString())){
////            reference.child(username).child("name").setValue(Name.getText().toString());
////            name=Name.getText().toString();
////            return true;
////
////        }else{
////            return false;
////        }
////
////    }
////
////    public boolean isEmailChanged(){
////        if(!email.equals(Name.getText().toString())){
////            reference.child(username).child("email").setValue(Email.getText().toString());
////            email=Email.getText().toString();
////            return true;
////
////        }else{
////            return false;
////        }
////
////    }
////
////    public boolean isPasswordChanged(){
////        if(!password.equals(Password.getText().toString())){
////            reference.child(username).child("password").setValue(Password.getText().toString());
////            password=Password.getText().toString();
////            return true;
////
////        }else{
////            return false;
////        }
////
////    }
////
////    public void showData(){
////        Intent intent =new Intent();
////        name=intent.getStringExtra("name");
////        email=intent.getStringExtra("email");
////        username=intent.getStringExtra("username");
////        password=intent.getStringExtra("password");
////
////        Name.setText(name);
////        Email.setText(email);
////        UserName.setText(username);
////        Password.setText(password);
////    }
//
//}
