package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;


public class PersonnelRecordsFragment extends Fragment {

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
        txtPitanja.setText("Zamolite nekoga da vam pokaže gde se nalazi služba kadrovse evidencije\n Ime i prezime osobe koja vam je pomogla:");
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                IzborSegmentaFragment w1 = new IzborSegmentaFragment(1,3);
                ft1.replace(R.id.fragment_frame, w1);
                ft1.commit();



            }
        });
    }



}