package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question= savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r,g,b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)

        messageEt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                hideKeyboard()
                handleAnswer()
            }
            return@setOnEditorActionListener true
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send){
            handleAnswer()
        }
    }

    private fun isValidAnswer(text: String) : Pair<Boolean, String> {
        when (benderObj.question) {
            Bender.Question.NAME  -> {
                return if (validateText(NAME_PATTERN, text)){
                    true to ""
                } else {
                    false to "Имя должно начинаться с заглавной буквы"
                }
            }
            Bender.Question.PROFESSION -> {
                return if (validateText(PROFESSION_PATTERN, text)){
                    true to ""
                } else {
                    false to "Профессия должна начинаться со строчной буквы"
                }
            }
            Bender.Question.MATERIAL -> {
                return if (validateText(MATERIAL_PATTERN, text)){
                    true to ""
                } else {
                    false to "Материал не должен содержать цифр"
                }
            }
            Bender.Question.BDAY -> {
                return if (validateText(BDAY_PATTERN, text)){
                    true to ""
                } else {
                    false to "Год моего рождения должен содержать только цифры"
                }
            }
            Bender.Question.SERIAL -> {
                return if (validateText(SERIAL_PATTERN, text)){
                    true to ""
                } else {
                    false to "Серийный номер содержит только цифры, и их 7"
                }
            }
            Bender.Question.IDLE -> {
                return true to ""
            }
        }
    }

    private fun handleAnswer() {
        hideKeyboard()
        val result = isValidAnswer(messageEt.text.toString())
        if (result.first) {
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        } else {
            textTxt.text = result.second
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS", benderObj.status.name)
        outState.putString("QUESTION", benderObj.question.name)
    }
}
