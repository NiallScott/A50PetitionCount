package uk.org.rivernile.android.a50petitiontracker.repositories.petition

import javax.inject.Inject

class PetitionRepository @Inject constructor(private val petitionService: PetitionService) {

    fun getPetitionCountLiveData() = PetitionLiveData(petitionService).also { it.start() }
}