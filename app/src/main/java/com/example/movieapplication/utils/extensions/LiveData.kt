package com.example.movieapplication.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.mutated(): MutableLiveData<T> {
    return this as MutableLiveData<T>
}