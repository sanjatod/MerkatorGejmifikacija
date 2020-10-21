package rs.merkator.merkatorgejmifikacija;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Touch implements View.OnTouchListener {

    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;

    private static final float MIN_ZOOM = 1f;
    private static final float MAX_ZOOM = 5f;

    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    private PointF start = new PointF();
    private PointF mid = new PointF();

    private int mode = NONE;
    private float oldDistance = 1f;

    public boolean onTouch(View view, MotionEvent event) {
        ImageView imageView = (ImageView)view;

        switch(event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                oldDistance = spacing(event);
                if(oldDistance > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;

            case MotionEvent.ACTION_MOVE:
                if(mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                }
                else if(mode == ZOOM) {
                    float newDistance = spacing(event);
                    if(newDistance > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDistance / oldDistance;
                        float[] values = new float[9];
                        matrix.getValues(values);
                        float currentScale = values[Matrix.MSCALE_X];
                        if(scale * currentScale > MAX_ZOOM)
                            scale = MAX_ZOOM / currentScale;
                        else if (scale * currentScale < MIN_ZOOM)
                            scale = MIN_ZOOM / currentScale;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }
        imageView.setImageMatrix(matrix);
        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        point.set((event.getX(0) + event.getX(1)) / 2, (event.getY(0) + event.getY(1)) / 2);
    }

}