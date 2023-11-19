package com.asusoft.chatingclient.api.member

data class MemberDto(
    var id: String?,
    var name: String?,
    var pw: String?
) {
    override fun toString(): String {
        return "MemberDto(id=$id, name=$name, pw=$pw)"
    }
}