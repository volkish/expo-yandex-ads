import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ExpoYandexAdsViewProps } from './ExpoYandexAds.types';

const NativeView: React.ComponentType<ExpoYandexAdsViewProps> =
  requireNativeViewManager('ExpoYandexAds');

export default function ExpoYandexAdsView(props: ExpoYandexAdsViewProps) {
  return <NativeView {...props} />;
}
