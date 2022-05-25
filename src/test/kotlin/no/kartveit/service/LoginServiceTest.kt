package no.kartveit.service

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


internal class LoginServiceTest {

    @Test
    fun returnsTrueWhenUsernameAndPasswordIsCorrect() {
        val loginService = LoginService()
        val validUserLogin = loginService.validateUser(username = "joakim", password = "1234")
        assertTrue(validUserLogin)
    }

    @Test
    fun returnsFalseWhenUsernameIsCorrectButPasswordIsIncorrect() {
        val loginService = LoginService()
        val validUserLogin = loginService.validateUser(username = "joakim", password = "4565464")
        assertFalse(validUserLogin)
    }

    @Test
    fun returnsFalseWhenUsernameIsIncorrectButPasswordIsVorrect() {
        val loginService = LoginService()
        val validUserLogin = loginService.validateUser(username = "joachim", password = "1234")
        assertFalse(validUserLogin)
    }
}
