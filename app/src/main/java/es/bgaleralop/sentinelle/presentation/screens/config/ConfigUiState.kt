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

package es.bgaleralop.sentinelle.presentation.screens.config

import es.bgaleralop.sentinelle.domain.model.enums.UserTier

/**
 * @author Bartolomé Galera López
 * @date 02-07-2026
 *
 * Estados de la pantalla de configuración.
 */
sealed interface ConfigUiState {
    object Loading : ConfigUiState

    data class Success(
        val currentTier: UserTier,
        val isAdvanceOptionEnabled: Boolean = currentTier != UserTier.FREE,
        val isEmojisFilterActivated: Boolean,
        val isExternalLinksActivated: Boolean,
        val isAdvanceMatchedActivated: Boolean,
        val isDarkMode: Boolean,
    ) : ConfigUiState

    data class Error(val message: String) : ConfigUiState
}