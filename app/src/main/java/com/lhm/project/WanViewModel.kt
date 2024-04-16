package com.lhm.project

import androidx.lifecycle.viewModelScope
import com.lhm.project.data.HotKey
import com.lhm.project.data.HotKeyList
import com.lhm.project.data.Tree
import com.lhm.project.data.TreeList
import com.lhm.project.utils.WanHelper
import com.example.miaow.base.http.get
import com.example.miaow.base.vm.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WanUiState(
    var hotKeyResult: MutableList<HotKey> = ArrayList(),
    var treeResult: MutableList<Tree> = ArrayList(),
    var searchHistoryResult: MutableList<String> = ArrayList(),
    var webBookmarkResult: MutableList<String> = ArrayList(),
    var webHistoryResult: MutableList<String> = ArrayList(),
    var updateTime: Long = 0
)

class WanViewModel : BaseViewModel() {

    private val _uiState = MutableStateFlow(WanUiState())

    val uiState: StateFlow<WanUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val hotKeyList = async { getHotKeyList() }
            val treeList = async { getTreeList() }
            val searchHistoryList = async { WanHelper.getSearchHistory() }
            val webBookmarkList = async { WanHelper.getWebBookmark() }
            val webHistoryList = async { WanHelper.getWebHistory() }
            _uiState.update { state ->
                hotKeyList.await().data?.let { data ->
                    state.hotKeyResult.clear()
                    state.hotKeyResult.addAll(data)
                }
                treeList.await().data?.let { data ->
                    state.treeResult.clear()
                    state.treeResult.addAll(data)
                }
                searchHistoryList.await().let {
                    state.searchHistoryResult.clear()
                    state.searchHistoryResult.addAll(it)
                }
                webHistoryList.await().let {
                    state.webHistoryResult.clear()
                    state.webHistoryResult.addAll(it)
                }
                webBookmarkList.await().let {
                    state.webBookmarkResult.clear()
                    state.webBookmarkResult.addAll(it)
                }
                state.copy(updateTime = System.nanoTime())
            }
        }
    }

    /**
     * 获取搜索热词
     */
    private suspend fun getHotKeyList(): HotKeyList {
        return coroutineScope {
            get {
                setUrl("hotkey/json")
            }
        }
    }

    /**
     * 获取项目分类
     */
    private suspend fun getTreeList(): TreeList {
        return coroutineScope {
            get {
                setUrl("tree/json")
            }
        }
    }

    fun onSearchHistory(isAdd: Boolean, text: String) {
        viewModelScope.launch {
            _uiState.update {
                if (it.searchHistoryResult.contains(text)) {
                    it.searchHistoryResult.remove(text)
                }
                if (isAdd) {
                    it.searchHistoryResult.add(0, text)
                }
                it.copy(updateTime = System.nanoTime())
            }
            WanHelper.setSearchHistory(_uiState.value.searchHistoryResult)
        }
    }

    fun onWebBookmark(isAdd: Boolean, text: String) {
        viewModelScope.launch {
            _uiState.update {
                if (it.webBookmarkResult.contains(text)) {
                    it.webBookmarkResult.remove(text)
                }
                if (isAdd) {
                    it.webBookmarkResult.add(0, text)
                }
                it.copy(updateTime = System.nanoTime())
            }
            WanHelper.setWebBookmark(_uiState.value.webBookmarkResult)
        }
    }

    fun onWebHistory(isAdd: Boolean, text: String) {
        viewModelScope.launch {
            _uiState.update {
                if (it.webHistoryResult.contains(text)) {
                    it.webHistoryResult.remove(text)
                }
                if (isAdd) {
                    it.webHistoryResult.add(0, text)
                }
                it.copy(updateTime = System.nanoTime())
            }
            WanHelper.setWebHistory(_uiState.value.webHistoryResult)
        }
    }

}