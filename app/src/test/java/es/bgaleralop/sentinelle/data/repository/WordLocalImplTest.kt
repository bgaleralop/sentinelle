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

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import es.bgaleralop.sentinelle.data.local.dao.BlacklistDao
import es.bgaleralop.sentinelle.data.local.entity.BlacklistEntity
import es.bgaleralop.sentinelle.domain.model.Word
import es.bgaleralop.sentinelle.domain.model.enums.BlacklistAction
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WordLocalImplTest {

    private lateinit var blacklistDao: BlacklistDao
    private lateinit var repository: WordLocalImpl

    @Before
    fun setUp() {
        blacklistDao = mockk()
        repository = WordLocalImpl(blacklistDao)
    }

    @Test
    fun `given words in database, when getBlacklist is called, then it returns domain words`() =
        runTest {
            // Given
            val entities = listOf(
                BlacklistEntity(id = 1, word = "spam", action = "DELETE", accountId = 1L)
            )
            every { blacklistDao.getAllWords() } returns flowOf(entities)

            // When & Then
            repository.getBlacklist().test {
                val result = awaitItem()
                assertThat(result).hasSize(1)
                assertThat(result[0].word).isEqualTo("spam")
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given a word, when addWord is called, then it calls dao insert`() = runTest {
        // Given
        val word = Word(id = 0, word = "new", action = BlacklistAction.DELETE, accountId = 1L)
        coEvery { blacklistDao.insertWord(any()) } returns Unit

        // When
        val result = repository.addWord(word)

        // Then
        assertThat(result.isSuccess).isTrue()
        coVerify { blacklistDao.insertWord(any()) }
    }

    @Test
    fun `given a dao error, when addWord is called, then it returns failure`() = runTest {
        // Given
        val word = Word(id = 0, word = "new", action = BlacklistAction.DELETE, accountId = 1L)
        coEvery { blacklistDao.insertWord(any()) } throws Exception("DB Error")

        // When
        val result = repository.addWord(word)

        // Then
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("DB Error")
    }
}