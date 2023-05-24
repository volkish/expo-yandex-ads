package expo.modules.yandexads

import expo.modules.kotlin.records.Field
import expo.modules.kotlin.records.Record

internal data class InitializeOptions(
  @Field var userConsent: Boolean = false,
  @Field var locationConsent: Boolean = false,
  @Field var enableLogging: Boolean = false,
  @Field var enableDebugErrorIndicator: Boolean = false,
) : Record