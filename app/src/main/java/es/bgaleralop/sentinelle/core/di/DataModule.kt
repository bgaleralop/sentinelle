/*
 *
 *  Copyright (C) 2026 Sentinelle Team <bgaleralop@gmail.com>
 *
 *  This source code is property of Sentinelle Team.
 *  It is made available publicly for portfolio evaluation and educational purposes only.
 *  Commercial use, reproduction, or distribution in any digital storefront is
 *  strictly prohibited under the PolyForm Noncommercial License 1.0.0.
 *
 *  For full license details, see the LICENSE.md file in the root directory.
 *
 */
package es.bgaleralop.sentinelle.core.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
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
import es.bgaleralop.sentinelle.data.repository.SparklineRepositoryImpl
import es.bgaleralop.sentinelle.data.repository.UserRepositoryImpl
import es.bgaleralop.sentinelle.data.repository.WordLocalImpl
import es.bgaleralop.sentinelle.domain.repository.AccountRepository
import es.bgaleralop.sentinelle.domain.repository.BannedUserRepository
import es.bgaleralop.sentinelle.domain.repository.ModerationLogRepository
import es.bgaleralop.sentinelle.domain.repository.SparklineRepository
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import es.bgaleralop.sentinelle.domain.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    // --- ENLACES DE REPOSITORIOS (Abstracciones) ---
    // @Binds es mucho más eficiente y limpio para nuestras propias clases

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    abstract fun bindBlacklistRepository(impl: WordLocalImpl): WordRepository

    @Binds
    @Singleton
    abstract fun bindModerationLogRepository(impl: ModerationLogRepositoryImpl): ModerationLogRepository

    @Binds
    @Singleton
    abstract fun bindBannedUserRepository(impl: BannedUserRepositoryImpl): BannedUserRepository

    @Binds
    @Singleton
    abstract fun bindSparklineRepository(impl: SparklineRepositoryImpl): SparklineRepository


    companion object {
        // --- PROVEEDORES DE LIBRERÍAS EXTERNAS (Instanciaciones) ---

        @Provides
        @Singleton
        fun provideDataBase(@ApplicationContext context: Context): SentinelleDatabase {
            return Room.databaseBuilder(
                context,
                SentinelleDatabase::class.java,
                "sentinelle_database"
            ).fallbackToDestructiveMigration(true).build()
        }

        @Provides
        @Singleton
        fun provideEncryptedPreferences(@ApplicationContext context: Context): EncryptedPreferencesManager {
            return EncryptedPreferencesManager(context)
        }

        @Provides
        @Singleton
        @ApplicationScope // Enlazado mediante el Qualifier
        fun provideApplicationScope(): CoroutineScope {
            return CoroutineScope(SupervisorJob() + Dispatchers.Default)
        }

        // --- PROVEEDORES DE DAOS ---

        @Provides
        fun provideAccountDao(db: SentinelleDatabase) = db.accountDao

        @Provides
        fun provideBannedUserDao(db: SentinelleDatabase) = db.bannedUserDao

        @Provides
        fun provideBlacklistDao(db: SentinelleDatabase) = db.blacklistDao

        @Provides
        fun provideModerationLogDao(db: SentinelleDatabase) = db.moderationLogDao
    }
}