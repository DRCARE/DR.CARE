package com.tvnsoftware.drcare.customizeview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.Size;
import com.tvnsoftware.drcare.R;


public class TopBarcodeView extends BarcodeView {
    public TopBarcodeView(Context context) {
        super(context);
    }

    public TopBarcodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopBarcodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    protected Rect calculateFramingRect(Rect container, Rect surface) {
        // intersection is the part of the container that is used for the preview
        Rect intersection = new Rect(container);
        boolean intersects = intersection.intersect(surface);

        Size framingRectSize = getFramingRectSize();
        if(framingRectSize != null) {
            // Specific size is specified. Make sure it's not larger than the container or surface.
            int horizontalMargin = Math.max(0, (intersection.width() - framingRectSize.width) / 2);
            int verticalMargin = Math.max(0, (intersection.height() - framingRectSize.height) / 4);
            intersection.inset(horizontalMargin, 0);
            final int marginTop = getResources().getDimensionPixelOffset(R.dimen.zing_barcode_margintop);
            intersection.top = marginTop;
            intersection.bottom = marginTop+ framingRectSize.height;
            return intersection;
        }
        // margin as 10% (default) of the smaller of width, height
        double marginFraction = getMarginFraction();
        int margin = (int)Math.min(intersection.width() * marginFraction, intersection.height() * marginFraction);
        intersection.inset(margin, margin);
        if (intersection.height() > intersection.width()) {
            // We don't want a frame that is taller than wide.
            intersection.inset(0, (intersection.height() - intersection.width()) / 2);
        }
        return intersection;
    }
}
