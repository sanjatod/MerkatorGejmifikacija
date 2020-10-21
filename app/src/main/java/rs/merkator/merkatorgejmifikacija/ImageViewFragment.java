package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ImageViewFragment extends Fragment {

    ImageView imageView;
    DataBaseHelper dbHelper;

    int d,s;

    public ImageViewFragment( int d,int s)

    {

        this.d=d;
        this.s=s;
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_view, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.imageView1);
            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/logistika.png");
            if (bmp!=null) {
                imageView.setImageBitmap(bmp);
            }
            else
            {
                imageView.setVisibility(View.GONE);
            }



        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try
                        {
                            dbHelper = new DataBaseHelper(getActivity());
                            dbHelper.openDataBase();
                            ContentValues iuValues = new ContentValues();
                            iuValues.put("Segment", 1);
                            iuValues.put("Dan", 3);
                            iuValues.put("Zapocet", 1);
                            dbHelper.myDataBase.insertOrThrow("AkcijaSegment",null,iuValues);
                        }
                        catch (Exception ex)
                        {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("GRESKA!   "+ex.getMessage())
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();
                        }
                        finally {

                            dbHelper.close();
                        }
                        BaseActivity.segment=+1;

                        getActivity().onBackPressed();


                    }
                });

    }



}