package bigcat.lab.pipchat.data.error

/**
 * @author hoantd on 8/23/22
 **/
data class RepositoryException(
    val code: Int,
    val errorBody: String?,
    val msg: String
) : RuntimeException(msg)
