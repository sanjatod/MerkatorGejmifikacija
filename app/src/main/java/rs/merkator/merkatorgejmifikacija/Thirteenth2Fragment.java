package rs.merkator.merkatorgejmifikacija;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Thirteenth2Fragment extends Fragment {


    Dan dan;
    int d,s;



    private float xCoOrdinate, yCoOrdinate;
    private float xCoOrdinate1, yCoOrdinate2;

    FloatingActionButton btnNext;

    ImageView imgDay;

    float tx1,tx2 , tx3 ;
    float ty1,ty2, ty3 ;
    TextView txtPut1, txtPut2, txtSPA, txtSK, txtSMT,txtOK,txtSPKI;

    boolean sluzbaMreza=false;
    boolean sluzbaPOS=false;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thirteenth2, container, false);
    }

    public Thirteenth2Fragment(Dan dan, int d,int s)

    {
        this.dan=dan;
        this.d=d;
        this.s=s;
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgDay = view.findViewById(R.id.imgDay);
        imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        btnNext=(FloatingActionButton) view.findViewById(R.id.btnNext) ;

        TextView txtPitanja = view.findViewById(R.id.txtTextPitanja);
        //txtPitanja.setText(dan.getTekstPitanja());

        txtPut1=(TextView) view.findViewById(R.id.txt2) ;
        txtPut2=(TextView) view.findViewById(R.id.txt4) ;

        txtSPA=(TextView) view.findViewById(R.id.txtSPA) ;
        txtSK=(TextView) view.findViewById(R.id.txtSK) ;
        txtSMT=(TextView) view.findViewById(R.id.txtSMT) ;
        txtOK=(TextView) view.findViewById(R.id.txtSOK) ;
        txtSPKI=(TextView) view.findViewById(R.id.txtSPKI) ;




        txtSPA.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked())
                {

                    case MotionEvent.ACTION_DOWN:

                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();

                        xCoOrdinate1 = txtSPA.getX();
                        yCoOrdinate2 =  txtSPA.getY();

                        tx1 = txtPut1.getX();
                        ty1 = txtPut1.getY();

                        tx2 = txtPut2.getX();
                        ty2 = txtPut2.getY();



                        break;
                    case MotionEvent.ACTION_MOVE:

                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();


                        break;

                    case MotionEvent.ACTION_UP:

//                        if (tx1+10>event.getRawX()+ xCoOrdinate && ty1+10>event.getRawY() + yCoOrdinate ) {
//                            ObjectAnimator animX = ObjectAnimator.ofFloat(txtSPA, "x", tx1);//tx-45
//                            ObjectAnimator animY = ObjectAnimator.ofFloat(txtSPA, "y", ty1);//ty-134
//                            AnimatorSet animSetXY = new AnimatorSet();
//                            animSetXY.playTogether(animX, animY);
//                            animSetXY.start();
//
//                            ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut1, "x", xCoOrdinate1);//tx-45
//                            ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut1, "y", yCoOrdinate2);//ty-134
//                            AnimatorSet animSetXY1 = new AnimatorSet();
//                            animSetXY1.playTogether(animX1, animY1);
//                            animSetXY1.start();
//
//                        }
//                        else
//                        {
//                            if (tx2+10>event.getRawX()+ xCoOrdinate && ty2+10>event.getRawY() + yCoOrdinate) {
//                                ObjectAnimator animX = ObjectAnimator.ofFloat(txtSPA, "x", tx2);//tx-45
//                                ObjectAnimator animY = ObjectAnimator.ofFloat(txtSPA, "y", ty2);//ty-134
//                                AnimatorSet animSetXY = new AnimatorSet();
//                                animSetXY.playTogether(animX, animY);
//                                animSetXY.start();
//
//                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut2, "x", xCoOrdinate1);//tx-45
//                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut2, "y", yCoOrdinate2);//ty-134
//                                AnimatorSet animSetXY1 = new AnimatorSet();
//                                animSetXY1.playTogether(animX1, animY1);
//                                animSetXY1.start();
//
//
//                            }
//
//                            else {
                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtSPA, "x", xCoOrdinate1);//tx-45
                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtSPA, "y", yCoOrdinate2);//ty-134
                                AnimatorSet animSetXY1 = new AnimatorSet();
                                animSetXY1.playTogether(animX1, animY1);
                                animSetXY1.start();
                                PogresanOdgovor();
                         //   }

                    //    }




                }
                return true;
            }
        });

        txtSK.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked())
                {

                    case MotionEvent.ACTION_DOWN:

                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();

                        xCoOrdinate1 = txtSK.getX();
                        yCoOrdinate2 =  txtSK.getY();

                        tx1 = txtPut1.getX();
                        ty1 = txtPut1.getY();

                        tx2 = txtPut2.getX();
                        ty2 = txtPut2.getY();



                        break;
                    case MotionEvent.ACTION_MOVE:

                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();


                        break;

                    case MotionEvent.ACTION_UP:

//                        if (tx1+10>event.getRawX()+ xCoOrdinate && ty1+10>event.getRawY() + yCoOrdinate ) {
//                            ObjectAnimator animX = ObjectAnimator.ofFloat(txtSK, "x", tx1);//tx-45
//                            ObjectAnimator animY = ObjectAnimator.ofFloat(txtSK, "y", ty1);//ty-134
//                            AnimatorSet animSetXY = new AnimatorSet();
//                            animSetXY.playTogether(animX, animY);
//                            animSetXY.start();
//
//                            ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut1, "x", xCoOrdinate1);//tx-45
//                            ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut1, "y", yCoOrdinate2);//ty-134
//                            AnimatorSet animSetXY1 = new AnimatorSet();
//                            animSetXY1.playTogether(animX1, animY1);
//                            animSetXY1.start();
//
//                        }
//                        else
//                        {
//                            if (tx2+10>event.getRawX()+ xCoOrdinate && ty2+10>event.getRawY() + yCoOrdinate) {
//                                ObjectAnimator animX = ObjectAnimator.ofFloat(txtSK, "x", tx2);//tx-45
//                                ObjectAnimator animY = ObjectAnimator.ofFloat(txtSK, "y", ty2);//ty-134
//                                AnimatorSet animSetXY = new AnimatorSet();
//                                animSetXY.playTogether(animX, animY);
//                                animSetXY.start();
//
//                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut2, "x", xCoOrdinate1);//tx-45
//                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut2, "y", yCoOrdinate2);//ty-134
//                                AnimatorSet animSetXY1 = new AnimatorSet();
//                                animSetXY1.playTogether(animX1, animY1);
//                                animSetXY1.start();
//
//
//                            }
//
//                            else {
                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtSK, "x", xCoOrdinate1);//tx-45
                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtSK, "y", yCoOrdinate2);//ty-134
                                AnimatorSet animSetXY1 = new AnimatorSet();
                                animSetXY1.playTogether(animX1, animY1);
                                animSetXY1.start();
                                PogresanOdgovor();
