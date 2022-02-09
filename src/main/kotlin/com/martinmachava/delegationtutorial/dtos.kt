package com.martinmachava.delegationtutorial

// Standard DTO
class UserDto(userEntity: UserEntity) {
    val id = userEntity.id
    val email = userEntity.email
    var phone = userEntity.phone
}

fun UserEntity.toDto() = UserDto(this)

// Delegated DTO
class UserDtoDelegated(userEntity: UserEntity) {
    val id by userEntity::id
    val email by userEntity::email
    var phone by userEntity::phone
}

fun UserEntity.toDelegatedDto() = UserDtoDelegated(this)