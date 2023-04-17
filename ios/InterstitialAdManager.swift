import ExpoModulesCore
import YandexMobileAds

public final class InterstitialAdManager: NSObject {
    weak var module: Module?
    
    var interstitialAd: YMAInterstitialAd!
    
    init(module: Module) {
        self.module = module
    }
    
    func load(adUnitId: String) {
        print("adUnitId \(adUnitId)")
        
        interstitialAd = YMAInterstitialAd.init(adUnitID: adUnitId)
        interstitialAd.delegate = self;
        interstitialAd.load()
    }
}

extension InterstitialAdManager: YMAInterstitialAdDelegate {
    
    public func interstitialAdDidLoad(_ interstitialAd: YMAInterstitialAd) {
        guard let currentVc = module?.appContext?.utilities?.currentViewController() else {
            return;
        }
        
        interstitialAd.present(from: currentVc)
        // module?.sendEvent("interstitialAdDidLoad")
    }
    
    public func interstitialAdDidFail(toLoad interstitialAd: YMAInterstitialAd, error: Error) {
        print(error)
    }
    
    public func interstitialAdDidFail(toPresent interstitialAd: YMAInterstitialAd, error: Error) {
        print(error)
    }
}
