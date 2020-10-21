package rs.merkator.merkatorgejmifikacija;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ApplicationDressCodeFragment extends Fragment {

    ImageView   imagesap_logo, imagewms, imagep_hubie, imagesky_rack_logo, imageibm_cognos;


    TextView txtText1,txtText2,txtText3,txtText4,txtText5;
    DragLinearLayout dragLinearLayout;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_application_dress_code, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtText1=view.findViewById(R.id.txtText1);
        txtText2=view.findViewById(R.id.txtText2);
        txtText3=view.findViewById(R.id.txtText3);
        txtText4=view.findViewById(R.id.txtText4);
        txtText5=view.findViewById(R.id.txtText5);

        imagesap_logo=view.findViewById(R.id.imageView2);
        imagewms=view.findViewById(R.id.imageView3);;
        imagep_hubie=view.findViewById(R.id.imageView4);;
        imagesky_rack_logo=view.findViewById(R.id.imageView5);;
        imageibm_cognos=view.findViewById(R.id.imageView6);

        dragLinearLayout = (DragLinearLayout) view.findViewById(R.id.container);
        for(int i = 0; i < dragLinearLayout.getChildCount(); i++){
            View child = dragLinearLayout.getChildAt(i);
            // the child will act as its own drag handle
            dragLinearLayout.setViewDraggable(child, child);
        }


        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean res=true;
                        for(int i = 0; i < dragLinearLayout.getChildCount(); i++){
                            View child = dragLinearLayout.getChildAt(i);
                            if (i==1 )
                            {
                                if (child instanceof TextView)
                                {
                                    TextView txt = (TextView) child;
                                    if (!txt.getText().toString().equals("TRADING"))
                                    {
                                        res=false;

                                    }

                                }
                            }
                            if (i==3 )
                            {
                                if (child instanceof TextView)
                                {
                                    TextView txt = (TextView) child;
                                    if (!txt.getText().toString().equals("MALOPRODAJA"))
                                    {
                                        res=false;

                                    }

                                }
                            }
                            if (i==2 )
                            {
                                if (child instanceof TextView)
                                {
                                    TextView txt = (TextView) child;
                                    if (!txt.getText().toString().equals("TRANSPORT"))
                                    {
                                        res=false;

                                    }

                                }
                            }
                            if (i==4 )
                            {
                                if (child instanceof TextView)
                                {
                                    TextView txt = (TextView) child;
                                    if (!txt.getText().toString().equals("SKLADIŠTE"))
                                    {
                                        res=false;

                                    }

                                }
                            }
                            if (i==0 )
                            {
                                if (child instanceof TextView)
                                {
                                    TextView txt = (TextView) child;
                                    if (!txt.getText().toString().equals("FINANSIJE"))
                                    {
                                        res=false;

                                    }

                                }
                            }

                        }
                        if(res==true) {

                            DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
                            try {
                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Odgovoreno", "1");

                                dbHelper.myDataBase.updateWithOnConflict("Dan1", iuValues, "rowid=68" , null, SQLiteDatabase.CONFLICT_ROLLBACK);
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