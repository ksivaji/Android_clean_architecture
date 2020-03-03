package com.example.githubrepos

import android.app.Activity
import android.app.Application
import android.util.Log
import com.example.githubrepos.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        setupAmpify()


    }

    private fun setupAmpify() {
        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.configure(applicationContext)
            Log.i("AmplifyGetStarted", "Amplify is all setup and ready to go!")
        } catch (exception: AmplifyException) {
            Log.e("AmplifyGetStarted", exception.getMessage())
        }
    }

    override fun activityInjector() = dispatchingAndroidInjector
}