package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;


public class NinthFragment extends Fragment {

    TextView txtPitanja;
    Dan dan;
    DataBaseHelper dbHelper;
    EditText etxtOdgovor;
    ImageView imageViewSlikaNaPitanju, imgDay;
    ViewGroup container;
    View view ;
    LinearLayout linearLayout ;
    int d,s;

    public NinthFragment(Dan dan, int d,int s)

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
        view=inflater.inflate(R.layout.fragment_ninth, container, false);
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


        String[] ponudjeni=null;
        if (dan.getOdgovori()!=null)
        {
           ponudjeni= dan.getOdgovori().split(";");

        }

        etxtOdgovor = view.findViewById(R.id.etxtOdgovor);

        if (ponudjeni!=  null) {
            etxtOdgovor.setText(ponudjeni[0].toString());
            etxtOdgovor.setSelection(etxtOdgovor.getText().length());
        }

        if (ponudjeni.length>1) {
            EditText editText = new EditText(getActivity(), null, R.style.CustomEditTextStyle);
            editText.setFocusable(true);
            editText.setSingleLine(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            editText.setGravity(Gravity.LEFT);


            if (ponudjeni != null)
            {editText.setText(ponudjeni[1].toString());
            editText.setSelection(editText.getText().length());}


            Drawable wrappedDrawable = DrawableCompat.wrap(etxtOdgovor.getBackground());
            DrawableCompat.setTint(wrappedDrawable.mutate(), getResources().getColor(R.color.red));
            editText.setBackgroundDrawable(wrappedDrawable);

            //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 70);
            //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 70);
            //lp.setMargins(10, 10, 10, 10);

            //editText.setId(i); //Set id to remove in the future.
            //LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
            linearLayout.addView(editText);//,lp
        }



        etxtOdgovor.requestFocus();
        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        txtPitanja.setText(dan.getTekstPitanja());
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (etxtOdgovor.getText().toString().isEmpty())
                {
                    etxtOdgovor.setError("Polje je obavezno!");
                    return;
                }
                else
                {
                    etxtOdgovor.setError(null);
                }



                String upisanOdgovor ="";
                String[] tacni_odgovori = new String[0];
                boolean res = false;

                if (dan.getTacanOdgovor() == null)
                    res = true;

                else {
                    tacni_odgovori = dan.getTacanOdgovor().split(";");
                    for (int i = 0; i < linearLayout.getChildCount(); i++) {
                        if (linearLayout.getChildAt(i) instanceof EditText) {
                            if (((EditText) linearLayout.getChildAt(i)).getText().toString().toLowerCase().trim().isEmpty())  {
                                ((EditText) linearLayout.getChildAt(i)).setError("Popunite polje");
                                res = false;
                                break;
                            } else {
                                ((EditText) linearLayout.getChildAt(i)).setError(null);

                                upisanOdgovor = (((EditText) linearLayout.getChildAt(i)).getText().toString().toLowerCase().trim());

                                for (String s : tacni_odgovori) {
                                    if (upisanOdgovor.toLowerCase().contains(s.trim().toLowerCase())) {
                                        res = true;
                                        break;
                                    }
                                    else
                                    {
                                        res = false;
                                        //break;
                                    }
                                }

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

                    if (dan.getTacanOdgovor()!=null && dan.getTacanOdgovor().equals("Adra Srbija"))
                    {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                getActivity().onBackPressed();
                            }
                        }, 2000);
                    }
                    else
                    {
                        getActivity().onBackPressed();
                    }



                }
                else
                {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Odgovor nije tačan!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    etxtOdgovor.setFocusable(true);
                                    dialog.dismiss();
                                }
                            })

                            .show();

                }



            }
        });
    }



}