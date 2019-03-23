package uk.co.getthenumbers.android.a50petitiontracker.petition

import com.google.gson.annotations.SerializedName

data class PetitionCount(@SerializedName("signature_count") val signatureCount: Int)