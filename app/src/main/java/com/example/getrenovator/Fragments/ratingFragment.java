package com.example.getrenovator.Fragments;

import static android.view.View.*;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import com.example.getrenovator.MainActivity;
import com.example.getrenovator.Models.Renovator;
import com.example.getrenovator.R;

import java.util.List;

public class ratingFragment extends Fragment {
    private static final int rb1 = 1;
    private static final int rb2 = 2;
    private static final int rb3 = 3;
    private static final int rb4 = 4;
    private static final int rb5 = 5;


    ClientPage cl = new ClientPage();
    List<Renovator> re = cl.RenoListForClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);

        Button rateBut =  view.findViewById(R.id.rateBut);

        RadioGroup group = view.findViewById(R.id.groupRate);

        RadioButton r1 = view.findViewById(R.id.rate1);
        RadioButton r2 = view.findViewById(R.id.rate2);
        RadioButton r3 = view.findViewById(R.id.rate3);
        RadioButton r4 = view.findViewById(R.id.rate4);
        RadioButton r5 = view.findViewById(R.id.rate5);

        r1.setId(rb1);
        r2.setId(rb2);
        r3.setId(rb3);
        r4.setId(rb4);
        r5.setId(rb5);

        TextView tx = view.findViewById(R.id.rateReno);
        tx.setText("please rate " + re.get(ClientPage.check).getFirst_name());



        rateBut.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v) {

                if (group.getCheckedRadioButtonId() != -1){
                    re.get(ClientPage.check).setCount();    // add +1 the number of people who rated.
                    re.get(ClientPage.check).calRating(group.getCheckedRadioButtonId());  //// function to calculate the rating of this renovator


                    Navigation.findNavController(view).navigate(R.id.action_ratingFragment_to_landingPage);
                }
            }
        });




        return view;
    }




}