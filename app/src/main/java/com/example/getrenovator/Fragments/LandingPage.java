package com.example.getrenovator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.getrenovator.MainActivity;
import com.example.getrenovator.Models.Person;
import com.example.getrenovator.Models.UserType;
import com.example.getrenovator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LandingPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LandingPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LandingPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LandingPage.
     */
    // TODO: Rename and change types and number of parameters
    public static LandingPage newInstance(String param1, String param2) {
        LandingPage fragment = new LandingPage();
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
        View view = inflater.inflate(R.layout.fragment_landing_page, container, false);

        Button buttonToLogin = view.findViewById(R.id.logInButton);
        Button buttonToRegisterPage = view.findViewById(R.id.regPageButton);


        buttonToRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_landingPage_to_registerPage);
            }
        });

        buttonToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.loginFunc(view);



            }
        });

        return view;
    }
}