package rs.merkator.merkatorgejmifikacija;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class WomenDressCodeFragment extends Fragment  {

    private int dan;
    private int segment;

    DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
    DragLinearLayout dragLinearLayout;

    public WomenDressCodeFragment(int dan, int segment)
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
        return inflater.inflate(R.layout.fragment_women_dress_code, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bottom_container


        dragLinearLayout = (DragLinearLayout) view.findViewById(R.id.container);
        for(int i = 0; i < dragLinearLayout.getChildCount(); i++){
            View child = dragLinearLayout.getChildAt(i);
            dragLinearLayout.setViewDraggable(child, child);
        }


        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean res=true;
                        for(int i = 0; i < dragLinearLayout.getChildCount(); i++){
                            View child = dragLinearLayout.getChildAt(i);
                            if (i==0 )
                            {
                                if (child instanceof TextView)
                                {
                                    TextView txt = (TextView) child;
                                    if (!txt.getText().toString().equals("CASUAL FRIDAY"))
                                    {
                                        res=false;

                                    }

                                }
                            }
                            if (i==1 )
                            {
                                if (child instanceof TextView)
                                {
                                    TextView txt = (TextView) child;
                                    if (!txt.getText().toString().equals("BUSINESS CASUAL"))
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
                                    if (!txt.getText().toString().equals("BUSINESS"))
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
                                    if (!txt.getText().toString().equals("CASUAL FRIDAY"))
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
                                    if (!txt.getText().toString().equals("NON-FORMAL"))
                                    {
                                        res=false;

                                    }

                                }
                            }

                        }
                        if(res==true) {
                            dbHelper.openDataBase();

                            ContentValues iuValues = new ContentValues();
                            iuValues.put("Stanje", "1");
                            dbHelper.myDataBase.insert("Kodeks", null,iuValues);

                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                            ManDressCodeFragment w1 = new ManDressCodeFragment(dan,segment);
                            ft1.replace(R.id.fragment_frame, w1);
                            ft1.commit();
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