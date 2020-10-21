package rs.merkator.merkatorgejmifikacija;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.List;

public class CustomSpinnerArrayAdapter extends ArrayAdapter<String> {

    private int mSelectedPosition = -1;

    public CustomSpinnerArrayAdapter(Context context, @LayoutRes int resource, @IdRes int textViewResourceId,
                                     @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);

        TextView textView = view.findViewById(R.id.option_text);

        textView.setTextColor(Color.BLACK);
        ImageView radioButton = view.findViewById(R.id.radio_button);
        radioButton.setVisibility(View.VISIBLE);

        if (position == mSelectedPosition
                || (mSelectedPosition == -1 && position == 0)) {
            radioButton.setImageDrawable(parent.getContext().getResources().getDrawable(R.drawable.ic_radio_active));
        }
        else {
            radioButton.setImageDrawable(parent.getContext().getResources().getDrawable(R.drawable.ic_radio_normal));
        }

        return view;
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
    }
}
