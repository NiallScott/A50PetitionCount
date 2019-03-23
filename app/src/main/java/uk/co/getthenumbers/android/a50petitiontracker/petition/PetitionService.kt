package uk.co.getthenumbers.android.a50petitiontracker.petition

import retrofit2.Call
import retrofit2.http.GET

interface PetitionService {

    @GET("petitions/241584/count.json")
    fun getPetitionCount(): Call<PetitionCount>
}