package alfianyusufabdullah.paging.data.source

import alfianyusufabdullah.paging.entity.MainResponse

interface MainDataSourceCallback {
    fun onSuccess(mainResponse: MainResponse)
    fun onFailed(throwable: Throwable)
}