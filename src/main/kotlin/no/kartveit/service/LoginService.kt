package no.kartveit.service

class LoginService {
    fun validateUser(userName: String, password: String): Boolean {
        // TODO change to call a database with to validate username and password is correct
        return userName.equals("joakim", ignoreCase = true) && password.equals("1234", ignoreCase = false)
    }
}