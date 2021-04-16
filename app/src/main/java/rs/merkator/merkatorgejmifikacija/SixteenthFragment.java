package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SixteenthFragment extends Fragment {
    RecyclerView checktextGrid;
    Dan dan;
    TextView txtPitanja;
   // List<FourthFragment.OdgovorTipa4> listaOdgovora;
    ImageView imageViewSlikaNaPitanju,imgDay;
    View dialogView1;
    TextView txtCestitamo;
    int d,s;
    boolean rez=false;

    public SixteenthFragment(Dan dan, int d, int s)

    {
        this.dan=dan;
        this.d=d;
        this.s=s;

    }


    // TODO: Rename and change types and number of parameters
//    public static ThirdFragment newInstance(String param1, String param2) {
//        ThirdFragment fragment = new ThirdFragment(dan1);
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dialogView1= inflater.inflate(R.layout.alertdialog1, null);
        // Inflate the layout for this fragment



            return inflater.inflate(R.layout.fragment_sixteenth, container, false);


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));




        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(rez) {
                            DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
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

                            } catch (SQLiteException ex) {
                                String s = "";
                            } finally {

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
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();

                        }
                    }
                });
    }


}