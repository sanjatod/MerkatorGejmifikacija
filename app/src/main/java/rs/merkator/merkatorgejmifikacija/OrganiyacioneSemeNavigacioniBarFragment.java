package rs.merkator.merkatorgejmifikacija;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;


public class OrganiyacioneSemeNavigacioniBarFragment extends Fragment {


 @Override
 public View onCreateView(
         LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState
 ) {
  // Inflate the layout for this fragment
  return inflater.inflate(R.layout.fragment_organiyacione_seme_navigacioni_bar, container, false);
 }


 public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);
  view.findViewById(R.id.btnFinansije).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Finansije.pdf");

           }
          });
  view.findViewById(R.id.btnInvesticije).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Investicije, razvoj i kontrola poslovnih procesa.pdf");

           }
          });
  view.findViewById(R.id.btnLjResursi).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Ljudski resursi.pdf");

           }
          });
  view.findViewById(R.id.btnLogistika).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Logistika.pdf");

           }
          });
  view.findViewById(R.id.btnMakro).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Makroshema.pdf");

           }
          });
  view.findViewById(R.id.btnMaloprodaja).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Maloprodaja.pdf");

           }
          });
  view.findViewById(R.id.btnMarketing).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Marketing.pdf");

           }
          });
  view.findViewById(R.id.btnOperacije).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Operacije.pdf");

           }
          });
  view.findViewById(R.id.btnTreding).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Trading i CM.pdf");

           }
          });
  view.findViewById(R.id.btnVeleprodaja).setOnClickListener(
          new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            OpenPdf("Veleprodaja.pdf");

           }
          });



 }

 private void OpenPdf(String name)
 {
  StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
  StrictMode.setVmPolicy(builder.build());


  File file = null;
  file = new File(Environment.getExternalStorageDirectory() + File.separator + "Download/Gejmifikacija/OrganizacioneSeme/"+name);
  //Toast.makeText(getApplicationContext(), file.toString() , Toast.LENGTH_LONG).show();
  if (file.exists()) {

   try {
    Uri path = Uri.fromFile(file);

    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(path, "application/pdf");//pdf
    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    startActivity(intent);
   } catch (ActivityNotFoundException ex)
   {
    String s="";
   }
  }




 }
}
