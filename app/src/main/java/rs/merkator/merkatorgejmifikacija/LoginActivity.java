package rs.merkator.merkatorgejmifikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ajts.androidmads.library.ExcelToSQLite;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    Fragment fragment ;
    DataBaseHelper dbHelper;
    TextInputEditText txtKorisnickoIme, txtLozinka;
    Context mContext;
    String korisnickoIme,lozinka, ime, prezime;
    String adminpass="1234";
    private final static int MY_PERMISSIONS_REQUEST_WRITE = 200;
    private final static int MY_PERMISSIONS_REQUEST_READ = 201;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper=new DataBaseHelper(this);
        mContext=this;
        AdminPanel.mContext=this;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE);

                // MY_PERMISSIONS_REQUEST_WRITE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ);

                // MY_PERMISSIONS_REQUEST_WRITE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }





        Button button = (Button) findViewById(R.id.btnLogin);
        txtKorisnickoIme =(TextInputEditText) findViewById(R.id.etUsername);
        txtLozinka =(TextInputEditText) findViewById(R.id.etPassword);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {



                    if (!dbHelper.checkDataBase()) {

                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Baza je prazna, morate sačekati import podataka")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        YourAsyncTask s = new YourAsyncTask(LoginActivity.this);
                                        s.execute();

                                        dialog.dismiss();
                                    }
                                })

                                .show();


                    } else {


                        try {
                            korisnickoIme = txtKorisnickoIme.getText().toString().trim();
                            lozinka = txtLozinka.getText().toString().trim();

                            if (StringUtils.isEmpty(korisnickoIme)) {
                                txtKorisnickoIme.setError("Polje je obavezno!");

                                return;
                            } else {
                                txtKorisnickoIme.setError(null);
                            }
                            if (StringUtils.isEmpty(lozinka)) {
                                txtLozinka.setError("Polje je obavezno!");

                                return;
                            } else {
                                txtKorisnickoIme.setError(null);
                            }


                            Cursor c, c1;
                            String sql = "SELECT Ime, Prezime,Administrator FROM Korisnik where KorisnickoIme='" + korisnickoIme + "' AND Lozinka='" + lozinka + "'";
                            String sql1 = "SELECT Lozinka FROM Korisnik where Administrator=1";

                            dbHelper.openDataBase();
                            c = dbHelper.myDataBase.rawQuery(sql, null);
                            c1 = dbHelper.myDataBase.rawQuery(sql1, null);
                            c1.moveToFirst();
                            adminpass = c1.getString(0);

                            if (c.getCount() == 0) {
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                                new MaterialDialog.Builder(mContext)
                                        .title("Korisnik nije registrovan. Da li želite da kreirate novog korisnika?")
                                        .positiveText("DA")
                                        .negativeText("NE")
                                        .positiveFocus(true)
                                        .callback(new MaterialDialog.ButtonCallback() {
                                            @Override
                                            public void onPositive(MaterialDialog dialog) {
                                                LayoutInflater inflater = getLayoutInflater();
                                                View view = null;
                                                if (view == null) {
                                                    view = inflater.inflate(R.layout.dialog_edit_text, null);
                                                }
                                                final EditText input = (EditText) view.findViewById(R.id.etxtSifra);
                                                if (input != null) {
                                                    ((ViewGroup) input.getParent()).removeView(input); // <- fix
                                                }

                                                alertDialogBuilder
                                                        .setTitle("Unesite šifru")
                                                        .setView(input)

                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                                String url = input.getText().toString();

                                                                if (url.equals(adminpass)) {
                                                                    Intent intent = new Intent(mContext, NoviKorisnikActivity.class);
                                                                    intent.putExtra("KorisnickoIme", txtKorisnickoIme.getText().toString());
                                                                    intent.putExtra("Lozinka", txtLozinka.getText().toString());
                                                                    startActivity(intent);

                                                                } else {
                                                                    new AlertDialog.Builder(mContext)
                                                                            .setTitle("Neispravna  šifru")
                                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                public void onClick(DialogInterface dialog, int whichButton) {

                                                                                    dialog.dismiss();
                                                                                }
                                                                            })

                                                                            .show();

                                                                }
                                                                dialog.dismiss();


                                                            }
                                                        })

                                                        .show();


                                            }

                                            @Override
                                            public void onNegative(MaterialDialog dialog) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .show();


                            } else {
                                c.moveToFirst();
                                if (c.getInt(c.getColumnIndex("Administrator")) == 0) {


                                    Intent intent = new Intent(mContext, BaseActivity.class);
                                    intent.putExtra("KorisnickoIme", korisnickoIme);
                                    intent.putExtra("Lozinka", lozinka);
                                    startActivity(intent);


                                } else {
                                    if (c.getInt(c.getColumnIndex("Administrator")) == 1) {
                                        Intent intent = new Intent(mContext, AdminPanel.class);
                                        intent.putExtra("KorisnickoIme", korisnickoIme);
                                        intent.putExtra("Lozinka", lozinka);
                                        intent.putExtra("Ime", c.getString(0));
                                        intent.putExtra("Prezime", c.getString(1));
                                        startActivity(intent);
                                    }
                                }


                            }


                        } catch (Exception ex) {
                            String s = "";

                        } finally {
                            dbHelper.close();
                        }


                    }

                }

        });
    }

    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        private boolean error=false;

        public YourAsyncTask(LoginActivity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Import podataka. Sačekajte....");
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... args) {

            try {

                dbHelper=new DataBaseHelper(mContext);
                String directory_path = Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/Import/Merkator.xls";
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }

                ExcelToSQLite excelToSQLite = new ExcelToSQLite(mContext, "Merkator", true);

                excelToSQLite.importFromFile(directory_path, new ExcelToSQLite.ImportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String dbName) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();

                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Podaci importovani, nastavite sa logovanjem!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            dialog.dismiss();
                                        }
                                    })

                                    .show();


                        }


                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            } catch (Exception e) {
                error=true;
                new AlertDialog.Builder(getApplication())
                        .setTitle("Greška pri importu podataka!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                dialog.dismiss();
                            }
                        })

                        .show();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            // do UI work here

        }
    }



}