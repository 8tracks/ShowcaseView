package com.espian.showcaseview.drawing;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.github.espiandev.showcaseview.R;

/**
 * User: martin
 * Date: 2/12/14
 * Time: 3:03 PM
 */
public class TwoArrowsClickDrawerImpl implements ClingDrawer {
	private Drawable mShowcaseDrawable;
	private Rect mShowcaseRect;
	public TwoArrowsClickDrawerImpl(Resources resources, int showcaseColor) {
		mShowcaseDrawable = resources.getDrawable(R.drawable.hand);
		mShowcaseDrawable.setColorFilter(showcaseColor, PorterDuff.Mode.MULTIPLY);
	}
	@Override
	public boolean calculateShowcaseRect(final float x, final float y) {
		if (mShowcaseRect == null) {
			mShowcaseRect = new Rect();
		}
		int cx = (int) x, cy = (int) y;
		int dw = getShowcaseWidth();
		int dh = getShowcaseHeight();
		if (mShowcaseRect.left == cx - dw / 2) {
			return false;
		}
		Log.d("ShowcaseView", "Recalculated");
		mShowcaseRect.left = cx - dw / 2;
		mShowcaseRect.top = cy - dh / 2;
		mShowcaseRect.right = cx + dw / 2;
		mShowcaseRect.bottom = cy + dh / 2;
		return true;
	}
	public Rect getShowcaseRect() {
		return mShowcaseRect;
	}
	@Override
	public void drawShowcase(final Canvas canvas, final float x, final float y, final float scaleMultiplier, final float radius) {
		Matrix mm = new Matrix();
		mm.postScale(scaleMultiplier, scaleMultiplier, x, y);
		canvas.setMatrix(mm);
		mShowcaseDrawable.setBounds(mShowcaseRect);
		mShowcaseDrawable.draw(canvas);
		canvas.setMatrix(new Matrix());
	}
	@Override
	public int getShowcaseWidth() {
		return mShowcaseDrawable.getIntrinsicWidth();
	}
	@Override
	public int getShowcaseHeight() {
		return mShowcaseDrawable.getIntrinsicHeight();
	}
}
