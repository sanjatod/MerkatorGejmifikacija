package rs.merkator.merkatorgejmifikacija;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class OfficeMenagerFragment extends Fragment {

    TextView txtPitanja;
    DataBaseHelper dbHelper;
    EditText etxtOdgovor;
    ImageView imageViewSlikaNaPitanju;
    ViewGroup container;
    View view ;
    LinearLayout linearLayout ;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        this.container=container;
        // Inflate the layout for this fragment
        dbHelper=new DataBaseHelper(getActivity());
        view=inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewSlikaNaPitanju = view.findViewById(R.id.imageView1);
        linearLayout = view.findViewById(R.id.linear);
        Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/ugovori.png");
        if (bmp!=null) {
            imageViewSlikaNaPitanju.setImageBitmap(bmp);
        }




        etxtOdgovor = view.findViewById(R.id.etxtOdgovor);


        etxtOdgovor.requestFocus();
        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        txtPitanja.setText("Idite do ingopulta i raspitajte te se gde je kancelarija menadžera objekta Roda Mega 428\n Ime i prezime osobe koja vam je pomogla:");
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                IzborSegmentaFragment w1 = new IzborSegmentaFragment(1,4);
                ft1.replace(R.id.fragment_frame, w1);
                ft1.commit();



            }
        });
    }



}