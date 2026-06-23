package es.bgaleralop.sentinelle.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.bgaleralop.sentinelle.data.local.SentinelleDatabase
import es.bgaleralop.sentinelle.data.local.dao.BlacklistDao
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSentinelleDatabase(@ApplicationContext context: Context): SentinelleDatabase {
        return Room.databaseBuilder(
            context,
            SentinelleDatabase::class.java,
            "sentinelle_local_db"
        ).build()
    }

    @Provides
    fun provideBlacklistDao(database: SentinelleDatabase): BlacklistDao {
        return database.blacklistDao()
    }
}