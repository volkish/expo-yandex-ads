import ExpoModulesCore
import YandexMobileAds
import UIKit
import SwiftUI

class ExpoYandexAdsView: ExpoView {
    let adViewDidLoad = EventDispatcher()
    let adViewDidFailLoading = EventDispatcher()
    
    var size: String?
    var adUnitId: String?
    
    private var adView: YMAAdView!
    
    required init(appContext: AppContext? = nil) {
        super.init(appContext: appContext)
        
        setNeedsLayout()
    }
    
    func reload() {
        if (adUnitId == nil || size == nil) {
            return ;
        }

        if (adView != nil) {
            adView.removeFromSuperview()
        }

        adView = YMAAdView.init(
            adUnitID: adUnitId!,
            adSize: getSize(size: size!)
        )

        adView.delegate = self
        adView.loadAd()
        
        addSubview(adView)
    }
   
    private func getSize(size: String) -> YMAAdSize {
        if (size == "BANNER_300x250") {
            return YMAAdSize.flexibleSize(with: .init(width: 300, height: 250))
        } else if (size == "BANNER_300x300") {
            return YMAAdSize.flexibleSize(with: .init(width: 300, height: 300))
        }else if (size == "BANNER_320x50") {
            return YMAAdSize.flexibleSize(with: .init(width: 320, height: 50))
        } else if (size == "BANNER_320x100") {
            return YMAAdSize.flexibleSize(with: .init(width: 320, height: 100))
        } else if (size == "BANNER_400x240") {
            return YMAAdSize.flexibleSize(with: .init(width: 400, height: 240))
        } else if (size == "BANNER_728x90") {
            return YMAAdSize.flexibleSize(with: .init(width: 728, height: 90))
        }
        
        return YMAAdSize.flexibleSize(with: .init(width: 250, height: 400))
    }
}

extension ExpoYandexAdsView: YMAAdViewDelegate {
    func adViewDidLoad(_ adView: YMAAdView) {
        adViewDidLoad([
            "adUnitID": adView.adUnitID,
            "width": adView.adContentSize().width,
            "height": adView.adContentSize().height,
        ])
    }
    
    func adViewDidFailLoading(_ adView: YMAAdView, error: Error) {
        adViewDidFailLoading([
            "message": error.localizedDescription
        ])
    }
}
