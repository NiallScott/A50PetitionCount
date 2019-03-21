package uk.org.rivernile.android.a50petitiontracker

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import uk.org.rivernile.android.a50petitiontracker.dagger.DaggerApplicationComponent
import javax.inject.Inject

class A50Application : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}