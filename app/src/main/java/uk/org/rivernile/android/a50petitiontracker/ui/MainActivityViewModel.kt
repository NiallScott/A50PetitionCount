package uk.org.rivernile.android.a50petitiontracker.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uk.org.rivernile.android.a50petitiontracker.repositories.petition.PetitionRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(petitionRepository: PetitionRepository) : ViewModel() {

    private val countLiveData = petitionRepository.getPetitionCountLiveData()

    override fun onCleared() {
        super.onCleared()

        countLiveData.stop()
    }

    fun getCount(): LiveData<Int> = countLiveData
}