package com.example.login.data.repository

import com.example.login.data.model.Dependency
import com.example.login.ui.network.BaseResult

object DependencyRepository {
    private var dataSet: MutableList<Dependency> = mutableListOf()

    init {
        initialice()
    }

    private fun initialice() {
        dataSet.add(
            Dependency(
                name = "Aula 2CFGS",
            )
        )
    }

    suspend fun validate(email: String, password:String): BaseResult<Dependency> {
        return BaseResult.Success(Dependency("2cfgs"))
    }
}