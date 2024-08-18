package com.practice.calculator.fragment

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.Editable
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import com.practice.calculator.MainActivity
import com.practice.calculator.R
import com.practice.calculator.base.BaseFragment
import com.practice.calculator.databinding.FragmentHomeBinding
import com.practice.calculator.errorLogs
import com.practice.calculator.utils.debugLogs
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import kotlin.math.truncate

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(
        R.layout.fragment_home
    ) {

    companion object {

        fun getInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }

    }

    private var afterResult = false

    override fun initUI() {
//        initThemeChangeImageButton()
        initInputEditText()
        initOutputTextView()
    }

    override fun initOnClickListeners() {
        try {
            binding.btAllClearButton.setOnClickListener {
                initViewStyle(
                    0,
                    R.color.primary_text,
                    (context as MainActivity).getActualScaledPixels(R.dimen.text_size_large),
                    "",
                    fontStyle = Typeface.DEFAULT_BOLD
                )
                initViewStyle(
                    1,
                    R.color.secondary_text,
                    (context as MainActivity).getActualScaledPixels(R.dimen.text_size_normal),
                    ""
                )
                //            initInputEditText()
                //            initOutputTextView()
            }

            binding.btPercentageButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.percentage)
//            )
//            checkAddOperator()
//            setInputOutputFields(
//                outputText = getOutputResult()
//            )
                appendEditText(
                    "%",
                    true
                )
            }

            binding.btDecimalPointButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.percentage)
//            )
//            checkAddOperator()
//            setInputOutputFields(
//                outputText = getOutputResult()
//            )
                appendEditText(
                    "."
                )
            }

            binding.ibBackSpaceImageButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                operator = 0
//            )
                if (binding.etInputEditText.text.toString().isEmpty()) {
                    binding.btAllClearButton.performClick()
                } else {
                    val currentEquation = binding.etInputEditText.text.toString()
                    val lastIndex = currentEquation.lastIndex
                    val newEquation = currentEquation.substring(0, lastIndex)
                    setInputOutputFields(newEquation)
                }
            }

            binding.btDivisionButton.setOnClickListener {
                if (binding.etInputEditText.text.toString().isNotEmpty()) {
//                setEditTextAtCursorPosition(
//                    binding.etInputEditText,
//                    "/"
//                )
//                checkAddOperator()
//                setInputOutputFields(
//                    outputText = getOutputResult()
//                )
                    appendEditText(
                        "/",
                        true
                    )
                }
            }

            binding.btMultiplicationButton.setOnClickListener {
                if (binding.etInputEditText.text.toString().isNotEmpty()) {
//                setEditTextAtCursorPosition(
//                    binding.etInputEditText,
//                    "*"
//                )
//                checkAddOperator()
//                setInputOutputFields(
//                    outputText = getOutputResult()
//                )
                    appendEditText(
                        "*",
                        true
                    )
                }
            }

            binding.btSubtractionButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.subtraction)
//            )
//            checkAddOperator()
//            setInputOutputFields(
//                outputText = getOutputResult()
//            )
                appendEditText(
                    "-",
                    true
                )
            }

            binding.btAdditionButton.setOnClickListener {
                if (binding.etInputEditText.text.toString().isNotEmpty()) {
//                setEditTextAtCursorPosition(
//                    binding.etInputEditText,
//                    getString(R.string.addition)
//                )
//                checkAddOperator()
//                setInputOutputFields(
//                    outputText = getOutputResult()
//                )
                    appendEditText(
                        getString(R.string.addition),
                        true
                    )
                }
            }

            binding.btDoubleZeroButton.setOnClickListener {
                val equation = binding.etInputEditText.text.toString()
                if (equation.isNotEmpty() && !(equation.length == 1 && equation[0] == '0')) {
//                setEditTextAtCursorPosition(
//                    binding.etInputEditText,
//                    getString(R.string.double_zero)
//                )
                    appendEditText(
                        getString(R.string.double_zero)
                    )
                } else {
                    binding.btZeroButton.performClick()
                }
            }

            binding.btZeroButton.setOnClickListener {
                if (binding.etInputEditText.text.toString() != "0") {
//                setEditTextAtCursorPosition(
//                    binding.etInputEditText,
//                    getString(R.string.one)
//                )
                    appendEditText(
                        getString(R.string.zero)
                    )
                }
            }

            binding.btOneButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.one)
//            )
                appendEditText(
                    getString(R.string.one)
                )
            }

            binding.btTwoButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.two)
//            )
                appendEditText(
                    getString(R.string.two)
                )
            }


            binding.btThreeButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.three)
//            )
                appendEditText(
                    getString(R.string.three)
                )
            }

            binding.btFourButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.four)
//            )
                appendEditText(
                    getString(R.string.four)
                )
            }

            binding.btFiveButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.five)
//            )
                appendEditText(
                    getString(R.string.five)
                )
            }

            binding.btSixButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.six)
//            )
                appendEditText(
                    getString(R.string.six)
                )
            }

            binding.btSevenButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.seven)
//            )
                appendEditText(
                    getString(R.string.seven)
                )
            }

            binding.btEightButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.eight)
//            )
                appendEditText(
                    getString(R.string.eight)
                )
            }

            binding.btNineButton.setOnClickListener {
//            setEditTextAtCursorPosition(
//                binding.etInputEditText,
//                getString(R.string.nine)
//            )
                appendEditText(
                    getString(R.string.nine)
                )
            }

            binding.btEqualsButton.setOnClickListener {
                appendEditText(visualChange = true)
            }
        } catch (e: Exception) {
            e.errorLogs()
        }
    }

