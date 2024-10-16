package rs.raf.rma.catalist.login

interface LogInContract {

    data class LoginState(
        val firstName : String = "",
        val lastName: String = "",
        val nickname: String = "",
        val email: String = ""
    )


    sealed class LoginUIEvents{

    }
}