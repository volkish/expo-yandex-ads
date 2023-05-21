import { Button, StyleSheet, Text, View } from 'react-native';

import { initialize, interstitialAd, ExpoYandexAdsView} from 'expo-yandex-ads';
import { useEffect } from 'react';

export default function App() {

    /*
    useEffect(() => {
    initialize({
      userConsent: true,
      // enableDebugErrorIndicator: true,
      // enableLogging: true,
      locationConsent: true
    })
  }, [])
  */
  // initialize({ userConsent: true, locationConsent: true });

  return (
    <View style={styles.container}>
      <Text>--</Text>
      <ExpoYandexAdsView
          style={{ width: 300, height: 250 }}
          onLayout={(event) => console.log(event.nativeEvent)}
          size='BANNER_300x250'
          adUnitId="R-M-243655-8" // 'R-M-2085336-1'
          onLoad={(...args: any) => console.error(args)}
          onError={(...args: any) => console.error(args)}
        />
      <Text>--</Text>
      <Button title='interstitialAd' onPress={() => interstitialAd("demo-interstitial-yandex")}/>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
