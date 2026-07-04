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

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import es.bgaleralop.sentinelle.domain.usecase.ConfigUseCase
import javax.inject.Singleton

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 02-07-2026
 *
 * Configuración inyección dependencias de los casos de uso y servicios.
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideConfigDataUseCase(userRepository: UserRepository): ConfigUseCase {
        return ConfigUseCase(userRepository)
    }
}