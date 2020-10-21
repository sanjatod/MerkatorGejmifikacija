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

public class EighthFragment extends Fragment {

    TextView txtPitanja;
    Dan dan;
    DataBaseHelper dbHelper;
    EditText et1,et2,et3,et4;
    ImageView imageViewSlikaNaPitanju;
    ViewGroup container;
    View view ;
    LinearLayout linearLayout ;
    int d,s;
    ImageView iv1, iv2, iv3, iv4;

    public EighthFragment(Dan dan, int d,int s)

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
        view=inflater.inflate(R.layout.fragment_eighth, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        et1 = view.findViewById(R.id.et1);
        et2 = view.findViewById(R.id.et2);
        et3 = view.findViewById(R.id.et3);
        et4 = view.findViewById(R.id.et4);
        iv1 = view.findViewById(R.id.iv1);
        Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/ideaorganic.png");
        if (bmp!=null) {
            iv1.setImageBitmap(bmp);
        }
        else
        {
            iv1.setVisibility(View.GONE);
        }
        iv2 = view.findViewById(R.id.iv2);
         bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/idea logo.jpg");
        if (bmp!=null) {
            iv2.setImageBitmap(bmp);
        }
        else
        {
            iv2.setVisibility(View.GONE);
        }
        iv3 = view.findViewById(R.id.iv3);
        bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/Picture3.jpg");
        if (bmp!=null) {
            iv3.setImageBitmap(bmp);
        }
        else
        {
            iv3.setVisibility(View.GONE);
        }
        iv4 = view.findViewById(R.id.iv4);
        bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/roda-2 logo.png");
        if (bmp!=null) {
            iv4.setImageBitmap(bmp);
        }
        else
        {
            iv4.setVisibility(View.GONE);
        }
        txtPitanja.setText(dan.getTekstPitanja());
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
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
        });
    }



}