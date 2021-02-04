package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;


public class TaskDecimalNumberPercentageFragment extends Fragment {
    TextView txtPitanja;
    EditText etxt1,etxt2,etxt3,etxt4,etxt5,etxt6,etxt7;

    Dan dan;
    int d, s;
    DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
    FloatingActionButton btnNext;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_decimal_number_percentage, container, false);
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        etxt1 = view.findViewById(R.id.etxt1);
        etxt2 = view.findViewById(R.id.etxt2);
        etxt3 = view.findViewById(R.id.etxt3);
        etxt4 = view.findViewById(R.id.etxt4);
        etxt5 = view.findViewById(R.id.etxt5);
        etxt6 = view.findViewById(R.id.etxt6);

        txtPitanja.setText("ZADATAK\n U tabeli ispod dopunite decimalne brojeve, procenat ili razlomak koji nedostaju.");

        btnNext = view.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        BaseActivity.proc=true;
                        getActivity().onBackPressed();

//                        if(etxt1.getText().toString().equals("0.8")
//                                && etxt2.getText().toString().equals("0.05")
//                                && etxt3.getText().toString().equals("75%")
//                                && etxt4.getText().toString().equals("40%")
//                                && etxt5.getText().toString().equals("5%")
//                                && (etxt6.getText().toString().equals("8/10")||etxt6.getText().toString().equals("4/5"))
//                                && (etxt7.getText().toString().equals("4/10")||etxt7.getText().toString().equals("2/5"))
//                        )
//                        {
//                            BaseActivity.proc=true;
//                            getActivity().onBackPressed();
//                        }
//                        else
//                        {
//                            new AlertDialog.Builder(getActivity())
//                                    .setTitle("Odgovor nije tačan!")
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int whichButton) {
//                                            //etxtOdgovor.setFocusable(true);
//                                            dialog.dismiss();
//                                        }
//                                    })
//
//                                    .show();
//                        }



                    }
                });

    }

}