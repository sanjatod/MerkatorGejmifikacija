package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;


public class WelcomeFragment extends Fragment {

    DataBaseHelper dbHelper;
    private int dan;
    private int segment;
    TextView txtWelcome;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    public WelcomeFragment(int dan, int segment)
    {
        this.dan = dan;
        this.segment=segment;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtWelcome = view.findViewById(R.id.txtWelcome);
        dbHelper=new DataBaseHelper(getActivity());

        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dan == 1 && segment == 2) {
                            try {

                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Segment", segment + 1);
                                iuValues.put("Dan", 1);
                                iuValues.put("Zapocet", 1);
                                dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                            } catch (Exception ex) {
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("GRESKA!   " + ex.getMessage())
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                dialog.dismiss();
                                            }
                                        })

                                        .show();
                            } finally {

                                dbHelper.close();
                            }
                            BaseActivity.segment = +1;

                            getActivity().onBackPressed();

                        } else {

                            if (dan == 1 && segment == 3) {
                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 1);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

                            } else {

                                if (dan == 1 && segment == 4) {
                                    try {

                                        dbHelper.openDataBase();
                                        ContentValues iuValues = new ContentValues();
                                        iuValues.put("Segment", segment + 1);
                                        iuValues.put("Dan", 1);
                                        iuValues.put("Zapocet", 1);
                                        dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                    } catch (Exception ex) {
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle("GRESKA!   " + ex.getMessage())
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int whichButton) {
                                                        dialog.dismiss();
                                                    }
                                                })

                                                .show();
                                    } finally {

                                        dbHelper.close();
                                    }
                                    BaseActivity.segment = +1;

                                    getActivity().onBackPressed();

                                } else {
                                    if (dan == 1 && segment == 5) {
                                        try {

                                            dbHelper.openDataBase();
                                            ContentValues iuValues = new ContentValues();
                                            iuValues.put("Segment", segment + 1);
                                            iuValues.put("Dan", 1);
                                            iuValues.put("Zapocet", 1);
                                            dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                        } catch (Exception ex) {
                                            new AlertDialog.Builder(getActivity())
                                                    .setTitle("GRESKA!   " + ex.getMessage())
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int whichButton) {
                                                            dialog.dismiss();
                                                        }
                                                    })

                                                    .show();
                                        } finally {

                                            dbHelper.close();
                                        }
                                        BaseActivity.segment = +1;

                                        getActivity().onBackPressed();

                                    } else {
                                        if (dan == 1) {

                                            //FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                                            //OrganizationalSchemeFragment w1 = new OrganizationalSchemeFragment(dan, segment);
                                            //ft1.replace(R.id.fragment_frame, w1);
                                            //ft1.commit();

                                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                                            MoveFragment w1 = new MoveFragment(dan, segment);
                                            ft1.replace(R.id.fragment_frame, w1);
                                            ft1.commit();
                                        }
                                    }
                                }

                            }

                            if (dan == 2 && segment == 0) {

                                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                                TaskFragment t = new TaskFragment(null, dan, segment);
                                ft1.replace(R.id.fragment_frame, t);
                                ft1.commit();

                            }

                            if (dan == 2 && segment == 1) {

                                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                                TaskFragment t = new TaskFragment(null, dan, segment);
                                ft1.replace(R.id.fragment_frame, t);
                                ft1.commit();

                            }

                            if (dan == 2 && segment == 2) {

//                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
//                            OrganizationalSchemeFragment w1 = new OrganizationalSchemeFragment(dan, segment);
//                            ft1.replace(R.id.fragment_frame, w1);
//                            ft1.commit();

                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 2);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

                            }

                            if (dan == 2 && segment == 3) {

//                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
//                            OrganizationalSchemeFragment w1 = new OrganizationalSchemeFragment(dan, segment);
//                            ft1.replace(R.id.fragment_frame, w1);
//                            ft1.commit();

                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 2);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

                            }
                            if (dan == 2 && segment == 4) {
                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 2);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

//                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
//                            OrganizationalSchemeFragment w1 = new OrganizationalSchemeFragment(dan, segment);
//                            ft1.replace(R.id.fragment_frame, w1);
//                            ft1.commit();

                            }

                            if (dan == 3 && segment == 0) {
//
//                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
//                            OrganizationalSchemeFragment w1 = new OrganizationalSchemeFragment(dan, segment);
//                            ft1.replace(R.id.fragment_frame, w1);
//                            ft1.commit();
                                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                                ImageViewFragment w1 = new ImageViewFragment(dan, segment);
                                ft1.replace(R.id.fragment_frame, w1);
                                ft1.commit();

                            }

                            if (dan == 3 && segment == 1) {

                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 3);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

                            }

                            if (dan == 3 && segment == 2) {

                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 3);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();

                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

                            }
                            if (dan == 3 && segment == 3) {

                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 3);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

                            }
                            if (dan == 3 && segment == 4) {

                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 3);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

                            }

                            if (dan == 3 && segment == 5) {

                                try {

                                    dbHelper.openDataBase();
                                    ContentValues iuValues = new ContentValues();
                                    iuValues.put("Segment", segment + 1);
                                    iuValues.put("Dan", 3);
                                    iuValues.put("Zapocet", 1);
                                    dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
                                } catch (Exception ex) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("GRESKA!   " + ex.getMessage())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int whichButton) {
                                                    dialog.dismiss();
                                                }
                                            })

                                            .show();
                                } finally {

                                    dbHelper.close();
                                }
                                BaseActivity.segment = +1;

                                getActivity().onBackPressed();

                            }
                        }


                    }
                });

        if(dan==1)
        {
            switch (segment) {
                case 0:

                    txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  OPŠTE INFORMACIJE");

                    break;
                case 1:
                    txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  IDEA AKADEMIJA");

                    break;
                case 2:
                    txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  SLUŽBA REGRUTACIJE I SELEKCIJE");

                    break;

                case 3:
                    txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Sektor\n  ORGANIZACIONOG DIZAJNA SLUŽBA PERSONALNE EVIDENCIJE");

                    break;
                case 4:
                    txtWelcome.setText("DOBRO DOŠLI U MERCATOR S\n RODA MEGA 428");

                    break;

                case 5:
                    txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n \n Segment\n MALOPRODAJNI OBJEKAT - HIPERMARKET");

                    break;


                default:
            }
        }
            else {
            if (dan == 2) {
                switch (segment) {
                    case 0:

                        txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  TRADING I CATEGORY MANAGEMENT");

                        break;
                    case 1:
                        txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  MALOPRODAJA");

                        break;
                    case 2:
                        txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  MARKETING");

                        break;

                    case 3:
                        txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  INVESTICIJAE, RAZVOJ I KONTROLA POSLOVNIH PROCESA  ");

                        break;
                    case 4:
                        txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n FINANSIJA, PRAVNIH I IT POSLOVA");

                        break;

                    default:
                }
            } else {
                if (dan == 3) {
                    switch (segment) {
                        case 0:

                            txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  LOGISTIKA");

                            break;
                        case 1:
                            txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  VELEPRODAJA");

                            break;
                        case 2:
                            txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n  VP iz MP Zmaj");

                            break;

                        case 3:
                            txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n VP 901 - VILINE VODE  ");
                            break;
                        case 4:
                            txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n Idea London");
                            break;
                        case 5:
                            txtWelcome.setText("DOBRODOŠLI U MERCATOR S\n Segment\n Idea Beograđanka");

                            break;

                        default:
                    }
                }
            }
        }





    }



}