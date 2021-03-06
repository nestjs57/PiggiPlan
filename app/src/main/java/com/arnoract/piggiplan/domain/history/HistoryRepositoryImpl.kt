package com.arnoract.piggiplan.domain.history

import com.arnoract.piggiplan.core.db.model.history.FriendHistoryDao
import com.arnoract.piggiplan.core.db.model.history.HistoryDao
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.domain.model.history.History
import com.arnoract.piggiplan.domain.model.history.SaveSearchBranchNearbyHistoryForm
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyHistory
import com.arnoract.piggiplan.domain.model.history.mapper.HistoryEntityToHistoryMapper
import com.arnoract.piggiplan.domain.model.history.mapper.HistoryWithFriendsToSearchBranchNearbyHistoryMapper
import com.arnoract.piggiplan.domain.model.history.mapper.SaveBranchNearbyHistoryFormToFriendHistoryEntityMapper
import com.arnoract.piggiplan.domain.model.history.mapper.SaveSearchBranchNearbyHistoryFormToHistoryEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryRepositoryImpl(
    private val historyDao: HistoryDao,
    private val friendHistoryDao: FriendHistoryDao
) : HistoryRepository {
    override suspend fun saveSearchBranchNearbyHistory(formSearchBranchNearby: SaveSearchBranchNearbyHistoryForm) {
        historyDao.insertOrUpdate(
            SaveSearchBranchNearbyHistoryFormToHistoryEntityMapper.map(
                formSearchBranchNearby
            )
        )
        val friendsHistory = formSearchBranchNearby.searchBranchNearbyFriend.map {
            SaveBranchNearbyHistoryFormToFriendHistoryEntityMapper(formSearchBranchNearby.historyId).map(
                it
            )
        }
        friendHistoryDao.deleteByHistoryId(formSearchBranchNearby.historyId)
        friendHistoryDao.insert(friendsHistory)
    }

    override suspend fun getSearchBranchNearbyHistory(historyId: HistoryId): SearchBranchNearbyHistory {
        return HistoryWithFriendsToSearchBranchNearbyHistoryMapper.map(
            historyDao.getHistoryWithFriends(historyId)
        )
    }

    override fun observeHistories(): Flow<List<History>> {
        return historyDao.observeHistories().map { histories ->
            histories.map {
                HistoryEntityToHistoryMapper.map(it)
            }
        }
    }
}