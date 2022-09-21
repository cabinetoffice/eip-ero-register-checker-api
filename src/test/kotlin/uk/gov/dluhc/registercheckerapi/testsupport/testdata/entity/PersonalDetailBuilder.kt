package uk.gov.dluhc.registercheckerapi.testsupport.testdata.entity

import uk.gov.dluhc.registercheckerapi.database.entity.Address
import uk.gov.dluhc.registercheckerapi.database.entity.PersonalDetail
import uk.gov.dluhc.registercheckerapi.testsupport.testdata.DataFaker.Companion.faker
import java.time.Instant
import java.time.LocalDate

fun buildPersonalDetail(
    firstName: String = faker.name().firstName(),
    middleNames: String? = faker.name().firstName(),
    surname: String = faker.name().lastName(),
    dateOfBirth: LocalDate? = faker.date().birthday().toLocalDateTime().toLocalDate(),
    email: String? = faker.internet().emailAddress(),
    phoneNumber: String? = faker.phoneNumber().cellPhone(),
    address: Address = buildAddress(),
    createdBy: String = "system",
    dateCreated: Instant = Instant.now(),
) = PersonalDetail(
    firstName = firstName,
    middleNames = middleNames,
    surname = surname,
    dateOfBirth = dateOfBirth,
    email = email,
    phoneNumber = phoneNumber,
    address = address,
    createdBy = createdBy,
    dateCreated = dateCreated,
)
