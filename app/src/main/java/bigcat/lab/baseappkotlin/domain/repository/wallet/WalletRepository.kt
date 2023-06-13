package bigcat.lab.baseappkotlin.domain.repository.wallet

import bigcat.lab.baseappkotlin.domain.repository.Repository
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

