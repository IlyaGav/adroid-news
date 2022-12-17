package ilyagav.news.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import ilyagav.news.R

class LoginActivity : AppCompatActivity() {

    private val signInButton: Button
        get() = findViewById(R.id.signIn)

    private val loginEditText: EditText
        get() = findViewById(R.id.login)

    private val passwordEditText: EditText
        get() = findViewById(R.id.password)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.setOnClickListener {
            signIn(loginEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun signIn(login: String, password: String) {
        if (login.lowercase() == "1" && password == "1") {
            successLogin()
        } else {
            loginEditText.error = getString(R.string.invalid_login_or_password)
            passwordEditText.error = getString(R.string.invalid_login_or_password)
        }
    }

    private fun successLogin() {
        val intent = Intent(this, ListNewsActivity::class.java)
        finish()
        startActivity(intent)
    }
}