//                            }
//
//                        }




                }
                return true;
            }
        });

        txtSMT.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked())
                {

                    case MotionEvent.ACTION_DOWN:

                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();

                        xCoOrdinate1 = txtSMT.getX();
                        yCoOrdinate2 =  txtSMT.getY();

                        tx1 = txtPut1.getX();
                        ty1 = txtPut1.getY();

                        tx2 = txtPut2.getX();
                        ty2 = txtPut2.getY();



                        break;
                    case MotionEvent.ACTION_MOVE:

                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();


                        break;

                    case MotionEvent.ACTION_UP:

                        if (tx1+10>event.getRawX()+ xCoOrdinate && ty1+10>event.getRawY() + yCoOrdinate ) {
                            ObjectAnimator animX = ObjectAnimator.ofFloat(txtSMT, "x", tx1);//tx-45
                            ObjectAnimator animY = ObjectAnimator.ofFloat(txtSMT, "y", ty1);//ty-134
                            AnimatorSet animSetXY = new AnimatorSet();
                            animSetXY.playTogether(animX, animY);
                            animSetXY.start();

                            ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut1, "x", xCoOrdinate1);//tx-45
                            ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut1, "y", yCoOrdinate2);//ty-134
                            AnimatorSet animSetXY1 = new AnimatorSet();
                            animSetXY1.playTogether(animX1, animY1);
                            animSetXY1.start();

                            sluzbaMreza=true;

                        }
