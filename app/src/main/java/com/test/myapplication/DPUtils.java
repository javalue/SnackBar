package com.test.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;import android.util.DisplayMetrics;

/**
 * Created by Chandler on 2017/6/27.
 */

public class DPUtils {
  private static float sPixelDensity = -1;

  public static float dpToPixel(@Nullable Context context, float dp) {
    if (sPixelDensity < 0) {
      if (context != null) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        sPixelDensity = metrics.density;
      }
    }
    return sPixelDensity * dp;
  }

  public static int dpToPixel(Context context, int dp) {
    return (int) (dpToPixel(context, (float) dp) + .5f);
  }

  public static int dpFloatToPixel(Context context, float dp) {
    return (int) (dpToPixel(context, dp) + .5f);
  }
}
