package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

public class MatrixFragment extends Fragment {

    TextView txtPitanja;
    Dan dan;
    DataBaseHelper dbHelper;
    EditText etxtOdgovor;
    ImageView imageViewSlikaNaPitanju;
    ViewGroup container;
    View view ;
    LinearLayout linearLayout ;
    int rowNum=3;
    int colNum=3;
    GridLayout gridLayout1;
    GridLayout gridLayout2;
    static boolean uspesno=false;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        this.container=container;
        // Inflate the layout for this fragment
        dbHelper=new DataBaseHelper(getActivity());
        view=inflater.inflate(R.layout.fragment_matrix, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout rv = view.findViewById(R.id.rv);
        LinearLayout rv1 = view.findViewById(R.id.rv1);

        Intent passedIntent = getActivity().getIntent();
        Bundle extras = passedIntent.getExtras();

        EditText[][] editTexts = new EditText[rowNum][colNum];

        gridLayout1 = new GridLayout(getActivity());


        //define how many rows and columns to be used in the layout
        gridLayout1.setRowCount(rowNum);
        gridLayout1.setColumnCount(colNum);

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                editTexts[i][j] = new EditText(getActivity(),null,R.style.CustomEditTextStyle);
                editTexts[i][j].setBackgroundResource(R.drawable.my_border);
               // android:background="@drawable/my_border"
                int number=0;
                if (i==0 && j==0)number=7;
                if (i==0 && j==1)number=10;
                if (i==0 && j==2)number=-3;
                if (i==1 && j==0)number=4;
                if (i==1 && j==1)number=-99;
                if (i==1 && j==2)number=0;
                if (i==2 && j==0)number=1;
                if (i==2 && j==1)number=-2;
                if (i==2 && j==2)number=3;
                setPos(editTexts[i][j], i, j,number,1 );
                gridLayout1.addView(editTexts[i][j]);
            }
        }
        //getActivity().setContentView(gridLayout);
        rv.addView(gridLayout1);

         gridLayout2 = new GridLayout(getActivity());

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                editTexts[i][j] = new EditText(getActivity(),null,R.style.CustomEditTextStyle);
                editTexts[i][j].setBackgroundResource(R.drawable.my_border);
                int number=0;
                if (i==0 && j==0)number=3;
                if (i==0 && j==1)number=-99;
                if (i==0 && j==2)number=27;
                if (i==1 && j==0)number=-2;
                if (i==1 && j==1)number=4;
                if (i==1 && j==2)number=-8;
                if (i==2 && j==0)number=1;
                if (i==2 && j==1)number=-1;
                if (i==2 && j==2)number=-1;
                setPos(editTexts[i][j], i, j,number ,2);
                gridLayout2.addView(editTexts[i][j]);
            }
        }
        //getActivity().setContentView(gridLayout);
        rv1.addView(gridLayout2);

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                boolean res=true;
                for (int y = 0; y < gridLayout1.getChildCount(); y++) {
                    if (gridLayout1.getChildAt(y) instanceof EditText) {

                        if (((EditText) gridLayout1.getChildAt(y)).getTag() != null && ((EditText) gridLayout1.getChildAt(y)).getTag().equals("1")) {
                            if (!((EditText) gridLayout1.getChildAt(y)).getText().toString().equals("4")) {
                                res = false;
                            }
                        }

                    }
                }

                    for (int z = 0; z< gridLayout2.getChildCount(); z++) {
                        if (gridLayout2.getChildAt(z) instanceof EditText) {

                            if (((EditText) gridLayout2.getChildAt(z)).getTag() != null && ((EditText) gridLayout2.getChildAt(z)).getTag().equals("2")) {
                                if (!((EditText) gridLayout2.getChildAt(z)).getText().toString().equals("9")) {
                                    res = false;
                                }
                            }
                        }
                }
                    uspesno=res;

                if (res)
                {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Zadatak je uspešno završen")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Zapocet", 2);
                                    dbHelper.myDataBase.updateWithOnConflict("AkcijaSegment", iuValues, "Segment=1 AND  Dan=2" , null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                    dbHelper.close();
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

    private void setPos(EditText editText, int row, int column, int number, int matrixnumber) {
        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.width = 100;
        param.height = 100;
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(row);
        param.columnSpec = GridLayout.spec(column);
        editText.setEnabled(false);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setText(String.valueOf(number));
        editText.setSingleLine(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();

        editText.setGravity(Gravity.CENTER);

        if (number==-99)
        {
            editText.setEnabled(true);
            editText.setFocusable(true);
            if (matrixnumber==1)
                editText.setTag("1");
            if (matrixnumber==2)
                editText.setTag("2");
            editText.setText("");
        }
        editText.setLayoutParams(param);
    }

}