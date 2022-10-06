package uk.gov.dluhc.registercheckerapi.messaging.models

import java.util.UUID

data class InitiateRegisterCheckMessage(
    val sourceType: RegisterCheckSourceType,
    // the VoterCardApplication.applicationId to allow the response from rca to be associated with the correct application
    val sourceReference: String,
    // the VoterCardApplicationRegisterStatus.id to allow the response from rca to be associated with the correct register status
    val sourceCorrelationId: UUID,
    // the user that requested the check or "system"
    val requestedBy: String,
    val gssCode: String,
    val personalDetail: RegisterCheckPersonalDetail,
)
