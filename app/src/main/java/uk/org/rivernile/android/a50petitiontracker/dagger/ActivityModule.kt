package uk.org.rivernile.android.a50petitiontracker.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uk.org.rivernile.android.a50petitiontracker.ui.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}