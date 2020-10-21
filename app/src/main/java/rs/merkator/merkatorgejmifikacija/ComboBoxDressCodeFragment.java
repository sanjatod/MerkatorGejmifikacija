package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ComboBoxDressCodeFragment  extends Fragment {
    Spinner spinnerOdgovori;
   // Dan1 dan1;
    TextView txtPitanja;
    List<OdgovorTipa3> listaOdgovora;
    List<String> listaPonudjenihOdgovora= new ArrayList<>();
    ImageView imageViewSlikaNaPitanju, imageDay;

  //  public ComboBoxDressCodeFragment(Dan1 dan1)

  //  {
   //     this.dan1=dan1;
 //   }



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //int i =1;
       listaOdgovora= new ArrayList<>();

       // String[] ary = dan1.getOdgovori().split(";");
       // listaPonudjenihOdgovora.add("");
       // for (String s:ary)
      //  {
        listaPonudjenihOdgovora.add("");
        OdgovorTipa3 odgovor = new OdgovorTipa3(1,"Cognos",false);
        listaOdgovora.add(odgovor);
        listaPonudjenihOdgovora.add("Cognos");
        odgovor = new OdgovorTipa3(2,"SAP",false);
        listaOdgovora.add(odgovor);
        listaPonudjenihOdgovora.add("SAP");
        odgovor = new OdgovorTipa3(3,"Hubie",false);
        listaOdgovora.add(odgovor);
        listaPonudjenihOdgovora.add("Hubie");
        odgovor = new OdgovorTipa3(4,"SkyTrack",false);
        listaOdgovora.add(odgovor);
        listaPonudjenihOdgovora.add("SkyTrack");
        odgovor = new OdgovorTipa3(5,"Nielsen",false);
        listaOdgovora.add(odgovor);
        listaPonudjenihOdgovora.add("Nielsen");


//            OdgovorTipa3 pitanje = new OdgovorTipa3(1,s,false);
//            listaOdgovora.add(pitanje);
//            listaPonudjenihOdgovora.add(pitanje.ponudjenOdgovor);
       //     i++;
      //  }
        imageViewSlikaNaPitanju = view.findViewById(R.id.imageView1);
        imageDay= view.findViewById(R.id.imgDay);
//        if (dan1.getSlika() != null && !dan1.getSlika().toString().isEmpty())
//        {
//            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/+dan1.getSlika().toString());
//            if (bmp!=null) {
//                imageViewSlikaNaPitanju.setImageBitmap(bmp);
//            }
//            else
//            {
//                imageViewSlikaNaPitanju.setVisibility(View.GONE);
//            }
//        }
//        else
//        {
//            imageViewSlikaNaPitanju.setVisibility(View.GONE);
//        }
        imageViewSlikaNaPitanju.setVisibility(View.GONE);
        imageDay.setVisibility(View.GONE);
        txtPitanja = view.findViewById(R.id.txtTextPitanja);
        txtPitanja.setText("Kao i svaka kompanija koja predstavlja deo retail sistema Srbije i Mercator -S " +
                "koristi različite aplikacije za izveštavanje. Koja je to osnovna aplikacija koja se koristi za " +
                "izveštavanje u kompaniji Mercator-S:");
        spinnerOdgovori= view.findViewById(R.id.spinnerOdgovori);
        final CustomSpinnerArrayAdapter spinnerArrayAdapter = new CustomSpinnerArrayAdapter(getActivity().getApplicationContext(), R.layout.dropdown_selected_list_item, R.id.option_text, listaPonudjenihOdgovora);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.dropdown_list_item);
        spinnerOdgovori.setAdapter(spinnerArrayAdapter);

        spinnerOdgovori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView selectedText = parent.findViewById(R.id.option_text);
                if (selectedText != null) {
                    if (position != 0) {
                        selectedText.setTextColor(Color.BLACK);
                    } else {
                        selectedText.setTextColor(getActivity().getResources().getColor(android.R.color.darker_gray));
                    }
                }
                spinnerArrayAdapter.setSelectedPosition(position);
                for (OdgovorTipa3 odgovor3:
                        listaOdgovora )
                {
                    if(position>0)
                        listaOdgovora.get(position-1).setIzabran(false);
                }

                if(position>0)
                    listaOdgovora.get(position-1).setIzabran(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String text = spinnerOdgovori.getSelectedItem().toString();
                        if (!text.equals("Cognos"))
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
                        else
                        {
                            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                            ApplicationDressCodeFragment w1 = new ApplicationDressCodeFragment();
                            ft1.replace(R.id.fragment_frame, w1);
                            ft1.commit();
                        }

//                        String[] tacni_odgovori = dan1.getTacanOdgovor().split(";");
//                        boolean res = false;
//                        StringBuffer sb = new StringBuffer("");
//
//                        for (OdgovorTipa3 tip3:listaOdgovora
//                        ) {
//                            if (tip3.isIzabran()) sb.append(tip3.getRb()+",");
//
//                        }
//
//                        for (String s : tacni_odgovori) {
//                            if (sb.toString().toLowerCase().contains(s.toLowerCase())) res = true;
//                            else {
//                                res = false;
//                                break;
//                            }
//
//                        }
//
//                        if(res) {
//                            DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
//                            try {
//                                dbHelper.openDataBase();
//                                ContentValues iuValues = new ContentValues();
//                                iuValues.put("Odgovoreno", "1");
//                                dbHelper.myDataBase.updateWithOnConflict("Dan1", iuValues, "rowid=" + dan1.getRowid(), null, SQLiteDatabase.CONFLICT_ROLLBACK);
//
//
//                            } catch (SQLiteException ex) {
//                                String s = "";
//                            } finally {
//
//                                dbHelper.close();
//                            }
//                            getActivity().onBackPressed();
//                        }
//                        else
//                        {
//                            new AlertDialog.Builder(getActivity())
//                                    .setTitle("Odgovor nije tačan!")
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int whichButton) {
//                                            dialog.dismiss();
//                                        }
//                                    })
//
//                                    .show();
//
//                        }
                    }
                });
    }

    public class PitanjeTipa3RecyclerViewAdapter extends RecyclerView.Adapter<PitanjeTipa3RecyclerViewAdapter.ViewHolder> {

        private Context mContext;


        public PitanjeTipa3RecyclerViewAdapter(Context context) {

            mContext = context;
        }

        @NonNull
        @Override
        public PitanjeTipa3RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PitanjeTipa3RecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalji_lista_pitanja_tip3, parent, false));
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
        public void onBindViewHolder(@NonNull PitanjeTipa3RecyclerViewAdapter.ViewHolder viewHolder, int position) {

            final OdgovorTipa3 odgovor = listaOdgovora.get(position);
            viewHolder.mChachBox.setId(position);
            viewHolder.mChachBox.setText(odgovor.getPonudjenOdgovor());
            viewHolder.mChachBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {

                    if(isChecked)
                    {
                        odgovor.setIzabran(true);
                    }
                    else
                    {
                        odgovor.setIzabran(false);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return listaOdgovora.size();
        }
    }

    private class OdgovorTipa3
    {
        private int rb;
        private String ponudjenOdgovor;
        private boolean izabran;

        public OdgovorTipa3(int rb, String ponudjenOdgovor, boolean izabran) {
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