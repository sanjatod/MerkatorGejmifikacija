package rs.merkator.merkatorgejmifikacija;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemClickListener implements View.OnClickListener {

    private final Entry entry;
    private final ImageView srcView;
    private final ViewGroup rootView;
    private final ViewGroup destContainer;
    private final Activity activity;

    public ItemClickListener( Activity activity, Entry entry, ImageView srcView ) {
        this.activity = activity;
        this.entry = entry;
        this.srcView = srcView;
        this.rootView = ( ViewGroup )activity.findViewById( R.id.root_layout );
        this.destContainer = (LinearLayout)activity.findViewById( R.id.destination_container );
    }

    @Override
    public void onClick( View v ) {
        final FrameLayout destView = createDestView();
        final ViewTreeObserver observer = destView.getViewTreeObserver();
        observer.addOnPreDrawListener( new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                observer.removeOnPreDrawListener( this );

                final LinearLayout entryView = ( LinearLayout )destView.findViewById( R.id.entry );
                final ImageView imageView = ( ImageView )destView.findViewById( R.id.entry_image );

                imageView.setTranslationX( getAbsX( srcView ) - getAbsX( imageView ) );
                imageView.setTranslationY( getAbsY( srcView ) - getAbsY( imageView ) );
                rootView.getOverlay().add( imageView );
                imageView.animate()
                        .translationX( 0 )
                        .translationY( 0 )
                        .setInterpolator( new DecelerateInterpolator( 2 ) )
                        .setDuration( 500 );

                destContainer.getOverlay().add( entryView );
                destView.getLayoutParams().height = entryView.getMeasuredHeight();
                entryView.setAlpha( 0 );
                entryView.setTranslationY( 100 );
                entryView.animate()
                        .translationY( 0 )
                        .setDuration( 500 )
                        .alpha( 1 )
                        .setStartDelay( 0 )
                        .setInterpolator( new DecelerateInterpolator( 2 ) )
                        .withEndAction( new Runnable() {

                            @Override
                            public void run() {
                                destContainer.getOverlay().remove( entryView );
                                destView.addView( entryView );
                                rootView.getOverlay().remove( imageView );
                                entryView.addView( imageView, 0 );
                            }
                        } );
                return true;
            }
        } );
    }

    private float getAbsX( View view ) {
        if( view.getParent() == view.getRootView() ) {
            return view.getX();
        } else {
            return view.getX() + getAbsX( ( View )view.getParent() );
        }
    }

    private float getAbsY( View view ) {
        if( view.getParent() == view.getRootView() ) {
            return view.getY();
        } else {
            return view.getY() + getAbsY( ( View )view.getParent() );
        }
    }

    private FrameLayout createDestView() {
        LayoutInflater layoutInflater = LayoutInflater.from( activity );
        FrameLayout destView = ( FrameLayout )layoutInflater.inflate( R.layout.entry,
                destContainer,
                false );
        ImageView imageView = ( ImageView )destView.findViewById( R.id.entry_image );
        imageView.setImageResource( entry.getImageResId() );
        TextView textView = ( TextView )destView.findViewById( R.id.entry_text );
        textView.setText( entry.getTitle() );
        destContainer.addView( destView );
        return destView;
    }
}