package ir.nabzi.places

import android.app.Application
import ir.nabzi.places.di.appModule
import ir.nabzi.places.di.dbModule
import ir.nabzi.places.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    dbModule
                )
            )
        }
    }
}