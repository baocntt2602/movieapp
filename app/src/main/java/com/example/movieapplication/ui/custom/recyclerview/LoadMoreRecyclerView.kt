package com.example.movieapplication.ui.custom.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

class LoadMoreRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var externalScrollListener: OnScrollChangeListener? = null
    var loadMoreInvoker: (() -> Unit)? = null

    private var lockLoadMore = false

    private val loadMoreAdapter = LoadingAdapter()

    private var dataAdapter: Adapter<*>? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpLoadingMoreListener()
    }

    override fun setOnScrollChangeListener(l: OnScrollChangeListener?) {
        this.externalScrollListener = l
    }

    @Synchronized
    fun unlockLoadMore() {
        lockLoadMore = false
    }

    fun showLoadMoreIndicator() {
        loadMoreAdapter.flag = true
    }

    fun hideLoadMoreIndicator() {
        unlockLoadMore()
        loadMoreAdapter.flag = false
    }

    private fun setUpLoadingMoreListener() {
        super.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            externalScrollListener?.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY)
            val dY = scrollY - oldScrollY
            if (!this.canScrollVertically(1) && dY > 0 && !lockLoadMore) {
                lockLoadMore = true
                loadMoreInvoker?.invoke()
            }
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        dataAdapter = adapter
        val concatAdapter = ConcatAdapter(adapter, loadMoreAdapter)
        super.setAdapter(concatAdapter)
    }
}