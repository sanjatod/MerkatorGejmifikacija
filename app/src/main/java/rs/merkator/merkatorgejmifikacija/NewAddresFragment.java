package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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


public class NewAddresFragment extends Fragment {

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
        Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/mapaRodaMega.png");
        if (bmp!=null) {
            imageViewSlikaNaPitanju.setImageBitmap(bmp);
        }




        etxtOdgovor = view.findViewById(R.id.etxtOdgovor);


        etxtOdgovor.requestFocus();
        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        txtPitanja.setText("Idite do zgrade Mega Roda, na adresi Đorđa Stanojevića 35. Koji ste prevoz koristili?");
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               // getActivity().onBackPressed();
                dbHelper.openDataBase();
                ContentValues iuValues = new ContentValues();
                iuValues.put("Zapocet", 2);
                dbHelper.myDataBase.updateWithOnConflict("AkcijaSegment", iuValues, "Segment=" + 4 + " AND  Dan=1", null, SQLiteDatabase.CONFLICT_ROLLBACK);
                dbHelper.close();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = fm.findFragmentByTag("IzborSegmenta");
                if (fragment == null) {
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new IzborSegmentaFragment(1, 4);
                    ft.addToBackStack(null);
                    ft.replace(R.id.fragment_frame, fragment);
                    ft.commit();
                    return;
                }



            }
        });
    }



}