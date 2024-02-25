package com.mayursarode.newsapp.ui.newsbysources

import androidx.lifecycle.viewModelScope
import com.mayursarode.newsapp.data.model.ApiArticle
import com.mayursarode.newsapp.data.repository.NewsBySourcesRepository
import com.mayursarode.newsapp.ui.base.BaseViewModel
import com.mayursarode.newsapp.ui.base.UiState
import com.mayursarode.newsapp.utils.DispatcherProvider
import com.mayursarode.newsapp.utils.ResourceProvider
import com.mayursarode.newsapp.utils.logger.Logger
import com.mayursarode.newsapp.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsBySourcesViewModel @Inject constructor(
    private val newsBySourcesRepository: NewsBySourcesRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
    private val resourceProvider: ResourceProvider,
    private val logger: Logger
) : BaseViewModel() {

    companion object {
        const val TAG = "NewsBySourcesViewModel"
    }

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState

    fun fetchNewsBySources(sourceName: String?) {
        viewModelScope.launch(dispatcherProvider.main) {
            if (!networkHelper.isNetworkConnected()) {
                val errorText = resourceProvider.getStringNoInternetAvailable()
                _uiState.value = UiState.Error(errorText)
                return@launch
            } else {
                logger.d(TAG, "fetchNewsBySources: $sourceName")
                _uiState.value = UiState.Loading
                val replaceString = sourceName?.replace("{", "")?.replace("}", "")
                logger.d(TAG, "replaceString: $replaceString")
                sourceName?.let { it ->
                    newsBySourcesRepository.getNewsBySources(it) //pass source
                        .catch { e ->
                            _uiState.value = UiState.Error(e.toString())
                            logger.d(
                                TAG, "fetchNewsBySources: ${e.message.toString()}"
                            )
                        }.collect {
                            _uiState.value = UiState.Success(it)
                            logger.d(TAG, it.toString())

                        }
                }
            }
        }
    }

}