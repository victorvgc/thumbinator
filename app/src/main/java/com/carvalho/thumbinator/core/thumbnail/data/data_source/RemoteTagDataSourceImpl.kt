package com.carvalho.thumbinator.core.thumbnail.data.data_source

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.data.model.RemoteTag
import com.carvalho.thumbinator.core.thumbnail.domain.data_source.TagDataSource
import com.carvalho.thumbinator.core.thumbnail.domain.model.ThumbTag
import com.carvalho.thumbinator.core.utils.COLLECTION_TAG_LIST
import com.carvalho.thumbinator.core.utils.COLLECTION_USER_INFO
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class RemoteTagDataSourceImpl @Inject constructor(private val remoteSource: FirebaseFirestore) : TagDataSource.Remote {
    override suspend fun saveTag(currentUser: CurrentUser, thumbTag: ThumbTag) {
        withContext(Dispatchers.IO) {
            remoteSource.collection(currentUser.uid)
                .document(COLLECTION_USER_INFO)
                .collection(COLLECTION_TAG_LIST)
                .add(RemoteTag.fromAppModel(thumbTag))
        }
    }

    override suspend fun getTag(currentUser: CurrentUser, thumbTag: ThumbTag): ThumbTag? =
        suspendCoroutine { continuation ->
            remoteSource.collection(currentUser.uid)
                .document(COLLECTION_USER_INFO)
                .collection(COLLECTION_TAG_LIST)
                .document(thumbTag.name)
                .get()
                .addOnSuccessListener {
                    val tag = it.toObject(RemoteTag::class.java)
                    tag?.name = it.id

                    continuation.resumeWith(
                        Result.success(
                            tag?.toAppModel()
                        )
                    )
                }
                .addOnFailureListener {
                    continuation.resumeWith(Result.success(null))
                }
                .addOnCanceledListener {
                    continuation.resumeWith(Result.success(null))
                }
        }
}