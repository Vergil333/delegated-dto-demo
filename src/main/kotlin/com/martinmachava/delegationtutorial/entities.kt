package com.martinmachava.delegationtutorial

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity(
    @Id val id: UUID = UUID.randomUUID(),
    @Column(nullable = false) val firstName: String,
    @Column(nullable = false) val lastName: String,
    @Column(nullable = false) val email: String,
    @Column(nullable = false) var phone: String,
    @Column(nullable = false) var address: String,
)

@Repository
interface UserRepository : CrudRepository<UserEntity, UUID>