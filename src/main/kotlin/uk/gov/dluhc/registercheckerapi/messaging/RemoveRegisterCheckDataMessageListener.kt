package uk.gov.dluhc.registercheckerapi.messaging

import io.awspring.cloud.messaging.listener.annotation.SqsListener
import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import uk.gov.dluhc.registercheckerapi.messaging.mapper.RegisterCheckRemovalMapper
import uk.gov.dluhc.registercheckerapi.messaging.models.RemoveRegisterCheckDataMessage
import uk.gov.dluhc.registercheckerapi.service.RegisterCheckRemovalService
import javax.validation.Valid

private val logger = KotlinLogging.logger { }

/**
 * Implementation of [MessageListener] to handle [RemoveRegisterCheckDataMessage] messages
 */
@Component
class RemoveRegisterCheckDataMessageListener(
    private val registerCheckRemovalService: RegisterCheckRemovalService,
    private val mapper: RegisterCheckRemovalMapper
) : MessageListener<RemoveRegisterCheckDataMessage> {

    @SqsListener("\${sqs.remove-applicant-register-check-data-queue-name}")
    override fun handleMessage(@Valid @Payload payload: RemoveRegisterCheckDataMessage) {
        with(payload) {
            logger.info {
                "RemoveRegisterCheckDataMessage received with " +
                    "sourceType: $sourceType and " +
                    "sourceReference: $sourceReference and " +
                    "gssCode: $gssCode"
            }
            registerCheckRemovalService.remove(mapper.toRemovalDto(this))
        }
    }
}
