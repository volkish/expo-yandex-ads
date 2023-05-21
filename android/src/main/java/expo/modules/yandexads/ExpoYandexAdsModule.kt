package expo.modules.yandexads

import androidx.preference.PreferenceManager
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import com.yandex.mobile.ads.common.MobileAds
import com.yandex.mobile.ads.instream.MobileInstreamAds
import android.util.Log;

data class InitializeOptions(
    val userConsent: Boolean = false,
    val locationConsent: Boolean = false,
    val enableLogging: Boolean = false,
    val enableDebugErrorIndicator: Boolean = false
)

class ExpoYandexAdsModule : Module() {

    // Each module class must implement the definition function. The definition consists of components
    // that describes the module's functionality and behavior.
    // See https://docs.expo.dev/modules/module-api for more details about available components.
    override fun definition() = ModuleDefinition {
        // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
        // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
        // The module will be accessible from `requireNativeModule('ExpoYandexAds')` in JavaScript.
        Name("ExpoYandexAds")

        /*
            // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
            Function("initialize") { options: InitializeOptions ->
              val preferences = PreferenceManager.getDefaultSharedPreferences(Common.appContext)
              preferences.run {
                  MobileAds.setUserConsent(options.userConsent)
                  MobileAds.setLocationConsent(options.locationConsent)
                  //MobileAds.setAgeRestrictedUser(options.ageRestrictedUser)
                  MobileAds.setAgeRestrictedUser(false)
              }

              MobileInstreamAds.setAdGroupPreloading(INSTREAM_AD_GROUP_PRELOADING_ENABLED)
              MobileAds.initialize(Common.appContext) {
                  println("MobileAds init")
              }

              MobileAds.enableLogging(options.enableLogging)
            }
            */

        OnCreate {
            val preferences = PreferenceManager.getDefaultSharedPreferences(Common.appContext)
            preferences.run {
                MobileAds.setUserConsent(true)
                MobileAds.setLocationConsent(true)
                //MobileAds.setAgeRestrictedUser(options.ageRestrictedUser)
                MobileAds.setAgeRestrictedUser(false)
            }

            MobileInstreamAds.setAdGroupPreloading(INSTREAM_AD_GROUP_PRELOADING_ENABLED)
            MobileAds.initialize(Common.appContext) {
                Log.d("EYA", "Mobile Ads init")
            }

            MobileAds.enableLogging(true)
        }

        AsyncFunction("interstitialAd") { adUnitId: String  ->
            val interstitialAd = InterstitialAdManager(adUnitId);
        }


        // Enables the module to be used as a native view. Definition components that are accepted as part of
        // the view definition: Prop, Events.
        View(ExpoYandexAdsView::class) {
            // Defines a setter for the `name` prop.
            Prop("size") { view: ExpoYandexAdsView, prop: String ->
                view.updateSize(prop)
                view.initBanner();
            }

            Prop("adUnitId") { view: ExpoYandexAdsView, prop: String ->
                view.updateAdUnitId(prop)
                view.initBanner();
            }

        }
    }

    private companion object {
        private const val INSTREAM_AD_GROUP_PRELOADING_ENABLED = true
    }
}
