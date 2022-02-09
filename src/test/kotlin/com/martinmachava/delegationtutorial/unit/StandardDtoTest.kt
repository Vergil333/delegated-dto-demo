package com.martinmachava.delegationtutorial.unit

import com.martinmachava.delegationtutorial.UserEntity
import com.martinmachava.delegationtutorial.toDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StandardDtoTest {

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
    fun `changed dto's property is not equal to entity's property`() {
        val userDto = userEntity.toDto()
        userDto.phone = "newPhone"

        assert(userDto.phone != userEntity.phone)
    }
}