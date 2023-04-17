import ExpoModulesCore
import YandexMobileAds

public final class ExpoYandexAdsModule: Module {
    private var interstitialAdManager: InterstitialAdManager!
    
    
    public func definition() -> ModuleDefinition {
        Name("ExpoYandexAds")
        
        OnCreate {
            interstitialAdManager = InterstitialAdManager(module: self)
        }

        Function("initialize") { (value: InitializeOptions) in
            YMAMobileAds.setUserConsent(value.userConsent)
            YMAMobileAds.setLocationTrackingEnabled(value.locationConsent)
            
            if (value.enableLogging) {
                YMAMobileAds.enableLogging()
            }
            
            if (value.enableDebugErrorIndicator) {
                YMAMobileAds.enableVisibilityErrorIndicator(for: [.hardware, .simulator])
            }
        }
        
        AsyncFunction("interstitialAd") { (adUnitId: String, promise: Promise) in
            interstitialAdManager.load(adUnitId: adUnitId)
            promise.resolve()
        }
        
        View(ExpoYandexAdsView.self) {
            Events(
                "adViewDidLoad",
                "adViewDidFailLoading"
            )
            
            Prop("adUnitId") { (view: ExpoYandexAdsView, adUnitId: String) in
                view.adUnitId = adUnitId
            }
            
            Prop("size") { (view: ExpoYandexAdsView, size: String) in
                view.size = size
            }
            
            OnViewDidUpdateProps { view in
                view.reload()
            }
        }
    }
}


struct InitializeOptions: Record {
    @Field
    var userConsent: Bool = false
    
    @Field
    var locationConsent: Bool = false
    
    @Field
    var enableLogging: Bool = false
    
    @Field
    var enableDebugErrorIndicator: Bool = false
}
