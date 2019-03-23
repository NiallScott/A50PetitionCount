package uk.co.getthenumbers.android.a50petitiontracker.petition

import javax.inject.Inject

class PetitionRepository @Inject constructor(private val petitionService: PetitionService) {

    fun getPetitionCountLiveData() = PetitionLiveData(petitionService).also { it.start() }
}