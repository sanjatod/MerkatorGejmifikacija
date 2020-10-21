package rs.merkator.merkatorgejmifikacija;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Date;

public class MoveFragment extends Fragment {
    DataBaseHelper dbHelper;
    private int dan;
    private int segment;
    TextView txtWelcome;
    ImageView imageViewOpenPDF;

    public MoveFragment(int dan, int segment)
    {
        this.dan = dan;
        this.segment=segment;

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        dbHelper=new DataBaseHelper(getActivity());
        return inflater.inflate(R.layout.fragment_move, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtWelcome = view.findViewById(R.id.txtText);
        imageViewOpenPDF= view.findViewById(R.id.imageViewOpenPDF);
        imageViewOpenPDF.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_menu_slideshow));

        if (dan==1) {
            switch (segment) {
                case 0:

                    txtWelcome.setText("Upoznajte se sa Mercator-om kroz kratak tekst koji sledi");
                    break;
                case 1:

                    txtWelcome.setText("Klikom na video upoznajte se sa misijom i programom IDEA Akademije");
                    break;
                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;


                default:

            }
        }

        view.findViewById(R.id.imageViewOpenPDF).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());
                        File file = null;

                        if (dan == 1) {
                            switch (segment) {
                                case 0:

                                    file = new File(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/MercatorSopstipodaci.pdf");
                                    if (file.exists()) {

                                        try {
                                            Uri path = Uri.fromFile(file);

                                            Intent intent = new Intent(Intent.ACTION_VIEW);
                                            intent.setDataAndType(path, "application/pdf");//pdf
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            startActivity(intent);
                                        } catch (ActivityNotFoundException ex) {
                                            String s = "";
                                        }
                                    }
                                    break;
                                case 1:


                                    file = new File(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/filmakademija.mp4");
                                    if (file.exists()) {

                                        try {
                                            Uri path = Uri.fromFile(file);

                                            Intent intent = new Intent(Intent.ACTION_VIEW);
                                            intent.setDataAndType(path, "video/mp4");//pdf
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            startActivity(intent);
                                        } catch (ActivityNotFoundException ex) {
                                            String s = "";
                                        }
                                    }

                                    break;
                                case 2:

                                    break;

                                case 3:

                                    break;

                                case 4:

                                    break;

                                case 5:

                                    break;


                                default:


                            }
                        }
                    }


                });


        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try
                        {

                            dbHelper.openDataBase();
                            ContentValues iuValues = new ContentValues();
                            iuValues.put("Segment", segment+1);
                            iuValues.put("Dan", 1);
                            iuValues.put("Zapocet", 1);
                            dbHelper.myDataBase.insertOrThrow("AkcijaSegment",null,iuValues);
                        }
                        catch (Exception ex)
                        {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("GRESKA!   "+ex.getMessage())
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();
                        }
                        finally {

                            dbHelper.close();
                        }
                        BaseActivity.segment=+1;

                        getActivity().onBackPressed();

                    }
                });
    }
}