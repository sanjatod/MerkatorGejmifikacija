package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FourthFragment extends Fragment {
    RecyclerView checktextGrid;
    Dan dan;
    TextView txtPitanja;
    List<OdgovorTipa4> listaOdgovora;
    ImageView imageViewSlikaNaPitanju,imgDay;
    View dialogView1;
    TextView txtCestitamo;
    int d,s;

    public FourthFragment(Dan dan, int d, int s)

    {
        this.dan=dan;
        this.d=d;
        this.s=s;

    }


    // TODO: Rename and change types and number of parameters
//    public static ThirdFragment newInstance(String param1, String param2) {
//        ThirdFragment fragment = new ThirdFragment(dan1);
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        dialogView1= inflater.inflate(R.layout.alertdialog1, null);
        // Inflate the layout for this fragment

//||  (d == 1 && dan.getRowid() == 76)
          if ((d == 3 && s == 6 && dan.getRowid() == 79) || (d == 3 && s == 4 && dan.getRowid() == 57) ) {
              return inflater.inflate(R.layout.fragment_fourth1, container, false);

          } else
              if(d == 1 && dan.getRowid() == 76  || (d == 3 && dan.getRowid() == 82))
                  return inflater.inflate(R.layout.fragment_fourth2, container, false);
                  else

              return inflater.inflate(R.layout.fragment_fourth, container, false);


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));

        txtCestitamo=dialogView1.findViewById(R.id.txtView);

        int i =1;
        listaOdgovora= new ArrayList<>();

        String[] ary = dan.getOdgovori().split(";");
        for (String s:ary)
        {
            OdgovorTipa4 pitanje = new OdgovorTipa4(i,s,false);
            listaOdgovora.add(pitanje);
            i++;
        }
        imageViewSlikaNaPitanju = view.findViewById(R.id.imageView1);
        if (dan.getSlika() != null && !dan.getSlika().toString().isEmpty())
        {
            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/"+dan.getSlika().toString());
            if (bmp!=null) {
                imageViewSlikaNaPitanju.setImageBitmap(bmp);
            }
            else
            {
                imageViewSlikaNaPitanju.setVisibility(View.GONE);
            }
        }
        else
        {
            imageViewSlikaNaPitanju.setVisibility(View.GONE);
        }



        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        txtPitanja.setText(dan.getTekstPitanja());
        checktextGrid=view.findViewById(R.id.rvCheckText);
        checktextGrid.setHasFixedSize(true);
        checktextGrid.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        checktextGrid.setLayoutManager(mLayoutManager);

        PitanjeTipa4RecyclerViewAdapter odgovoriRecyclerViewAdapter = new PitanjeTipa4RecyclerViewAdapter(getActivity());
        checktextGrid.setAdapter(odgovoriRecyclerViewAdapter);


        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String[] tacni_odgovori = dan.getTacanOdgovor().split(";");
                        boolean res = false;
                        StringBuffer sb = new StringBuffer("");

                        for (String s:tacni_odgovori)
                        {
                            sb.append(s+",");
                        }
                        List<OdgovorTipa4> list =new ArrayList<>();
                        for (OdgovorTipa4 tip4:listaOdgovora) {
                            if (tip4.isIzabran()) {
                                if (sb.toString().toLowerCase().contains(String.valueOf(tip4.getRb()))) {
                                    res = true;
                                    list.add(tip4);
                                }
                                else {
                                    res = false;
                                    break;
                                }
                            }
                        }

                        if (tacni_odgovori.length!=list.size()) res=false;

//res=true;

                        if(res) {
                            DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
                            try {
                                dbHelper.openDataBase();
                                ContentValues iuValues = new ContentValues();
                                iuValues.put("Odgovoreno", "1");
                                if (d==1)
                                    dbHelper.myDataBase.updateWithOnConflict("Dan1", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                if (d==2)
                                    dbHelper.myDataBase.updateWithOnConflict("Dan2", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
                                if (d==3)
                                    dbHelper.myDataBase.updateWithOnConflict("Dan3", iuValues, "rowid=" + dan.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);

                            } catch (SQLiteException ex) {
                                String s = "";
                            } finally {

                                dbHelper.close();
                            }

                            if (d==1 &&  dan.getRowid()==63)
                            {
                                txtCestitamo.setText("Objašnjenje za self scen: Želeći da odgovori na potrebe savremenog načina života, Mercator-S konstantno radi na unapređenju brzine kupovine.Kompanija se na tržištu izdvaja po tome što je u odabranim prodavnicama obezbedila samouslužne kase na kojima kupci sami mogu skenirati proizvode i platiti račun. Samo nekoliko godina kasnije odabrani obejkti dobili su Sken za tren, tehnološko rešenje koje potrošačima omogućava da već tokom kupovine skeniraju proizvode, a na posebno obeleženoj kasi, bez čekanja u redu, samo plate iznos računa.");
                                new AlertDialog.Builder(getActivity())
                                        .setView(dialogView1)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                getActivity().onBackPressed();
                                                dialog.dismiss();
                                            }
                                        })

                                        .show();
                            }
                            else
                            {
                                getActivity().onBackPressed();
                            }


                        }
                        else
                        {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Odgovor nije tačan!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.dismiss();
                                        }
                                    })

                                    .show();

                        }
                    }
                });
    }

    public class PitanjeTipa4RecyclerViewAdapter extends RecyclerView.Adapter<PitanjeTipa4RecyclerViewAdapter.ViewHolder> {

        private Context mContext;
        private int poz;


        public PitanjeTipa4RecyclerViewAdapter(Context context) {

            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(d == 1 && dan.getRowid() == 76)
            return new PitanjeTipa4RecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalji_lista_pitanja_tip31, parent, false));
            else
            return new PitanjeTipa4RecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalji_lista_pitanja_tip3, parent, false));
        }
        class ViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout mParent;
            private CheckBox mChachBox;


            ViewHolder(View itemView) {
                super(itemView);

                mParent = itemView.findViewById(R.id.parent);
                mChachBox = itemView.findViewById(R.id.checkBox);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull PitanjeTipa4RecyclerViewAdapter.ViewHolder viewHolder, final int position) {


            final OdgovorTipa4 odgovor = listaOdgovora.get(position);
            viewHolder.mChachBox.setId(position);
            viewHolder.mChachBox.setText(odgovor.getPonudjenOdgovor());

            viewHolder.mChachBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    CheckBox c =(CheckBox)buttonView;
                    poz = c.getId();
                    if(isChecked) {
                        odgovor.setIzabran(true);
                        listaOdgovora.get(poz).setIzabran(true);
                    }
                    else
                    {
                        odgovor.setIzabran(false);
                        listaOdgovora.get(poz).setIzabran(false);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return listaOdgovora.size();
        }
    }

    private class OdgovorTipa4
    {
        private int rb;
        private String ponudjenOdgovor;
        private boolean izabran;

        public OdgovorTipa4(int rb, String ponudjenOdgovor, boolean izabran) {
            this.ponudjenOdgovor = ponudjenOdgovor;
            this.izabran = izabran;
            this.rb=rb;
        }

        public int getRb() {
            return rb;
        }

        public void setRb(int rb) {
            this.rb = rb;
        }

        public String getPonudjenOdgovor() {
            return ponudjenOdgovor;
        }

        public void setPonudjenOdgovor(String ponudjenOdgovor) {
            this.ponudjenOdgovor = ponudjenOdgovor;
        }

        public boolean isIzabran() {
            return izabran;
        }

        public void setIzabran(boolean izabran) {
            this.izabran = izabran;
        }
    }

}