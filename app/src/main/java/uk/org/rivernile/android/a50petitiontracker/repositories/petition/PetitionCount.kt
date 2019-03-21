package uk.org.rivernile.android.a50petitiontracker.repositories.petition

import com.google.gson.annotations.SerializedName

data class PetitionCount(@SerializedName("signature_count") val signatureCount: Int)