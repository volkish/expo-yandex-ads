import * as React from 'react';

import { ExpoYandexAdsViewProps } from './ExpoYandexAds.types';

export default function ExpoYandexAdsView(props: ExpoYandexAdsViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
