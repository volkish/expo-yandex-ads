package expo.modules.yandexads

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.viewevent.EventDispatcher
import expo.modules.kotlin.views.ExpoView


class ExpoYandexAdsView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {

    private val onLoad by EventDispatcher()
    private val onError by EventDispatcher()
    private val eventLogger = BannerAdEventLogger()

    var size: String = "";
    var adUnitId: String = "";
    lateinit var mSize: AdSize;
    lateinit var mBannerAdView: BannerAdView;

    fun updateAdUnitId(newValue: String) {
        adUnitId = newValue
    }

    fun updateSize(newValue: String) {
        size = newValue
    }

    private fun dp2px(dp: Int, dm: DisplayMetrics): Int {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), dm))
    }

    fun initBanner() {
        if (size.isNotEmpty() && adUnitId.isNotEmpty()) {
            mBannerAdView = BannerAdView(context);
            mBannerAdView.setAdUnitId(adUnitId);

            Log.d("EYA", "Ad unit: $adUnitId")
            Log.d("EYA", "Size: $size")

            when (size) {
                "BANNER_300x250" -> {
                    mSize = AdSize.flexibleSize(300, 250);
                };
                "BANNER_300x300" -> {
                    mSize = AdSize.flexibleSize(300, 300);
                };
                "BANNER_320x50" -> {
                    mSize = AdSize.flexibleSize(320, 50);
                };
                "BANNER_320x100" -> {
                    mSize = AdSize.flexibleSize(320, 100);
                };
                "BANNER_400x240" -> {
                    mSize = AdSize.flexibleSize(400, 240);
                };
                "BANNER_728x90" -> {
                    mSize = AdSize.flexibleSize(728, 90);
                };
                else -> { // Note the block
                    mSize = AdSize.flexibleSize(300, 250);

                }
            }
            mBannerAdView.setAdSize(mSize);

            mBannerAdView.setBannerAdEventListener(eventLogger);

            val adRequest = AdRequest.Builder().build();

            // Загрузка объявления.
            mBannerAdView.loadAd(adRequest);
            mBannerAdView.layoutParams = LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            )
            mBannerAdView.gravity = Gravity.CENTER
        }
    }

    init {

    }

    private inner class BannerAdEventLogger : BannerAdEventListener {

        override fun onAdLoaded() {
            Log.d("EYA", "Banner ad loaded")

            val params = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ).apply {
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            }

            val r: Resources = context.resources
            val dm = r.displayMetrics
            val pxW = if (mSize.width > 0) dp2px(
                mSize.width,
                dm
            ) else r.displayMetrics.widthPixels
            val pxH: Int = dp2px(mSize.height, dm)

            mBannerAdView.measure(pxW, pxH)
            mBannerAdView.layout(0, 0, pxW, pxH)

            addView(mBannerAdView, params)
        }

        override fun onAdFailedToLoad(error: AdRequestError) {
            Log.d("EYA", "Banner ad failed to load with code ${error.code}: ${error.description}")
        }

        override fun onAdClicked() {
            Log.d("EYA", "Banner ad clicked")
        }

        override fun onLeftApplication() {
            Log.d("EYA", "Left application")
        }

        override fun onReturnedToApplication() {
            Log.d("EYA", "Returned to application")
        }

        override fun onImpression(data: ImpressionData?) {
            Log.d("EYA", "Impression: ${data?.rawData}")
        }
    }

}
