package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
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


public class ThirdFragment extends Fragment {
    Spinner spinnerOdgovori;
    Dan dan;
    TextView txtPitanja;
    List<OdgovorTipa3> listaOdgovora;
    List<String> listaPonudjenihOdgovora= new ArrayList<>();
    ImageView imageViewSlikaNaPitanju, imgDay;

    int d,s;

    public ThirdFragment(Dan dan, int d,int s)

    {
        this.dan=dan;
        this.d=d;
        this.s=s;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        if ((d==1 && (dan.getRowid()==42||dan.getRowid()==43)) || (d==2 && (dan.getRowid()==34||dan.getRowid()==35||dan.getRowid()==109))  )
            return inflater.inflate(R.layout.fragment_third1, container, false);
        else
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));



        int i =1;
        listaOdgovora= new ArrayList<>();

        String[] ary = dan.getOdgovori().split(";");
        listaPonudjenihOdgovora.add("");
        for (String s:ary)
        {
            OdgovorTipa3 pitanje = new OdgovorTipa3(i,s,false);
            listaOdgovora.add(pitanje);
            listaPonudjenihOdgovora.add(pitanje.ponudjenOdgovor);
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
                    odgovor3.setIzabran(false);
                }

                if(position>0)
                listaOdgovora.get(position-1).setIzabran(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        String[] tacni_odgovori = dan.getTacanOdgovor().split(";");
                        boolean res = false;
                        StringBuffer sb = new StringBuffer("");

                        for (OdgovorTipa3 tip3:listaOdgovora
                        ) {
                            if (tip3.isIzabran()) sb.append(tip3.getRb()+",");

                        }

                        for (String s : tacni_odgovori) {
                            if (sb.toString().toLowerCase().contains(s.toLowerCase())) res = true;
                            else {
                                res = false;
                                break;
                            }

                        }

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
                            getActivity().onBackPressed();
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

    public class PitanjeTipa3RecyclerViewAdapter extends RecyclerView.Adapter<PitanjeTipa3RecyclerViewAdapter.ViewHolder> {

        private Context mContext;


        public PitanjeTipa3RecyclerViewAdapter(Context context) {

            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalji_lista_pitanja_tip3, parent, false));
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