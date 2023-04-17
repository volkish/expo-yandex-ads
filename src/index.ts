import ExpoYandexAdsView from './ExpoYandexAdsView';
import ExpoYandexAdsModule from './ExpoYandexAdsModule';

import { ExpoYandexAdsInitializeOptions, ExpoYandexAdsViewProps } from './ExpoYandexAds.types';

export function initialize (options: ExpoYandexAdsInitializeOptions) {
  ExpoYandexAdsModule.initialize(options)
}

export async function interstitialAd(adUnitId: string) {
  ExpoYandexAdsModule.interstitialAd(adUnitId)
}

export { ExpoYandexAdsView, ExpoYandexAdsViewProps };
