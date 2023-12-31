package com.example.gossip.firestoredb.repository

import android.net.Uri
import com.example.gossip.model.UserDataModelResponse
import com.example.gossip.util.Resource
import com.example.gossip.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    fun getUsers(): Flow<ResultState<List<UserDataModelResponse.User>>>

    fun searchUsers(searchString: String): Flow<ResultState<List<UserDataModelResponse.User>>>

    fun getUserData(key: String): Flow<ResultState<UserDataModelResponse.User?>>

    fun delete(
        key: String
    ): Flow<ResultState<String>>

    fun uploadPic(
        image: Uri,
        key: String
    ): Flow<ResultState<String>>

    suspend fun getProfilePic(
        key: String
    ): Resource<Uri>

    fun updateUser(
        res: UserDataModelResponse.User
    ): Flow<ResultState<String>>

}