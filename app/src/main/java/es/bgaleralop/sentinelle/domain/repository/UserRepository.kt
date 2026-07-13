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

package es.bgaleralop.sentinelle.domain.repository

import es.bgaleralop.sentinelle.domain.model.UserSettingsState
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 24-06-2026
 *
 * Interface for the user repository.
 */
interface UserRepository {
    // La fuente única de la verdad reactiva en memoria
    val settingsState: StateFlow<UserSettingsState>

    // Lectura síncrona puntual instantánea.
    fun getCachedSettings(): UserSettingsState

    // Métodos de retrocompativilidad
    @Deprecated("Read directly from settingsStore instead")
    suspend fun getUserTier(): Flow<UserTier>

    // Métodos de escritrua asíncronos
    suspend fun setUserTier(tier: UserTier)
    suspend fun setEmojisFilter(enabled: Boolean)
    suspend fun setExternalLinks(enabled: Boolean)
    suspend fun setAdvancedMatchedFilter(enabled: Boolean)
    suspend fun setDarkMode(enabled: Boolean)
    suspend fun setLastFetch(timestamp: Long)
}