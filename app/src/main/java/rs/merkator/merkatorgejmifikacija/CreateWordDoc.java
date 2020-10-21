package rs.merkator.merkatorgejmifikacija;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.util.*;
import java.io.*;
import java.lang.*;
//import org.apache.poi.hwpf.*;
//import org.apache.poi.hwpf.usermodel.*;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.poifs.filesystem.*;
//import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.poi.xwpf.usermodel.XWPFStyles;
//import org.apache.xmlbeans.XmlException;

public class CreateWordDoc  {

        public static String main(Context cnt) {

                String myFilePath=null;

                PdfDocument myPdfDocument = new PdfDocument();
                PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(900,600,1).create();
                PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);


                DataBaseHelper dbHelper = new DataBaseHelper(cnt);
                String ime="", pol="", sluzba="", namePath="Evaluacija_";
                try {
                        dbHelper.openDataBase();
                        Cursor c,c1;
                        String sql ="SELECT Ime, Prezime, Pol, Sluzba FROM Korisnik where Administrator=0";
                        c=dbHelper.myDataBase.rawQuery(sql,null);
                        if (c.getCount()>0)
                        {
                        c.moveToFirst();
                        ime=c.getString(0)+" "+ c.getString(1);
                        pol="Pol: "+c.getString(2);
                        sluzba="Služba: "+c.getString(3);
                        namePath+=c.getString(0)+"_"+ c.getString(1);


                Paint myPaint = new Paint();
                Paint myPaintOdgovor = new Paint();
                String myString = "Korisnik:  "+ime;
                int x = 5, y=25;
                        myPaintOdgovor.setTextSize(12);
                        myPaintOdgovor.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
                myPage.getCanvas().drawText(myString, x, y, myPaint);
                y+=myPaint.descent()-myPaint.ascent();
                myPage.getCanvas().drawText(pol, x, y, myPaint);
                y+=myPaint.descent()-myPaint.ascent();
                myPage.getCanvas().drawText(sluzba, x, y, myPaint);
                        y+=myPaint.descent()-myPaint.ascent();
                        y+=myPaint.descent()-myPaint.ascent();
                        y+=myPaint.descent()-myPaint.ascent();


                String sql1 ="SELECT rowid, Odgovor FROM Evaluacija where Odgovoreno=1 order by CAST(rowid as int)";
                c1=dbHelper.myDataBase.rawQuery(sql1,null);
                String pitanje1="",pitanje2="",pitanje3="",pitanje4="",pitanje5="",pitanje6="",pitanje7="",pitanje8="",pitanje9=""
                        ,pitanje10="",pitanje11="",pitanje12="",pitanje13="",pitanje14="",pitanje15="",pitanje16="";
                        String odgovor1="Odgovor:",odgovor2="Odgovor:",odgovor3="Odgovor:",odgovor4="Odgovor:",odgovor5="Odgovor:",odgovor6="Odgovor:"
                                ,odgovor7="Odgovor:",odgovor8="Odgovor:",odgovor9="Odgovor:"
                                ,odgovor10="Odgovor:",odgovor11="Odgovor:",odgovor12="Odgovor:",odgovor13="Odgovor:",odgovor14="Odgovor:",odgovor15="Odgovor:",odgovor16="Odgovor:";


                if (c1.moveToFirst()) {
                        do {
                                if (c1.getInt(0) == 1) {
                                        pitanje1 = "1. U kojoj meri je ispunjena svrha programa?";
                                        odgovor1 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 2) {
                                        pitanje2 = "2. Kako biste ocenili ukupan sadržaj programa koji ste prošli za ovih 5 dana?";
                                        odgovor2 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 3) {
                                        pitanje3 = "3. Kako biste ocenili teorijsku pokrivenost segmenata kroz koje ste prolazili?";
                                        odgovor3 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 4) {
                                        pitanje4 = "4. Kako biste ocenili stručnost kolega uzevši u obzir teme koje su Vama izlagali?";
                                        odgovor4 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 5) {
                                        pitanje5 = "5. Kako biste ocenili prenošenje znanja i komunikacijske veštine kolega sa kojima ste stupali u kontakt tokom programa?";
                                        odgovor5 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 6) {
                                        pitanje6 = "6. Kako biste ocenili gostoprimstvo kolega u sektoru koje ste obilazili?";
                                        odgovor6 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 7) {
                                        pitanje7 = "7. Molimo Vas napišite nam Vaše mišljenje vezano za pristupačnost i ponašanje kolega prema Vama tokom trajanja programa?";
                                        odgovor7 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 8) {
                                        pitanje8 = "8. Kako biste ocenili korisnost informacija koje ste dobili u toku trajanja programa?";
                                        odgovor8 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 9) {
                                        pitanje9 = "9. Navedite teme koje zahtevaju kraće izlaganje";
                                        odgovor9 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 10) {
                                        pitanje10 = "10. Navedite teme koje nisu obuhvatile sve neophodne informacije";
                                        odgovor10 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 11) {
                                        pitanje11 = "11.Navedite koji deo programa Vam je bio najzanimljiviji";
                                        odgovor11 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 12) {
                                        pitanje12 = "12.Kako biste ocenili tempo/dinamiku programa?";
                                        odgovor12 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 13) {
                                        pitanje13 = "13.Kako bi ste ocenili raspored obilazaka?";
                                        odgovor13 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 14) {
                                        pitanje14 = "14.U kojoj meri je program uvođenja novozaposelnog u kompaniju ispunio Vaša očekivanja?";
                                        odgovor14 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 15) {
                                        pitanje15 = "15.Vaša konačna ocena programa?";
                                        odgovor15 += c1.getString(1);
                                }
                                if (c1.getInt(0) == 16) {
                                        pitanje16 = "16.Upišite komentar vezan za sveobuhvatnu ocenu programa uvođenja novozaposlenog u posao";
                                        odgovor16 += c1.getString(1);
                                }

                        } while (c1.moveToNext());

                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje1, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor1, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje2, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor2, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje3, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor3, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje4, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor4, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje5, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor5, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje6, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor6, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje7, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor7, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje8, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor8, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje9, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor9, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje10, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor10, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje11, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor11, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje12, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor12, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje13, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor13, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje14, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor14, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje15, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor15, x, y, myPaintOdgovor);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(pitanje16, x, y, myPaint);
                        y += myPaint.descent() - myPaint.ascent();
                        myPage.getCanvas().drawText(odgovor16, x, y, myPaintOdgovor);

                        myPdfDocument.finishPage(myPage);



                         myFilePath = Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/Evaluacija/"+namePath+".pdf";
                        File myFile = new File(myFilePath);
                        try {
                                myPdfDocument.writeTo(new FileOutputStream(myFile));
                        }
                        catch (Exception e){
                                e.printStackTrace();

                        }

                        myPdfDocument.close();
                }
                else
                {
                        return myFilePath;
                }
                }
                } catch (SQLiteException ex) {
                        String s = "";
                } finally {

                        dbHelper.close();
                }





        return myFilePath;


        }
}


