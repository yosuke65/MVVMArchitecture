package com.example.mvvmarchitecture.models

class Repositories : ArrayList<RepositoryItem>()

data class RepositoryItem(
    val author: String,
    val avatar: String,
    val builtBy: List<BuiltBy>,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val name: String,
    val stars: Int,
    val url: String
)

data class BuiltBy(
    val avatar: String,
    val href: String,
    val username: String
)