package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import java.util.Date;

public class IzborSegmentaFragment extends Fragment {

    Button btnIdeAkademija, btnOpsteInformacije, btnHipermarket,btnRodaMega, btnSluzbaKadrovske,btnSluzbaRegistracije, btnHR;
    Button btnTradingManagment, btnMaloprodaja, btnSluzbaMarketing,btnSluzbaInvesticijeRazvoj, btnPodrucjeFinansija;
    Button btnLogistika, btnVeleprodaja, btnZmaj, btnLondon, btnBeogradjanka,btnEvaluacija, btnSektorIT, btnSektorPoslovnihProcesa;
    DataBaseHelper dbHelper;
    private int dan;
    private int segment;
    Fragment fragment ;
    FragmentManager fm ;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        if (dan==1)
            return inflater.inflate(R.layout.fragment_izbor_segmenta, container, false);
        else
        if (dan==2)
            return inflater.inflate(R.layout.fragment_izbor_segment_dan2, container, false);
        else
            return inflater.inflate(R.layout.fragment_izbor_segment_dan3, container, false);
    }

    public IzborSegmentaFragment(int dan, int segment)
    {
        this.dan = dan;
        this.segment=segment;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHelper=new DataBaseHelper(getActivity());

        if (dan==1) {
            btnOpsteInformacije = view.findViewById(R.id.btnOpsteInformacije);
            btnOpsteInformacije.setClickable(false);
            btnOpsteInformacije.setEnabled(false);
            btnIdeAkademija = view.findViewById(R.id.btnIdeaAkademija);
            btnIdeAkademija.setClickable(false);
            btnIdeAkademija.setEnabled(false);
            btnHipermarket = view.findViewById(R.id.btnHipermarket);
            btnHipermarket.setClickable(false);
            btnHipermarket.setEnabled(false);
            btnRodaMega = view.findViewById(R.id.btnRodaMega);
            btnRodaMega.setClickable(false);
            btnRodaMega.setEnabled(false);
            btnSluzbaKadrovske = view.findViewById(R.id.btnSluzbaKadrovske);
            btnSluzbaKadrovske.setClickable(false);
            btnSluzbaKadrovske.setEnabled(false);
            btnSluzbaRegistracije = view.findViewById(R.id.btnSluzbaRegistracije);
            btnSluzbaRegistracije.setClickable(false);
            btnSluzbaRegistracije.setEnabled(false);
            btnHR=view.findViewById(R.id.btnSektorHR);
            btnHR.setClickable(false);
            btnHR.setEnabled(false);

            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(100); //You can manage the blinking time with this parameter
            anim.setStartOffset(50);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // if you want to change color at start of animation
                    //textview.settextcolor("your color")
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // if you want to change color at end of animation
                    //textview.settextcolor("your color")
                }

                @Override
                public void onAnimationRepeat(Animation animation) {


                }
            });

            if (dan == 1) {
                switch (segment) {
                    case 0:

                        btnOpsteInformacije.setClickable(true);
                        btnOpsteInformacije.setEnabled(true);
                        btnOpsteInformacije.startAnimation(anim);
                        CustomButtonDay1(btnOpsteInformacije);
                        btnOpsteInformacije.requestFocus();

                        break;
                    case 1:
                        btnIdeAkademija.setClickable(true);
                        btnIdeAkademija.setEnabled(true);
                        btnIdeAkademija.startAnimation(anim);
                        CustomButtonDay1(btnOpsteInformacije);
                        CustomButtonDay1(btnIdeAkademija);
                        btnIdeAkademija.requestFocus();

                        break;
                    case 2:
                        btnSluzbaRegistracije.setClickable(true);
                        btnSluzbaRegistracije.setEnabled(true);
                        btnSluzbaRegistracije.startAnimation(anim);
                        CustomButtonDay1(btnOpsteInformacije);
                        CustomButtonDay1(btnIdeAkademija);
                        CustomButtonDay1(btnSluzbaRegistracije);
                        btnSluzbaRegistracije.requestFocus();

                        break;

                    case 3:
                        btnSluzbaKadrovske.setClickable(true);
                        btnSluzbaKadrovske.setEnabled(true);
                        btnSluzbaKadrovske.startAnimation(anim);
                        CustomButtonDay1(btnOpsteInformacije);
                        CustomButtonDay1(btnIdeAkademija);
                        CustomButtonDay1(btnSluzbaRegistracije);
                        CustomButtonDay1(btnSluzbaKadrovske);
                        btnSluzbaKadrovske.requestFocus();

                        break;

                    case 4:
                        btnHR.setClickable(true);
                        btnHR.setEnabled(true);
                        btnHR.startAnimation(anim);
                        CustomButtonDay1(btnOpsteInformacije);
                        CustomButtonDay1(btnIdeAkademija);
                        CustomButtonDay1(btnSluzbaRegistracije);
                        CustomButtonDay1(btnSluzbaKadrovske);
                        CustomButtonDay1(btnHR);
                        btnHR.requestFocus();

                        break;

                    case 5:
                        btnRodaMega.setClickable(true);
                        btnRodaMega.setEnabled(true);
                        btnRodaMega.startAnimation(anim);
                        CustomButtonDay1(btnOpsteInformacije);
                        CustomButtonDay1(btnIdeAkademija);
                        CustomButtonDay1(btnSluzbaRegistracije);
                        CustomButtonDay1(btnSluzbaKadrovske);
                        CustomButtonDay1(btnHR);
                        CustomButtonDay1(btnRodaMega);
                        btnRodaMega.requestFocus();

                        break;

                    case 6:
                        btnHipermarket.setClickable(true);
                        btnHipermarket.setEnabled(true);
                        btnHipermarket.startAnimation(anim);
                        CustomButtonDay1(btnOpsteInformacije);
                        CustomButtonDay1(btnIdeAkademija);
                        CustomButtonDay1(btnSluzbaRegistracije);
                        CustomButtonDay1(btnSluzbaKadrovske);
                        CustomButtonDay1(btnHR);
                        CustomButtonDay1(btnRodaMega);
                        CustomButtonDay1(btnHipermarket);
                        btnHipermarket.requestFocus();

                        break;


                    default:

                }
            }


            btnOpsteInformacije.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnSluzbaRegistracije.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });

            btnIdeAkademija.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });

            btnSluzbaKadrovske.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });

            btnHR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnRodaMega.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnHipermarket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
        }

        if (dan==2) {
            btnTradingManagment = view.findViewById(R.id.btnTradingManagment);
            btnTradingManagment.setClickable(false);
            btnTradingManagment.setEnabled(false);
            btnMaloprodaja = view.findViewById(R.id.btnMaloprodaja);
            btnMaloprodaja.setClickable(false);
            btnMaloprodaja.setEnabled(false);
            btnSluzbaMarketing = view.findViewById(R.id.btnSluzbaMarketing);
            btnSluzbaMarketing.setClickable(false);
            btnSluzbaMarketing.setEnabled(false);
            btnSluzbaInvesticijeRazvoj = view.findViewById(R.id.btnSluzbaInvesticijeRazvoj);
            btnSluzbaInvesticijeRazvoj.setClickable(false);
            btnSluzbaInvesticijeRazvoj.setEnabled(false);
            btnPodrucjeFinansija = view.findViewById(R.id.btnPodrucjeFinansija);
            btnPodrucjeFinansija.setClickable(false);
            btnPodrucjeFinansija.setEnabled(false);
//            btnSektorIT = view.findViewById(R.id.btnPodrucjeIT);
//            btnSektorIT.setClickable(false);
//            btnSektorIT.setEnabled(false);
//            btnSektorPoslovnihProcesa = view.findViewById(R.id.btnPodrucjePravnihPoslova);
//            btnSektorPoslovnihProcesa.setClickable(false);
//            btnSektorPoslovnihProcesa.setEnabled(false);

            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(100); //You can manage the blinking time with this parameter
            anim.setStartOffset(50);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // if you want to change color at start of animation
                    //textview.settextcolor("your color")
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // if you want to change color at end of animation
                    //textview.settextcolor("your color")
                }

                @Override
                public void onAnimationRepeat(Animation animation) {


                }
            });

            if (dan == 2) {
                switch (segment) {
                    case 0:

                        btnTradingManagment.setClickable(true);
                        btnTradingManagment.setEnabled(true);
                        btnTradingManagment.startAnimation(anim);
                        CustomButtonDay2(btnTradingManagment);
                        btnTradingManagment.requestFocus();

                        break;
                    case 1:
                        btnMaloprodaja.setClickable(true);
                        btnMaloprodaja.setEnabled(true);
                        btnMaloprodaja.startAnimation(anim);
                        CustomButtonDay2(btnTradingManagment);
                        CustomButtonDay2(btnMaloprodaja);
                        btnMaloprodaja.requestFocus();

                        break;
                    case 2:
                        btnSluzbaMarketing.setClickable(true);
                        btnSluzbaMarketing.setEnabled(true);
                        btnSluzbaMarketing.startAnimation(anim);
                        CustomButtonDay2(btnTradingManagment);
                        CustomButtonDay2(btnMaloprodaja);
                        CustomButtonDay2(btnSluzbaMarketing);
                        btnSluzbaMarketing.requestFocus();

                        break;

                    case 3:
                        btnSluzbaInvesticijeRazvoj.setClickable(true);
                        btnSluzbaInvesticijeRazvoj.setEnabled(true);
                        btnSluzbaInvesticijeRazvoj.startAnimation(anim);
                        CustomButtonDay2(btnTradingManagment);
                        CustomButtonDay2(btnMaloprodaja);
                        CustomButtonDay2(btnSluzbaMarketing);
                        CustomButtonDay2(btnSluzbaInvesticijeRazvoj);
                        btnSluzbaInvesticijeRazvoj.requestFocus();

                        break;

                    case 4:
                        btnPodrucjeFinansija.setClickable(true);
                        btnPodrucjeFinansija.setEnabled(true);
                        btnPodrucjeFinansija.startAnimation(anim);
                        CustomButtonDay2(btnTradingManagment);
                        CustomButtonDay2(btnMaloprodaja);
                        CustomButtonDay2(btnSluzbaMarketing);
                        CustomButtonDay2(btnSluzbaInvesticijeRazvoj);
                        CustomButtonDay2(btnPodrucjeFinansija);
                        btnPodrucjeFinansija.requestFocus();

                        break;
//                    case 5:
//                        btnSektorIT.setClickable(true);
//                        btnSektorIT.setEnabled(true);
//                        btnSektorIT.startAnimation(anim);
//                        CustomButtonDay2(btnTradingManagment);
//                        CustomButtonDay2(btnMaloprodaja);
//                        CustomButtonDay2(btnSluzbaMarketing);
//                        CustomButtonDay2(btnSluzbaInvesticijeRazvoj);
//                        CustomButtonDay2(btnPodrucjeFinansija);
//                        CustomButtonDay2(btnSektorIT);
//                        btnSektorIT.requestFocus();
//
//                        break;
//                    case 6:
//                        btnSektorPoslovnihProcesa.setClickable(true);
//                        btnSektorPoslovnihProcesa.setEnabled(true);
//                        btnSektorPoslovnihProcesa.startAnimation(anim);
//                        CustomButtonDay2(btnTradingManagment);
//                        CustomButtonDay2(btnMaloprodaja);
//                        CustomButtonDay2(btnSluzbaMarketing);
//                        CustomButtonDay2(btnSluzbaInvesticijeRazvoj);
//                        CustomButtonDay2(btnPodrucjeFinansija);
//                        CustomButtonDay2(btnSektorIT);
//                        CustomButtonDay2(btnSektorPoslovnihProcesa);
//                        btnSektorPoslovnihProcesa.requestFocus();
//
//                        break;

                    default:

                }
            }


            btnTradingManagment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnMaloprodaja.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });

            btnSluzbaMarketing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });

            btnSluzbaInvesticijeRazvoj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnPodrucjeFinansija.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                   // OpenFragment();
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


            });