//                        else
//                        {
//                            if (tx2+10>event.getRawX()+ xCoOrdinate && ty2+10>event.getRawY() + yCoOrdinate) {
//                                ObjectAnimator animX = ObjectAnimator.ofFloat(txtSMT, "x", tx2);//tx-45
//                                ObjectAnimator animY = ObjectAnimator.ofFloat(txtSMT, "y", ty2);//ty-134
//                                AnimatorSet animSetXY = new AnimatorSet();
//                                animSetXY.playTogether(animX, animY);
//                                animSetXY.start();
//
//                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut2, "x", xCoOrdinate1);//tx-45
//                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut2, "y", yCoOrdinate2);//ty-134
//                                AnimatorSet animSetXY1 = new AnimatorSet();
//                                animSetXY1.playTogether(animX1, animY1);
//                                animSetXY1.start();
//
//
//                            }

                            else {
                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtSMT, "x", xCoOrdinate1);//tx-45
                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtSMT, "y", yCoOrdinate2);//ty-134
                                AnimatorSet animSetXY1 = new AnimatorSet();
                                animSetXY1.playTogether(animX1, animY1);
                                animSetXY1.start();
                            PogresanOdgovor();
                            }

                   //     }




                }
                return true;
            }
        });

        txtOK.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked())
                {

                    case MotionEvent.ACTION_DOWN:

                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();

                        xCoOrdinate1 = txtOK.getX();
                        yCoOrdinate2 =  txtOK.getY();

                        tx1 = txtPut1.getX();
                        ty1 = txtPut1.getY();

                        tx2 = txtPut2.getX();
                        ty2 = txtPut2.getY();



                        break;
                    case MotionEvent.ACTION_MOVE:

                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();


                        break;

                    case MotionEvent.ACTION_UP:

//                        if (tx1+10>event.getRawX()+ xCoOrdinate && ty1+10>event.getRawY() + yCoOrdinate ) {
//                            ObjectAnimator animX = ObjectAnimator.ofFloat(txtOK, "x", tx1);//tx-45
//                            ObjectAnimator animY = ObjectAnimator.ofFloat(txtOK, "y", ty1);//ty-134
//                            AnimatorSet animSetXY = new AnimatorSet();
//                            animSetXY.playTogether(animX, animY);
//                            animSetXY.start();
//
//                            ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut1, "x", xCoOrdinate1);//tx-45
//                            ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut1, "y", yCoOrdinate2);//ty-134
//                            AnimatorSet animSetXY1 = new AnimatorSet();
//                            animSetXY1.playTogether(animX1, animY1);
//                            animSetXY1.start();
//
//                        }
//                        else
//                        {
//                            if (tx2+10>event.getRawX()+ xCoOrdinate && ty2+10>event.getRawY() + yCoOrdinate) {
//                                ObjectAnimator animX = ObjectAnimator.ofFloat(txtOK, "x", tx2);//tx-45
//                                ObjectAnimator animY = ObjectAnimator.ofFloat(txtOK, "y", ty2);//ty-134
//                                AnimatorSet animSetXY = new AnimatorSet();
//                                animSetXY.playTogether(animX, animY);
//                                animSetXY.start();
//
//                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut2, "x", xCoOrdinate1);//tx-45
//                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut2, "y", yCoOrdinate2);//ty-134
//                                AnimatorSet animSetXY1 = new AnimatorSet();
//                                animSetXY1.playTogether(animX1, animY1);
//                                animSetXY1.start();
//
//
//                            }
//
//                            else {
                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtOK, "x", xCoOrdinate1);//tx-45
                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtOK, "y", yCoOrdinate2);//ty-134
                                AnimatorSet animSetXY1 = new AnimatorSet();
                                animSetXY1.playTogether(animX1, animY1);
                                animSetXY1.start();
                                PogresanOdgovor();
