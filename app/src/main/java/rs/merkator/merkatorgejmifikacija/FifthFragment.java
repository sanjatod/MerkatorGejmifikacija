package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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

public class FifthFragment extends Fragment {
    RecyclerView checktextGrid;
    Dan dan;
    TextView txtPitanja;
    List<OdgovorTipa5> listaOdgovora;
    ImageView imageViewSlikaNaPitanju, imgDay;

    int d,s;

    public FifthFragment(Dan dan, int d,int s)

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
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int i = 1;
        listaOdgovora = new ArrayList<>();

        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));

        String[] ary = dan.getOdgovori().split(";");
        for (String s : ary) {
            OdgovorTipa5 pitanje = new OdgovorTipa5(i, s, false);
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
        checktextGrid = view.findViewById(R.id.rvCheckText);
        checktextGrid.setHasFixedSize(true);
        checktextGrid.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        checktextGrid.setLayoutManager(mLayoutManager);
//        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.line_divider);
//        Drawable verticalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.line_divider);
//        checktextGrid.addItemDecoration(new GridDividerItemDecoration(horizontalDivider, verticalDivider, 4));

        PitanjeTipa5RecyclerViewAdapter odgovoriRecyclerViewAdapter = new PitanjeTipa5RecyclerViewAdapter(getActivity());
        checktextGrid.setAdapter(odgovoriRecyclerViewAdapter);


        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String[] tacni_odgovori = dan.getTacanOdgovor().split(";");
                        boolean res = false;
                        StringBuffer sb = new StringBuffer("");

                        for (OdgovorTipa5 tip4 : listaOdgovora
                        ) {
                            if (tip4.isIzabran()) sb.append(tip4.getRb() + ",");

                        }

                        for (String s : tacni_odgovori) {
                            if (sb.toString().toLowerCase().contains(s.toLowerCase())) res = true;
                            else {
                                res = false;
                                break;
                            }

                        }

                        if (res) {
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
                        } else {
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

    public class PitanjeTipa5RecyclerViewAdapter extends RecyclerView.Adapter<PitanjeTipa5RecyclerViewAdapter.ViewHolder> {

        private Context mContext;


        public PitanjeTipa5RecyclerViewAdapter(Context context) {

            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PitanjeTipa5RecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalji_lista_pitanja_tip5, parent, false));
        }
        class ViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout mParent;
            private TextView txtOdgovor;
            private TextView txtRB;

            ViewHolder(View itemView) {
                super(itemView);

                mParent = itemView.findViewById(R.id.parent);
                txtOdgovor = itemView.findViewById(R.id.txtOdgovor);
                txtRB = itemView.findViewById(R.id.txtRB);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull final PitanjeTipa5RecyclerViewAdapter.ViewHolder viewHolder, int position) {

            final OdgovorTipa5  odgovor = listaOdgovora.get(position);

            viewHolder.txtOdgovor.setText(odgovor.getPonudjenOdgovor());
            viewHolder.txtRB.setText(position+1+". ");
            if (!odgovor.isIzabran())
            {

                viewHolder.txtRB.setTypeface(null, Typeface.NORMAL);
                viewHolder.txtOdgovor.setTypeface(null, Typeface.NORMAL);
            }
            else {

                viewHolder.txtRB.setTypeface(null, Typeface.BOLD);
                viewHolder.txtOdgovor.setTypeface(null, Typeface.BOLD);
            }

            viewHolder.txtOdgovor.setId(position);
            viewHolder.txtOdgovor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    for (OdgovorTipa5 odgovor5:
                        listaOdgovora )
                    {
                        odgovor5.setIzabran(false);
                    }

                    if (listaOdgovora.get(v.getId()).isIzabran())
                    {
                        //odgovor.setIzabran(false);
                        listaOdgovora.get(v.getId()).setIzabran(false);
                        viewHolder.txtRB.setTypeface(null, Typeface.NORMAL);
                        viewHolder.txtOdgovor.setTypeface(null, Typeface.NORMAL);
                    }
                    else {
                        //listaOdgovora.get(v.getId()).setIzabran(true);
                        listaOdgovora.get(v.getId()).setIzabran(true);
                        viewHolder.txtRB.setTypeface(null, Typeface.BOLD);
                        viewHolder.txtOdgovor.setTypeface(null, Typeface.BOLD);
                    }
                    notifyDataSetChanged();

                }
            });

        }

        @Override
        public int getItemCount() {
            return listaOdgovora.size();
        }
    }

    private class OdgovorTipa5
    {
        private int rb;
        private String ponudjenOdgovor;
        private boolean izabran;

        public OdgovorTipa5(int rb, String ponudjenOdgovor, boolean izabran) {
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