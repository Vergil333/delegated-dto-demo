package com.martinmachava.delegationtutorial.unit

import com.martinmachava.delegationtutorial.UserEntity
import com.martinmachava.delegationtutorial.toDelegatedDto
import com.martinmachava.delegationtutorial.toDto
import org.junit.jupiter.api.Test

class MediumDemoTest {

    @Test
    fun `standard vs delegated DTO difference demonstration`() {
        val entity = UserEntity(
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            phone = "a",
            address = "address",
        )

        val standardDto = entity.toDto()
        val delegatedDto = entity.toDelegatedDto()

        assert(delegatedDto.phone == entity.phone)// "a" == "a"
        assert(standardDto.phone == entity.phone)// "a" == "a"

        standardDto.phone = "b"

        assert(standardDto.phone != entity.phone)// "b" != "a"

        delegatedDto.phone = "c"

        assert(delegatedDto.phone == entity.phone)// "c" == "c"
        assert(standardDto.phone != entity.phone)// "b" != "c"

        entity.phone = "d"

        assert(delegatedDto.phone == entity.phone)// "d" == "d"
        assert(standardDto.phone != entity.phone)// "b" != "d"
    }
}