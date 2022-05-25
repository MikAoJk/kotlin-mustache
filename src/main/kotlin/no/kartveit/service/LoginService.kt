package no.kartveit.service

class LoginService {
    fun validateUser(username: String, password: String): Boolean {
        // TODO change to call a database with to validate username and password is correct
        return username.equals("joakim", ignoreCase = true) && password.equals("1234", ignoreCase = false)
    }
}
