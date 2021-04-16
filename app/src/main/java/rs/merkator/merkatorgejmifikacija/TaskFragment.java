package rs.merkator.merkatorgejmifikacija;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment {
    TextView txtPitanja;
    ImageView imageViewSlikaNaPitanju,imgDay;
    Dan dan;
    int d, s;
    DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
    FloatingActionButton btnNext;

    public TaskFragment(Dan dan, int d, int s) {
        this.dan = dan;
        this.d=d;
        this.s=s;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewSlikaNaPitanju = view.findViewById(R.id.imageView1);
        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));

        if (dan!=null && dan.getSlika()!=null) {

            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/" + dan.getSlika().toString());
            if (bmp != null) {
                imageViewSlikaNaPitanju.setImageBitmap(bmp);
            } else {
                imageViewSlikaNaPitanju.setVisibility(View.GONE);
            }
        }
        else
        {
            if (dan==null && d==4) {

                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/ideaakademija.png" );
                if (bmp != null) {
                    imageViewSlikaNaPitanju.setImageBitmap(bmp);
                } else {
                    imageViewSlikaNaPitanju.setVisibility(View.GONE);
                }
            }
            else
            imageViewSlikaNaPitanju.setVisibility(View.GONE);
        }

        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        if (d==2&&s==0)
        {
            txtPitanja.setText("Pitajte nekog u blizini gde sede kolege iz područja Tradinga & CM-a kako bi vam pomogao da odgovorite tačno na pitanja koje slede");
        }
        else
        {
            if (d==2&&s==1&&dan==null)
            {
                txtPitanja.setText("Pitajte nekog u blizini gde sede kolege iz područja Maloprodaje kako bi vam pomogao da odgovorite tačno na pitanja koje slede");
            }
            else
            {
                if (d==4)
                {
                    txtPitanja.setText("Idite do Idea Akademije, Nehruova 56b. Javite se zaposlenom na info pultu kako biste uradili evaluaciju programa.");
                    imgDay.setVisibility(View.GONE);
                }
                else {

                    txtPitanja.setText(dan.getTekstPitanja().replace("\\n", "\n"));
                }

            }


        }

        imageViewSlikaNaPitanju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (d==2 && s==3 && dan.getRowid()==59) {
                    FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                    TicTakToeActivity w1 = new TicTakToeActivity();
                    ft1.replace(R.id.fragment_frame, w1);
                    ft1.commit();
                }

                if (d==2 && s==4 && dan.getRowid()==112) {
                   //kontroling za gejm.pdf
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());


                    File file = null;
                    file = new File(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/Gejmifikacija sektor kontrole RM poslovanja.docx");
                    //Toast.makeText(getApplicationContext(), file.toString() , Toast.LENGTH_LONG).show();
                    if (file.exists()) {

                        try {
                            Uri path = Uri.fromFile(file);

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(path, "application/docx");//pdf
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                        } catch (ActivityNotFoundException ex)
                        {
                            String s="";
                        }
                    }
                }

                if (d==2 && s==4 && dan.getRowid()==153) {
                    //kontroling za gejm.pdf
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());


                    File file = null;
                    file = new File(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/SEKTOR ZA OPŠTE POSLOVE I NEROBNE NABAVKE.docx");
                    //Toast.makeText(getApplicationContext(), file.toString() , Toast.LENGTH_LONG).show();
                    if (file.exists()) {

                        try {
                            Uri path = Uri.fromFile(file);

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(path, "application/docx");//pdf
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                        } catch (ActivityNotFoundException ex)
                        {
                            String s="";
                        }
                    }
                }
            }
        });


        if (d==2 && s==2 && dan!=null &&  dan.getRowid()==43)
        {
            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
            MatArrayFragment w1 = new MatArrayFragment();
            ft1.replace(R.id.fragment_frame, w1);
            ft1.commit();

        }
        btnNext = view.findViewById(R.id.btnNext);
        if (d==2 && s==3 && dan!=null && dan.getRowid()==59)
        {
            btnNext.setVisibility(View.GONE);

        }

        btnNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                       if (dan!=null) {
                           dbHelper.openDataBase();
                           ContentValues iuValues = new ContentValues();
                           iuValues.put("Odgovoreno", "1");
                           if (d == 1)
                               dbHelper.myDataBase.updateWithOnConflict("Dan1", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                           if (d == 2)
                               dbHelper.myDataBase.updateWithOnConflict("Dan2", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                           if (d==3)
                               dbHelper.myDataBase.updateWithOnConflict("Dan3", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                           dbHelper.close();
                       }

                        if (dan==null && d==2 && s==0)
                        {
//                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
//                            OrganizationalSchemeFragment w1 = new OrganizationalSchemeFragment(d, s);
//                            ft1.replace(R.id.fragment_frame, w1);
//                            ft1.commit();
                            try {

                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Segment", s + 1);
                                iuValues.put("Dan", 2);
                                iuValues.put("Zapocet", 1);
                                dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                            } catch (Exception ex) {
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
                            BaseActivity.segment = +1;

                            getActivity().onBackPressed();
                        }
                        else {
                            if (dan == null && d == 2 && s == 1 ) {
//                                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
//                                OrganizationalSchemeFragment w1 = new OrganizationalSchemeFragment(d, s);
//                                ft1.replace(R.id.fragment_frame, w1);
//                                ft1.commit();

                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", s + 1);
                                    iuValues.put("Dan", 2);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
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
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();
                            } else {
                                if (dan == null && d == 4 ) {
                                    BaseActivity.evaluacija=true;
                                    getActivity().onBackPressed();
                                }
                                else

                                getActivity().onBackPressed();
                            }
                        }


                    }
                });

    }

}