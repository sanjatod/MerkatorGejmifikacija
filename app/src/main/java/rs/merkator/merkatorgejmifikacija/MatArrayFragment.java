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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class MatArrayFragment extends Fragment {
    TextView txtPitanja;
    ImageView imageViewSlikaNaPitanju,imgDay;
    Dan dan;
    int d, s;
    DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
    EditText eTxtNumber;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mat_array, container, false);
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewSlikaNaPitanju = view.findViewById(R.id.imageView1);
        imgDay = view.findViewById(R.id.imgDay);
        eTxtNumber = view.findViewById(R.id.eTxtNumber);

       imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));



            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/Picture6.jpg") ;
            if (bmp != null) {
                imageViewSlikaNaPitanju.setImageBitmap(bmp);
            } else {
                imageViewSlikaNaPitanju.setVisibility(View.GONE);
            }



        txtPitanja = view.findViewById(R.id.txtTextPitanja);






        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (eTxtNumber.getText().toString().contains("40"))
                        {
                            dbHelper.openDataBase();
                            ContentValues iuValues = new ContentValues();
                            iuValues.put("Odgovoreno", "1");
                            dbHelper.myDataBase.updateWithOnConflict("Dan2", iuValues, "rowid=43" , null, SQLiteDatabase.CONFLICT_ROLLBACK);
                            dbHelper.close();
                            getActivity().onBackPressed();
                        }
                        else
                        {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Odgovor nije tačan!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            eTxtNumber.setFocusable(true);
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();
                        }





                    }
                });

    }

}