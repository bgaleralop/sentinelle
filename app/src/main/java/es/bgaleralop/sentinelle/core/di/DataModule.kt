package es.bgaleralop.sentinelle.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.bgaleralop.sentinelle.data.local.SentinelleDatabase
import es.bgaleralop.sentinelle.data.local.security.EncryptedPreferencesManager
import es.bgaleralop.sentinelle.data.repository.AccountRepositoryImpl
import es.bgaleralop.sentinelle.data.repository.BannedUserRepositoryImpl
import es.bgaleralop.sentinelle.data.repository.ModerationLogRepositoryImpl
import es.bgaleralop.sentinelle.data.repository.UserRepositoryImpl
import es.bgaleralop.sentinelle.data.repository.WordLocalImpl
import es.bgaleralop.sentinelle.domain.repository.AccountRepository
import es.bgaleralop.sentinelle.domain.repository.BannedUserRepository
import es.bgaleralop.sentinelle.domain.repository.ModerationLogRepository
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import es.bgaleralop.sentinelle.domain.repository.WordRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): SentinelleDatabase {
        return Room.databaseBuilder(
            context,
            SentinelleDatabase::class.java,
            "sentinelle_database"
        ).fallbackToDestructiveMigration(true).build() // Destructiva para agilizar el MVP
    }

    @Provides
    @Singleton
    fun provideEncryptedPreferences(@ApplicationContext context: Context): EncryptedPreferencesManager {
        return EncryptedPreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(prefs: EncryptedPreferencesManager): UserRepository {
        return UserRepositoryImpl(prefs)
    }

    @Provides
    @Singleton
    fun provideAccountRepository(db: SentinelleDatabase): AccountRepository {
        return AccountRepositoryImpl(db.accountDao)
    }

    @Provides
    @Singleton
    fun provideBlacklistRepository(db: SentinelleDatabase): WordRepository {
        return WordLocalImpl(db.blacklistDao)
    }

    @Provides
    @Singleton
    fun provideModerationLogRepository(db: SentinelleDatabase): ModerationLogRepository {
        return ModerationLogRepositoryImpl(db.moderationLogDao)
    }

    @Provides
    @Singleton
    fun provideBannedUserRepository(db: SentinelleDatabase): BannedUserRepository {
        return BannedUserRepositoryImpl(db.bannedUserDao)
    }
}