package rs.merkator.merkatorgejmifikacija;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoviKorisnikActivity extends AppCompatActivity {
    EditText etxKorisnickoIme, etxtLozinka, etxtIme, etxtPrezime;
    Spinner spinnerPol, spinnerSluzba;
    Button btnKreirajNovogKorisnika;
    DataBaseHelper dbHelper;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novi_korisnik);

        dbHelper=new DataBaseHelper(this);
        mContext=this;

        Intent myIntent = getIntent(); // gets the previously created intent
        String korisnickoIme = myIntent.getStringExtra("KorisnickoIme"); // will return "FirstKeyValue"
        String lozinka= myIntent.getStringExtra("Lozinka");

        etxKorisnickoIme=(EditText) findViewById(R.id.etxtKorisnickoIme);
        etxKorisnickoIme.setText(korisnickoIme);
        etxtLozinka=(EditText) findViewById(R.id.etxtLozinka);
        etxtLozinka.setText(lozinka);
        etxtIme=(EditText) findViewById(R.id.etxtIme);
        etxtPrezime=(EditText) findViewById(R.id.etxtPrezime);
        spinnerPol=(Spinner) findViewById(R.id.spinnerPol);
        spinnerSluzba=(Spinner) findViewById(R.id.spinnerSluzba);
        btnKreirajNovogKorisnika=(Button) findViewById(R.id.btnKreiraj);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("M");
        spinnerArray.add("Ž");

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_spinner_item, spinnerArray);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerPol.setAdapter(adapter);


        final CustomSpinnerArrayAdapter spinnerArrayAdapter = new CustomSpinnerArrayAdapter(getApplicationContext(), R.layout.dropdown_selected_list_item, R.id.option_text, spinnerArray);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.dropdown_list_item);
        spinnerPol.setAdapter(spinnerArrayAdapter);


        List<String> spinnerArraySluzba =  new ArrayList<String>();
        spinnerArraySluzba.add("Poslovno područje ljudskih resursa");
        spinnerArraySluzba.add("Poslovno područje maloprodaje");
        spinnerArraySluzba.add("Poslovno područje veleprodaje");
        spinnerArraySluzba.add("Poslovno područje logistika");
        spinnerArraySluzba.add("Poslovno područje marketing");
        spinnerArraySluzba.add("Poslovno područje operacija");
        spinnerArraySluzba.add("Poslovno područje tradinga & CM");
        spinnerArraySluzba.add("Područje finansija");
        spinnerArraySluzba.add("Područje investicija, razvoja i kontrole poslovnih procesa");
        spinnerArraySluzba.add("Sektor pravnih poslova");
        spinnerArraySluzba.add("Sektor poslovne aplikacije");
        spinnerArraySluzba.add("Sektor ICT i operativnog poslovanja");

//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
//                this, android.R.layout.simple_spinner_item, spinnerArraySluzba);
//
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSluzba.setAdapter(adapter1);

        final CustomSpinnerArrayAdapter spinnerArrayAdapter1 = new CustomSpinnerArrayAdapter(getApplicationContext(), R.layout.dropdown_selected_list_item, R.id.option_text, spinnerArraySluzba);
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.dropdown_list_item);
        spinnerSluzba.setAdapter(spinnerArrayAdapter1);

        btnKreirajNovogKorisnika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                try
                {
                   // dbHelper.ClineDb();

                    dbHelper.openDataBase();
                    dbHelper.myDataBase.beginTransaction();



                    dbHelper.myDataBase.delete("Korisnik", "Administrator=0", null);
                    dbHelper.myDataBase.execSQL("delete from AkcijaDan");
                    dbHelper.myDataBase.execSQL("delete from AkcijaSegment");
                    dbHelper.myDataBase.execSQL("delete from Kodeks");
                    ContentValues iuValues1 = new ContentValues();
                    iuValues1.put("Odgovoreno", 0);
                    dbHelper.myDataBase.updateWithOnConflict("Dan1", iuValues1, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
                    dbHelper.myDataBase.updateWithOnConflict("Dan2", iuValues1, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
                    dbHelper.myDataBase.updateWithOnConflict("Dan3", iuValues1, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues1, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);

                    BaseActivity.dan=0;
                    BaseActivity.segment=0;
                    BaseActivity.puzzle=false;
                    BaseActivity.puzzleBlue=false;
                    BaseActivity.puzzleGren=false;
                    BaseActivity.continueArray=false;
                    BaseActivity.proc=false;
                    BaseActivity.evaluacija=false;
                    BaseActivity.evaluacijastep=0;
                    BaseActivity.firistTime=false;


                    ContentValues iuValues = new ContentValues();
                    iuValues.put("ID", 2);
                    iuValues.put("KorisnickoIme", etxKorisnickoIme.getText().toString().trim());
                    iuValues.put("Lozinka", etxtLozinka.getText().toString().trim());
                    iuValues.put("Aktivan", 1);
                    iuValues.put("Administrator", 0);
                    iuValues.put("Ime", etxtIme.getText().toString().trim());
                    iuValues.put("Prezime", etxtPrezime.getText().toString().trim());
                    iuValues.put("Pol", spinnerPol.getSelectedItem().toString());
                    iuValues.put("Sluzba", spinnerSluzba.getSelectedItem().toString());
                    dbHelper.myDataBase.insertOrThrow("Korisnik", null, iuValues);
                    dbHelper.myDataBase.setTransactionSuccessful();


                }
                catch (SQLiteException  ex)
                {
                    new AlertDialog.Builder(NoviKorisnikActivity.this)
                            .setTitle("Greška"+ex.toString())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    dialog.dismiss();
                                }
                            })

                            .show();

                }
                catch (Exception  ex1)
                {
                    new AlertDialog.Builder(NoviKorisnikActivity.this)
                            .setTitle("Greška"+ex1.toString())
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

                new AlertDialog.Builder(NoviKorisnikActivity.this)
                        .setTitle("Korisnik je uspešno kreiran!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                finish();
                                dialog.dismiss();
                            }
                        })

                        .show();






            }
        });



    }
}