//                            }
//
//                        }




                }
                return true;
            }
        });

        txtSPKI.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked())
                {

                    case MotionEvent.ACTION_DOWN:

                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();

                        xCoOrdinate1 = txtSPKI.getX();
                        yCoOrdinate2 =  txtSPKI.getY();

                        tx1 = txtPut1.getX();
                        ty1 = txtPut1.getY();

                        tx2 = txtPut2.getX();
                        ty2 = txtPut2.getY();



                        break;
                    case MotionEvent.ACTION_MOVE:

                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();


                        break;

                    case MotionEvent.ACTION_UP:

//                        if (tx1+10>event.getRawX()+ xCoOrdinate && ty1+10>event.getRawY() + yCoOrdinate ) {
//                            ObjectAnimator animX = ObjectAnimator.ofFloat(txtSPKI, "x", tx1);//tx-45
//                            ObjectAnimator animY = ObjectAnimator.ofFloat(txtSPKI, "y", ty1);//ty-134
//                            AnimatorSet animSetXY = new AnimatorSet();
//                            animSetXY.playTogether(animX, animY);
//                            animSetXY.start();
//
//                            ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut1, "x", xCoOrdinate1);//tx-45
//                            ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut1, "y", yCoOrdinate2);//ty-134
//                            AnimatorSet animSetXY1 = new AnimatorSet();
//                            animSetXY1.playTogether(animX1, animY1);
//                            animSetXY1.start();
//
//                        }
//                        else
//                        {
                            if (tx2+10>event.getRawX()+ xCoOrdinate && ty2+10>event.getRawY() + yCoOrdinate) {
                                ObjectAnimator animX = ObjectAnimator.ofFloat(txtSPKI, "x", tx2);//tx-45
                                ObjectAnimator animY = ObjectAnimator.ofFloat(txtSPKI, "y", ty2);//ty-134
                                AnimatorSet animSetXY = new AnimatorSet();
                                animSetXY.playTogether(animX, animY);
                                animSetXY.start();

                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut2, "x", xCoOrdinate1);//tx-45
                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut2, "y", yCoOrdinate2);//ty-134
                                AnimatorSet animSetXY1 = new AnimatorSet();
                                animSetXY1.playTogether(animX1, animY1);
                                animSetXY1.start();
                                sluzbaPOS=true;


                            }

                            else {
                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtSPKI, "x", xCoOrdinate1);//tx-45
                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtSPKI, "y", yCoOrdinate2);//ty-134
                                AnimatorSet animSetXY1 = new AnimatorSet();
                                animSetXY1.playTogether(animX1, animY1);
                                animSetXY1.start();
                                PogresanOdgovor();
                            }

                       // }

                }
                return true;
            }
        });

        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        if (sluzbaMreza==true && sluzbaPOS==true) {
                            DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
                            try {
                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Odgovoreno", "1");
                                if (d == 1)
                                    dbHelper.myDataBase.updateWithOnConflict("Dan1", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                if (d == 2)
                                    dbHelper.myDataBase.updateWithOnConflict("Dan2", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                if (d == 3)
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


    private void PogresanOdgovor()
    {
        new AlertDialog.Builder(getActivity())
                .setTitle("Pogrešan odgovor!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })

                .show();
    }

}