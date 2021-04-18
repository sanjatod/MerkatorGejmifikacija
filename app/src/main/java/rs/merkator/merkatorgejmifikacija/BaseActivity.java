package rs.merkator.merkatorgejmifikacija;


import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Contacts;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    Fragment fragment ;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DataBaseHelper dbHelper;
    Context mContext;
    static ArrayList<Dan> listDan = new ArrayList<>();
    FragmentManager fm = getSupportFragmentManager();
    public static int dan=0;
    public  int danZavrsen=0;
    public static int segment=0;
    public static boolean puzzle=false;

    public static boolean puzzleBlue=false;
    public static boolean puzzleGren=false;
    public static boolean continueArray=false;
    public static boolean proc=false;
    LayoutInflater inflater;
    View dialogView;
    View dialogView1;
    TextView txtCestitamo;
    public static boolean evaluacija=false;
    public static int evaluacijastep;
    public static int cookes;
    public static  boolean firistTime=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);

        inflater = this.getLayoutInflater();
        dialogView= inflater.inflate(R.layout.alertdialog, null);
        dialogView1= inflater.inflate(R.layout.alertdialog1, null);
        txtCestitamo=dialogView1.findViewById(R.id.txtView);

        dbHelper=new DataBaseHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mContext=this;

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_close , R.string.nav_app_bar_open_drawer_description);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(BaseActivity.this);

        try {
        dbHelper.openDataBase();
        Cursor c2;
        String sql2 ="SELECT Odgovoreno FROM Dan1 where Segment=-1";
        c2=dbHelper.myDataBase.rawQuery(sql2,null);
        c2.moveToFirst();
        if (c2.getInt(0)==0) {


            fragment = fm.findFragmentByTag("Thirteenth fragment");
            if (fragment == null) {
                fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment = new PuzzleFragmentEnd();
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_frame, fragment);
                ft.commit();
                return;
            }
        }


        }

           // dbHelper.copyDataBase();
        catch (Exception e) {
            e.printStackTrace();
        }


        //     Intent intent = new Intent(mContext, MainActivity2.class);

   //     startActivity(intent);

        ZapocetiDan();

        UIFragment();


    }

        private void UIFragment()
    {


            if(dan==0)
            {
                fragment = fm.findFragmentByTag("IzborDana");
                if (fragment == null) {
                    fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new IzborDanaFragment(dan, danZavrsen);
                    ft.addToBackStack(null);
                    ft.replace(R.id.fragment_frame, fragment);
                    ft.commit();
                    return;
                }
            }

            if (dan == 1)
            {
                    Dan1();
            }

            if (dan == 2)
            {
                Dan2();
            }
        if (dan == 3)
        {
            Dan3();
        }


    }

    @Override
    public void onBackPressed()
    {
       if (evaluacija)
       {

//           if (evaluacijastep==14) {
//               fragment = fm.findFragmentByTag("Evaluacija" + evaluacijastep);
//               if (fragment == null) {
//                   fm = getSupportFragmentManager();
//                   FragmentTransaction ft = fm.beginTransaction();
//                   fragment = new PuzzleFragmentEnd();
//                   ft.addToBackStack(null);
//                   ft.replace(R.id.fragment_frame, fragment);
//                   ft.commit();
//                   return;
//               }
//           }
//           else
//           {

               if (evaluacijastep==14) {

                   String sql ="SELECT Ime, Prezime FROM Korisnik where Administrator=0";


                   dbHelper.openDataBase();
                   Cursor c=dbHelper.myDataBase.rawQuery(sql,null);

                   c.moveToFirst();
                   String name =c.getString(1)+" "+c.getString(0);
                   dbHelper.close();
                   TextView t=dialogView.findViewById(R.id.textView19);
                   t.setVisibility(View.GONE);
                   new AlertDialog.Builder(mContext)
                           .setTitle("   ČESTITAMO,    "+name+"\n   USPEŠNO STE ZAVRŠILI PROCES UVOĐENJA NOVOZAPOSLENOG U KOMPANIJU MERCATOR-S")
                           .setView(dialogView)
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int whichButton) {

                                   dialog.dismiss();
                                   Date d = new Date();
                                   CharSequence s = android.text.format.DateFormat.format("dd-MM-yyyy:hh-mm-ss",d.getTime());
                                   String kraj=s.toString();


                                   dbHelper.openDataBase();
                                   ContentValues iuValues = new ContentValues();
                                   iuValues.put("Kraj", kraj);
                                   dbHelper.myDataBase.updateWithOnConflict("AkcijaDan", iuValues, " Dan3=1" , null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                   dbHelper.close();

                                   dbHelper.openDataBase();
                                   ContentValues iuValues1 = new ContentValues();
                                   iuValues.put("Zapocet", 2);
                                   dbHelper.myDataBase.updateWithOnConflict("AkcijaSegment", iuValues1, "Segment=7 AND  Dan=3" , null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                   dbHelper.close();


                                   finish();
                                   //System.exit(0);


                               }
                           })

                           .show();
               }
               else {
                   fragment = fm.findFragmentByTag("Evaluacija" + evaluacijastep);
                   if (fragment == null) {
                       fm = getSupportFragmentManager();
                       FragmentTransaction ft = fm.beginTransaction();
                       fragment = new EvaluacijaFragment();
                       ft.addToBackStack(null);
                       ft.replace(R.id.fragment_frame, fragment);
                       ft.commit();
                       return;
                   }
               }

           //}

       }
       else
           {

              if (firistTime==false)
               UIFragment();
           }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null && item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {

        switch (menuItem.getItemId()) {
            case R.id.kodeks:


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame,
                        new KodeksFragment(dan, segment)).commit();
                break;
            case R.id.oseme:


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame,
                        new OrganiyacioneSemeNavigacioniBarFragment()).commit();
                break;
            case R.id.mapa_progresa:


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame,
                        new MapaProgresaFragment()).commit();
                break;
            case R.id.odjava:
                startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                break;
            case R.id.korisniLinkovi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame,
                        new KorisniLinkoviFragment()).commit();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void  ZapocetiDan()
    {
        dbHelper.openDataBase();
        String sqlAkcija = "SELECT Pocetak,Dan1,Dan2,Dan3 FROM AkcijaDan where Kraj is null";
        Cursor cAkcija = dbHelper.myDataBase.rawQuery(sqlAkcija, null);
        if (cAkcija.moveToFirst()) {
            do {
                if (cAkcija.getInt(cAkcija.getColumnIndex("Dan1")) == 1) dan = 1;
                if (cAkcija.getInt(cAkcija.getColumnIndex("Dan2")) == 1) dan = 2;
                if (cAkcija.getInt(cAkcija.getColumnIndex("Dan3")) == 1) dan = 3;

            } while (cAkcija.moveToNext());
        }

        String sqlAkcijaZavrsena = "SELECT Dan1,Dan2,Dan3 FROM AkcijaDan where Kraj is not null ";
        Cursor cAkcijaZavrsena = dbHelper.myDataBase.rawQuery(sqlAkcijaZavrsena, null);
        if (cAkcijaZavrsena.moveToFirst()) {
            do {
                if (cAkcijaZavrsena.getInt(cAkcijaZavrsena.getColumnIndex("Dan1")) == 1) danZavrsen = 1;
                if (cAkcijaZavrsena.getInt(cAkcijaZavrsena.getColumnIndex("Dan2")) == 1) danZavrsen = 2;
                if (cAkcijaZavrsena.getInt(cAkcijaZavrsena.getColumnIndex("Dan3")) == 1) danZavrsen = 3;

            } while (cAkcijaZavrsena.moveToNext());
        }
        dbHelper.close();

    }

    private boolean  ZapocetiSegment(int dan)
    {
        boolean rez=false;
        dbHelper.openDataBase();
        String sqlAkcija = "SELECT Segment FROM AkcijaSegment where Zapocet=1 and Segment!=0 and Dan="+dan;
        Cursor cAkcija = dbHelper.myDataBase.rawQuery(sqlAkcija, null);
        if (cAkcija.moveToFirst()) {
            do {
                segment=cAkcija.getInt(cAkcija.getColumnIndex("Segment"));

            } while (cAkcija.moveToNext());
        }

        if (segment==0)
        {

            String sql1 = "SELECT Segment FROM AkcijaSegment where Zapocet=2 and Dan=" + dan+" and Segment!=0  ORDER BY Segment desc limit 1 ";
            Cursor c1 = dbHelper.myDataBase.rawQuery(sql1, null);
            if (c1.moveToFirst()) {
                do {
                    segment = c1.getInt(c1.getColumnIndex("Segment"));
                    fragment = fm.findFragmentByTag("IzborSegmenta");
                    if (fragment == null) {
                        fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        fragment = new IzborSegmentaFragment(dan, segment);
                        ft.addToBackStack(null);
                        ft.replace(R.id.fragment_frame, fragment);
                        ft.commit();
                        rez= true;

                    }

                } while (c1.moveToNext());
            }

        }
        dbHelper.close();
        return rez;

    }

    private void NoviSegment() {
        if (dan == 3 && segment == 7) {

            fragment = fm.findFragmentByTag("IzborSegmenta");
            if (fragment == null) {
                fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fragment = new EvaluacijaFragment();
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_frame, fragment);
                ft.commit();
                return;
            }
        }else {


                fragment = fm.findFragmentByTag("IzborSegmenta");
                if (fragment == null) {
                    fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new IzborSegmentaFragment(dan, segment);
                    ft.addToBackStack(null);
                    ft.replace(R.id.fragment_frame, fragment);
                    ft.commit();
                    return;
                }

            }


    }

    private void Dan1()
    {
        try {

                if (ZapocetiSegment(1))
                    return;

                if (segment==0)
                {
                    fragment = fm.findFragmentByTag("IzborSegmenta");
                    if (fragment == null) {
                        fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        fragment = new IzborSegmentaFragment(dan, segment);
                        ft.addToBackStack(null);
                        ft.replace(R.id.fragment_frame, fragment);
                        ft.commit();
                        return;
                    }
                }
                else
                {

                    fm = getSupportFragmentManager();
                    Cursor c;
                    String sql = "SELECT  rowid, RB,TekstPitanja, Slika,TipOdgovora,TacanOdgovor,PonudjeniOdgovori, Odgovoreno, Segment,TekstIspodPitanja FROM Dan1 where Segment="+segment+" Order by CAST(RB as int) ";
                    dbHelper.openDataBase();
                    c = dbHelper.myDataBase.rawQuery(sql, null);

                    listDan=new ArrayList<>();

                    if (c.moveToFirst()) {
                        do {
                            // do what you need with the cursor here
                            Dan dan1 = new Dan(c.getInt(c.getColumnIndex("rowid")),
                                    c.getInt(c.getColumnIndex("RB")),
                                    c.getString(c.getColumnIndex("TekstPitanja")),
                                    c.getString(c.getColumnIndex("Slika")),
                                    c.getInt(c.getColumnIndex("TipOdgovora")),
                                    c.getString(c.getColumnIndex("TacanOdgovor")),
                                    c.getString(c.getColumnIndex("PonudjeniOdgovori")),
                                    c.getInt(c.getColumnIndex("Odgovoreno")),
                                    c.getInt(c.getColumnIndex("Segment")),
                                    c.getString(c.getColumnIndex("TekstIspodPitanja")));
                            listDan.add(dan1);
                        } while (c.moveToNext());
                    }
                    dbHelper.close();
                    c.close();


                    for (Dan dan1: listDan) {

                        if (dan1.getTipOdgovora() == 1 && dan1.getOdgovoreno() == 0) {
                            fragment = fm.findFragmentByTag("myFragmentTag");
                            if (fragment == null) {
                                FragmentTransaction ft = fm.beginTransaction();
                                fragment = new FirstFragment(dan1,dan,segment);
                                ft.addToBackStack(null);
                                ft.replace(R.id.fragment_frame, fragment);
                                ft.commit();
                                return;

                            }
                        } else {
                            if (dan1.getTipOdgovora() == 2 && dan1.getOdgovoreno() == 0) {
                                fragment = fm.findFragmentByTag("2");
                                if (fragment == null) {
                                    FragmentTransaction ft = fm.beginTransaction();
                                    fragment = new SecondFragment(dan1,dan,segment);
                                    ft.addToBackStack(null);
                                    ft.replace(R.id.fragment_frame, fragment);
                                    ft.commit();
                                    return;

                                }

                            } else {
                                if (dan1.getTipOdgovora() == 3 && dan1.getOdgovoreno() == 0) {
                                    fragment = fm.findFragmentByTag("3");
                                    if (fragment == null) {
                                        FragmentTransaction ft = fm.beginTransaction();
                                        fragment = new ThirdFragment(dan1,dan,segment);
                                        ft.addToBackStack(null);
                                        ft.replace(R.id.fragment_frame, fragment);
                                        ft.commit();
                                        return;

                                    }

                                } else {
                                    if (dan1.getTipOdgovora() == 4 && dan1.getOdgovoreno() == 0) {
                                        fragment = fm.findFragmentByTag("4");
                                        if (fragment == null) {
                                            FragmentTransaction ft = fm.beginTransaction();
                                            fragment = new FourthFragment(dan1,dan,segment);
                                            ft.addToBackStack(null);
                                            ft.replace(R.id.fragment_frame, fragment);
                                            ft.commit();
                                            return;

                                        }

                                    } else {

                                        if (dan1.getTipOdgovora() == 5 && dan1.getOdgovoreno() == 0) {
                                            fragment = fm.findFragmentByTag("5");
                                            if (fragment == null) {
                                                FragmentTransaction ft = fm.beginTransaction();
                                                fragment = new FifthFragment(dan1,dan,segment);
                                                ft.addToBackStack(null);
                                                ft.replace(R.id.fragment_frame, fragment);
                                                ft.commit();
                                                return;

                                            }

                                        } else
                                        {

                                            if (dan1.getTipOdgovora() == 6 && dan1.getOdgovoreno() == 0) {
                                                fragment = fm.findFragmentByTag("6");
                                                if (fragment == null) {
                                                    FragmentTransaction ft = fm.beginTransaction();
                                                    fragment = new TaskFragment(dan1, dan,segment);
                                                    ft.addToBackStack(null);
                                                    ft.replace(R.id.fragment_frame, fragment);
                                                    ft.commit();
                                                    return;

                                                }
                                            }
                                            else
                                            {
                                                if (dan1.getTipOdgovora() == 7 && dan1.getOdgovoreno() == 0) {
                                                    fragment = fm.findFragmentByTag("6");
                                                    if (fragment == null) {
                                                        FragmentTransaction ft = fm.beginTransaction();
                                                        fragment = new SeventhFragment(dan1,dan,segment );
                                                        ft.addToBackStack(null);
                                                        ft.replace(R.id.fragment_frame, fragment);
                                                        ft.commit();
                                                        return;

                                                    }
                                                }
                                                else
                                                {
                                                    if (dan1.getTipOdgovora() == 9 && dan1.getOdgovoreno() == 0) {
                                                        fragment = fm.findFragmentByTag("6");
                                                        if (fragment == null) {
                                                            FragmentTransaction ft = fm.beginTransaction();
                                                            fragment = new NinthFragment(dan1,dan,segment );
                                                            ft.addToBackStack(null);
                                                            ft.replace(R.id.fragment_frame, fragment);
                                                            ft.commit();
                                                            return;

                                                        }
                                                    }
                                                    else
                                                    {
                                                        if (dan1.getTipOdgovora() == 14 && dan1.getOdgovoreno() == 0 && dan1.getRowid()==68) {
                                                            fragment = fm.findFragmentByTag("6");
                                                            if (fragment == null) {
                                                                FragmentTransaction ft = fm.beginTransaction();
                                                                fragment = new ApplicationDressCodeFragment();
                                                                ft.addToBackStack(null);
                                                                ft.replace(R.id.fragment_frame, fragment);
                                                                ft.commit();
                                                                return;

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }

                    switch (segment) {
                        case 1:
                            txtCestitamo.setText("Čestitamo, uspešno ste završili segment Opšte informacije");
                            new AlertDialog.Builder(mContext)
                                    .setView(dialogView1)
                                    .setCancelable(false)

                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            ZatvoriSegmentDan1();
                                            NoviSegment();
                                            dialog.dismiss();
                                            ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);

                                        }
                                    })

                                    .show();

                            break;
                        case 2:

                            txtCestitamo.setText("Čestitamo, uspešno ste završili segment\nIdea Akademija");

                            new AlertDialog.Builder(mContext)
                                    // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                    .setView(dialogView1)
                                    .setCancelable(false)

                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            ZatvoriSegmentDan1();
                                            NoviSegment();
                                            dialog.dismiss();
                                            ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);
                                        }
                                    })

                                    .show();

                            break;
                        case 3:

                                txtCestitamo.setText("Čestitamo, uspešno ste završili segment\nSlužba regrutacije i selekcije");
                                new AlertDialog.Builder(mContext)
                                        // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                        .setView(dialogView1)
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                dialog.dismiss();
                                                ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);

                                                ZatvoriSegmentDan1();
                                                NoviSegment();

//                                                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
//                                                PersonnelRecordsFragment w1 = new PersonnelRecordsFragment();
//                                                ft1.replace(R.id.fragment_frame, w1);
//                                                ft1.commit();
//                                                fragment = fm.findFragmentByTag("PrviFragment");
//                                                if (fragment == null) {
//                                                    fm = getSupportFragmentManager();
//                                                    FragmentTransaction ft = fm.beginTransaction();
//                                                    fragment = new PersonnelRecordsFragment();
//                                                    ft.addToBackStack(null);
//                                                    ft.replace(R.id.fragment_frame, fragment);
//                                                    ft.commit();
//                                                    return;
//
//                                                }


                                            }
                                        })

                                        .show();


                            break;



                        case 4:
                            if (puzzle==false) {
                                txtCestitamo.setText("Čestitamo, uspešno ste završili segment\nSektor organizacionog dizajna služba personalne evidencije");
                                new AlertDialog.Builder(mContext)

                                        .setCancelable(false)
                                        .setView(dialogView1)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {

                                                ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);
                                                dialog.dismiss();

                                                txtCestitamo.setText("Zadatak: Složite slagalicu!");
                                                new AlertDialog.Builder(mContext)

                                                        .setCancelable(false)
                                                        .setView(dialogView1)
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int whichButton) {

                                                                ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);
                                                                dialog.dismiss();
                                                                ZatvoriSegmentDan1();


                                                                fragment = fm.findFragmentByTag("Puzzle");
                                                                if (fragment == null) {
                                                                    fm = getSupportFragmentManager();
                                                                    FragmentTransaction ft = fm.beginTransaction();
                                                                    fragment = new PuzzleFragment();
                                                                    ft.addToBackStack(null);
                                                                    ft.replace(R.id.fragment_frame, fragment);
                                                                    ft.commit();
                                                                    return;

                                                                }



                                                            }
                                                        })

                                                        .show();




                                            }
                                        })

                                        .show();
                            }
                            else
                            {
                                NoviSegment();
                            }


                            break;

                        case 5:
                            txtCestitamo.setText("Čestitamo, uspešno ste završili segment\nSektor HR operacija-a");
                            new AlertDialog.Builder(mContext)
                                    .setCancelable(false)
                                    .setView(dialogView1)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);
                                            dialog.dismiss();

                                            fragment = fm.findFragmentByTag("NewAdress");
                                            if (fragment == null) {
                                                fm = getSupportFragmentManager();
                                                FragmentTransaction ft = fm.beginTransaction();
                                                fragment = new NewAddresFragment();
                                                ft.addToBackStack(null);
                                                ft.replace(R.id.fragment_frame, fragment);
                                                ft.commit();


                                            }
                                        }
                                    })

                                    .show();

                            break;

                        case 6:
                            txtCestitamo.setText("Čestitamo, uspešno ste završili segment\nRoda Mega 428");
                            new AlertDialog.Builder(mContext)
                                    .setCancelable(false)
                                    .setView(dialogView1)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            ZatvoriSegmentDan1();
                                            NoviSegment();
                                            ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);
                                            dialog.dismiss();

                                        }
                                    })

                                    .show();

                            break;

                        case 7:
                            // proverite da li je zavrsen Kodeks oblacenja
                            dbHelper.openDataBase();
                            String sql="Select Stanje from Kodeks";
                            Cursor c = dbHelper.myDataBase.rawQuery(sql, null);
                            int stanje=0;
                            if (c.moveToFirst()) {
                                do {
                                   stanje=c.getInt(0);
                                } while (c.moveToNext());
                            }
                            if (stanje==2) {
                                new AlertDialog.Builder(mContext)
                                        .setTitle("                           ČESTITAMO, USPEŠNO STE ZAVRŠILI 1.DAN")
                                        .setView(dialogView)
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                storeDate(BaseActivity.this);

                                                dialog.dismiss();
                                                Date d = new Date();
                                                CharSequence s = android.text.format.DateFormat.format("dd-MM-yyyy:hh-mm-ss", d.getTime());
                                                String kraj = s.toString();

                                                dbHelper.openDataBase();
                                                ContentValues iuValues = new ContentValues();
                                                iuValues.put("Kraj", kraj);
                                                dbHelper.myDataBase.updateWithOnConflict("AkcijaDan", iuValues, " Dan1=1", null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                                dbHelper.close();




                                                finish();
                                                System.exit(0);


//


                                            }
                                        })

                                        .show();
                            }
                            else
                            {
                                new AlertDialog.Builder(this)
                                        .setTitle("Niste završili segment Kodeks oblačenja. U task baru izaberite opciju Kodeks ")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                dialog.dismiss();
                                            }
                                        })

                                        .show();

                            }
                            break;


                        default:

                    }


        }
        catch(Exception ex)
        {
            new AlertDialog.Builder(mContext)
                    .setTitle("GRESKA!   "+ex.getMessage())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    })

                    .show();

        }
        finally{
            dbHelper.close();
        }


    }



    private void Dan2()
    {
        try {

            if (ZapocetiSegment(2))
                return;

            if (segment == 0) {
                fragment = fm.findFragmentByTag("IzborSegmenta");
                if (fragment == null) {
                    fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new IzborSegmentaFragment(dan, segment);
                    ft.addToBackStack(null);
                    ft.replace(R.id.fragment_frame, fragment);
                    ft.commit();
                    return;
                }
            } else {

                fm = getSupportFragmentManager();
                Cursor c;
                String sql = "SELECT  rowid, RB,TekstPitanja, Slika,TipOdgovora,TacanOdgovor,PonudjeniOdgovori, Odgovoreno, Segment,TekstIspodPitanja FROM Dan2 where Segment=" + segment + " Order by CAST(RB as int)";
                dbHelper.openDataBase();
                c = dbHelper.myDataBase.rawQuery(sql, null);

                listDan = new ArrayList<>();

                if (c.moveToFirst()) {
                    do {
                        // do what you need with the cursor here
                        Dan dan2 = new Dan(c.getInt(c.getColumnIndex("rowid")),
                                c.getInt(c.getColumnIndex("RB")),
                                c.getString(c.getColumnIndex("TekstPitanja")),
                                c.getString(c.getColumnIndex("Slika")),
                                c.getInt(c.getColumnIndex("TipOdgovora")),
                                c.getString(c.getColumnIndex("TacanOdgovor")),
                                c.getString(c.getColumnIndex("PonudjeniOdgovori")),
                                c.getInt(c.getColumnIndex("Odgovoreno")),
                                c.getInt(c.getColumnIndex("Segment")),
                                c.getString(c.getColumnIndex("TekstIspodPitanja")));

                        listDan.add(dan2);
                    } while (c.moveToNext());
                }
                dbHelper.close();
                c.close();


                for (Dan dan2 : listDan) {

                    if (dan2.getTipOdgovora() == 1 && dan2.getOdgovoreno() == 0) {
                        fragment = fm.findFragmentByTag("myFragmentTag");
                        if (fragment == null) {
                            FragmentTransaction ft = fm.beginTransaction();
                            fragment = new FirstFragment(dan2, dan, segment);
                            ft.addToBackStack(null);
                            ft.replace(R.id.fragment_frame, fragment);
                            ft.commit();
                            return;

                        }
                    } else {
                        if (dan2.getTipOdgovora() == 2 && dan2.getOdgovoreno() == 0) {
                            fragment = fm.findFragmentByTag("2");
                            if (fragment == null) {
                                FragmentTransaction ft = fm.beginTransaction();
                                fragment = new SecondFragment(dan2, dan, segment);
                                ft.addToBackStack(null);
                                ft.replace(R.id.fragment_frame, fragment);
                                ft.commit();
                                return;

                            }

                        } else {
                            if (dan2.getTipOdgovora() == 3 && dan2.getOdgovoreno() == 0) {
                                fragment = fm.findFragmentByTag("3");
                                if (fragment == null) {
                                    FragmentTransaction ft = fm.beginTransaction();
                                    fragment = new ThirdFragment(dan2, dan, segment);
                                    ft.addToBackStack(null);
                                    ft.replace(R.id.fragment_frame, fragment);
                                    ft.commit();
                                    return;

                                }

                            } else {
                                if (dan2.getTipOdgovora() == 4 && dan2.getOdgovoreno() == 0) {
                                    fragment = fm.findFragmentByTag("4");
                                    if (fragment == null) {
                                        FragmentTransaction ft = fm.beginTransaction();
                                        fragment = new FourthFragment(dan2, dan, segment);
                                        ft.addToBackStack(null);
                                        ft.replace(R.id.fragment_frame, fragment);
                                        ft.commit();
                                        return;

                                    }

                                } else {

                                    if (dan2.getTipOdgovora() == 5 && dan2.getOdgovoreno() == 0) {
                                        fragment = fm.findFragmentByTag("5");
                                        if (fragment == null) {
                                            FragmentTransaction ft = fm.beginTransaction();
                                            fragment = new FifthFragment(dan2, dan, segment);
                                            ft.addToBackStack(null);
                                            ft.replace(R.id.fragment_frame, fragment);
                                            ft.commit();
                                            return;

                                        }

                                    } else {

                                        if (dan2.getTipOdgovora() == 6 && dan2.getOdgovoreno() == 0) {

                                            fragment = fm.findFragmentByTag("6");
                                            if (fragment == null) {
                                                FragmentTransaction ft = fm.beginTransaction();
                                                fragment = new TaskFragment(dan2, dan, segment);
                                                ft.addToBackStack(null);
                                                ft.replace(R.id.fragment_frame, fragment);
                                                ft.commit();
                                                return;

                                            }
                                        } else {
                                            if (dan2.getTipOdgovora() == 7 && dan2.getOdgovoreno() == 0) {
                                                fragment = fm.findFragmentByTag("7");
                                                if (fragment == null) {
                                                    FragmentTransaction ft = fm.beginTransaction();
                                                    fragment = new SeventhFragment(dan2, dan, segment);
                                                    ft.addToBackStack(null);
                                                    ft.replace(R.id.fragment_frame, fragment);
                                                    ft.commit();
                                                    return;

                                                }
                                            } else {


                                                if (dan2.getTipOdgovora() == 9 && dan2.getOdgovoreno() == 0) {
                                                    fragment = fm.findFragmentByTag("9");
                                                    if (fragment == null) {
                                                        FragmentTransaction ft = fm.beginTransaction();
                                                        fragment = new NinthFragment(dan2, dan, segment);
                                                        ft.addToBackStack(null);
                                                        ft.replace(R.id.fragment_frame, fragment);
                                                        ft.commit();
                                                        return;

                                                    }
                                                } else {
                                                    if (dan2.getTipOdgovora() == 8 && dan2.getOdgovoreno() == 0) {
                                                        fragment = fm.findFragmentByTag("8");
                                                        if (fragment == null) {
                                                            FragmentTransaction ft = fm.beginTransaction();
                                                            fragment = new EighthFragment(dan2, dan, segment);
                                                            ft.addToBackStack(null);
                                                            ft.replace(R.id.fragment_frame, fragment);
                                                            ft.commit();
                                                            return;

                                                        }
                                                    } else {
                                                        if (dan2.getTipOdgovora() == 10 && dan2.getOdgovoreno() == 0) {
                                                            fragment = fm.findFragmentByTag("8");
                                                            if (fragment == null) {
                                                                FragmentTransaction ft = fm.beginTransaction();
                                                                fragment = new TenthFragment(dan2, dan, segment);
                                                                ft.addToBackStack(null);
                                                                ft.replace(R.id.fragment_frame, fragment);
                                                                ft.commit();
                                                                return;

                                                            }
                                                        } else {
                                                            if (dan2.getTipOdgovora() == 11 && dan2.getOdgovoreno() == 0) {
                                                                fragment = fm.findFragmentByTag("11");
                                                                if (fragment == null) {
                                                                    FragmentTransaction ft = fm.beginTransaction();
                                                                    fragment = new EleventhFragment(dan2, dan, segment);
                                                                    ft.addToBackStack(null);
                                                                    ft.replace(R.id.fragment_frame, fragment);
                                                                    ft.commit();
                                                                    return;

                                                                }
                                                            } else {
                                                                if (dan2.getTipOdgovora() == 13 && dan2.getOdgovoreno() == 0) {
                                                                    if (dan2.getRowid() == 74) {
                                                                        fragment = fm.findFragmentByTag("11");
                                                                        if (fragment == null) {
                                                                            FragmentTransaction ft = fm.beginTransaction();
                                                                            fragment = new Thirteenth3Fragment(dan2, dan, segment);
                                                                            ft.addToBackStack(null);
                                                                            ft.replace(R.id.fragment_frame, fragment);
                                                                            ft.commit();
                                                                            return;

                                                                        }
                                                                    }
                                                                    if (dan2.getRowid() == 140) {
                                                                        fragment = fm.findFragmentByTag("11");
                                                                        if (fragment == null) {
                                                                            FragmentTransaction ft = fm.beginTransaction();
                                                                            fragment = new Thirteenth1Fragment(dan2, dan, segment);
                                                                            ft.addToBackStack(null);
                                                                            ft.replace(R.id.fragment_frame, fragment);
                                                                            ft.commit();
                                                                            return;

                                                                        }
                                                                    }


                                                                } else {
                                                                    if (dan2.getTipOdgovora() == 12 && dan2.getOdgovoreno() == 0) {

                                                                        fragment = fm.findFragmentByTag("12");
                                                                        if (fragment == null) {
                                                                            FragmentTransaction ft = fm.beginTransaction();
                                                                            fragment = new ThirteenthFragment(dan2, dan, segment);
                                                                            ft.addToBackStack(null);
                                                                            ft.replace(R.id.fragment_frame, fragment);
                                                                            ft.commit();
                                                                            return;

                                                                        }


                                                                    }
                                                                    else
                                                                    {
                                                                        if (dan2.getTipOdgovora() == 15 && dan2.getOdgovoreno() == 0) {
                                                                            fragment = fm.findFragmentByTag("myFragmentTag");
                                                                            if (fragment == null) {
                                                                                FragmentTransaction ft = fm.beginTransaction();
                                                                                fragment = new FifteenthFragment(dan2, dan, segment);
                                                                                ft.addToBackStack(null);
                                                                                ft.replace(R.id.fragment_frame, fragment);
                                                                                ft.commit();
                                                                                return;

                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            if (dan2.getTipOdgovora() == 16 && dan2.getOdgovoreno() == 0) {
                                                                                fragment = fm.findFragmentByTag("myFragmentTag");
                                                                                if (fragment == null) {
                                                                                    FragmentTransaction ft = fm.beginTransaction();
                                                                                    fragment = new SixteenthFragment(dan2, dan, segment);
                                                                                    ft.addToBackStack(null);
                                                                                    ft.replace(R.id.fragment_frame, fragment);
                                                                                    ft.commit();
                                                                                    return;

                                                                                }
                                                                            }
                                                                            else
                                                                            {
                                                                                if (dan2.getTipOdgovora() == 17 && dan2.getOdgovoreno() == 0) {
                                                                                    fragment = fm.findFragmentByTag("myFragmentTag");
                                                                                    if (fragment == null) {
                                                                                        FragmentTransaction ft = fm.beginTransaction();
                                                                                        fragment = new SeventeenthFragment(dan2, dan, segment);
                                                                                        ft.addToBackStack(null);
                                                                                        ft.replace(R.id.fragment_frame, fragment);
                                                                                        ft.commit();
                                                                                        return;

                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }






            if (segment==1 && MatrixFragment.uspesno==false)
            {
                fragment = fm.findFragmentByTag("Zadatak");
                if (fragment == null) {
                    fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new MatrixFragment();
                    ft.addToBackStack(null);
                    ft.replace(R.id.fragment_frame, fragment);
                    ft.commit();
                    return;

                }
            }

            switch (segment) {
                case 1:
                    txtCestitamo.setText("Čestitamo, uspešno ste završili segment Tradinga i category managament");
                    new AlertDialog.Builder(mContext)
                            // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                            .setView(dialogView1)
                            .setCancelable(false)

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ZatvoriSegmentDan2();
                                    NoviSegment();
                                    dialog.dismiss();
                                    ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);

                                }
                            })

                            .show();

                    break;


                case 2:
                    txtCestitamo.setText("Čestitamo, uspešno ste završili segment Maloprodaja");
                    new AlertDialog.Builder(mContext)
                            // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                            .setView(dialogView1)
                            .setCancelable(false)

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ZatvoriSegmentDan2();
                                    NoviSegment();
                                    ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);
                                    dialog.dismiss();


                                }
                            })

                            .show();

//                    new AlertDialog.Builder(mContext)
//                            .setTitle("Čestitamo, uspešno ste završili segment Maloprodaja")
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int whichButton) {
//                                    NoviSegment();
//                                    dialog.dismiss();
//                                }
//                            })
//
//                            .show();

                    break;
                case 3:

                    if (puzzleBlue==false)
                    {
                        txtCestitamo.setText("Zadatak slagalica!");
                        new AlertDialog.Builder(mContext)
                                // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                .setView(dialogView1)
                                .setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);
                                        dialog.dismiss();
                                        fragment = fm.findFragmentByTag("Zadatak");
                                        if (fragment == null) {
                                            fm = getSupportFragmentManager();
                                            FragmentTransaction ft = fm.beginTransaction();
                                            fragment = new PuzzleBlueCardFragment();
                                            ft.addToBackStack(null);
                                            ft.replace(R.id.fragment_frame, fragment);
                                            ft.commit();
                                            return;

                                        }



                                    }
                                })

                                .show();

                    }
                    else {


                        if (puzzleGren==false)
                        {
                            txtCestitamo.setText("Zadatak slagalica!");
                            new AlertDialog.Builder(mContext)
                                    // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                    .setView(dialogView1)
                                    .setCancelable(false)

                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);
                                            dialog.dismiss();

                                            fragment = fm.findFragmentByTag("Zadatak");
                                            if (fragment == null) {
                                                fm = getSupportFragmentManager();
                                                FragmentTransaction ft = fm.beginTransaction();
                                                fragment = new PuzzleGreenCardFragment();
                                                ft.addToBackStack(null);
                                                ft.replace(R.id.fragment_frame, fragment);
                                                ft.commit();
                                                return;

                                            }


                                        }
                                    })

                                    .show();
                        }
                        else {
                            txtCestitamo.setText("Čestitamo, uspešno ste završili segment Marketing");
                            new AlertDialog.Builder(mContext)
                                    // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                    .setView(dialogView1)
                                    .setCancelable(false)

                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            ZatvoriSegmentDan2();
                                            NoviSegment();
                                            dialog.dismiss();
                                            ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);

                                        }
                                    })

                                    .show();

                        }
                    }

                    break;



                case 4:

                    txtCestitamo.setText("Čestitamo, uspešno ste završili segment Investicije, razvoj i kontrola poslovnih procesa");
                    new AlertDialog.Builder(mContext)
                            // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                            .setView(dialogView1)
                            .setCancelable(false)

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ZatvoriSegmentDan2();
                                    NoviSegment();
                                    dialog.dismiss();
                                    ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);

                                }
                            })

                            .show();

                    break;

                case 5:

                    txtCestitamo.setText("Čestitamo, uspešno ste završili segment Područje Finansija");
                    new AlertDialog.Builder(mContext)
                            // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                            .setView(dialogView1)
                            .setCancelable(false)

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    new AlertDialog.Builder(mContext)
                                            .setTitle("                           ČESTITAMO, USPEŠNO STE ZAVRŠILI 2.DAN")
                                            .setView(dialogView)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {

                                                    dialog.dismiss();
                                                    Date d = new Date();
                                                    CharSequence s = android.text.format.DateFormat.format("dd-MM-yyyy:hh-mm-ss",d.getTime());
                                                    String kraj=s.toString();

                                                    dbHelper.openDataBase();
                                                    ContentValues iuValues = new ContentValues();
                                                    iuValues.put("Kraj", kraj);
                                                    dbHelper.myDataBase.updateWithOnConflict("AkcijaDan", iuValues, " Dan2=1" , null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                                    dbHelper.close();
                                                    storeDate(BaseActivity.this);
                                                    //retrieveDate();

                                                    finish();
                                                    System.exit(0);

                                                }
                                            })

                                            .show();
                                    dialog.dismiss();
                                    ((ViewGroup)dialogView1.getParent()).removeView(dialogView1);

                                }
                            })

                            .show();

                    break;

                default:

            }

        }
        catch(Exception ex)
        {
            new AlertDialog.Builder(mContext)
                    .setTitle("GRESKA!   "+ex.getMessage())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    })

                    .show();

        }
        finally{
            dbHelper.close();
        }


    }

    private void Dan3() {
        try {

            if (ZapocetiSegment(3))
                return;

            if (segment == 0) {
                fragment = fm.findFragmentByTag("IzborSegmenta");
                if (fragment == null) {
                    fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = new IzborSegmentaFragment(dan, segment);
                    ft.addToBackStack(null);
                    ft.replace(R.id.fragment_frame, fragment);
                    ft.commit();
                    return;
                }
            } else {

                fm = getSupportFragmentManager();
                Cursor c;
                String sql = "SELECT  rowid, RB,TekstPitanja, Slika,TipOdgovora,TacanOdgovor,PonudjeniOdgovori, Odgovoreno, Segment,TekstIspodPitanja FROM Dan3 where Segment=" + segment + " Order by CAST(RB as int)";
                dbHelper.openDataBase();
                c = dbHelper.myDataBase.rawQuery(sql, null);

                listDan = new ArrayList<>();

                if (c.moveToFirst()) {
                    do {
                        // do what you need with the cursor here
                        Dan dan1 = new Dan(c.getInt(c.getColumnIndex("rowid")),
                                c.getInt(c.getColumnIndex("RB")),
                                c.getString(c.getColumnIndex("TekstPitanja")),
                                c.getString(c.getColumnIndex("Slika")),
                                c.getInt(c.getColumnIndex("TipOdgovora")),
                                c.getString(c.getColumnIndex("TacanOdgovor")),
                                c.getString(c.getColumnIndex("PonudjeniOdgovori")),
                                c.getInt(c.getColumnIndex("Odgovoreno")),
                                c.getInt(c.getColumnIndex("Segment")),
                                c.getString(c.getColumnIndex("TekstIspodPitanja")));
                        listDan.add(dan1);
                    } while (c.moveToNext());
                }
                dbHelper.close();
                c.close();


                for (Dan dan2 : listDan) {

                    if (dan2.getTipOdgovora() == 1 && dan2.getOdgovoreno() == 0) {
                        fragment = fm.findFragmentByTag("myFragmentTag");
                        if (fragment == null) {
                            FragmentTransaction ft = fm.beginTransaction();
                            fragment = new FirstFragment(dan2, dan, segment);
                            ft.addToBackStack(null);
                            ft.replace(R.id.fragment_frame, fragment);
                            ft.commit();
                            return;

                        }
                    } else {
                        if (dan2.getTipOdgovora() == 2 && dan2.getOdgovoreno() == 0) {
                            fragment = fm.findFragmentByTag("2");
                            if (fragment == null) {
                                FragmentTransaction ft = fm.beginTransaction();
                                fragment = new SecondFragment(dan2, dan, segment);
                                ft.addToBackStack(null);
                                ft.replace(R.id.fragment_frame, fragment);
                                ft.commit();
                                return;

                            }

                        } else {
                            if (dan2.getTipOdgovora() == 3 && dan2.getOdgovoreno() == 0) {
                                fragment = fm.findFragmentByTag("3");
                                if (fragment == null) {
                                    FragmentTransaction ft = fm.beginTransaction();
                                    fragment = new ThirdFragment(dan2, dan, segment);
                                    ft.addToBackStack(null);
                                    ft.replace(R.id.fragment_frame, fragment);
                                    ft.commit();
                                    return;

                                }

                            } else {
//                                if (dan2.getTipOdgovora() == 4 && dan2.getOdgovoreno() == 0) {
//                                    fragment = fm.findFragmentByTag("4");
//                                    if (fragment == null) {
//                                        FragmentTransaction ft = fm.beginTransaction();
//                                        fragment = new FourthFragment(dan2, dan, segment);
//                                        ft.addToBackStack(null);
//                                        ft.replace(R.id.fragment_frame, fragment);
//                                        ft.commit();
//                                        return;
//
//                                    }
//
//                                }
//                                if (dan2.getTipOdgovora() == 4 && dan2.getOdgovoreno() == 0 ) {
//                                    if (proc == false  &&  dan2.getRowid() == 127  ) {
//                                        fragment = fm.findFragmentByTag("Zadatak");
//                                        if (fragment == null) {
//                                            fm = getSupportFragmentManager();
//                                            FragmentTransaction ft = fm.beginTransaction();
//                                            fragment = new TaskDecimalNumberPercentageFragment();
//                                            ft.addToBackStack(null);
//                                            ft.replace(R.id.fragment_frame, fragment);
//                                            ft.commit();
//                                            return;
//
//                                        }
//                                    } else {

                                            fragment = fm.findFragmentByTag("4");
                                            if (fragment == null) {
                                                FragmentTransaction ft = fm.beginTransaction();
                                                fragment = new FourthFragment(dan2, dan, segment);
                                                ft.addToBackStack(null);
                                                ft.replace(R.id.fragment_frame, fragment);
                                                ft.commit();
                                                return;

                                       // }

                                   // }
                                }
                                else {

                                    if (dan2.getTipOdgovora() == 5 && dan2.getOdgovoreno() == 0) {
                                        fragment = fm.findFragmentByTag("5");
                                        if (fragment == null) {
                                            FragmentTransaction ft = fm.beginTransaction();
                                            fragment = new FifthFragment(dan2, dan, segment);
                                            ft.addToBackStack(null);
                                            ft.replace(R.id.fragment_frame, fragment);
                                            ft.commit();
                                            return;

                                        }

                                    } else {

                                        if (dan2.getTipOdgovora() == 6 && dan2.getOdgovoreno() == 0) {

                                            fragment = fm.findFragmentByTag("6");
                                            if (fragment == null) {
                                                FragmentTransaction ft = fm.beginTransaction();
                                                fragment = new TaskFragment(dan2, dan, segment);
                                                ft.addToBackStack(null);
                                                ft.replace(R.id.fragment_frame, fragment);
                                                ft.commit();
                                                return;

                                            }
                                        } else {
                                            if (dan2.getTipOdgovora() == 7 && dan2.getOdgovoreno() == 0) {
                                                fragment = fm.findFragmentByTag("7");
                                                if (fragment == null) {
                                                    FragmentTransaction ft = fm.beginTransaction();
                                                    fragment = new SeventhFragment(dan2, dan, segment);
                                                    ft.addToBackStack(null);
                                                    ft.replace(R.id.fragment_frame, fragment);
                                                    ft.commit();
                                                    return;

                                                }
                                            } else {


                                                if (dan2.getTipOdgovora() == 9 && dan2.getOdgovoreno() == 0) {
                                                    fragment = fm.findFragmentByTag("9");
                                                    if (fragment == null) {
                                                        FragmentTransaction ft = fm.beginTransaction();
                                                        fragment = new NinthFragment(dan2, dan, segment);
                                                        ft.addToBackStack(null);
                                                        ft.replace(R.id.fragment_frame, fragment);
                                                        ft.commit();
                                                        return;

                                                    }
                                                } else {
                                                    if (dan2.getTipOdgovora() == 8 && dan2.getOdgovoreno() == 0) {
                                                        fragment = fm.findFragmentByTag("8");
                                                        if (fragment == null) {
                                                            FragmentTransaction ft = fm.beginTransaction();
                                                            fragment = new EighthFragment(dan2, dan, segment);
                                                            ft.addToBackStack(null);
                                                            ft.replace(R.id.fragment_frame, fragment);
                                                            ft.commit();
                                                            return;

                                                        }
                                                    } else {
                                                        if (dan2.getTipOdgovora() == 10 && dan2.getOdgovoreno() == 0) {
                                                            fragment = fm.findFragmentByTag("8");
                                                            if (fragment == null) {
                                                                FragmentTransaction ft = fm.beginTransaction();
                                                                fragment = new TenthFragment(dan2, dan, segment);
                                                                ft.addToBackStack(null);
                                                                ft.replace(R.id.fragment_frame, fragment);
                                                                ft.commit();
                                                                return;

                                                            }
                                                        } else {
                                                            if (dan2.getTipOdgovora() == 11 && dan2.getOdgovoreno() == 0) {
                                                                fragment = fm.findFragmentByTag("11");
                                                                if (fragment == null) {
                                                                    FragmentTransaction ft = fm.beginTransaction();
                                                                    fragment = new EleventhFragment(dan2, dan, segment);
                                                                    ft.addToBackStack(null);
                                                                    ft.replace(R.id.fragment_frame, fragment);
                                                                    ft.commit();
                                                                    return;

                                                                }
                                                            } else {
                                                                if (dan2.getRowid() == 91 && dan2.getOdgovoreno() == 0) {
                                                                    fragment = fm.findFragmentByTag("11");
                                                                    if (fragment == null) {
                                                                        FragmentTransaction ft = fm.beginTransaction();
                                                                        fragment = new Thirteenth2Fragment(dan2, dan, segment);
                                                                        ft.addToBackStack(null);
                                                                        ft.replace(R.id.fragment_frame, fragment);
                                                                        ft.commit();
                                                                        return;

                                                                    }
                                                                }



                                                            }
                                                        }
                                                    }

                                                }
                                            }
                                        }

                                    }


                                }


                            }


                        }
                    }

                }

                switch (segment) {

                    case 1:

                        txtCestitamo.setText("Čestitamo, uspešno ste završili segment Sektor za poslovne aplikacije i Sektor ICT i operativnog poslovanja");
                        new AlertDialog.Builder(mContext)
                                // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                .setView(dialogView1)
                                .setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        ZatvoriSegmentDan3();
                                        NoviSegment();
                                        dialog.dismiss();
                                        ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);

                                    }
                                })

                                .show();

                        break;
                    case 2:

                        txtCestitamo.setText("Čestitamo, uspešno ste završili segment Pravnih poslova");
                        new AlertDialog.Builder(mContext)
                                // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                .setView(dialogView1)
                                .setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        ZatvoriSegmentDan3();
                                        NoviSegment();
                                        dialog.dismiss();
                                        ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);

                                    }
                                })

                                .show();

                        break;
                    case 3:

                        txtCestitamo.setText("Čestitamo, uspešno ste završili segment Logistika");
                        new AlertDialog.Builder(mContext)
                                // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                .setView(dialogView1)
                                .setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        ZatvoriSegmentDan3();
                                        NoviSegment();
                                        dialog.dismiss();
                                        ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);

                                    }
                                })

                                .show();


                        break;
                    case 4:
                        if (continueArray == false) {
                            fragment = fm.findFragmentByTag("Zadatak");
                            if (fragment == null) {
                                fm = getSupportFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                fragment = new ContinueArrayFragment();
                                ft.addToBackStack(null);
                                ft.replace(R.id.fragment_frame, fragment);
                                ft.commit();
                                return;

                            }
                        } else {

                            txtCestitamo.setText("Čestitamo, uspešno ste završili segment Veleprodaja");
                            new AlertDialog.Builder(mContext)
                                    // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                    .setView(dialogView1)
                                    .setCancelable(false)

                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            ZatvoriSegmentDan3();
                                            NoviSegment();
                                            dialog.dismiss();
                                            ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);

                                        }
                                    })

                                    .show();

                        }

                        break;
                    case 5:

                        txtCestitamo.setText("Čestitamo, uspešno ste završili segment VP IZ MP Zmaj");
                        new AlertDialog.Builder(mContext)
                                // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                .setView(dialogView1)
                                .setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        ZatvoriSegmentDan3();
                                        NoviSegment();
                                        dialog.dismiss();
                                        ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);

                                    }
                                })

                                .show();

                        break;

                    case 6:

                        txtCestitamo.setText("Čestitamo, uspešno ste završili segment Idea London");
                        new AlertDialog.Builder(mContext)
                                // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                .setView(dialogView1)
                                .setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        ZatvoriSegmentDan3();
                                        NoviSegment();
                                        dialog.dismiss();
                                        ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);

                                    }
                                })

                                .show();


                        break;

                    case 7:

                        txtCestitamo.setText("Čestitamo, uspešno ste završili segment Idea 401 Super Beograđanka");
                        new AlertDialog.Builder(mContext)
                                // .setTitle("Čestitamo, uspešno ste završili segment Opšte informacije")
                                .setView(dialogView1)
                                .setCancelable(false)

                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        ZatvoriSegmentDan3();
                                        NoviSegment();
                                        dialog.dismiss();
                                        ((ViewGroup) dialogView1.getParent()).removeView(dialogView1);

                                        fragment = fm.findFragmentByTag("IzborSegmenta");
                                        if (fragment == null) {
                                            fm = getSupportFragmentManager();
                                            FragmentTransaction ft = fm.beginTransaction();
                                            fragment = new IzborSegmentaFragment(dan, segment);
                                            ft.addToBackStack(null);
                                            ft.replace(R.id.fragment_frame, fragment);
                                            ft.commit();


                                        }


                                    }
                                })

                                .show();

                        break;


                    default:

                }
            }


            }
        catch(Exception ex)
            {
                new AlertDialog.Builder(mContext)
                        .setTitle("GRESKA!   " + ex.getMessage())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                dialog.dismiss();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame,
                                        new KodeksFragment(dan, segment)).commit();
                            }
                        })

                        .show();

            }
        finally{
                dbHelper.close();
            }


        }

        public static void storeDate (Context c){

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (!sharedPreferences.contains("dateTime")) {
                Date date = new Date();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);

                sharedPreferences.edit().putLong("dateTime", date.getTime()).apply();
            }

        }

        public static boolean retrieveDate (Context mContext){
            boolean ret = true;
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

            Date myDate = new Date(sharedPreferences.getLong("dateTime", 0));

            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int yearn = calendar.get(Calendar.YEAR);
            int monthn = calendar.get(Calendar.MONTH) + 1;
            int dayn = calendar.get(Calendar.DAY_OF_MONTH);
            int hourn = calendar.get(Calendar.HOUR_OF_DAY);

            Calendar c = Calendar.getInstance(); // use system date on first time for initialization.
            c.setTime(myDate);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);

            if (day == dayn) {
                ret = false;
                new AlertDialog.Builder(mContext)
                        .setTitle("Aplikacijan je zaključana do sutra !")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                System.exit(0);
                                dialog.dismiss();
                            }
                        })

                        .show();
            }
            return ret;

        }

        private void ZatvoriSegmentDan1 ()
        {
            dbHelper.openDataBase();
            ContentValues iuValues = new ContentValues();
            iuValues.put("Zapocet", 2);
            dbHelper.myDataBase.updateWithOnConflict("AkcijaSegment", iuValues, "Segment=" + segment + " AND  Dan=1", null, SQLiteDatabase.CONFLICT_ROLLBACK);
            dbHelper.close();

        }
        private void ZatvoriSegmentDan2 ()
        {
            dbHelper.openDataBase();
            ContentValues iuValues = new ContentValues();
            iuValues.put("Zapocet", 2);
            dbHelper.myDataBase.updateWithOnConflict("AkcijaSegment", iuValues, "Segment=" + segment + " AND  Dan=2", null, SQLiteDatabase.CONFLICT_ROLLBACK);
            dbHelper.close();

        }
        private void ZatvoriSegmentDan3 ()
        {
            dbHelper.openDataBase();
            ContentValues iuValues = new ContentValues();
            iuValues.put("Zapocet", 2);
            dbHelper.myDataBase.updateWithOnConflict("AkcijaSegment", iuValues, "Segment=" + segment + " AND  Dan=3", null, SQLiteDatabase.CONFLICT_ROLLBACK);
            dbHelper.close();

        }






}


