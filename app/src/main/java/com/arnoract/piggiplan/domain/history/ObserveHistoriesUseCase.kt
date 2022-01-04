package com.arnoract.piggiplan.domain.history

import com.arnoract.piggiplan.core.MediatorUseCase
import com.arnoract.piggiplan.core.Result
import com.arnoract.piggiplan.domain.model.history.History
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveHistoriesUseCase(
    private val historyRepository: HistoryRepository
) : MediatorUseCase<Unit, List<History>>() {
    override fun execute(parameters: Unit): Flow<Result<List<History>>> {
        return historyRepository.observeHistories().map { Result.Success(it) }
    }
}