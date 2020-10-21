package rs.merkator.merkatorgejmifikacija;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KodeksFragment extends Fragment {

    ImageView imageViewOpenPDF;
    private int dan;
    private int segment;
    DataBaseHelper dbHelper;
    FloatingActionButton btnNext;
    Cursor c1;
    public KodeksFragment(int dan, int segment)
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
        return inflater.inflate(R.layout.kodeks_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHelper=new DataBaseHelper(getActivity());
        btnNext= view.findViewById(R.id.btnNext);
        // provera da li je kodeks oblacenja vec zavrsen
        dbHelper.openDataBase();
        String sql ="select Stanje from Kodeks ";
         c1 = dbHelper.myDataBase.rawQuery(sql, null);

         c1.moveToFirst();
         if (c1.getCount()>0)
         if(!c1.isNull(0)&& c1.getInt(0)==2) btnNext.setVisibility(View.GONE);


        dbHelper.close();

        view.findViewById(R.id.imageViewOpenPDF).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());


                        File file = null;
                        file = new File(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/kodeks.pdf");
                        //Toast.makeText(getApplicationContext(), file.toString() , Toast.LENGTH_LONG).show();
                        if (file.exists()) {

                            try {
                                Uri path = Uri.fromFile(file);

                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setDataAndType(path, "application/pdf");//pdf
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            } catch (ActivityNotFoundException ex)
                            {
                                    String s="";
                            }
                            }



//                            Intent target = new Intent(Intent.ACTION_VIEW);
//                            target.setDataAndType(Uri.fromFile(file), "application/pdf");
//                            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//                            Intent intent = Intent.createChooser(target, "Open File");
//                            try {
//                                startActivity(intent);
//                            } catch (ActivityNotFoundException e) {
//                                // Instruct the user to install a PDF reader here, or something
//                            }


                    }
                });


        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                        WomenDressCodeFragment w1 = new WomenDressCodeFragment(dan,segment);
                        ft1.replace(R.id.fragment_frame, w1);
                        ft1.commit();
                    }
                });
    }


}