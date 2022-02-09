package com.martinmachava.delegationtutorial.integration

import com.martinmachava.delegationtutorial.DelegationTutorialApplicationTests
import com.martinmachava.delegationtutorial.UserEntity
import com.martinmachava.delegationtutorial.UserRepository
import com.martinmachava.delegationtutorial.toDelegatedDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import java.util.*

internal class DelegatedDtoIntegrationTest(
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
        entity.toDelegatedDto()
    }

    @Test
    @Transactional
    fun `dto's property change is delegated to entity`() {
        val entity = userRepository.findById(savedUserId).get()
        val dto = entity.toDelegatedDto()
        dto.phone = "newPhone"

        val loadAgain = userRepository.findById(savedUserId).get()//just to be sure it a new instance

        assert(dto.phone == loadAgain.phone)
    }
}