import { ViewProps } from "react-native";

export type ExpoYandexAdsViewProps = ViewProps & {
  size: string;
  adUnitId: string;
  onLoad?: () => void,
  onError?: (args: any) => void,
};

export type ExpoYandexAdsInitializeOptions = {
  userConsent?: boolean,
  locationConsent?: boolean,
  enableLogging?: boolean,
  enableDebugErrorIndicator?: boolean,
}