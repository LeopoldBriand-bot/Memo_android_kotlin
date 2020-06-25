package main

import android.app.Application
import dagger.AppComponent
import dagger.DaggerAppComponent

class DIApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().application(this)?.build()!!
    }

    companion object {

        private var instance: DIApplication? = null
        // Getter singleton :
        fun getAppComponent(): AppComponent? {
            return instance!!.appComponent
        }
    }

}