//            btnSektorIT.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                   // OpenFragment();
//                    try {
//
//                        dbHelper.openDataBase();
//                        ContentValues iuValues = new ContentValues();
//                        iuValues.put("Segment", segment + 1);
//                        iuValues.put("Dan", 2);
//                        iuValues.put("Zapocet", 1);
//                        dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
//                    } catch (Exception ex) {
//                        new AlertDialog.Builder(getActivity())
//                                .setTitle("GRESKA!   " + ex.getMessage())
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                        dialog.dismiss();
//                                    }
//                                })
//
//                                .show();
//                    } finally {
//
//                        dbHelper.close();
//                    }
//                    BaseActivity.segment = +1;
//
//                    getActivity().onBackPressed();
//                }
//            });
//            btnSektorPoslovnihProcesa.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    //OpenFragment();
//                    try {
//
//                        dbHelper.openDataBase();
//                        ContentValues iuValues = new ContentValues();
//                        iuValues.put("Segment", segment + 1);
//                        iuValues.put("Dan", 2);
//                        iuValues.put("Zapocet", 1);
//                        dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
//                    } catch (Exception ex) {
//                        new AlertDialog.Builder(getActivity())
//                                .setTitle("GRESKA!   " + ex.getMessage())
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                        dialog.dismiss();
//                                    }
//                                })
//
//                                .show();
//                    } finally {
//
//                        dbHelper.close();
//                    }
//                    BaseActivity.segment = +1;
//
//                    getActivity().onBackPressed();
//                }
//            });

        }

        if (dan==3) {

            btnSektorIT = view.findViewById(R.id.btnPodrucjeIT);
            btnSektorIT.setClickable(false);
            btnSektorIT.setEnabled(false);
            btnSektorPoslovnihProcesa = view.findViewById(R.id.btnPodrucjePravnihPoslova);
            btnSektorPoslovnihProcesa.setClickable(false);
           btnSektorPoslovnihProcesa.setEnabled(false);
            btnLogistika = view.findViewById(R.id.btnLogistika);
            btnLogistika.setClickable(false);
            btnLogistika.setEnabled(false);
            btnVeleprodaja = view.findViewById(R.id.btnVeleprodaja);
            btnVeleprodaja.setClickable(false);
            btnVeleprodaja.setEnabled(false);
            btnZmaj = view.findViewById(R.id.btnZmaj);
            btnZmaj.setClickable(false);
            btnZmaj.setEnabled(false);
            btnLondon = view.findViewById(R.id.btnLondon);
            btnLondon.setClickable(false);
            btnLondon.setEnabled(false);
            btnBeogradjanka = view.findViewById(R.id.btnBeogradjanka);
            btnBeogradjanka.setClickable(false);
            btnBeogradjanka.setEnabled(false);
            btnEvaluacija = view.findViewById(R.id.btnEvaluacija);
            btnEvaluacija.setClickable(false);
            btnEvaluacija.setEnabled(false);

            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(100); //You can manage the blinking time with this parameter
            anim.setStartOffset(50);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // if you want to change color at start of animation
                    //textview.settextcolor("your color")
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // if you want to change color at end of animation
                    //textview.settextcolor("your color")
                }

                @Override
                public void onAnimationRepeat(Animation animation) {


                }
            });

            if (dan == 3) {
                switch (segment) {
                    case 0:

                        btnSektorIT.setClickable(true);
                        btnSektorIT.setEnabled(true);
                        btnSektorIT.startAnimation(anim);
                        CustomButtonDay3(btnSektorIT);
                        btnLogistika.requestFocus();

                        break;
                    case 1:

                        btnSektorPoslovnihProcesa.setClickable(true);
                        btnSektorPoslovnihProcesa.setEnabled(true);
                        btnSektorPoslovnihProcesa.startAnimation(anim);
                        CustomButtonDay3(btnSektorIT);
                        CustomButtonDay3(btnSektorPoslovnihProcesa);
                        btnLogistika.requestFocus();

                        break;
                    case 2:

                        btnLogistika.setClickable(true);
                        btnLogistika.setEnabled(true);
                        btnLogistika.startAnimation(anim);
                        CustomButtonDay3(btnSektorIT);
                        CustomButtonDay3(btnSektorPoslovnihProcesa);
                        CustomButtonDay3(btnLogistika);
                        btnLogistika.requestFocus();

                        break;
                    case 3:
                        btnVeleprodaja.setClickable(true);
                        btnVeleprodaja.setEnabled(true);
                        btnVeleprodaja.startAnimation(anim);
                        CustomButtonDay3(btnSektorIT);
                        CustomButtonDay3(btnSektorPoslovnihProcesa);
                        CustomButtonDay3(btnLogistika);
                        CustomButtonDay3(btnVeleprodaja);
                        btnVeleprodaja.requestFocus();

                        break;
                    case 4:
                        btnZmaj.setClickable(true);
                        btnZmaj.setEnabled(true);
                        CustomButtonDay3(btnSektorIT);
                        CustomButtonDay3(btnSektorPoslovnihProcesa);
                        CustomButtonDay3(btnLogistika);
                        CustomButtonDay3(btnVeleprodaja);
                        CustomButtonDay3(btnZmaj);
                        btnZmaj.startAnimation(anim);
                        btnZmaj.requestFocus();

                        break;



                    case 5:
                        btnLondon.setClickable(true);
                        btnLondon.setEnabled(true);
                        btnLondon.startAnimation(anim);
                        CustomButtonDay3(btnSektorIT);
                        CustomButtonDay3(btnSektorPoslovnihProcesa);
                        CustomButtonDay3(btnLogistika);
                        CustomButtonDay3(btnVeleprodaja);
                        CustomButtonDay3(btnZmaj);
                        CustomButtonDay3(btnLondon);
                        btnLondon.requestFocus();

                        break;
                    case 6:
                        btnBeogradjanka.setClickable(true);
                        btnBeogradjanka.setEnabled(true);
                        btnBeogradjanka.startAnimation(anim);
                        CustomButtonDay3(btnSektorIT);
                        CustomButtonDay3(btnSektorPoslovnihProcesa);
                        CustomButtonDay3(btnLogistika);
                        CustomButtonDay3(btnVeleprodaja);
                        CustomButtonDay3(btnZmaj);
                        CustomButtonDay3(btnLondon);
                        CustomButtonDay3(btnBeogradjanka);
                        btnBeogradjanka.requestFocus();

                        break;

                    case 7:
                        btnEvaluacija.setClickable(true);
                        btnEvaluacija.setEnabled(true);
                        btnEvaluacija.startAnimation(anim);
                        CustomButtonDay3(btnSektorIT);
                        CustomButtonDay3(btnSektorPoslovnihProcesa);
                        CustomButtonDay3(btnLogistika);
                        CustomButtonDay3(btnVeleprodaja);
                        CustomButtonDay3(btnZmaj);
                        CustomButtonDay3(btnLondon);
                        CustomButtonDay3(btnBeogradjanka);
                        CustomButtonDay3(btnEvaluacija);
                        btnEvaluacija.requestFocus();

                        break;

                    default:

                }
            }

                        btnSektorIT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
