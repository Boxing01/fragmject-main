package com.lhm.project.ui.search

import androidx.lifecycle.viewModelScope
import com.lhm.project.data.Article
import com.lhm.project.data.ArticleList
import com.example.miaow.base.http.post
import com.example.miaow.base.vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SearchUiState(
    var isSearch: Boolean = false,
    var refreshing: Boolean = false,
    var loading: Boolean = false,
    var finishing: Boolean = false,
    var articlesResult: MutableList<Article> = ArrayList()
)

class SearchViewModel : BaseViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())

    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun clearArticles() {
        _uiState.update {
            it.copy(isSearch = false, articlesResult = ArrayList())
        }
    }

    fun getHome(key: String) {
        _uiState.update {
            it.copy(isSearch = true, refreshing = true, loading = false, finishing = false)
        }
        getList(key, getHomePage())
    }

    fun getNext(key: String) {
        _uiState.update {
            it.copy(refreshing = false, loading = false, finishing = false)
        }
        getList(key, getNextPage())
    }

    /**
     * 搜索
     * k 搜索关键词
     * page 0开始
     */
    private fun getList(key: String, page: Int) {
        viewModelScope.launch {
            val response = post<ArticleList> {
                setUrl("article/query/{page}/json")
                putParam("k", key)
                putPath("page", page.toString())
            }
            updatePageCont(response.data?.pageCount?.toInt())
            _uiState.update { state ->
                response.data?.datas?.let { datas ->
                    if (isHomePage()) {
                        state.articlesResult.clear()
                    }
                    state.articlesResult.addAll(datas)
                }
                state.copy(
                    refreshing = false,
                    loading = hasNextPage(),
                    finishing = !hasNextPage()
                )
            }
        }
    }

}