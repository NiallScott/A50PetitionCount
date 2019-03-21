package uk.org.rivernile.android.a50petitiontracker.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import uk.org.rivernile.android.a50petitiontracker.dagger.main.PetitionRepositoryModule

@Module(includes = [
    ViewModelModule::class,
    ActivityModule::class,
    PetitionRepositoryModule::class
])
class ApplicationModule {

    @Provides
    fun provideApplicationContext(application: Application): Context = application
}