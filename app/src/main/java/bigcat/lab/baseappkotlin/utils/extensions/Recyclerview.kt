package bizverse.lab.healthylifestyle.utils.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author mvn-toan.nguyen2 on 8/22/22
 **/

fun RecyclerView.addScrollLoadMoreListener(onLoadMore: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            adapter?.let {
                val linearManager: LinearLayoutManager = layoutManager as LinearLayoutManager
                if (linearManager.findLastCompletelyVisibleItemPosition() == it.itemCount - 1) {
                    onLoadMore()
                }
            }
        }
    })
}
