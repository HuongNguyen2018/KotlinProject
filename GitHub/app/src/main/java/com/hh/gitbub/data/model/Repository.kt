package com.hh.gitbub.data.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/*
 {
    "id": 135781701,
    "node_id": "MDEwOlJlcG9zaXRvcnkxMzU3ODE3MDE=",
    "name": "HHMarket",
    "full_name": "HuongNguyen2018/HHMarket",
    "private": false,
    "owner": {
      "login": "HuongNguyen2018",
      "id": 39840623,
      "node_id": "MDQ6VXNlcjM5ODQwNjIz",
      "avatar_url": "https://avatars0.githubusercontent.com/u/39840623?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/HuongNguyen2018",
      "html_url": "https://github.com/HuongNguyen2018",
      "followers_url": "https://api.github.com/users/HuongNguyen2018/followers",
      "following_url": "https://api.github.com/users/HuongNguyen2018/following{/other_user}",
      "gists_url": "https://api.github.com/users/HuongNguyen2018/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/HuongNguyen2018/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/HuongNguyen2018/subscriptions",
      "organizations_url": "https://api.github.com/users/HuongNguyen2018/orgs",
      "repos_url": "https://api.github.com/users/HuongNguyen2018/repos",
      "events_url": "https://api.github.com/users/HuongNguyen2018/events{/privacy}",
      "received_events_url": "https://api.github.com/users/HuongNguyen2018/received_events",
      "type": "User",
      "site_admin": false
    },
    "html_url": "https://github.com/HuongNguyen2018/HHMarket",
    "description": null,
    "fork": false,
    "url": "https://api.github.com/repos/HuongNguyen2018/HHMarket",
    "forks_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/forks",
    "keys_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/keys{/key_id}",
    "collaborators_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/collaborators{/collaborator}",
    "teams_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/teams",
    "hooks_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/hooks",
    "issue_events_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/issues/events{/number}",
    "events_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/events",
    "assignees_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/assignees{/user}",
    "branches_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/branches{/branch}",
    "tags_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/tags",
    "blobs_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/git/blobs{/sha}",
    "git_tags_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/git/tags{/sha}",
    "git_refs_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/git/refs{/sha}",
    "trees_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/git/trees{/sha}",
    "statuses_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/statuses/{sha}",
    "languages_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/languages",
    "stargazers_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/stargazers",
    "contributors_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/contributors",
    "subscribers_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/subscribers",
    "subscription_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/subscription",
    "commits_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/commits{/sha}",
    "git_commits_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/git/commits{/sha}",
    "comments_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/comments{/number}",
    "issue_comment_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/issues/comments{/number}",
    "contents_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/contents/{+path}",
    "compare_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/compare/{base}...{head}",
    "merges_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/merges",
    "archive_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/{archive_format}{/ref}",
    "downloads_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/downloads",
    "issues_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/issues{/number}",
    "pulls_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/pulls{/number}",
    "milestones_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/milestones{/number}",
    "notifications_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/notifications{?since,all,participating}",
    "labels_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/labels{/name}",
    "releases_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/releases{/id}",
    "deployments_url": "https://api.github.com/repos/HuongNguyen2018/HHMarket/deployments",
    "created_at": "2018-06-02T02:47:46Z",
    "updated_at": "2019-06-12T03:15:50Z",
    "pushed_at": "2019-06-12T03:15:48Z",
    "git_url": "git://github.com/HuongNguyen2018/HHMarket.git",
    "ssh_url": "git@github.com:HuongNguyen2018/HHMarket.git",
    "clone_url": "https://github.com/HuongNguyen2018/HHMarket.git",
    "svn_url": "https://github.com/HuongNguyen2018/HHMarket",
    "homepage": null,
    "size": 5611,
    "stargazers_count": 0,
    "watchers_count": 0,
    "language": "Jupyter Notebook",
    "has_issues": true,
    "has_projects": true,
    "has_downloads": true,
    "has_wiki": true,
    "has_pages": false,
    "forks_count": 0,
    "mirror_url": null,
    "archived": false,
    "disabled": false,
    "open_issues_count": 0,
    "license": null,
    "forks": 0,
    "open_issues": 0,
    "watchers": 0,
    "default_branch": "master"
  },
 */
data class Repository(
    val name: String,
    val language: String,
    @SerializedName("updated_at")
    val updatedDate: String,
    val description: String
) {

    //"2019-06-12T03:15:50Z",

    var updateDateString : String
    get() = getDateString()
    set(value) {updateDateString = getDateString()}

    fun getDateString(): String {
        var formattedDate: String = ""
        updatedDate.isNullOrBlank().let {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
            val date: Date = inputFormat.parse(updatedDate)
            formattedDate = outputFormat.format(date)
        }
        return formattedDate
    }

}