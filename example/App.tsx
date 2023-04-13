import { StyleSheet, Text, View } from 'react-native';

import * as ExpoYandexAds from 'expo-yandex-ads';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>{ExpoYandexAds.hello()}</Text>
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
