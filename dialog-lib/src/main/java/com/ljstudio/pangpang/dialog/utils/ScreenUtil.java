package com.ljstudio.pangpang.dialog.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


public class ScreenUtil {
	
	private ScreenUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * Screen Width
	 */
	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context .getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * Screen Height
	 */
	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

    /** 
     * dp to px
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * px to dp
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    } 
    
    /**
     * status Height
     */
    public static int getStatusHeight(Context context) {  
    	Rect frame = new Rect();
    	((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    	int statusBarHeight = frame.top;
    	return statusBarHeight;
    } 

    
    /**
     * bar Height
     */
    public static int getBarHeight(Context context) {
    	int contentTop = ((Activity)context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    	return contentTop;
    }


	/**
	 * 截屏
	 */
    public static Bitmap screenShot(Context context, boolean isSave) {
		Bitmap bitmap;
		// View是你需要截图的View
		View view = ((Activity) context).getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();

//		int width = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
//		int height = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		Point point = new Point();
		display.getSize(point);
		int width = point.x;
		int height = point.y;

		// 状态栏高度
//		Rect frame = new Rect();
//		((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//		int statusBarHeight = frame.top;

//		bitmap = Bitmap.createScaledBitmap(b1, width, height - statusBarHeight, true);
		bitmap = Bitmap.createScaledBitmap(b1, width, height, true);

//		if (isSave) {
//			saveImage2SD(bitmap);
//		}
		return bitmap;
	}

	/**
	 * Toggle keyboard If the keyboard is visible,then hidden it,if it's
	 * invisible,then show it
	 *
	 * @param context 上下文
	 */
	public static void toggleKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

}
