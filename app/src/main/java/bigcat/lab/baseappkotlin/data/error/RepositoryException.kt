package bigcat.lab.baseappkotlin.data.error

/**
 * @author mvn-toan.nguyen2 on 8/23/22
 **/
data class RepositoryException(
    val code: Int,
    val errorBody: String?,
    val msg: String
) : RuntimeException(msg)
