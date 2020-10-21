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
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

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


public class SeventhFragment extends Fragment {

    TextView txtPitanja;
    Dan dan;
    DataBaseHelper dbHelper;
    //EditText etxtOdgovor;
    ImageView imageViewSlikaNaPitanju,imgDay;
    ViewGroup container;
    View view ;
    LinearLayout linearLayout ;
    int d,s;

    public SeventhFragment(Dan dan, int d,int s)

    {
        this.dan=dan;
        this.d=d;
        this.s=s;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        this.container=container;
        // Inflate the layout for this fragment
        dbHelper=new DataBaseHelper(getActivity());
        view=inflater.inflate(R.layout.fragment_seventh, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewSlikaNaPitanju = view.findViewById(R.id.imageView1);

        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));


        linearLayout = view.findViewById(R.id.linear);
        if (dan.getSlika() != null && !dan.getSlika().toString().isEmpty())
        {
            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/"+dan.getSlika().toString());
            if (bmp!=null) {
                imageViewSlikaNaPitanju.setImageBitmap(bmp);
            }
            else
            {
                imageViewSlikaNaPitanju.setVisibility(View.GONE);
            }
        }
        else
        {
            imageViewSlikaNaPitanju.setVisibility(View.GONE);
        }

        // adra srbija
        if (dan.getTacanOdgovor()!=null &&  dan.getTacanOdgovor().equals("Adra Srbija")) imageViewSlikaNaPitanju.setVisibility(View.GONE);

        int i=1;
        if (dan.getOdgovori()!=null)
        {
            String s = dan.getOdgovori();
            try
            {
                i=Integer.parseInt(s);
            }
            catch (Exception ex)
            {

            }

        }

        //etxtOdgovor = view.findViewById(R.id.etxtOdgovor);

        for(int y = 0; y < i; y++)
        {


            EditText editText = new EditText(getActivity(),null,R.style.CustomEditTextStyle);
            editText.setFocusable(true);
            editText.setSingleLine(true);
            editText.setFocusableInTouchMode(true);
            if (y==0)editText.requestFocus();
            editText.setGravity(Gravity.CENTER);


            Drawable wrappedDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(getActivity(), R.drawable.logo_merkator));
            DrawableCompat.setTint(wrappedDrawable.mutate(), getResources().getColor(android.R.color.holo_blue_light));
            editText.setBackgroundDrawable(wrappedDrawable);

           // ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(200, 50);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(600, 100);
            lp.setMargins(10, 10, 10, 10);

            //editText.setId(i); //Set id to remove in the future.
            //LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
            linearLayout.addView(editText,lp);//,lp



        }


        //etxtOdgovor.requestFocus();
        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        txtPitanja.setText(dan.getTekstPitanja());
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                int i=1;
                if (dan.getOdgovori()!=null)
                {
                    String s = dan.getOdgovori();
                    try
                    {
                        i=Integer.parseInt(s);
                    }
                    catch (Exception ex)
                    {

                    }

                }
                String[] tacni_odgovori=null ;

                boolean res = false;

                    if (dan.getTacanOdgovor() != null)
                        tacni_odgovori = dan.getTacanOdgovor().split(";");

                String[]  upisani_odgovori=null ;

                    for (int y = 0; y < linearLayout.getChildCount(); y++) {
                        if (linearLayout.getChildAt(y) instanceof EditText)
                        {
                                String s=((EditText) linearLayout.getChildAt(y)).getText().toString().toLowerCase().trim();

                            for (int j = 0; j < i; j++) {
                                String s1=tacni_odgovori[j].toString().toLowerCase().trim();


                                if (s.toLowerCase().toString().contains(s1.toLowerCase().toString()))
                                {

                                    res = true;

                                }
                                else
                                {
                                    res = false;

                                }

                            }
                        }

                }


                if (res)
                {


                    try {
                        dbHelper.openDataBase();
                        ContentValues iuValues = new ContentValues();
                        iuValues.put("Odgovoreno", "1");
                        if (d==1)
                            dbHelper.myDataBase.updateWithOnConflict("Dan1", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                        if (d==2)
                            dbHelper.myDataBase.updateWithOnConflict("Dan2", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                        if (d==3)
                            dbHelper.myDataBase.updateWithOnConflict("Dan3", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                    }
                    catch (SQLiteException ex)
                    {
                        String s="";
                    }
                    finally {

                        dbHelper.close();
                    }
                    getActivity().onBackPressed();


                }
                else
                {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Odgovor nije tačan!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //etxtOdgovor.setFocusable(true);
                                    dialog.dismiss();
                                }
                            })

                            .show();

                }


            }
        });
    }



}