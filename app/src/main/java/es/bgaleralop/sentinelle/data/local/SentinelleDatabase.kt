package es.bgaleralop.sentinelle.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.bgaleralop.sentinelle.data.local.dao.AccountDao
import es.bgaleralop.sentinelle.data.local.dao.BannedUserDao
import es.bgaleralop.sentinelle.data.local.dao.BlacklistDao
import es.bgaleralop.sentinelle.data.local.dao.ModerationLogDao
import es.bgaleralop.sentinelle.data.local.entity.AccountEntity
import es.bgaleralop.sentinelle.data.local.entity.BannedUserEntity
import es.bgaleralop.sentinelle.data.local.entity.BlacklistEntity
import es.bgaleralop.sentinelle.data.local.entity.ModerationLogEntity

@Database(
    entities = [
        AccountEntity::class,
        BlacklistEntity::class,
        ModerationLogEntity::class,
        BannedUserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SentinelleDatabase : RoomDatabase() {
    abstract val accountDao: AccountDao
    abstract val blacklistDao: BlacklistDao
    abstract val moderationLogDao: ModerationLogDao
    abstract val bannedUserDao: BannedUserDao
}