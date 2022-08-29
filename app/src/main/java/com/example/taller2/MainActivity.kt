package com.example.taller2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    private var resultado = 0
    private var intentos = 0
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultado = (1..1000).random()
        initViews()

    }


    private fun initViews(){
        val buttonPlayAgain: Button = findViewById(R.id.PlayAgain)
        val indicador: TextView = findViewById(R.id.Indicador)
        val inte: TextView = findViewById(R.id.Intentos)
        val entrada: EditText = findViewById(R.id.imputText)
        val bicho: ImageView = findViewById(R.id.Bicho)

        entrada.addTextChangedListener {
            handler.removeCallbacksAndMessages(null)
            handler.postDelayed({
                if (it?.toString().orEmpty().isNotEmpty()){
                    when{
                        (it.toString().toInt() < resultado) -> {
                            intentos++
                            inte.text = intentos.toString()
                            indicador.text = "Es Mayor"
                        }
                        (it.toString().toInt() > resultado) -> {
                            intentos++
                            inte.text = intentos.toString()
                            indicador.text = "Es Menor"
                        }
                        else -> {
                            indicador.text = "Ganaste!!"
                            buttonPlayAgain.visibility = View.VISIBLE
                            bicho.visibility = View.VISIBLE
                        }
                    }
                }
            },900)
        }

        buttonPlayAgain.setOnClickListener {
            resultado = (1..1000).random()
            buttonPlayAgain.visibility = View.GONE
            bicho.visibility = View.GONE
            indicador.text = "0"
            intentos = 0
            inte.text = Editable.Factory.getInstance().newEditable("")



        }


    }

}