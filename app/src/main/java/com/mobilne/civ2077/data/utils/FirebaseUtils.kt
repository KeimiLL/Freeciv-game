package com.mobilne.civ2077.data.utils

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            // if true we have an exception
            if (it.exception != null) {
                // failure
                cont.resumeWithException(it.exception!!)
            } else {
                // success
                cont.resume(it.result, null)
            }
        }
    }
}