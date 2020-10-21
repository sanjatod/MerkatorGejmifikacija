package rs.merkator.merkatorgejmifikacija;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.Selection;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThirteenthFragment extends Fragment {


    Dan dan;
    int d,s;



    private float xCoOrdinate, yCoOrdinate;
    private float xCoOrdinate1, yCoOrdinate2;
    private boolean p1=false,p2=false,p3=false;

    FloatingActionButton btnNext;
    ImageView imgDay;


    private float xCurrentPos, yCurrentPos;
    RelativeLayout parentLayout ;
    LinearLayout rootView,firstParent,secondParent;

    float tx1,tx2 , tx3 ;
    float ty1,ty2, ty3 ;
    TextView txtPut1, txtPut2, txtPut3, txtEbitda, txtAmortizacija,txtBrutoMarza;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.movelayout, container, false);
    }

    public ThirteenthFragment(Dan dan, int d,int s)

    {
        this.dan=dan;
        this.d=d;
        this.s=s;
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgDay = view.findViewById(R.id.imgDay);
        btnNext=(FloatingActionButton) view.findViewById(R.id.btnNext) ;

        TextView txtPitanja = view.findViewById(R.id.txtTextPitanja);
        txtPitanja.setText(dan.getTekstPitanja());

        txtPut1=(TextView) view.findViewById(R.id.txtPut1) ;
        txtPut2=(TextView) view.findViewById(R.id.txtPut2) ;
        txtPut3=(TextView) view.findViewById(R.id.txtPut3) ;

        txtEbitda=(TextView) view.findViewById(R.id.txtEbitda) ;
        txtAmortizacija=(TextView) view.findViewById(R.id.txtAmortizacija) ;
        txtBrutoMarza=(TextView) view.findViewById(R.id.txtBrutoMarza) ;

        txtBrutoMarza.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked())
                {

                    case MotionEvent.ACTION_DOWN:

                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();

                        xCoOrdinate1 = txtBrutoMarza.getX();
                        yCoOrdinate2 =  txtBrutoMarza.getY();

                        tx1 = txtPut1.getX();
                        ty1 = txtPut1.getY();

                        tx2 = txtPut2.getX();
                        ty2 = txtPut2.getY();

                        tx3 = txtPut3.getX();
                        ty3 = txtPut3.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();


                        break;

                    case MotionEvent.ACTION_UP:

                        if (tx1+10>event.getRawX()+ xCoOrdinate && ty1+10>event.getRawY() + yCoOrdinate ) {
                            ObjectAnimator animX = ObjectAnimator.ofFloat(txtBrutoMarza, "x", tx1);//tx-45
                            ObjectAnimator animY = ObjectAnimator.ofFloat(txtBrutoMarza, "y", ty1);//ty-134
                            AnimatorSet animSetXY = new AnimatorSet();
                            animSetXY.playTogether(animX, animY);
                            animSetXY.start();

                            ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut1, "x", xCoOrdinate1);//tx-45
                            ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut1, "y", yCoOrdinate2);//ty-134
                            AnimatorSet animSetXY1 = new AnimatorSet();
                            animSetXY1.playTogether(animX1, animY1);
                            animSetXY1.start();
                            p1=true;

                        }
                        else
                        {
//                            if (tx2+10>event.getRawX()+ xCoOrdinate && ty2+10>event.getRawY() + yCoOrdinate) {
//                                ObjectAnimator animX = ObjectAnimator.ofFloat(txtBrutoMarza, "x", tx2);//tx-45
//                                ObjectAnimator animY = ObjectAnimator.ofFloat(txtBrutoMarza, "y", ty2);//ty-134
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
//                            else
//                            {
//                                if (tx3+10>event.getRawX()+ xCoOrdinate && ty3+10>event.getRawY() + yCoOrdinate) {
//                                    ObjectAnimator animX = ObjectAnimator.ofFloat(txtBrutoMarza, "x", tx3);//tx-45
//                                    ObjectAnimator animY = ObjectAnimator.ofFloat(txtBrutoMarza, "y", ty3);//ty-134
//                                    AnimatorSet animSetXY = new AnimatorSet();
//                                    animSetXY.playTogether(animX, animY);
//                                    animSetXY.start();
//
//                                    ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut3, "x", xCoOrdinate1);//tx-45
//                                    ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut3, "y", yCoOrdinate2);//ty-134
//                                    AnimatorSet animSetXY1 = new AnimatorSet();
//                                    animSetXY1.playTogether(animX1, animY1);
//                                    animSetXY1.start();
//
//
//                                }
//                                else {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Odgovor nije tačan!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtBrutoMarza, "x", xCoOrdinate1);//tx-45
                                            ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtBrutoMarza, "y", yCoOrdinate2);//ty-134
                                            AnimatorSet animSetXY1 = new AnimatorSet();
                                            animSetXY1.playTogether(animX1, animY1);
                                            animSetXY1.start();
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();

                                //}
                            //}
                        }




                }
                return true;
            }
        });

        txtEbitda.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked())
                {

                    case MotionEvent.ACTION_DOWN:

                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();

                        xCoOrdinate1 = txtEbitda.getX();
                        yCoOrdinate2 =  txtEbitda.getY();

                        tx1 = txtPut1.getX();
                        ty1 = txtPut1.getY();

                        tx2 = txtPut2.getX();
                        ty2 = txtPut2.getY();

                        tx3 = txtPut3.getX();
                        ty3 = txtPut3.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();


                        break;

                    case MotionEvent.ACTION_UP:
//
//                        if (tx1+10>event.getRawX()+ xCoOrdinate && ty1+10>event.getRawY() + yCoOrdinate ) {
//                            ObjectAnimator animX = ObjectAnimator.ofFloat(txtEbitda, "x", tx1);//tx-45
//                            ObjectAnimator animY = ObjectAnimator.ofFloat(txtEbitda, "y", ty1);//ty-134
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
//
//
//
//                        }
//                        else
//                        {
//                            if (tx2+10>event.getRawX()+ xCoOrdinate && ty2+10>event.getRawY() + yCoOrdinate) {
//                                ObjectAnimator animX = ObjectAnimator.ofFloat(txtEbitda, "x", tx2);//tx-45
//                                ObjectAnimator animY = ObjectAnimator.ofFloat(txtEbitda, "y", ty2);//ty-134
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
//                            else
//                            {
                                if (tx3+10>event.getRawX()+ xCoOrdinate && ty3+10>event.getRawY() + yCoOrdinate) {
                                    ObjectAnimator animX = ObjectAnimator.ofFloat(txtEbitda, "x", tx3);//tx-45
                                    ObjectAnimator animY = ObjectAnimator.ofFloat(txtEbitda, "y", ty3);//ty-134
                                    AnimatorSet animSetXY = new AnimatorSet();
                                    animSetXY.playTogether(animX, animY);
                                    animSetXY.start();

                                    ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut3, "x", xCoOrdinate1);//tx-45
                                    ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut3, "y", yCoOrdinate2);//ty-134
                                    AnimatorSet animSetXY1 = new AnimatorSet();
                                    animSetXY1.playTogether(animX1, animY1);
                                    animSetXY1.start();
                                    p2=true;


                                }
                                else {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("Odgovor nije tačan!")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtEbitda, "x", xCoOrdinate1);//tx-45
                                                    ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtEbitda, "y", yCoOrdinate2);//ty-134
                                                    AnimatorSet animSetXY1 = new AnimatorSet();
                                                    animSetXY1.playTogether(animX1, animY1);
                                                    animSetXY1.start();
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();

                                }
                            //}
                      //  }




                }
                return true;
            }
        });

        txtAmortizacija.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked())
                {

                    case MotionEvent.ACTION_DOWN:

                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();

                        xCoOrdinate1 = txtAmortizacija.getX();
                        yCoOrdinate2 =  txtAmortizacija.getY();

                        tx1 = txtPut1.getX();
                        ty1 = txtPut1.getY();

                        tx2 = txtPut2.getX();
                        ty2 = txtPut2.getY();

                        tx3 = txtPut3.getX();
                        ty3 = txtPut3.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();


                        break;

                    case MotionEvent.ACTION_UP:

//                        if (tx1+10>event.getRawX()+ xCoOrdinate && ty1+10>event.getRawY() + yCoOrdinate ) {
//                            ObjectAnimator animX = ObjectAnimator.ofFloat(txtAmortizacija, "x", tx1);//tx-45
//                            ObjectAnimator animY = ObjectAnimator.ofFloat(txtAmortizacija, "y", ty1);//ty-134
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
//
//
//
//                        }
//                        else
//                        {
                            if (tx2+10>event.getRawX()+ xCoOrdinate && ty2+10>event.getRawY() + yCoOrdinate) {
                                ObjectAnimator animX = ObjectAnimator.ofFloat(txtAmortizacija, "x", tx2);//tx-45
                                ObjectAnimator animY = ObjectAnimator.ofFloat(txtAmortizacija, "y", ty2);//ty-134
                                AnimatorSet animSetXY = new AnimatorSet();
                                animSetXY.playTogether(animX, animY);
                                animSetXY.start();

                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut2, "x", xCoOrdinate1);//tx-45
                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut2, "y", yCoOrdinate2);//ty-134
                                AnimatorSet animSetXY1 = new AnimatorSet();
                                animSetXY1.playTogether(animX1, animY1);
                                animSetXY1.start();
                                p3=true;



