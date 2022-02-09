package com.martinmachava.delegationtutorial.unit

import com.martinmachava.delegationtutorial.UserEntity
import com.martinmachava.delegationtutorial.toDelegatedDto
import com.martinmachava.delegationtutorial.toDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DelegatedDtoTest {

    lateinit var userEntity: UserEntity

    @BeforeEach
    fun createTestingUser() {
        userEntity = UserEntity(
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            phone = "phone",
            address = "address",
        )
    }

    @Test
    fun `convert entity into DTO`() {
        userEntity.toDto()
    }

    @Test
    fun `dto's property change is delegated to entity`() {
        val userDto = userEntity.toDelegatedDto()
        userDto.phone = "newPhone"

        assert(userDto.phone == userEntity.phone)
    }
}