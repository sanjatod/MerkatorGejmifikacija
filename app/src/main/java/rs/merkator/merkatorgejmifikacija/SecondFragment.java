package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    RecyclerView imageGrid;
    Dan dan;
    List<PitanjeTipa2> listPitanjeTipa2;
    TextView txtPitanja;
    ImageView imageViewSlikaNaPitanju,imgDay;
    int d,s;

    public SecondFragment(Dan dan, int d,int s)

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
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgDay = view.findViewById(R.id.imgDay);
        if (d==1) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_1));
        if (d==2) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_drugi));
        if (d==3) imgDay.setImageDrawable(getResources().getDrawable(R.drawable.dan_treci));
        listPitanjeTipa2= new ArrayList<>();
        int i =1;

        String[] ary = dan.getOdgovori().split(";");
        for (String s:ary)
        {
            PitanjeTipa2 pitanje = new PitanjeTipa2(i,s,false);
            listPitanjeTipa2.add(pitanje);
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
        imageGrid=view.findViewById(R.id.rvImage);
        imageGrid.setHasFixedSize(true);
        imageGrid.setNestedScrollingEnabled(false);
        int rowNumber=listPitanjeTipa2.size();
        if (listPitanjeTipa2.size()%2==0)
            rowNumber= listPitanjeTipa2.size()/2;
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), rowNumber);
        imageGrid.setLayoutManager(mLayoutManager);
//        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.line_divider);
//        Drawable verticalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.line_divider);
//        imageGrid.addItemDecoration(new GridDividerItemDecoration(horizontalDivider, verticalDivider, 4));

        PitanjeTipa2RecyclerViewAdapter customersSegmentationRecyclerViewAdapter = new PitanjeTipa2RecyclerViewAdapter(getActivity());
        imageGrid.setAdapter(customersSegmentationRecyclerViewAdapter);

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String[] tacni_odgovori = dan.getTacanOdgovor().split(";");
                boolean res = false;
                StringBuffer sb = new StringBuffer("");

                for (String s:tacni_odgovori)
                {
                    sb.append(s+",");
                }

                List<PitanjeTipa2> list =new ArrayList<>();

                for (PitanjeTipa2 tip2:listPitanjeTipa2) {
                    if (tip2.isCek()) {
                        if (sb.toString().toLowerCase().contains(String.valueOf(tip2.getRb()))) {
                            res = true;
                            list.add(tip2);
                        }
                        else {
                            res = false;
                            break;
                        }
                    }
                }


                if (tacni_odgovori.length!=list.size()) res=false;

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

    public class PitanjeTipa2RecyclerViewAdapter extends RecyclerView.Adapter<PitanjeTipa2RecyclerViewAdapter.ViewHolder> {

        private Context mContext;

        public PitanjeTipa2RecyclerViewAdapter(Context context) {

            mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalji_lista_pitanja_tipa2, parent, false));
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout mParent;
            private ImageView mImage;
            private CheckBox mChachBox;

            ViewHolder(View itemView) {
                super(itemView);

                mParent = itemView.findViewById(R.id.parent);
                mImage = itemView.findViewById(R.id.image);
                mChachBox = itemView.findViewById(R.id.checkBox);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

            final PitanjeTipa2 pitanje = listPitanjeTipa2.get(position);
            viewHolder.mChachBox.setId(position);
            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/"+pitanje.getText());
            if (bmp!=null) {
                viewHolder.mImage.setImageBitmap(bmp);
            }
            viewHolder.mChachBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if(isChecked)
                    {
                        pitanje.setCek(true);
                    }
                    else
                    {
                        pitanje.setCek(false);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return listPitanjeTipa2.size();
        }
    }

    public class PitanjeTipa2
    {
        int rb;
        String text;
        boolean cek;

        public PitanjeTipa2(int rb, String text, boolean cek) {
            this.rb = rb;
            this.text = text;
            this.cek=cek;
        }

        public int getRb() {
            return rb;
        }

        public void setRb(int rb) {
            this.rb = rb;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isCek() {
            return cek;
        }

        public void setCek(boolean cek) {
            this.cek = cek;
        }
    }

}