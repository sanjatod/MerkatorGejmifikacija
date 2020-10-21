package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;


public class IzborDanaFragment extends Fragment {
    ImageView imgDan1, imgDan2, imgDan3;
    DataBaseHelper dbHelper;
    private int dan,danZavrsen;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        dbHelper=new DataBaseHelper(getActivity());
        return inflater.inflate(R.layout.fragment_izbor_dana, container, false);
    }

    public IzborDanaFragment(int dan, int danZavrsen)
    {
        this.dan = dan;
        this.danZavrsen = danZavrsen;


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgDan1 = view.findViewById(R.id.imgPrviDan);
        imgDan2 = view.findViewById(R.id.imgDrugiDan);
        imgDan3 = view.findViewById(R.id.imgTreciDan);

        if (dan==0 && danZavrsen==0)
        {
            imgDan2.setClickable(false);
            imgDan3.setClickable(false);
        }
        if (dan==0 && danZavrsen==1)
        {
            imgDan1.setClickable(false);
            imgDan3.setClickable(false);
        }

        if (dan==0 && danZavrsen==2)
        {
            imgDan2.setClickable(false);
            imgDan3.setClickable(false);
        }

        view.findViewById(R.id.imgPrviDan).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // startovanje dana
                        BaseActivity.storeDate(getActivity().getApplicationContext());

                        if (dan == 0 && danZavrsen!= 0) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Dan je zaključan!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();
                        } else {
                            try {
                                Date d = new Date();
                                CharSequence s = android.text.format.DateFormat.format("dd-MM-yyyy:hh-mm-ss", d.getTime());
                                String pocetak = s.toString();

                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Pocetak", pocetak);
                                iuValues.putNull("Kraj");
                                iuValues.put("Dan1", "1");
                                iuValues.put("Dan2", "0");
                                iuValues.put("Dan3", "0");
                                dbHelper.myDataBase.insertOrThrow("AkcijaDan", null, iuValues);
                            } catch (SQLiteException ex) {
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("GRESKA!   " + ex.getMessage())
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                dialog.dismiss();
                                            }
                                        })

                                        .show();
                            } finally {

                                dbHelper.close();
                            }
                            BaseActivity.dan = 1;

                            getActivity().onBackPressed();

                        }
                    }
                });

        view.findViewById(R.id.imgDrugiDan).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!BaseActivity.retrieveDate(getActivity())) {
                            return;
                        }

                        if (dan == 0  && danZavrsen!= 1) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Dan je zaključan!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();
                        } else {
                            BaseActivity.storeDate(getActivity().getApplicationContext());
                            try {
                                Date d = new Date();
                                CharSequence s = android.text.format.DateFormat.format("dd-MM-yyyy:hh-mm-ss", d.getTime());
                                String pocetak = s.toString();

                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Pocetak", pocetak);
                                iuValues.putNull("Kraj");
                                iuValues.put("Dan1", "0");
                                iuValues.put("Dan2", "1");
                                iuValues.put("Dan3", "0");
                                dbHelper.myDataBase.insertOrThrow("AkcijaDan", null, iuValues);
                            } catch (SQLiteException ex) {
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("GRESKA!   " + ex.getMessage())
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                dialog.dismiss();
                                            }
                                        })

                                        .show();
                            } finally {

                                dbHelper.close();
                            }
                            BaseActivity.dan = 2;
                            BaseActivity.segment = 0;
                            getActivity().onBackPressed();

                        }
                    }
                });

        view.findViewById(R.id.imgTreciDan).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!BaseActivity.retrieveDate(getActivity())) {
                            return;
                        }
                        if (dan == 0  && danZavrsen!= 2) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Dan je zaključan!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();
                        } else {

                            // startovanje dana
                            try {
                                Date d = new Date();
                                CharSequence s = android.text.format.DateFormat.format("dd-MM-yyyy:hh-mm-ss", d.getTime());
                                String pocetak = s.toString();

                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Pocetak", pocetak);
                                iuValues.putNull("Kraj");
                                iuValues.put("Dan1", "0");
                                iuValues.put("Dan2", "0");
                                iuValues.put("Dan3", "1");
                                dbHelper.myDataBase.insertOrThrow("AkcijaDan", null, iuValues);
                            } catch (SQLiteException ex) {
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("GRESKA!   " + ex.getMessage())
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                dialog.dismiss();
                                            }
                                        })

                                        .show();
                            } finally {

                                dbHelper.close();
                            }
                            BaseActivity.dan = 3;
                            BaseActivity.segment = 0;
                            getActivity().onBackPressed();

                        }
                    }
                });
    }
}