package uk.gov.dluhc.registercheckerapi.rest

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import uk.gov.dluhc.registercheckerapi.client.ElectoralRegistrationOfficeManagementApiException
import uk.gov.dluhc.registercheckerapi.client.IerApiException
import uk.gov.dluhc.registercheckerapi.client.IerEroNotFoundException
import uk.gov.dluhc.registercheckerapi.exception.GssCodeMismatchException

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [IerApiException::class])
    protected fun handleIerApiException(
        e: IerApiException,
        request: WebRequest
    ): ResponseEntity<Any?>? {
        return handleExceptionInternal(
            e,
            "Error getting eroId for certificate serial",
            HttpHeaders(),
            INTERNAL_SERVER_ERROR,
            request
        )
    }

    @ExceptionHandler(value = [IerEroNotFoundException::class])
    protected fun handleIerNotFoundApiException(
        e: IerApiException,
        request: WebRequest
    ): ResponseEntity<Any?>? {
        return handleExceptionInternal(e, "${e.message}", HttpHeaders(), NOT_FOUND, request)
    }

    @ExceptionHandler(value = [ElectoralRegistrationOfficeManagementApiException::class])
    protected fun handleElectoralRegistrationOfficeManagementApiException(
        e: ElectoralRegistrationOfficeManagementApiException,
        request: WebRequest
    ): ResponseEntity<Any?>? {
        return handleExceptionInternal(e, "Error retrieving GSS codes", HttpHeaders(), INTERNAL_SERVER_ERROR, request)
    }

    @ExceptionHandler(value = [GssCodeMismatchException::class])
    protected fun handleGssCodeMismatchExceptionThrowsForbidden(
        e: GssCodeMismatchException,
        request: WebRequest
    ): ResponseEntity<Any?>? {
        return handleExceptionInternal(e, e.message, HttpHeaders(), FORBIDDEN, request)
    }
}
