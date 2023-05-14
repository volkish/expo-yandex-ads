package expo.modules.yandexads

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.views.ExpoView
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.common.MobileAds


class ExpoYandexAdsView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
    var size: String? = null
    var adUnitId: String = ""



    internal val textView = TextView(context).also {
        addView(it)
        it.layoutParams = LayoutParams(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
        )
        it.gravity = Gravity.CENTER
        it.setTextSize(TypedValue.COMPLEX_UNIT_SP,70f);
        it.text = "Hello!"
    }

    init {
        MobileAds.setUserConsent(true)
        MobileAds.setLocationConsent(true)
        //MobileAds.setAgeRestrictedUser(options.ageRestrictedUser)
        MobileAds.setAgeRestrictedUser(false)
        MobileAds.initialize(Common.appContext) {
            println("MobileAds init")
        }

        val mBannerAdView = BannerAdView(context);
        mBannerAdView.setAdUnitId(adUnitId);

        when (size) {
            "BANNER_300x250" -> mBannerAdView.setAdSize(AdSize.flexibleSize(300, 250));
            "BANNER_300x300" -> mBannerAdView.setAdSize(AdSize.flexibleSize(300, 300));
            "BANNER_320x50" -> mBannerAdView.setAdSize(AdSize.flexibleSize(320, 50));
            "BANNER_320x100" -> mBannerAdView.setAdSize(AdSize.flexibleSize(320, 100));
            "BANNER_400x240" -> mBannerAdView.setAdSize(AdSize.flexibleSize(400, 240));
            "BANNER_728x90" -> mBannerAdView.setAdSize(AdSize.flexibleSize(728, 90));
            else -> { // Note the block
                mBannerAdView.setAdSize(AdSize.flexibleSize(300, 250));
            }
        }

        addView(mBannerAdView)
    }
}
