package no.kartveit.service

class LoginService {
    fun validateUser(userid: String, password: String): Boolean {
        return userid.equals("joakim", ignoreCase = true) && password.equals("1234", ignoreCase = true)
    }
}