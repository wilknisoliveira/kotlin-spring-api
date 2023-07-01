package com.springkotlin.services

import com.springkotlin.exceptions.ResourceNotFoundException
import com.springkotlin.model.Person
import com.springkotlin.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {
    @Autowired
    private lateinit var repository: PersonRepository
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<Person>{
        logger.info("Getting all")
        return repository.findAll()
    }
    fun findById(id: Long): Person{
        logger.info("Finding one person!")
        return repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found for this ID!")}
    }

    fun create(person: Person): Person {
        logger.info("Creating a person with the id ${person.id}.")
        return repository.save(person)
    }

    fun update(person: Person): Person{
        logger.info("Updating a person with the id ${person.id}.")
        val entity = repository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records found for this ID!")}

        person.firstName = person.firstName
        person.lastName = person.lastName
        person.address = person.address
        person.gender = person.gender
        return repository.save(entity)
    }

    fun delete(id: Long){
        logger.info("Deleting a person with the id ${id}.")

        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!")}
        repository.delete(entity)
    }
}