package dagger

import android.app.Application
import android.content.Context
import appDatabase.AppDatabaseHelper
import memo.MemoDAO
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideMemoDAO(context: Context): MemoDAO
    {
        return AppDatabaseHelper.getDatabase(context).memoDAO()!!
    }
}