package rs.merkator.merkatorgejmifikacija;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Fragment fragment ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        new AlertDialog.Builder(this)
                .setTitle("Složite slagalicu!Prevlačite polja na odgovarajuću poziciju!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        dialog.dismiss();
                    }
                })

                .show();

        FragmentManager fm = getSupportFragmentManager();
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