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

package es.bgaleralop.sentinelle.data.repository

import es.bgaleralop.sentinelle.core.di.ApplicationScope
import es.bgaleralop.sentinelle.data.local.security.EncryptedPreferencesManager
import es.bgaleralop.sentinelle.domain.model.UserSettingsState
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import es.bgaleralop.sentinelle.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val prefsManager: EncryptedPreferencesManager,
    @ApplicationScope externalScope: CoroutineScope
) : UserRepository {

    init {
        UserRepository.globalInstance = this
    }

    // Cada vez que obserPreferencesChanged emite, se mapea la foto actual a UserSettingState
    override val settingsState: StateFlow<UserSettingsState> =
        prefsManager.observePreferencesChanged()
            .map {
                UserSettingsState(
                    userTier = prefsManager.getUserTier(),
                    isEmojisFilterEnabled = prefsManager.getEmojisFilter(),
                    isExternalLinksFilterEnabled = prefsManager.getExternalLinks(),
                    isAdvancedMatchedFilterEnabled = prefsManager.getAdvancedMatchedFilter(),
                    isDarkMode = prefsManager.getDarkMode(),
                )
            }
            .stateIn(
                scope = externalScope,
                started = SharingStarted.Eagerly, // Se inicializa inmediatamente con el proceso de la app
                initialValue = UserSettingsState()
            )

    override fun getCachedSettings(): UserSettingsState {
        return settingsState.value
    }

    /*TODO("FUNCION MOCKEADA PARA PRUEBAS*/
    @Deprecated("Read directly from settingsStore instead")
    override suspend fun getUserTier(): Flow<UserTier> {
        return flowOf(UserTier.PRO)
    }

    // Escrituras
    override suspend fun setUserTier(tier: UserTier) = prefsManager.setUserTier(tier)
    override suspend fun setEmojisFilter(enabled: Boolean) = prefsManager.setEmojisFilter(enabled)
    override suspend fun setExternalLinks(enabled: Boolean) = prefsManager.setExternalLinks(enabled)
    override suspend fun setAdvancedMatchedFilter(enabled: Boolean) =
        prefsManager.setAdvancedMatchedFilter(enabled)

    override suspend fun setDarkMode(enabled: Boolean) = prefsManager.setDarkMode(enabled)
    override suspend fun setLastFetch(timestamp: Long) = prefsManager.setLastFetch(timestamp)
}
