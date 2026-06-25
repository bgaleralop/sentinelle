package es.bgaleralop.sentinelle.data.local.mappers

import es.bgaleralop.sentinelle.data.local.entity.AccountEntity
import es.bgaleralop.sentinelle.data.local.entity.BannedUserEntity
import es.bgaleralop.sentinelle.data.local.entity.BlacklistEntity
import es.bgaleralop.sentinelle.data.local.entity.ModerationLogEntity
import es.bgaleralop.sentinelle.domain.model.BannedUser
import es.bgaleralop.sentinelle.domain.model.ModerationLog
import es.bgaleralop.sentinelle.domain.model.SentinelleAccount
import es.bgaleralop.sentinelle.domain.model.Word
import es.bgaleralop.sentinelle.domain.model.enums.BlacklistAction
import es.bgaleralop.sentinelle.domain.model.enums.Platform

/**
 * @author Bartolomé Galera López (bgaleralop)
 * @date 23-06-2026
 *
 * File that contains the different mappers between our local repository entities and our domain classes.
 */
fun AccountEntity.toDomain() = SentinelleAccount(
    platform = Platform.valueOf(platform),
    accountHandle = accountHandle,
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessTokenExpiration = accessTokenExpiration,
    refreshTokenExpiration = refreshTokenExpiration,
    id = id,
    isActive = isActive
)

fun SentinelleAccount.toEntity() = AccountEntity(
    platform = platform.name,
    accountHandle = accountHandle,
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessTokenExpiration = accessTokenExpiration,
    refreshTokenExpiration = refreshTokenExpiration,
    id = id,
    isActive = isActive
)

fun BlacklistEntity.toDomain() = Word(
    id = id,
    accountId = accountId,
    word = word,
    action = BlacklistAction.valueOf(action)
)

fun Word.toEntity() = BlacklistEntity(
    id = id,
    accountId = accountId,
    word = word,
    action = action.name
)

fun ModerationLogEntity.toDomain() = ModerationLog(
    id,
    accountId,
    commentId,
    authorUsername,
    commentText,
    timestamp,
    isSpam,
    matchedWord,
    actionTaken
)
fun ModerationLog.toEntity() = ModerationLogEntity(
    id, accountId, commentId, authorUsername, commentText, timestamp, isSpam, matchedWord, actionTaken
)

fun BannedUserEntity.toDomain() = BannedUser(platformUserId, accountId, username, bannedAt, reason)
fun BannedUser.toEntity() = BannedUserEntity(platformUserId, accountId, username, bannedAt, reason)