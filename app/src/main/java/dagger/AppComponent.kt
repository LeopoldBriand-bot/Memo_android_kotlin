package dagger

import android.app.Application
import repository.MainRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(diApplication: Application): Builder?
        fun build(): AppComponent?
    }

    fun inject(mainRepository: MainRepository)

}