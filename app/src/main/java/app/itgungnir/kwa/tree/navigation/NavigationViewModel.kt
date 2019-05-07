package app.itgungnir.kwa.tree.navigation

import android.annotation.SuppressLint
import app.itgungnir.kwa.common.http.HttpClient
import app.itgungnir.kwa.common.http.handleResult
import app.itgungnir.kwa.common.http.io2Main
import my.itgungnir.rxmvvm.core.mvvm.BaseViewModel

class NavigationViewModel : BaseViewModel<NavigationState>(initialState = NavigationState()) {

    @SuppressLint("CheckResult")
    fun getNavigationList() {
        HttpClient.api.navigation()
            .handleResult()
            .io2Main()
            .map {
                it.map { item ->
                    NavigationState.NavigationVO(
                        title = item.name,
                        children = item.articles.map { data ->
                            NavigationState.NavigationVO.NavTagVO(
                                name = data.title,
                                link = data.link
                            )
                        },
                        selected = false
                    )
                }
            }.subscribe({
                setState {
                    copy(
                        items = it,
                        error = null
                    )
                }
            }, {
                setState {
                    copy(
                        error = it
                    )
                }
            })
    }
}