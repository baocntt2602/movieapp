package com.example.movieapplication.utils.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Looper
import android.view.View
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.core.util.Preconditions
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

val View.activity: Activity?
    get() {
        var context: Context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

val View.fragment: Fragment?
    get() {
        return try {
            FragmentManager.findFragment(this)
        } catch (e: IllegalStateException) {
            null
        }
    }

val View.lifecycle : Lifecycle? get() {
    var lifecycle: Lifecycle? = null

    val hostFrag = this.fragment
    if (hostFrag != null) {
        lifecycle = hostFrag.lifecycle
    } else {
        val hostActivity = this.activity as? FragmentActivity
        if (hostActivity != null) {
            lifecycle = hostActivity.lifecycle
        }
    }

    return lifecycle
}

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("RestrictedApi")
@CheckResult
fun EditText.textChangesFlow(): Flow<CharSequence> {
    return callbackFlow {
        Preconditions.checkState(
            Looper.getMainLooper().thread === Thread.currentThread(),
            "Not in application's main thread"
        )
        val listener = doOnTextChanged { text, _, _, _ -> if (text != null) trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }
}

@OptIn(FlowPreview::class)
fun EditText.onTextChangesDebounce(
    debounce: Long = 700,
    minLifeCycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    onTextChanged: (String) -> Unit
) {
    val lifecycle = this.lifecycle
    requireNotNull(lifecycle)
    textChangesFlow()
        .debounce(debounce)
        .flowWithLifecycle(lifecycle, minLifeCycleState)
        .onEach {
            onTextChanged.invoke(it.toString())
        }
        .launchIn(lifecycle.coroutineScope)
}