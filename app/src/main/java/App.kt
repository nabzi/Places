import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
//        startKoin {
//            // declare used Android context
//            androidContext(this@App)
//            // declare modules
//            modules(appModule)
//        }

        // Default binding components
        //DataBindingUtil.setDefaultComponent(BindingComponent())

    }
}