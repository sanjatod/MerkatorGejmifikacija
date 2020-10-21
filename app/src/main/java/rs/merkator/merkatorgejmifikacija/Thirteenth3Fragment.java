package rs.merkator.merkatorgejmifikacija;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Thirteenth3Fragment extends Fragment {


    Dan dan;
    int d,s;



    private float xCoOrdinate, yCoOrdinate;
    private float xCoOrdinate1, yCoOrdinate2;

    FloatingActionButton btnNext;

    ImageView imgDay;


    private float xCurrentPos, yCurrentPos;
    RelativeLayout parentLayout ;
    LinearLayout rootView,firstParent,secondParent;

    float tx1,tx2 , tx3 , tx4;
    float ty1,ty2, ty3, ty4 ;
    TextView txt1, txt2,txt3,txt4,txtOsiguranje,txtKancelarijskiMaterijal,txtGas,txtLokali,txtPosiljka;
    EditText etxt1,etxt2,etxt3,etxt4,etxt5;



    public Thirteenth3Fragment(Dan dan, int d,int s)

    {
        this.dan=dan;
        this.d=d;
        this.s=s;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.movelayout1, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgDay = view.findViewById(R.id.imgDay);
        btnNext=(FloatingActionButton) view.findViewById(R.id.btnNext) ;

        TextView txtPitanja = view.findViewById(R.id.txtTextPitanja);
        txtPitanja.setText(dan.getTekstPitanja());

        etxt1 = view.findViewById(R.id.etxt1);
        etxt2 = view.findViewById(R.id.etxt2);
        etxt3 = view.findViewById(R.id.etxt3);
        etxt4 = view.findViewById(R.id.etxt4);
        etxt5 = view.findViewById(R.id.etxt5);

        txtOsiguranje=(TextView) view.findViewById(R.id.txtOsiguranje) ;
        txtKancelarijskiMaterijal=(TextView) view.findViewById(R.id.txtKancelarijskiMaterijal) ;
        txtGas=(TextView) view.findViewById(R.id.txtGas) ;
        txtLokali=(TextView) view.findViewById(R.id.txtLokali) ;
        txtPosiljka=(TextView) view.findViewById(R.id.txtPosiljka) ;


        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        if(etxt1.getText().toString().equals("1")&& etxt2.getText().toString().equals("4")&&etxt3.getText().toString().equals("4")&&etxt4.getText().toString().equals("2")&& etxt5.getText().toString().equals("3") ) {
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