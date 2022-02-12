package com.example.getrenovator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.getrenovator.Models.Client;
import com.example.getrenovator.Models.Person;
import com.example.getrenovator.Models.Renovator;
import com.example.getrenovator.Models.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public static List<Renovator> renoList = new ArrayList<>();
    public static List<Client> clientList = new ArrayList<>();
    public static String CityClint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        getAllData();   //Here you initialize the existing lists of objects





    }

    public void regFunc() {

        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress2)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();
        String confirmPass = ((EditText) findViewById(R.id.editTextTextPassword3)).getText().toString();


        if(!password.equals(confirmPass)){
            Toast.makeText(MainActivity.this, "The passwords must match.", Toast.LENGTH_LONG).show();
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "reg ok", Toast.LENGTH_LONG).show();
                            addData();
                            onBackPressed();

                        } else {
                            Toast.makeText(MainActivity.this, "reg fail", Toast.LENGTH_LONG).show();

                        }

                    }
                });

    }


    public void addData(){

        String firstName = ((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.editTextTextPersonName2)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress2)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
        RadioGroup radioGroup= findViewById(R.id.radioGroup);
        Spinner spin = findViewById(R.id.spin);
        int selected=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=findViewById(selected);
        UserType type = UserType.valueOf(radioButton.getText().toString());
        String citySel = spin.getSelectedItem().toString();




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(firstName);
        myRef.setValue(new Person(firstName, lastName, email, phone, type, citySel));


    }


    public void loginFunc(View view){

        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {

        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            String email =  Objects.requireNonNull(task.getResult().getUser()).getEmail();
            assert email != null;
            String[] name = email.split("@");

            if (task.isSuccessful()) {
                Toast.makeText(MainActivity.this, "login ok" , Toast.LENGTH_LONG).show();
                getData(name, view);
            }

            else {
                Toast.makeText(MainActivity.this, "login fail" , Toast.LENGTH_LONG).show(); }



        }
    });

    }


    //Function for transfer to the correct screen
    public void getData(@NonNull String[] name, View view){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(String.valueOf(name[0]));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CityClint = null;
                String userType = (String)(dataSnapshot.child("userType").getValue());

                if(userType.equals("Renovator"))
                {
                    Navigation.findNavController(view).navigate(R.id.action_landingPage_to_renovatorPage);
                }
                else {
                    CityClint = dataSnapshot.child("city").getValue().toString();
                    Navigation.findNavController(view).navigate(R.id.action_landingPage_to_clientPage);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }


    //Function for receiving existing objects
    public void getAllData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

        myRef.addValueEventListener(new ValueEventListener()        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                renoList.clear();
                clientList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String type = postSnapshot.child("userType").getValue().toString();// CLint--Renovator
                    if(type.equals("Renovator")) {
                        Renovator ren = postSnapshot.getValue(Renovator.class);
                        addReno(ren);
                    }
                    else {
                        Client cl = postSnapshot.getValue(Client.class);
                        clientList.add(cl);
                    }


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }



    // add renovator and rate 4
    public  void addReno(Renovator r){
        r.setRating("4");
        renoList.add(r);
    }
    // return the list of renovator
    public static List<Renovator> getReno(){

        return renoList;
    }


















    public static List<Client> getClientListList(){

        return clientList;
    }


    public  void addClient(Client c){
        clientList.add(c);
    }

}