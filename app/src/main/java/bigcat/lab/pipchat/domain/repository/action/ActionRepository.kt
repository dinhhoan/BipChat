package bigcat.lab.pipchat.domain.repository.action

import bigcat.lab.pipchat.domain.repository.Repository
import javax.inject.Inject

/**
 *
 * @author at-hoan.tran
 */
class ActionRepository @Inject constructor(
    private val walletDataSource: ActionDataSource
) : Repository() {

//    fun getReferralRewards(perPage:Int, page:Int): Flow<FlowResult<T>> =
//        safeFlow {
//            walletDataSource.getReferralRewards(perPage, page)
//        }

}

