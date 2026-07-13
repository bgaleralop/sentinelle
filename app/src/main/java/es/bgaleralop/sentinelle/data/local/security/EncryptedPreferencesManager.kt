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

package es.bgaleralop.sentinelle.data.local.security

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import es.bgaleralop.sentinelle.domain.model.enums.UserTier
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * @author Bartolomé Galear López (bgaleralop)
 * @date 24-06-2026
 *
 * Class that manage the encrypted preferences
 */
class EncryptedPreferencesManager(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "sentinelle_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /**
     * Notifica cualquier cambio en el archivo de SharedPreferences
     */
    fun observePreferencesChanged(): Flow<Unit> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
            trySend(Unit)
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        // Send initial value
        trySend(Unit)

        awaitClose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    fun getUserTier(): UserTier {
        val tierName = sharedPreferences.getString(KEY_USER_TIER, UserTier.FREE.name)

        return try {
            Log.d("EncryptedPreferences", "getUserTier: Tier name: $tierName")
            UserTier.valueOf(tierName ?: UserTier.FREE.name)
        } catch (e: Exception) {
            Log.e("EncryptedPreferences", "getUserTier: Error getting tier", e)
            UserTier.FREE
        }
    }

    fun setUserTier(tier: UserTier) {
        sharedPreferences.edit { putString(KEY_USER_TIER, tier.name) }
    }

    fun setEmojisFilter(enabled: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_EMOJIS_FILTER, enabled) }
    }

    fun getEmojisFilter(): Boolean {
        return sharedPreferences.getBoolean(KEY_EMOJIS_FILTER, false)
    }

    fun setExternalLinks(enabled: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_EXTERNAL_LINKS_FILTER, enabled) }
    }

    fun getExternalLinks(): Boolean {
        return sharedPreferences.getBoolean(KEY_EXTERNAL_LINKS_FILTER, false)
    }

    fun setAdvancedMatchedFilter(enabled: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_ADVANCED_MATCHED_FILTER, enabled) }
    }

    fun getAdvancedMatchedFilter(): Boolean {
        return sharedPreferences.getBoolean(KEY_ADVANCED_MATCHED_FILTER, false)
    }

    fun setDarkMode(enabled: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_DARK_MODE, enabled) }
    }

    fun getDarkMode(): Boolean {
        return sharedPreferences.getBoolean(KEY_DARK_MODE, true)
    }

    fun setLastFetch(lastFetch: Long) {
        sharedPreferences.edit { putLong(KEY_LAST_FETCH, lastFetch) }
    }

    fun getLastFetch(): Long {
        return sharedPreferences.getLong(KEY_LAST_FETCH, 0L)
    }



    companion object {
        private const val KEY_USER_TIER = "secure_key_user_tier"
        private const val KEY_EMOJIS_FILTER = "emojis_filter"
        private const val KEY_EXTERNAL_LINKS_FILTER = "external_links_filter"
        private const val KEY_ADVANCED_MATCHED_FILTER = "advanced_matched_filter"
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_LAST_FETCH = "last_fetch"
    }
}
