package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContinueArrayFragment extends Fragment {
    TextView txtPitanja;
    ImageView imageViewSlikaNaPitanju,imgDay;
    Dan dan;
    int d, s;
    DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
    Button btnNext, btn1,btn2, btn3,btn4,btn5,btn6;
    int buttonChack=0;
    LinearLayout linearLayout;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_continue_array, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = view.findViewById(R.id.linear);
        imageViewSlikaNaPitanju = view.findViewById(R.id.imageView1);
        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));
        btn1= (Button) view.findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonChack=1;
                changeBackground(btn1);
            }
        });
        setBackground(btn1);
        btn2= (Button) view.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonChack=2;
                changeBackground(btn2);
            }
        });

        setBackground(btn2);
        btn3= (Button) view.findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonChack=3;
                changeBackground(btn3);
            }
        });

        setBackground(btn3);
        btn4= (Button) view.findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonChack=4;
                changeBackground(btn4);
            }
        });

        setBackground(btn4);
        btn5= (Button) view.findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonChack=5;
                changeBackground(btn5);
            }
        });

        setBackground(btn5);
        btn6= (Button) view.findViewById(R.id.button6);
        setBackground(btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonChack=6;
                changeBackground(btn6);
            }
        });



            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/taskcontiniuarray.png" );
            if (bmp != null) {
                imageViewSlikaNaPitanju.setImageBitmap(bmp);
            } else {
                imageViewSlikaNaPitanju.setVisibility(View.GONE);
            }



        txtPitanja = view.findViewById(R.id.txtTextPitanja);

            txtPitanja.setText("ZADATAK \n U prvom redu ispod nalaze se četiri kvadrata poređana po određenom pravilu. Koji od šest kvadrata iz drugog reda nastavlja niz po pravilu? Obeležite tačan odgovor klikom na odgovarajući broj");

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (buttonChack==3) {
                    BaseActivity.continueArray=true;
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
    private void   setBackground(Button btn) {
        btn.setBackgroundColor(Color.WHITE);
        btn.setGravity(Gravity.CENTER);
        btn.setTextColor(getResources().getColor(R.color.red));
    }

    private void   changeBackground(Button btn)
    {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            if (linearLayout.getChildAt(i) instanceof Button) {
                    ((Button) linearLayout.getChildAt(i)).setBackgroundColor(Color.WHITE);
                    ((Button) linearLayout.getChildAt(i)).setTextColor(Color.WHITE);
                    ((Button) linearLayout.getChildAt(i)).setTextColor(getResources().getColor(R.color.red));

            }
        }
        btn.setBackgroundColor(getResources().getColor(R.color.red));
        btn.setGravity(Gravity.CENTER);
        btn.setTextColor(Color.WHITE);
    }
    private View.OnClickListener handleClick = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn1:
                    buttonChack=1;
                    changeBackground(btn1);
                    break;
                case R.id.button2:
                    buttonChack=2;
                    changeBackground(btn2);
                    break;
                case R.id.button3:
                    buttonChack=3;
                    changeBackground(btn3);
                    break;
                case R.id.button4:
                    buttonChack=4;
                    changeBackground(btn4);
                    break;
                case R.id.button5:
                    buttonChack=5;
                    changeBackground(btn5);
                    break;
                case R.id.button6:
                    buttonChack=6;
                    changeBackground(btn6);
                    break;
            }
        }
    };

}