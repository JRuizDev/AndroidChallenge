package es.joaquin.androidchallenge.model

data class ChallengesVo(
    val id: Int,
    val name: String,
    val description: String,
    val ownerLogin: String,
    val isFork: Boolean,
)