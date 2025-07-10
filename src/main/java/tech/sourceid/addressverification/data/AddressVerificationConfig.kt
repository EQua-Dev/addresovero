package tech.sourceid.addressverification.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressVerificationConfig(
    val apiKey: String = "",
    val token: String = "",
    val customerID: String = "",
    val initialAddressText: String? = null,
    val locationFetchIntervalHours: Double = 0.5,
    val locationFetchDurationDays: Double = 1.0,
    val verifyLocation: Boolean = false
): Parcelable

