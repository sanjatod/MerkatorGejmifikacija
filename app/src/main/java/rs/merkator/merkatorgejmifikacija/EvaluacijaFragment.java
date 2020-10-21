package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EvaluacijaFragment  extends Fragment {

    DataBaseHelper dbHelper;
    ViewGroup container;
    View view ;
    Fragment fragment ;
    FragmentManager fm ;
    Button btn1,btn2,btn3,btn4,btn5;
    Button btn6,btn7,btn8,btn9,btn10;
    Button btn11,btn12,btn13,btn14,btn15;
    LinearLayout linearLayout,linearLayout1,linearLayout2;
    int buttonChack=0;
    int buttonChack1=0;
    int buttonChack2=0;
    EditText etxt;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        this.container=container;
        // Inflate the layout for this fragment
        dbHelper=new DataBaseHelper(getActivity());
        switch (BaseActivity.evaluacijastep) {
            case 1:
                view=inflater.inflate(R.layout.fragment_evaluacija_1, container, false);
                break;
            case 2:
                view=inflater.inflate(R.layout.fragment_evaluacija_2, container, false);

                break;
            case 3:view=inflater.inflate(R.layout.fragment_evaluacija_3, container, false);
                break;

            case 4:
                view=inflater.inflate(R.layout.fragment_evaluacija_4, container, false);
                break;
            case 5:
                view=inflater.inflate(R.layout.fragment_evaluacija_5, container, false);
                break;
            case 6:
                view=inflater.inflate(R.layout.fragment_evaluacija_6, container, false);
                break;
            case 7:
                view=inflater.inflate(R.layout.fragment_evaluacija_7, container, false);
                break;
            case 8:
                view=inflater.inflate(R.layout.fragment_evaluacija_8, container, false);

                break;
            case 9:
                view=inflater.inflate(R.layout.fragment_evaluacija_9, container, false);
                break;
            case 10:
                view=inflater.inflate(R.layout.fragment_evaluacija_10, container, false);
                break;

            case 11:
                view=inflater.inflate(R.layout.fragment_evaluacija_11, container, false);
                break;
            case 12:
                view=inflater.inflate(R.layout.fragment_evaluacija_12, container, false);
                break;
            case 0:
                view=inflater.inflate(R.layout.fragment_evaluacija_1, container, false);
                break;
            case 13:
                view=inflater.inflate(R.layout.fragment_end, container, false);

                break;
            default:

                break;
        }

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch (BaseActivity.evaluacijastep) {

            case 2:
                linearLayout = view.findViewById(R.id.linear);
                btn1= (Button) view.findViewById(R.id.button7);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=1;
                        changeBackground(btn1,linearLayout);
                    }
                });
                setBackground(btn1);
                btn2= (Button) view.findViewById(R.id.button8);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=2;
                        changeBackground(btn2,linearLayout);
                    }
                });

                setBackground(btn2);
                btn3= (Button) view.findViewById(R.id.button9);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=3;
                        changeBackground(btn3,linearLayout);
                    }
                });

                setBackground(btn3);
                btn4= (Button) view.findViewById(R.id.button10);
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=4;
                        changeBackground(btn4,linearLayout);
                    }
                });

                setBackground(btn4);
                btn5= (Button) view.findViewById(R.id.button11);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=5;
                        changeBackground(btn5,linearLayout);
                    }
                });
                setBackground(btn5);




                break;
            case 3:
                linearLayout = view.findViewById(R.id.linear);
                btn1= (Button) view.findViewById(R.id.button7);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=1;
                        changeBackground(btn1,linearLayout);
                    }
                });
                setBackground(btn1);
                btn2= (Button) view.findViewById(R.id.button8);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=2;
                        changeBackground(btn2,linearLayout);
                    }
                });

                setBackground(btn2);
                btn3= (Button) view.findViewById(R.id.button9);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=3;
                        changeBackground(btn3,linearLayout);
                    }
                });
                setBackground(btn3);
                btn4= (Button) view.findViewById(R.id.button10);
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=4;
                        changeBackground(btn4,linearLayout);
                    }
                });
                setBackground(btn4);
                btn5= (Button) view.findViewById(R.id.button11);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=5;
                        changeBackground(btn5,linearLayout);
                    }
                });
                setBackground(btn5);

                linearLayout1 = view.findViewById(R.id.linear1);
                btn6= (Button) view.findViewById(R.id.button1);
                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=1;
                        changeBackground(btn6,linearLayout1);
                    }
                });
                setBackground(btn6);
                btn7= (Button) view.findViewById(R.id.button2);
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=2;
                        changeBackground(btn7,linearLayout1);
                    }
                });
                setBackground(btn7);
                btn8= (Button) view.findViewById(R.id.button3);
                btn8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=3;
                        changeBackground(btn8,linearLayout1);
                    }
                });
                setBackground(btn8);
                btn9= (Button) view.findViewById(R.id.button4);
                btn9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=4;
                        changeBackground(btn9,linearLayout1);
                    }
                });
                setBackground(btn9);
                btn10= (Button) view.findViewById(R.id.button5);
                btn10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=5;
                        changeBackground(btn10,linearLayout1);
                    }
                });
                setBackground(btn10);


                break;

            case 4:
                linearLayout = view.findViewById(R.id.linear);
                btn1= (Button) view.findViewById(R.id.button7);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=1;
                        changeBackground(btn1,linearLayout);
                    }
                });
                setBackground(btn1);
                btn2= (Button) view.findViewById(R.id.button8);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=2;
                        changeBackground(btn2,linearLayout);
                    }
                });

                setBackground(btn2);
                btn3= (Button) view.findViewById(R.id.button9);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=3;
                        changeBackground(btn3,linearLayout);
                    }
                });
                setBackground(btn3);
                btn4= (Button) view.findViewById(R.id.button10);
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=4;
                        changeBackground(btn4,linearLayout);
                    }
                });
                setBackground(btn4);
                btn5= (Button) view.findViewById(R.id.button11);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=5;
                        changeBackground(btn5,linearLayout);
                    }
                });
                setBackground(btn5);

                linearLayout1 = view.findViewById(R.id.linear1);
                btn6= (Button) view.findViewById(R.id.button1);
                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=1;
                        changeBackground(btn6,linearLayout1);
                    }
                });
                setBackground(btn6);
                btn7= (Button) view.findViewById(R.id.button2);
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=2;
                        changeBackground(btn7,linearLayout1);
                    }
                });
                setBackground(btn7);
                btn8= (Button) view.findViewById(R.id.button3);
                btn8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=3;
                        changeBackground(btn8,linearLayout1);
                    }
                });
                setBackground(btn8);
                btn9= (Button) view.findViewById(R.id.button4);
                btn9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=4;
                        changeBackground(btn9,linearLayout1);
                    }
                });
                setBackground(btn9);
                btn10= (Button) view.findViewById(R.id.button5);
                btn10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=5;
                        changeBackground(btn10,linearLayout1);
                    }
                });
                setBackground(btn10);
                linearLayout2 = view.findViewById(R.id.linear2);
                btn11= (Button) view.findViewById(R.id.button13);
                btn11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack2=1;
                        changeBackground(btn11,linearLayout2);
                    }
                });
                setBackground(btn11);
                btn12= (Button) view.findViewById(R.id.button14);
                btn12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack2=2;
                        changeBackground(btn12,linearLayout2);
                    }
                });

                setBackground(btn12);
                btn13= (Button) view.findViewById(R.id.button15);
                btn13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack2=3;
                        changeBackground(btn13,linearLayout2);
                    }
                });

                setBackground(btn13);
                btn14= (Button) view.findViewById(R.id.button16);
                btn14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack2=4;
                        changeBackground(btn14,linearLayout2);
                    }
                });

                setBackground(btn14);
                btn15= (Button) view.findViewById(R.id.button17);
                btn15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack2=5;
                        changeBackground(btn15,linearLayout2);
                    }
                });
                setBackground(btn15);

                break;
            case 5:
                etxt= (EditText) view.findViewById(R.id.etxt);

                break;
            case 6:
                linearLayout = view.findViewById(R.id.linear);
                btn1= (Button) view.findViewById(R.id.button7);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=1;
                        changeBackground(btn1,linearLayout);
                    }
                });
                setBackground(btn1);
                btn2= (Button) view.findViewById(R.id.button8);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=2;
                        changeBackground(btn2,linearLayout);
                    }
                });

                setBackground(btn2);
                btn3= (Button) view.findViewById(R.id.button9);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=3;
                        changeBackground(btn3,linearLayout);
                    }
                });

                setBackground(btn3);
                btn4= (Button) view.findViewById(R.id.button10);
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=4;
                        changeBackground(btn4,linearLayout);
                    }
                });

                setBackground(btn4);
                btn5= (Button) view.findViewById(R.id.button11);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=5;
                        changeBackground(btn5,linearLayout);
                    }
                });
                setBackground(btn5);

                break;
            case 7:
                etxt= (EditText) view.findViewById(R.id.etxt);

                break;
            case 8:
                etxt= (EditText) view.findViewById(R.id.etxt);
                break;
            case 9:
                etxt= (EditText) view.findViewById(R.id.etxt);
                break;
            case 10:
                linearLayout = view.findViewById(R.id.linear);
                btn1= (Button) view.findViewById(R.id.button7);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=1;
                        changeBackground(btn1,linearLayout);
                    }
                });
                setBackground(btn1);
                btn2= (Button) view.findViewById(R.id.button8);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=2;
                        changeBackground(btn2,linearLayout);
                    }
                });

                setBackground(btn2);
                btn3= (Button) view.findViewById(R.id.button9);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=3;
                        changeBackground(btn3,linearLayout);
                    }
                });
                setBackground(btn3);
                btn4= (Button) view.findViewById(R.id.button10);
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=4;
                        changeBackground(btn4,linearLayout);
                    }
                });
                setBackground(btn4);
                btn5= (Button) view.findViewById(R.id.button11);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=5;
                        changeBackground(btn5,linearLayout);
                    }
                });
                setBackground(btn5);

                linearLayout1 = view.findViewById(R.id.linear1);
                btn6= (Button) view.findViewById(R.id.button1);
                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=1;
                        changeBackground(btn6,linearLayout1);
                    }
                });
                setBackground(btn6);
                btn7= (Button) view.findViewById(R.id.button2);
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=2;
                        changeBackground(btn7,linearLayout1);
                    }
                });
                setBackground(btn7);
                btn8= (Button) view.findViewById(R.id.button3);
                btn8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=3;
                        changeBackground(btn8,linearLayout1);
                    }
                });
                setBackground(btn8);
                btn9= (Button) view.findViewById(R.id.button4);
                btn9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=4;
                        changeBackground(btn9,linearLayout1);
                    }
                });
                setBackground(btn9);
                btn10= (Button) view.findViewById(R.id.button5);
                btn10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=5;
                        changeBackground(btn10,linearLayout1);
                    }
                });
                setBackground(btn10);

                break;

            case 11:
                linearLayout = view.findViewById(R.id.linear);
                btn1= (Button) view.findViewById(R.id.button7);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=1;
                        changeBackground(btn1,linearLayout);
                    }
                });
                setBackground(btn1);
                btn2= (Button) view.findViewById(R.id.button8);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=2;
                        changeBackground(btn2,linearLayout);
                    }
                });

                setBackground(btn2);
                btn3= (Button) view.findViewById(R.id.button9);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=3;
                        changeBackground(btn3,linearLayout);
                    }
                });
                setBackground(btn3);
                btn4= (Button) view.findViewById(R.id.button10);
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=4;
                        changeBackground(btn4,linearLayout);
                    }
                });
                setBackground(btn4);
                btn5= (Button) view.findViewById(R.id.button11);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack=5;
                        changeBackground(btn5,linearLayout);
                    }
                });
                setBackground(btn5);

                linearLayout1 = view.findViewById(R.id.linear1);
                btn6= (Button) view.findViewById(R.id.button1);
                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=1;
                        changeBackground(btn6,linearLayout1);
                    }
                });
                setBackground(btn6);
                btn7= (Button) view.findViewById(R.id.button2);
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=2;
                        changeBackground(btn7,linearLayout1);
                    }
                });
                setBackground(btn7);
                btn8= (Button) view.findViewById(R.id.button3);
                btn8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=3;
                        changeBackground(btn8,linearLayout1);
                    }
                });
                setBackground(btn8);
                btn9= (Button) view.findViewById(R.id.button4);
                btn9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=4;
                        changeBackground(btn9,linearLayout1);
                    }
                });
                setBackground(btn9);
                btn10= (Button) view.findViewById(R.id.button5);
                btn10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonChack1=5;
                        changeBackground(btn10,linearLayout1);
                    }
                });
                setBackground(btn10);

                break;
            case 12:
                etxt= (EditText) view.findViewById(R.id.etxt);

                break;
            case 0:

                break;
            default:
                //view=inflater.inflate(R.layout.fragment_evaluacija_1, container, false);
                break;
        }

        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
                        switch (BaseActivity.evaluacijastep) {
                            case 2:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", buttonChack);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=1", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                break;
                            case 3:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", buttonChack);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=2", null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                    ContentValues iuValues1 = new ContentValues();
                                    iuValues1.put("Odgovoreno", "1");
                                    iuValues1.put("Odgovor", buttonChack1);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues1, "rowid=3", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                break;

                            case 4:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", buttonChack);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=4", null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                    ContentValues iuValues1 = new ContentValues();
                                    iuValues1.put("Odgovoreno", "1");
                                    iuValues1.put("Odgovor", buttonChack1);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues1, "rowid=5", null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                    ContentValues iuValues2 = new ContentValues();
                                    iuValues2.put("Odgovoreno", "1");
                                    iuValues2.put("Odgovor", buttonChack2);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues2, "rowid=6", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                break;
                            case 5:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", etxt.getText().toString());
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=7", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                break;
                            case 6:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", buttonChack);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=8", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                break;
                            case 7:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", etxt.getText().toString());
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=9", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }


                                break;
                            case 8:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", etxt.getText().toString());
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=10", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                break;
                            case 9:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", etxt.getText().toString());
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=11", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                break;
                            case 10:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", buttonChack);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=12", null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                    ContentValues iuValues1 = new ContentValues();
                                    iuValues1.put("Odgovoreno", "1");
                                    iuValues1.put("Odgovor", buttonChack1);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues1, "rowid=13", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }
                                break;

                            case 11:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", buttonChack);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=14", null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                    ContentValues iuValues1 = new ContentValues();
                                    iuValues1.put("Odgovoreno", "1");
                                    iuValues1.put("Odgovor", buttonChack1);
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues1, "rowid=15", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }
                                break;
                            case 12:
                                try {
                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Odgovoreno", "1");
                                    iuValues.put("Odgovor", etxt.getText().toString());
                                    dbHelper.myDataBase.updateWithOnConflict("Evaluacija", iuValues, "rowid=16", null, SQLiteDatabase.CONFLICT_ROLLBACK);

                                } catch (SQLiteException ex) {
                                    String s = "";
                                } finally {

                                    dbHelper.close();
                                }

                                break;
                            case 13:
                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Zapocet", 2);
                                dbHelper.myDataBase.updateWithOnConflict("AkcijaSegment", iuValues, "Segment=7 AND  Dan=3" , null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                dbHelper.close();

                                break;
                            case 0:

                                break;
                            default:
                                //view=inflater.inflate(R.layout.fragment_evaluacija_1, container, false);
                                break;
                        }

                        BaseActivity.evaluacija=true;
                        BaseActivity.evaluacijastep+=1;
                        getActivity().onBackPressed();

                    }

                });

    }

    private void   setBackground(Button btn) {
        btn.setBackgroundColor(Color.WHITE);
        btn.setGravity(Gravity.CENTER);
        btn.setTextColor(getResources().getColor(R.color.red));
    }

    private void   changeBackground(Button btn, LinearLayout linearLayout )
    {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            if (linearLayout.getChildAt(i) instanceof Button) {
                ((Button) linearLayout.getChildAt(i)).setBackgroundColor(Color.WHITE);
                ((Button) linearLayout.getChildAt(i)).setTextColor(Color.WHITE);
                ((Button) linearLayout.getChildAt(i)).setTextColor(getResources().getColor(R.color.red));

            }
        }
        btn.setBackgroundColor(getResources().getColor(R.color.red));
        btn.setGravity(Gravity.CENTER);
        btn.setTextColor(Color.WHITE);
    }



}