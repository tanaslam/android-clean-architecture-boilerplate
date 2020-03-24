package org.healthapi.android.boilerplate.cache.db

/**
 * This class defines the tables found within the application Database. All table
 * definitions should contain column names and a sequence for creating the table.
 */
object Db {

    object Covid19StatsTable {
        const val TABLE_NAME = "coiv19stat"

        const val STATS_ID = "stat_id"
        const val STATE = "state"
        const val COUNTRY = "country"
        const val COUNTRY_CODE = "country_code"
        const val LAST_UPDATED = "last_update"
        const val CONFIRMED = "confirmed"
        const val DEATHS = "death"
        const val RECORVED = "recovered"

        const val CREATE =
                """CREATE TABLE $TABLE_NAME (
                        $STATS_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        $STATE TEXT NOT NULL,
                        $COUNTRY TEXT NOT NULL,
                        $COUNTRY_CODE TEXT NOT NULL,
                        $LAST_UPDATED NUMBER,
                        $CONFIRMED NUMBER,
                        $DEATHS NUMBER,
                        $RECORVED NUMBER
                ); """
    }

}