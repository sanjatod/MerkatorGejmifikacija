package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {



    private static String DB_PATH = "/data/data/rs.merkator.merkatorgejmifikacija/databases/";
    //private static String DB_PATH = "/data/user/0/rs.merkator.merkatorgejmifikacija/databases/";

    private static String DB_NAME = "Merkator";

    public SQLiteDatabase myDataBase;

    private final Context myContext;
    static int DB_VERSION_new=1;

//	  static final String TABLE = "emp";
//	  static final String C_ID = "_id";
//	  static final String C_ENAME = "ename";
//	  static final String C_DESIGNATION = "designation";
//	  static final String C_SALARY = "salary";



    public DataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION_new);
        this.myContext = context;




    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create a first version of your DB in this method only
//	    db.execSQL("CREATE TABLE " + TABLE + " ( " + C_ID
//	        + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_ENAME + " text, "
//	        + C_DESIGNATION + " text, " + C_SALARY + " text )");
        //this.onUpgrade(db, 1, DB_VERSION_new); // run onUpgrade manually


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub

        if(newVersion > oldVersion)
        {
            try {
                copyDatabaseToSdCard();
                copyDataBase();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();
        if(dbExist)
        {
            //do nothing - database already exist
            this.getWritableDatabase();
        }else{
            try {
                //this.getWritableDatabase();
                this.getReadableDatabase();
                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }




        }





    }



    public boolean checkDataBase(){

        SQLiteDatabase checkDB = null;
        String myPath = DB_PATH + DB_NAME;

        try{

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            //DB_VERSION_Old=checkDB.getVersion();


        }catch(SQLiteException e)
        {

            //database does't exist yet.
             String s="";
//			 try {
//				copyDataBase();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

        }

        //checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    public void copyDataBase() throws IOException{

        AdminPanel.ImportDb();




//        //Open your local db as the input stream
//        InputStream myInput = myContext.getAssets().open(DB_NAME);
//
//        // Path to the just created empty db
//        String outFileName = DB_PATH + DB_NAME;
//
//        //Open the empty db as the output stream
//        OutputStream myOutput = new FileOutputStream(outFileName);
//
//        //transfer bytes from the inputfile to the outputfile
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myInput.read(buffer))>0){
//            myOutput.write(buffer, 0, length);
//        }
//
//        //Close the streams
//        myOutput.flush();
//        myOutput.close();
//        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        try
        {
            String myPath = DB_PATH + DB_NAME;



            myDataBase = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);//, SQLiteDatabase.OPEN_READWRITE
        }
        catch (SQLException ex)
        {
            throw ex;
        }


    }

    public void deleteDataBase() throws SQLException{

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        this.myContext.deleteDatabase(myPath);


    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();


        super.close();

    }

    //@Override
    public  void execSQL (String upit)
    {

        if(myDataBase != null)
            myDataBase.execSQL(upit);

    }


    public String  copyDatabaseToSdCard() {
        //Log.e("Databasehealper", "********************************");


        OutputStream out=null;
        File f2=null;
        try {
            File f1 = new File(DB_PATH + DB_NAME);
            if (f1.exists()) {

                f2 = new File(Environment.getExternalStorageDirectory()+ File.separator + "Download/Gejmifikacija/Backup" + File.separator + "Merkator");
                f2.createNewFile();
                InputStream in = new FileInputStream(f1);
                out = new FileOutputStream(f2);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return f2.getPath().toString();
    }

    public void ClineDb()
    {
        try {
            openDataBase();
            myDataBase.beginTransaction();
            myDataBase.delete("Korisnik", "Administrator=0", null);
            ContentValues iuValues = new ContentValues();
            iuValues.put("Odgovoreno", 0);
            myDataBase.updateWithOnConflict("Dan1", iuValues, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
            myDataBase.updateWithOnConflict("Dan2", iuValues, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
            myDataBase.updateWithOnConflict("Dan3", iuValues, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
            myDataBase.updateWithOnConflict("Evaluacija", iuValues, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
            myDataBase.execSQL("delete from AkcijaDan");
            myDataBase.execSQL("delete from AkcijaSegment");
            myDataBase.execSQL("delete from Kodeks");
           // myDataBase.delete("AkcijaDan", "1=1", null);
           // myDataBase.delete("AkcijaSegment", "1=1", null);
           // myDataBase.delete("Kodeks", "1=1", null);
            myDataBase.setTransactionSuccessful();
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally {
            myDataBase.endTransaction();
            close();
        }

    }

    public void CleanDb()
    {
        try {
            openDataBase();
            myDataBase.beginTransaction();
            myDataBase.delete("Korisnik", "Administrator=0", null);
            ContentValues iuValues = new ContentValues();
            iuValues.put("Odgovoreno", 0);
            myDataBase.updateWithOnConflict("Dan1", iuValues, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
            myDataBase.updateWithOnConflict("Dan2", iuValues, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
            myDataBase.updateWithOnConflict("Dan3", iuValues, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
            myDataBase.updateWithOnConflict("Evaluacija", iuValues, null, null, SQLiteDatabase.CONFLICT_ROLLBACK);
            myDataBase.delete("AkcijaDan", null, null);
            myDataBase.delete("AkcijaSegment", null, null);
            myDataBase.delete("Kodeks", null, null);
            myDataBase.setTransactionSuccessful();
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally {
            myDataBase.endTransaction();
            close();
        }

    }

    public void DeleteDataDb()
    {
        try {
            openDataBase();
            myDataBase.beginTransaction();
            myDataBase.delete("Korisnik", "Administrator=0", null);
            myDataBase.delete("Dan1", null, null);
            myDataBase.delete("Dan2", null, null);
            myDataBase.delete("Dan3", null, null);
            myDataBase.delete("Evaluacija", null, null);
            myDataBase.delete("AkcijaDan", null, null);
            myDataBase.delete("AkcijaSegment", null, null);
            myDataBase.delete("Kodeks", null, null);
            myDataBase.setTransactionSuccessful();
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally {
            myDataBase.endTransaction();
            close();
        }

    }




}

