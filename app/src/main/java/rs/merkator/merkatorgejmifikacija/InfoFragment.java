package rs.merkator.merkatorgejmifikacija;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

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

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class InfoFragment extends AppCompatActivity {
    TextView txtPitanja;
    Dan dan;
    DataBaseHelper dbHelper;
    EditText etxtOdgovor;
    ImageView imageViewSlikaNaPitanju, imgDay;
    ViewGroup container;
    LinearLayout linearLayout ;
    int d,s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info);

        imageViewSlikaNaPitanju = findViewById(R.id.imageView1);
        imgDay = findViewById(R.id.imgDay);
        imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        imageViewSlikaNaPitanju.setVisibility(View.GONE);

        etxtOdgovor = findViewById(R.id.etxtOdgovor);

        etxtOdgovor.requestFocus();
        txtPitanja = findViewById(R.id.txtTextPitanja);
        txtPitanja.setText("Idite do info pulta i raspitajte se gde je kancelarija menadžera objekta Roda Mega 428. Upišite ime i prezime osobe koja vam je pomogla:");
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                Intent intent = new Intent(InfoFragment.this, BaseActivity.class);
                startActivity(intent);
            }


        });
    }


}