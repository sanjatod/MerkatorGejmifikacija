package rs.merkator.merkatorgejmifikacija;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;

import com.ajts.androidmads.library.ExcelToSQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.ajts.androidmads.library.SQLiteToExcel;

public class AdminPanel extends AppCompatActivity {
    EditText etxKorisnickoIme, etxtLozinka, etxtIme, etxtPrezime;
    Button btnKreirajNovogKorisnika, btnEvaluacija, btnImport;
    static DataBaseHelper  dbHelper;
    public static Context mContext;
    SQLiteToExcel sqliteToExcel = new SQLiteToExcel(this, "Merkator.db");
    private final static int MY_PERMISSIONS_REQUEST_WRITE = 200;
    private final static int MY_PERMISSIONS_REQUEST_READ = 201;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        dbHelper=new DataBaseHelper(this);
        mContext=this;

        Intent myIntent = getIntent(); // gets the previously created intent
        String korisnickoIme = myIntent.getStringExtra("KorisnickoIme"); // will return "FirstKeyValue"
        String lozinka= myIntent.getStringExtra("Lozinka");
        String ime = myIntent.getStringExtra("Ime"); // will return "FirstKeyValue"
        String prezime= myIntent.getStringExtra("Prezime");

        etxKorisnickoIme=(EditText) findViewById(R.id.etxtKorisnickoIme);
        etxKorisnickoIme.setText(korisnickoIme);
        etxtLozinka=(EditText) findViewById(R.id.etxtLozinka);
        etxtLozinka.setText(lozinka);
        etxtIme=(EditText) findViewById(R.id.etxtIme);
        etxtIme.setText(ime);
        etxtPrezime=(EditText) findViewById(R.id.etxtPrezime);
        etxtPrezime.setText(prezime);
        btnKreirajNovogKorisnika=(Button) findViewById(R.id.btnKreiraj);
        btnEvaluacija=(Button) findViewById(R.id.btnIzvestaj);
        btnImport=(Button) findViewById(R.id.btnImportDBToExcel);

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




        btnKreirajNovogKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                try
                {
                   // dbHelper.copyDataBase();

                    dbHelper.openDataBase();
                    dbHelper.myDataBase.beginTransaction();
                    ContentValues iuValues = new ContentValues();

                    dbHelper.myDataBase.delete("Korisnik", "Administrator=1",null);

                    iuValues.put("ID", 1);
                    iuValues.put("KorisnickoIme", etxKorisnickoIme.getText().toString().trim());
                    iuValues.put("Lozinka", etxtLozinka.getText().toString().trim());
                    iuValues.put("Aktivan", 1);
                    iuValues.put("Administrator", 1);
                    iuValues.put("Ime", etxtIme.getText().toString().trim());
                    iuValues.put("Prezime", etxtPrezime.getText().toString().trim());
                    dbHelper.myDataBase.insertOrThrow("Korisnik", null, iuValues);
                    dbHelper.myDataBase.setTransactionSuccessful();

                    new AlertDialog.Builder(AdminPanel.this)
                            .setTitle("Podaci za administratora su promenjeni")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    dialog.dismiss();
                                }
                            })

                            .show();


                }
                catch (Exception ex)
                {
                    new AlertDialog.Builder(AdminPanel.this)
                            .setTitle("Greska"+ex.toString())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    dialog.dismiss();
                                }
                            })

                            .show();

                }
                finally {
                    dbHelper.myDataBase.endTransaction();
                    dbHelper.close();
                }


            }
        });

        btnEvaluacija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                String path=CreateWordDoc.main(null);

                if (path!=null)
                {
                File file = new File(path);
                //File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/MercatorSopstipodaci.pdf");
                if (file.exists()) {

                    try {
                        Uri üri = Uri.fromFile(file);

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(üri, "application/pdf");//pdf
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        String s = "";
                    }
                }
                }
                else
                {
                    new AlertDialog.Builder(AdminPanel.this)
                            .setTitle("Dokument je prazan!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    dialog.dismiss();
                                }
                            })

                            .show();
                }

            }
        });
//
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String directory_path = Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/Import/Merkator.xls";
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }

                ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), "Merkator", true);

                excelToSQLite.importFromFile(directory_path, new ExcelToSQLite.ImportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String dbName) {

                        dbHelper.copyDatabaseToSdCard();

                        new AlertDialog.Builder(AdminPanel.this)
                                .setTitle("Uspesno importovani podaci")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                        dialog.dismiss();
                                    }
                                })

                                .show();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


//                String directory_path = Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/Import";
//                File file = new File(directory_path);
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//
//
//                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), "Merkator", directory_path);
//
//                sqliteToExcel.exportAllTables("Merkator.xls", new SQLiteToExcel.ExportListener() {
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onCompleted(String filePath) {
//                        new AlertDialog.Builder(AdminPanel.this)
//                                .setTitle("Uspesno eksportovani podaci")
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//
//                                        dialog.dismiss();
//                                    }
//                                })
//
//                                .show();
//                    }
//
//                    @Override
//                    public void onError(Exception e)
//                    {
//
//                    }
//                });


           }
       });
        }

        public static void ImportDb()
        {

            new AlertDialog.Builder(mContext)
                    .setTitle("Podaci će biti importovani iz exel file! Da bi ste nastavili dalje, morate dobiti poruku da import uspešno prošao! ")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

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


                                    new AlertDialog.Builder(mContext)
                                            .setTitle("Uspešno importovani podaci")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {

                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                        }
                    })

                    .show();



        }

}

