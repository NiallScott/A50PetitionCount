package uk.co.getthenumbers.android.a50petitiontracker.petition

import android.os.Handler
import android.os.Message
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetitionLiveData(private val petitionService: PetitionService) : LiveData<Int>() {

    companion object {

        private const val REFRESH = 1
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                loadCount()
            }
        }
    }

    private var isStarted = false

    fun start() {
        isStarted = true
        handler.sendEmptyMessage(REFRESH)
    }

    fun stop() {
        isStarted = false
        handler.removeCallbacksAndMessages(null)
    }

    private fun loadCount() {
        petitionService.getPetitionCount().enqueue(object: Callback<PetitionCount> {
            override fun onFailure(call: Call<PetitionCount>, t: Throwable) {
                scheduleNextRefresh()
            }

            override fun onResponse(call: Call<PetitionCount>, response: Response<PetitionCount>) {
                if (response.isSuccessful) {
                    val body = response.body()

                    body?.let {
                        value = it.signatureCount
                    }
                }

                scheduleNextRefresh()
            }
        })
    }

    private fun scheduleNextRefresh() {
        if (isStarted) {
            handler.sendEmptyMessageDelayed(REFRESH, 60000)
        }
    }
}