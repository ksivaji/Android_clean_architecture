package com.example.githubrepos.utils

import com.example.githubrepos.data.GithubRepo
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class UserRepoDeserializer : JsonDeserializer<List<GithubRepo>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<GithubRepo> {
        val userRepos = mutableListOf<GithubRepo>()
        val jsonArray = json!!.asJsonArray
        for (i in 0 until jsonArray.size()) {
            val item = jsonArray[i].asJsonObject

            val repoName = item.get("userName").asString
            val repoId = item.get("userId").asInt
            val repoUserId = item.get("owner").asJsonObject.get("userId").asInt
            val repoUrl = item.get("html_url").asString

            val userRepo = GithubRepo(repoId, repoUserId, repoName, repoUrl)
            userRepos.add(userRepo)
        }
        return userRepos
    }

}