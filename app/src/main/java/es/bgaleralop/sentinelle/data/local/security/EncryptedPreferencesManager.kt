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

    fun observeUserTier(): Flow<UserTier> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == KEY_USER_TIER) {
                trySend(getUserTier())
            }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        // Send initial value
        trySend(getUserTier())

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

    companion object {
        private const val KEY_USER_TIER = "secure_key_user_tier"
    }
}
