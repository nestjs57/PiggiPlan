package com.arnoract.piggiplan.di

import com.arnoract.piggiplan.core.db.DaoBuilder
import com.arnoract.piggiplan.core.db.DatabaseBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseBuilder(androidApplication()).buildRoomDatabaseStorage() }
    single { DaoBuilder(get()).getRestaurantDao() }
    single { DaoBuilder(get()).getBranchDao() }
    single { DaoBuilder(get()).getFavoriteDao() }
    single { DaoBuilder(get()).getHistoryDao() }
    single { DaoBuilder(get()).getFriendHistoryDao() }
}