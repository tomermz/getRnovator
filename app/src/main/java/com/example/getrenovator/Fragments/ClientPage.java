package com.example.getrenovator.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.getrenovator.MainActivity;
import com.example.getrenovator.Models.Renovator;
import com.example.getrenovator.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientPage extends Fragment {


    Map<String ,LatLng> cityMap = getLatList(); // the latLng of cities.
    List<Renovator> renovatorList = getReno();
    private static final int rb0 = 0;   //first radio button id
    private static final int rb1 = 1;   //second radio button id
    private static final int rb2 = 2;   //third radio button id
    public static int check = -1;
    public String citClintLat = MainActivity.CityClint;     // the city of client
    public  List<Renovator> RenoListForClient = getindx(citClintLat); //the list of 3 Renovator on map




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_client_page, container, false);
        Button conBut =  view.findViewById(R.id.conBut);

        RadioGroup radioGroup = view.findViewById(R.id.selectRad);
        RadioButton r0 =        view.findViewById(R.id.indx0);
        RadioButton r1 =        view.findViewById(R.id.indx1);
        RadioButton r2 =        view.findViewById(R.id.indx2);
        r0.setId(rb0);
        r1.setId(rb1);
        r2.setId(rb2);

        TextView name0 =    view.findViewById(R.id.textViewName0);
        TextView phone0 =   view.findViewById(R.id.textViewPhone0);
        TextView city0 =    view.findViewById(R.id.textViewCity0);
        TextView rating0 =  view.findViewById(R.id.textViewRate0);

        TextView name1 =    view.findViewById(R.id.textViewName1);
        TextView phone1 =   view.findViewById(R.id.textViewPhone1);
        TextView city1 =    view.findViewById(R.id.textViewCity1);
        TextView rating1 =  view.findViewById(R.id.textViewRate1);

        TextView name2 =     view.findViewById(R.id.textViewName2);
        TextView phone2 =    view.findViewById(R.id.textViewPhone2);
        TextView city2 =     view.findViewById(R.id.textViewCity2);
        TextView rating2 =   view.findViewById(R.id.textViewRate2);





        name0.setText(RenoListForClient.get(0).getFirst_name());
        phone0.setText(RenoListForClient.get(0).getPhone());
        city0.setText(RenoListForClient.get(0).getCity());
        rating0.setText(RenoListForClient.get(0).getRating());
        String ci0 = RenoListForClient.get(0).getCity();

        name1.setText(RenoListForClient.get(1).getFirst_name());
        phone1.setText(RenoListForClient.get(1).getPhone());
        city1.setText(RenoListForClient.get(1).getCity());
        rating1.setText(RenoListForClient.get(1).getRating());
        String ci1 = RenoListForClient.get(1).getCity();

        name2.setText(RenoListForClient.get(2).getFirst_name());
        phone2.setText(RenoListForClient.get(2).getPhone());
        city2.setText(RenoListForClient.get(2).getCity());
        rating2.setText(RenoListForClient.get(2).getRating());
        String ci2 = RenoListForClient.get(2).getCity();



        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(googleMap -> {


            googleMap.addMarker(new MarkerOptions()
                    .position(getLatLng(citClintLat,0))
                    .title("I am here")
                    .icon(BitmapDescriptorFactory.defaultMarker
                            (BitmapDescriptorFactory.HUE_RED)));


            googleMap.addMarker(new MarkerOptions()
                            .position( getLatLng( ci0,1 ) )
                    .title(RenoListForClient.get(0).getFirst_name() +"-" + RenoListForClient.get(0).getPhone())
                            .icon(BitmapDescriptorFactory.defaultMarker
                                    (BitmapDescriptorFactory.HUE_ORANGE)));

            googleMap.addMarker(new MarkerOptions()
                    .position( getLatLng( ci1,2 ) )
                    .title(RenoListForClient.get(1).getFirst_name() + "-" + RenoListForClient.get(1).getPhone())
                    .icon(BitmapDescriptorFactory.defaultMarker
                            (BitmapDescriptorFactory.HUE_ORANGE)));

            googleMap.addMarker(new MarkerOptions()
                    .position( getLatLng( ci2,3 ) )
                    .title(RenoListForClient.get(2).getFirst_name() + "-" + RenoListForClient.get(2).getPhone())
                    .icon(BitmapDescriptorFactory.defaultMarker
                            (BitmapDescriptorFactory.HUE_ORANGE)));



            googleMap.setOnMapClickListener(latLng -> googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(getLatLng(citClintLat,0),14)));

        });

        conBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (radioGroup.getCheckedRadioButtonId() != -1)     // check if the radio button is clicked.
                {
                    check = radioGroup.getCheckedRadioButtonId();
                    Navigation.findNavController(view).navigate(R.id.action_clientPage_to_ratingFragment);
                }
            }
        });





        return view;
    }


    //get the latitude and longitude
    public Map<String, LatLng> getLatList() {

        Map<String,LatLng> LatMap = new HashMap();

        LatLng Holon=  new LatLng(32.01034, 34.77918);
        LatLng Tel_Aviv = new LatLng( 32.05043, 34.75224);
        LatLng Herzliya = new LatLng(32.166313, 34.843311);
        LatLng Netanya = new LatLng(32.3328, 34.8600);
        LatLng Ramat_Gan = new LatLng(32.0697, 34.8117);



        LatMap.put("Holon",Holon);
        LatMap.put("Tel_Aviv",Tel_Aviv);
        LatMap.put("Herzliya",Herzliya);
        LatMap.put("Netanya",Netanya);
        LatMap.put("Ramat_Gan",Ramat_Gan);

        return LatMap;

    }


    // get the latitude and the longitude of the cities
    public LatLng getLatLng(String c,Integer i){

        LatLng L = cityMap.get(c);

        if (i == 0)
            return L;

        List<Double> num = new ArrayList<>();
        num.add(0.000324);
        num.add(0.001555);
        num.add(0.003364);
        num.add(0.0044);
        num.add(0.5624);
        num.add(0.8924);
        num.add(0.56);
        num.add(0.774);
        num.add(0.2224);

        assert L != null;
        double  x = L.latitude+ num.get(i);
        double  y = L.longitude+num.get(i);

        return new LatLng(x, y);
    }

    // get the 3 list of renovator
    public List<Renovator> getindx(String c) {

        List<Renovator> temp = renovatorList;
        int  count = 0;
        List<Renovator> indx = new ArrayList<>(3);
        for(Renovator ren : renovatorList)
        {
            if(c.equals(ren.getCity()) && count<3)
            {
                indx.add(ren);
                count+=1;
            }

        }

        for(int i = count ; i<3; i++)
        {
            indx.add(renovatorList.get(i));
        }

        return indx;
    }


    //get the Renovator list
    public List<Renovator>getReno(){
        return MainActivity.getReno();

    }

}

