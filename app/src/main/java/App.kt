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
            // declare used Android context
            androidContext(this@App)
            // declare modules
            modules(
                listOf(
                    appModule,
                    networkModule,
                    dbModule
                )
            )
        }

        // Default binding components
        //DataBindingUtil.setDefaultComponent(BindingComponent())

    }
}