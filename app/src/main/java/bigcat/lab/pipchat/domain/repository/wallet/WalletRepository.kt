package bigcat.lab.pipchat.domain.repository.wallet

import bigcat.lab.pipchat.domain.repository.Repository
import javax.inject.Inject

/**
 *
 * @author at-hoan.tran
 */
class WalletRepository @Inject constructor(
    private val walletDataSource: WalletDataSource
) : Repository() {

//    fun getReferralRewards(perPage:Int, page:Int): Flow<FlowResult<T>> =
//        safeFlow {
//            walletDataSource.getReferralRewards(perPage, page)
//        }

}

