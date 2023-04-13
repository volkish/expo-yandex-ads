import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoYandexAds.web.ts
// and on native platforms to ExpoYandexAds.ts
import ExpoYandexAdsModule from './ExpoYandexAdsModule';
import ExpoYandexAdsView from './ExpoYandexAdsView';
import { ChangeEventPayload, ExpoYandexAdsViewProps } from './ExpoYandexAds.types';

// Get the native constant value.
export const PI = ExpoYandexAdsModule.PI;

export function hello(): string {
  return ExpoYandexAdsModule.hello();
}

export async function setValueAsync(value: string) {
  return await ExpoYandexAdsModule.setValueAsync(value);
}

const emitter = new EventEmitter(ExpoYandexAdsModule ?? NativeModulesProxy.ExpoYandexAds);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoYandexAdsView, ExpoYandexAdsViewProps, ChangeEventPayload };
