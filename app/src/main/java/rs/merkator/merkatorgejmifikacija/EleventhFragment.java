package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class EleventhFragment extends Fragment {

    TextView txtPitanja;
    Dan dan;
    DataBaseHelper dbHelper;
    ImageView  imgDay;
    ViewGroup container;
    View view ;
    LinearLayout linearLayout ;
    LinearLayout linearLayout1 ;
    int d,s;
    //Button btn1;
    String[] tacni_odgovori;
    String[] ponudjeni_odgovori;
    List<OdgovorTipa11> listaOdgovora =new ArrayList<>();;

    public EleventhFragment(Dan dan, int d,int s)

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
        view=inflater.inflate(R.layout.fragment_eleventh, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //imageViewSlikaNaPitanju = view.findViewById(R.id.imageView1);

        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));
        linearLayout = view.findViewById(R.id.linear);
        linearLayout1 = view.findViewById(R.id.linear2);
//        if (dan.getSlika() != null && !dan.getSlika().toString().isEmpty()) {
//            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/" + dan.getSlika().toString());
//            if (bmp != null) {
//                imageViewSlikaNaPitanju.setImageBitmap(bmp);
//            } else {
//                imageViewSlikaNaPitanju.setVisibility(View.GONE);
//            }
//        } else {
//            imageViewSlikaNaPitanju.setVisibility(View.GONE);
//        }

//        btn1 = view.findViewById(R.id.btnButton);
//        btn1.setOnClickListener(mbtnClickListener);
//        btn1.setBackgroundColor(Color.WHITE);
//        btn1.setTag("0");


        int i = 1;

        if (dan.getTacanOdgovor() != null) {
            tacni_odgovori = dan.getTacanOdgovor().split(";");
        }

            if (dan.getOdgovori()!= null) {
                ponudjeni_odgovori = dan.getOdgovori().split(";");
            }

