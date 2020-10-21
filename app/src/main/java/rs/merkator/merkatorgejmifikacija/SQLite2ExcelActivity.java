package rs.merkator.merkatorgejmifikacija;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ajts.androidmads.library.SQLiteToExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SQLite2ExcelActivity extends AppCompatActivity {

   // EditText edtUser, edtContactNo;
    Button  btnExport; //btnSaveUser,
    ListView lvUsers;
    CustomAdapter lvUserAdapter;
    List<Dan> usersList = new ArrayList<>();

    DataBaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_2_xl);

//        assert getSupportActionBar() != null;
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DataBaseHelper(getApplicationContext());


//        edtUser = (EditText) findViewById(R.id.edt_user);
//        edtContactNo = (EditText) findViewById(R.id.edt_c_no);
//        btnSaveUser = (Button) findViewById(R.id.btn_save_user);
        btnExport = (Button) findViewById(R.id.btn_export);



        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                String directory_path =Environment.getExternalStorageDirectory() + File.separator + "Download/";
                //String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Download/";
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                // Export SQLite DB as EXCEL FILE
                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(),"Mercator" , directory_path);
                sqliteToExcel.exportAllTables("Mercator.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart()
                    {

                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Toast.makeText(SQLite2ExcelActivity.this, "Successfully Exported!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
    }

    boolean validate(EditText editText) {
        if (editText.getText().toString().length() == 0) {
            editText.setError("Field Required");
            editText.requestFocus();
        }
        return editText.getText().toString().length() > 0;
    }



}