//                    try {
//
//                        dbHelper.openDataBase();
//                        ContentValues iuValues = new ContentValues();
//                        iuValues.put("Segment", segment + 1);
//                        iuValues.put("Dan", 2);
//                        iuValues.put("Zapocet", 1);
//                        dbHelper.myDataBase.insertOrThrow("AkcijaSegment", null, iuValues);
//                    } catch (Exception ex) {
//                        new AlertDialog.Builder(getActivity())
//                                .setTitle("GRESKA!   " + ex.getMessage())
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                        dialog.dismiss();
//                                    }
//                                })
//
//                                .show();
//                    } finally {
//
//                        dbHelper.close();
//                    }
//                    BaseActivity.segment = +1;
//
//                    getActivity().onBackPressed();
                }
            });
            btnSektorPoslovnihProcesa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //OpenFragment();
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
            });

            btnLogistika.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnVeleprodaja.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnZmaj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });

            btnLondon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnBeogradjanka.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    OpenFragment();
                }
            });
            btnEvaluacija.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        dbHelper.openDataBase();
                        ContentValues iuValues = new ContentValues();
                        iuValues.put("Segment", segment+1);
                        iuValues.put("Dan", 1);
                        iuValues.put("Zapocet", 1);
                        dbHelper.myDataBase.insertOrThrow("AkcijaSegment",null,iuValues);

                    }
                    catch (Exception e)
                    {

                    }


                    FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                    TaskFragment w1 = new TaskFragment(null,4,0 );
                    ft1.replace(R.id.fragment_frame, w1);
                    ft1.commit();
                }
            });


        }
    }

    private void OpenFragment()
    {
        // startovanje segmenta
        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
        WelcomeFragment w1 = new WelcomeFragment(dan,segment);
        ft1.replace(R.id.fragment_frame, w1);
        ft1.commit();
    }
        private void DefaultButton(Button  btn)
        {
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setGravity(Gravity.CENTER);
            btn.setBackgroundColor(getResources().getColor(R.color.red));
        }

        private void CustomButtonDay1(Button  btn)
        {
            btn.setTextColor(getResources().getColor(R.color.red));
            btn.setGravity(Gravity.CENTER);
            btn.setBackgroundColor(getResources().getColor(R.color.day1));
        }
    private void CustomButtonDay2(Button  btn)
    {
        btn.setTextColor(getResources().getColor(R.color.red));
        btn.setGravity(Gravity.CENTER);
        btn.setBackgroundColor(getResources().getColor(R.color.day2));
    }
    private void CustomButtonDay3(Button  btn)
    {
        btn.setTextColor(getResources().getColor(R.color.red));
        btn.setGravity(Gravity.CENTER);
        btn.setBackgroundColor(getResources().getColor(R.color.day3));
    }




}