package com.martinmachava.delegationtutorial.integration

import com.martinmachava.delegationtutorial.DelegationTutorialApplicationTests
import com.martinmachava.delegationtutorial.UserEntity
import com.martinmachava.delegationtutorial.UserRepository
import com.martinmachava.delegationtutorial.toDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import java.util.*

internal class StandardDtoIntegrationTest(
    @Autowired val userRepository: UserRepository,
) : DelegationTutorialApplicationTests() {

    lateinit var savedUserId: UUID

    @BeforeEach
    fun addUserToDatabase() {

        val userEntity = UserEntity(
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            phone = "phone",
            address = "address",
        )

        savedUserId = userRepository.save(userEntity).id
    }

    @AfterEach
    fun cleanDB() {
        userRepository.deleteAll()
    }

    @Test
    fun `convert entity into DTO`() {
        val entity = userRepository.findById(savedUserId).get()
        entity.toDto()
    }

    @Test
    @Transactional
    fun `changed dto's property is not equal to entity's property`() {
        val entity = userRepository.findById(savedUserId).get()
        val dto = entity.toDto()
        dto.phone = "newPhone"

        assert(dto.phone != entity.phone)
    }
}