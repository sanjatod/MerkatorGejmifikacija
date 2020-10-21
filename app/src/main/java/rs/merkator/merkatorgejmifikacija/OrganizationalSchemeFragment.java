package rs.merkator.merkatorgejmifikacija;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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


public class OrganizationalSchemeFragment  extends Fragment {

    DataBaseHelper dbHelper;
    private int dan;
    private int segment;
    //ImageView imgoshem;
    RecyclerView imageGrid;
    List<Bitmap> listIV;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_organizational_scheme, container, false);
    }

    public OrganizationalSchemeFragment(int dan, int segment)
    {
        this.dan = dan;
        this.segment=segment;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper=new DataBaseHelper(getActivity());
        //imgoshem = view.findViewById(R.id.imageView);

        listIV= new ArrayList<>();
        imageGrid=view.findViewById(R.id.rvImage);
        imageGrid.setHasFixedSize(true);
        imageGrid.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        imageGrid.setLayoutManager(mLayoutManager);


        //organizaciona_sema_ljudski_resursi.png

        if (dan==2) {
            if (segment == 0) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/organizaciona_sema_trading_management.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/organizaciona_sema_trading_management1.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/organizaciona_sema_trading_management1.png");
                if (bmp != null)
                    listIV.add(bmp);

            }

            if (segment == 1) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semamaloprdaja.png");
                if (bmp != null)
                    listIV.add(bmp);
            }

            if (segment == 2) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semamarketing.png");
                if (bmp != null)
                    listIV.add(bmp);

            }

            if (segment == 3) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semainvesticija.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semainvesticija1.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semainvesticija2.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semainvesticija3.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semainvesticija4.png");
                if (bmp != null)
                    listIV.add(bmp);

            }
            //shemait.png

            if (segment == 4) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/shemait.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/shemait1.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/shemait2.png");
                if (bmp != null)
                    listIV.add(bmp);
            }
        }

        if (dan==1) {
            if (segment == 0) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/organizaciona_sema_drustva1.png");
                if (bmp != null) {

                    listIV.add(bmp);
                }
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/organizaciona_sema_drustva2.png");
                if (bmp != null) {
                   // imgoshem.setImageBitmap(bmp);
                    listIV.add(bmp);
                }
            }

            if (segment == 1) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/organizaciona_sema_ljudski_resursi.png");
                if (bmp != null)
                    listIV.add(bmp);

            }

            if (segment == 2) {
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
            }


        }

        if (dan==3) {
            if (segment == 0) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/logistika1.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/logistika2.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/logistika3.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/logistika4.png");
                if (bmp != null)
                    listIV.add(bmp);
                 bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/logistika5.png");
                if (bmp != null)
                    listIV.add(bmp);
            }

            if (segment == 1) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semamaloprdaja.png");
                if (bmp != null)
                    listIV.add(bmp);
            }


            if (segment == 2) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semamarketing.png");
                if (bmp != null)
                    listIV.add(bmp);
            }

            if (segment == 3) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/semainvesticija.png");
                if (bmp != null)
                    listIV.add(bmp);
            }
            //shemait.png

            if (segment == 4) {
                Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/shemait.png");
                if (bmp != null)
                    listIV.add(bmp);
            }


        }

        RecyclerViewAdapter customersSegmentationRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        imageGrid.setAdapter(customersSegmentationRecyclerViewAdapter);


        view.findViewById(R.id.btnNext).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (dan == 2 && segment == 0) {

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
                        } else {

                            if (dan == 2 && segment == 1) {
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
                            } else {

                                if (dan == 2 && segment == 2) {
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
                                } else {

                                    if (dan == 2 && segment == 3) {
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
                                    } else {
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
                                        }
                                        else {
                                            if (dan==3 && segment == 0)
                                            {
                                                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                                                ImageViewFragment w1 = new ImageViewFragment(dan, segment);
                                                ft1.replace(R.id.fragment_frame, w1);
                                                ft1.commit();
                                            }
                                               else {

                                                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                                                MoveFragment w1 = new MoveFragment(dan, segment);
                                                ft1.replace(R.id.fragment_frame, w1);
                                                ft1.commit();
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                });



        }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private Context mContext;

        public RecyclerViewAdapter(Context context) {

            mContext = context;
        }

        @NonNull
        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalji_org_shema, parent, false));
        }

        class ViewHolder extends RecyclerView.ViewHolder {


            private ImageView mImage;


            ViewHolder(View itemView) {
                super(itemView);


                mImage = itemView.findViewById(R.id.imageView);

            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {


            for (Bitmap bip:listIV
                 ) {
                viewHolder.mImage.setImageBitmap(bip);

            }




        }

        @Override
        public int getItemCount() {
            return listIV.size();
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