//                            }
//                            else
//                            {
//                                if (tx3+10>event.getRawX()+ xCoOrdinate && ty3+10>event.getRawY() + yCoOrdinate) {
//                                    ObjectAnimator animX = ObjectAnimator.ofFloat(txtAmortizacija, "x", tx3);//tx-45
//                                    ObjectAnimator animY = ObjectAnimator.ofFloat(txtAmortizacija, "y", ty3);//ty-134
//                                    AnimatorSet animSetXY = new AnimatorSet();
//                                    animSetXY.playTogether(animX, animY);
//                                    animSetXY.start();
//
//                                    ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtPut3, "x", xCoOrdinate1);//tx-45
//                                    ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtPut3, "y", yCoOrdinate2);//ty-134
//                                    AnimatorSet animSetXY1 = new AnimatorSet();
//                                    animSetXY1.playTogether(animX1, animY1);
//                                    animSetXY1.start();
//
//


                                }
                                else {
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("Odgovor nije tačan!")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ObjectAnimator animX1 = ObjectAnimator.ofFloat(txtAmortizacija, "x", xCoOrdinate1);//tx-45
                                                ObjectAnimator animY1 = ObjectAnimator.ofFloat(txtAmortizacija, "y", yCoOrdinate2);//ty-134
                                                AnimatorSet animSetXY1 = new AnimatorSet();
                                                animSetXY1.playTogether(animX1, animY1);
                                                animSetXY1.start();
                                                dialog.dismiss();
                                            }
                                        })

                                        .show();



                                }
                          //  }
                      //  }




                }
                return true;
            }
        });

        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (p1 == true && p2 == true && p3 == true) {
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
                        } else {
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