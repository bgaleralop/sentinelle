package es.bgaleralop.sentinelle.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.bgaleralop.sentinelle.data.local.dao.BlacklistDao
import es.bgaleralop.sentinelle.data.local.entity.BlacklistEntity

@Database(
    entities = [BlacklistEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SentinelleDatabase : RoomDatabase() {
    abstract fun blacklistDao(): BlacklistDao
}