package com.asusoft.chatingclient.api.member

import com.google.gson.annotations.SerializedName

data class MemberDto(
    var key: Long,
    var name: String?,
    var id: String?,
    var pw: String?
) {
    override fun toString(): String {
        return "MemberDto(key=$key, name=$name, id=$id, pw=$pw)"
    }
}