//            listaOdgovora.add(new OdgovorTipa11(0,ponudjeni_odgovori[0],false));
//            btn1.setText(ponudjeni_odgovori[0].toString());

            if (ponudjeni_odgovori.length==6)
            {
                for (int y = 0; y < 3; y++) {


                    //int t=y+1;
                    listaOdgovora.add(new OdgovorTipa11(y, ponudjeni_odgovori[y], false));
                    String tag = String.valueOf(y);
                    Button btn = new Button(getActivity(), null, R.style.CustomButtonStyle);
                    btn.requestFocus();
                    btn.setTag(tag);
                    btn.setBackgroundColor(Color.WHITE);
                    btn.setGravity(Gravity.CENTER);
                    btn.setTextColor(getResources().getColor(R.color.red));
                    btn.setOnClickListener(mbtnClickListener);
                    btn.setText(ponudjeni_odgovori[y].toString());

                    //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 70);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 100);
                    lp.setMargins(20, 20, 20, 20);

                    //                Drawable wrappedDrawable = DrawableCompat.wrap(btn1.getBackground());
                    //                DrawableCompat.setTint(wrappedDrawable.mutate(), getResources().getColor(R.color.red));
                    //                btn.setBackgroundDrawable(wrappedDrawable);
                    linearLayout.addView(btn, lp);//,lp


                }
                for (int y = 3; y < 6; y++) {


                    //int t=y+1;
                    listaOdgovora.add(new OdgovorTipa11(y, ponudjeni_odgovori[y], false));
                    String tag = String.valueOf(y);
                    Button btn = new Button(getActivity(), null, R.style.CustomButtonStyle);
                    btn.requestFocus();
                    btn.setTag(tag);
                    btn.setBackgroundColor(Color.WHITE);
                    btn.setGravity(Gravity.CENTER);
                    btn.setTextColor(getResources().getColor(R.color.red));
                    btn.setOnClickListener(mbtnClickListener);
                    btn.setText(ponudjeni_odgovori[y].toString());

                    //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 70);
                    //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300, 200);
                    //lp.setMargins(5, 5, 5, 5);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 100);
                    lp.setMargins(20, 20, 20, 20);

                    //                Drawable wrappedDrawable = DrawableCompat.wrap(btn1.getBackground());
                    //                DrawableCompat.setTint(wrappedDrawable.mutate(), getResources().getColor(R.color.red));
                    //                btn.setBackgroundDrawable(wrappedDrawable);
                    linearLayout1.addView(btn, lp);//,lp


                }
            }
            else {

                if (ponudjeni_odgovori.length==5)
                {
                    for (int y = 0; y < 3; y++) {


                        //int t=y+1;
                        listaOdgovora.add(new OdgovorTipa11(y, ponudjeni_odgovori[y], false));
                        String tag = String.valueOf(y);
                        Button btn = new Button(getActivity(), null, R.style.CustomButtonStyle);
                        btn.requestFocus();
                        btn.setTag(tag);
                        btn.setBackgroundColor(Color.WHITE);
                        btn.setGravity(Gravity.CENTER);
                        btn.setTextColor(getResources().getColor(R.color.red));
                        btn.setOnClickListener(mbtnClickListener);
                        btn.setText(ponudjeni_odgovori[y].toString());

                        //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 70);

                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 100);
                        lp.setMargins(20, 20, 20, 20);

                        //                Drawable wrappedDrawable = DrawableCompat.wrap(btn1.getBackground());
                        //                DrawableCompat.setTint(wrappedDrawable.mutate(), getResources().getColor(R.color.red));
                        //                btn.setBackgroundDrawable(wrappedDrawable);
                        linearLayout.addView(btn, lp);//,lp


                    }
                    for (int y = 3; y < 5; y++) {


                        //int t=y+1;
                        listaOdgovora.add(new OdgovorTipa11(y, ponudjeni_odgovori[y], false));
                        String tag = String.valueOf(y);
                        Button btn = new Button(getActivity(), null, R.style.CustomButtonStyle);
                        btn.requestFocus();
                        btn.setTag(tag);
                        btn.setBackgroundColor(Color.WHITE);
                        btn.setGravity(Gravity.CENTER);
                        btn.setTextColor(getResources().getColor(R.color.red));
                        btn.setOnClickListener(mbtnClickListener);
                        btn.setText(ponudjeni_odgovori[y].toString());

                        //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 70);

                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 100);
                        lp.setMargins(20, 20, 20, 20);

                        //                Drawable wrappedDrawable = DrawableCompat.wrap(btn1.getBackground());
                        //                DrawableCompat.setTint(wrappedDrawable.mutate(), getResources().getColor(R.color.red));
                        //                btn.setBackgroundDrawable(wrappedDrawable);
                        linearLayout1.addView(btn, lp);//,lp


                    }
                }
                else {

                    for (int y = 0; y < ponudjeni_odgovori.length; y++) {


                        //int t=y+1;
                        listaOdgovora.add(new OdgovorTipa11(y, ponudjeni_odgovori[y], false));
                        String tag = String.valueOf(y);
                        Button btn = new Button(getActivity(), null, R.style.CustomButtonStyle);
                        btn.requestFocus();
                        btn.setTag(tag);
                        btn.setBackgroundColor(Color.WHITE);
                        btn.setGravity(Gravity.CENTER);
                        btn.setTextColor(getResources().getColor(R.color.red));
                        btn.setOnClickListener(mbtnClickListener);
                        btn.setText(ponudjeni_odgovori[y].toString());

                        //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 70);

                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 100);
                        lp.setMargins(20, 20, 20, 20);

                        //                Drawable wrappedDrawable = DrawableCompat.wrap(btn1.getBackground());
                        //                DrawableCompat.setTint(wrappedDrawable.mutate(), getResources().getColor(R.color.red));
                        //                btn.setBackgroundDrawable(wrappedDrawable);
                        linearLayout.addView(btn, lp);//,lp


                    }
                }
            }
           // btn1.requestFocus();
            txtPitanja = view.findViewById(R.id.txtTextPitanja);
            txtPitanja.setText(dan.getTekstPitanja());


            view.findViewById(R.id.btnNext).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean res = false;

                            StringBuffer sb = new StringBuffer("");

                            for (String s : tacni_odgovori) {
                                sb.append(s + ",");
                            }

                            List<OdgovorTipa11> list = new ArrayList<>();
                            for (OdgovorTipa11 tip4 : listaOdgovora) {
                                if (tip4.isIzabran()) {
                                    if (sb.toString().toLowerCase().contains(String.valueOf(tip4.getRb()+1))) {
                                        res = true;
                                        list.add(tip4);
                                    } else {
                                        res = false;
                                        break;
                                    }
                                }
                            }

                            if (tacni_odgovori.length != list.size()) res = false;

                            if (res) {


                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    if (d == 1)
                                        dbHelper.myDataBase.updateWithOnConflict("Dan1", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                    if (d == 2)
                                        dbHelper.myDataBase.updateWithOnConflict("Dan2", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                    if (d==3)
                                        dbHelper.myDataBase.updateWithOnConflict("Dan3", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                if (dan.getTacanOdgovor() != null && dan.getTacanOdgovor().equals("Adra Srbija")) {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {

                                            getActivity().onBackPressed();
                                        }
                                    }, 2000);
                                } else {
                                    getActivity().onBackPressed();
                                }


                            } else {
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


        private class OdgovorTipa11
        {
            private int rb;
            private String ponudjenOdgovor;
            private boolean izabran;

            public OdgovorTipa11(int rb, String ponudjenOdgovor, boolean izabran) {
                this.ponudjenOdgovor = ponudjenOdgovor;
                this.izabran = izabran;
                this.rb=rb;
            }

            public int getRb() {
                return rb;
            }

            public void setRb(int rb) {
                this.rb = rb;
            }

            public String getPonudjenOdgovor() {
                return ponudjenOdgovor;
            }

            public void setPonudjenOdgovor(String ponudjenOdgovor) {
                this.ponudjenOdgovor = ponudjenOdgovor;
            }

            public boolean isIzabran() {
                return izabran;
            }

            public void setIzabran(boolean izabran) {
                this.izabran = izabran;
            }
        }

        private View.OnClickListener mbtnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                Button btn=(Button)v;
                int poz=Integer.valueOf(btn.getTag().toString());
                if(listaOdgovora.get(poz).isIzabran())
                {
                    listaOdgovora.get(poz).setIzabran(false);
                    btn.setTextColor(getResources().getColor(R.color.red));
                    btn.setGravity(Gravity.CENTER);
                    btn.setBackgroundColor(Color.WHITE);

                }
                else
                {
                    listaOdgovora.get(poz).setIzabran(true);
                    btn.setTextColor(Color.WHITE);
                    btn.setGravity(Gravity.CENTER);
                    btn.setBackgroundColor(getResources().getColor(R.color.red));
                }

            }
        };

    }