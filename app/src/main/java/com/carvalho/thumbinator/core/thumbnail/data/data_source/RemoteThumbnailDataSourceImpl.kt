package com.carvalho.thumbinator.core.thumbnail.data.data_source

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.thumbnail.data.model.RemoteThumbnail
import com.carvalho.thumbinator.core.thumbnail.domain.data_source.ThumbnailDataSource
import com.carvalho.thumbinator.core.thumbnail.domain.model.Thumbnail
import com.carvalho.thumbinator.core.utils.COLLECTION_THUMBNAIL_LIST
import com.carvalho.thumbinator.core.utils.COLLECTION_USER_INFO
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RemoteThumbnailDataSourceImpl @Inject constructor(private val remoteSource: FirebaseFirestore) :
    ThumbnailDataSource.Remote {
    override suspend fun fetchThumbnailList(currentUser: CurrentUser): Flow<List<Thumbnail>> =
        callbackFlow {
            val subscription = remoteSource.collection(currentUser.uid)
                .document(COLLECTION_USER_INFO)
                .collection(COLLECTION_THUMBNAIL_LIST)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        trySend(emptyList())

                        return@addSnapshotListener
                    }


                    val list = mutableListOf<Thumbnail>()
                    if (value != null) {

                        for (doc in value.documents) {
                            val thumb = doc.toObject(RemoteThumbnail::class.java)

                            if (thumb != null)
                                list.add(thumb.toAppModel())
                        }
                    }

                    trySend(list)
                }

            awaitClose {
                subscription.remove()
            }
        }
}