//    @SuppressLint("ResourceType")
//    private fun initThemeChangeImageButton(){
//        try {
//            binding.ibThemeChangeImageButton.apply {
//                setImageResource(R.drawable.sun)
//                setBackgroundColor(resources.getInteger((R.color.background_color)))
//            }
//        }catch (e: Exception){
//            e.errorLogs()
//        }
//    }

    private fun initInputEditText() {
        try {
//            disableSoftInputFromAppearing(binding.etInputEditText)
            //TODO: try wht these three things do??
            binding.etInputEditText.movementMethod = ScrollingMovementMethod()
            binding.etInputEditText.setText(0)
            initViewStyle(
                0,
                colorId = R.color.primary_text,
                fontSizeResourceId = (context as MainActivity).getActualScaledPixels(R.dimen.text_size_large),
                fontStyle = Typeface.DEFAULT_BOLD
            )
            initEditTextAutoSize()
        } catch (e: Exception) {
            e.errorLogs()
        }
    }

    private fun initViewStyle(
        viewType: Int,
        colorId: Int,
        fontSizeResourceId: Float,
        content: String? = "",
        fontStyle: Typeface? = Typeface.DEFAULT
    ) {
        try {
            val color = resources.getInteger(colorId)
            when (viewType) {
                0 -> {
                    //EditText
                    binding.etInputEditText.apply {
                        setText(content)
                        setTextColor(color)
                        textSize = fontSizeResourceId
                        typeface = fontStyle
                    }
                }

                1 -> {
                    //TextView
                    binding.tvOutputTextView.apply {
                        text = content
                        setTextColor(color)
                        textSize = fontSizeResourceId
                        typeface = fontStyle
                    }
                }
            }
        }catch (e: Exception){
            e.errorLogs()
        }
    }

    private fun initEditTextAutoSize() {
        try {
            val maxTextScale = resources.getDimension(R.dimen.text_size_large)
            val minTextScale =
                resources.getDimension(R.dimen.text_size_normal)//0.2 * maxTextScale // ensure text doesn't get too small.
            binding.etInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val paint = TextPaint(binding.etInputEditText.paint)
                    val desiredTextWidth = StaticLayout.getDesiredWidth(s, paint)

                    // added so that the text isn't slightly too big
                    val ensureWiggleRoom = 0.95F
                    val scaleFactor = binding.etInputEditText.width / desiredTextWidth
                    val candidateTextSize =
                        truncate(binding.etInputEditText.textSize * scaleFactor * ensureWiggleRoom)
                    if (candidateTextSize > minTextScale && candidateTextSize < maxTextScale) {
                        binding.etInputEditText.setTextSize(
                            TypedValue.COMPLEX_UNIT_PX,
                            candidateTextSize
                        )
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }catch (e: Exception){
            e.errorLogs()
        }
    }

    private fun initOutputTextView() {
        initViewStyle(
            1,
            R.color.secondary_text,
            (context as MainActivity).getActualScaledPixels(R.dimen.text_size_normal)
        )
    }

    private fun setInputText(inputText: String? = null) {
        if (inputText != null) {
            binding.etInputEditText.setText(inputText)
        }
    }

    private fun setOutputText() {
        binding.tvOutputTextView.text = getOutputResult()
    }

    private fun setInputOutputFields(inputText: String? = null) {
        setInputText(inputText)
        setOutputText()
    }

    private fun getOutputResult(): String {
        val equation = binding.etInputEditText.text.toString()
        try {
            val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
            val resRaw = engine.eval(equation)
            val res = resRaw.toString()
            return if (res.endsWith(".0")) {
                res.replace(".0", "")
            } else {
                res
            }
        } catch (e: Exception) {
            e.errorLogs()
        }
        return equation
    }

    private fun checkAddOperator(): Boolean {
        val currentEquation = binding.etInputEditText.text.toString()
        return !(currentEquation.endsWith("%") || currentEquation.endsWith("/") || currentEquation.endsWith(
            "*"
        )
                || currentEquation.endsWith("+") || currentEquation.endsWith("-")
                || currentEquation.endsWith("."))
    }

    private fun appendEditText(
        appendValue: String? = "",
        operator: Boolean? = false,
        visualChange: Boolean? = false
    ) {
        try {
            var currentEquation = if (afterResult && operator!!) {
                binding.tvOutputTextView.text.toString()
            } else {
                if (!afterResult) {
                    binding.etInputEditText.text.toString()
                } else {
                    ""
                }
            }
            if (checkAddOperator() || !operator!!) {
                currentEquation += appendValue
            }
            if (visualChange!!) {
                initViewStyle(
                    0,
                    R.color.secondary_text,
                    (context as MainActivity).getActualScaledPixels(R.dimen.text_size_normal),
                    content = currentEquation,
                    fontStyle = Typeface.DEFAULT
                )
                initViewStyle(
                    1,
                    R.color.primary_text,
                    (context as MainActivity).getActualScaledPixels(R.dimen.text_size_normal),
                    content = getOutputResult(),
                    fontStyle = Typeface.DEFAULT_BOLD
                )
                afterResult = true
            } else {
                binding.btAllClearButton.performClick()
                setInputOutputFields(
                    currentEquation
                )
                afterResult = false
            }
            debugLogs(binding.etInputEditText.text.toString() + " th ans " + getOutputResult())
        } catch (e: Exception) {
            e.errorLogs()
        }
    }


}
