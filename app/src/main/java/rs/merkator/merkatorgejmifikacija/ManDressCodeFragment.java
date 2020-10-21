package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.util.List;


public class ManDressCodeFragment extends Fragment {

    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7;
    private int dan;
    private int segment;
    DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
    public ManDressCodeFragment(int dan, int segment)
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
        return inflater.inflate(R.layout.fragment_man_dress_code, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
//        PuzzleFragment w1 = new PuzzleFragment(dan,segment);
//        ft1.replace(R.id.fragment_frame, w1);
//        ft1.commit();
        cb1=view.findViewById(R.id.checkBox1);
        cb2=view.findViewById(R.id.checkBox2);
        cb3=view.findViewById(R.id.checkBox3);
        cb4=view.findViewById(R.id.checkBox4);
        cb5=view.findViewById(R.id.checkBox5);
        cb6=view.findViewById(R.id.checkBox6);
        cb7=view.findViewById(R.id.checkBox7);

        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (!cb1.isChecked()&&!cb2.isChecked()&&!cb3.isChecked()&&cb4.isChecked()
                                &&cb5.isChecked()&&!cb6.isChecked()&&!cb7.isChecked())
                        {
                            dbHelper.openDataBase();
                            ContentValues iuValues = new ContentValues();
                            iuValues.put("Stanje", "2");

                            dbHelper.myDataBase.updateWithOnConflict("Kodeks", iuValues,null, null, SQLiteDatabase.CONFLICT_ROLLBACK);


                            new AlertDialog.Builder(getContext())
                                    .setTitle("Čestitamo, uspešno ste završili segment Kodeks oblačenja")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            getActivity().onBackPressed();
                                            dialog.dismiss();


                                        }
                                    })

                                    .show();

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