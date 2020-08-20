package com.example.mvvmarchitecture.models

class Repositories : ArrayList<RepositoryItem>()

data class RepositoryItem(
    val author: String,
    val avatar: String,
    val description: String,
    val language: String,
    val name: String,
    val stars: Int,
    val url: String
)
