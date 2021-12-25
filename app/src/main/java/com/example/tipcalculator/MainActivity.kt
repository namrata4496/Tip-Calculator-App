package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{calculate_tip()}
    }
    private fun calculate_tip(){
        val setInputToString=binding.serviceCost.text.toString()
        val serviceCostInput=setInputToString.toDoubleOrNull()
        if (serviceCostInput == null) {
            binding.ans.text = ""
            binding.totalAmount.text=""
            return
        }
        val percent=when(binding.options.checkedRadioButtonId){
            R.id.good->0.15
            R.id.ok->0.10
            else ->0.20
        }
        var tip=percent*serviceCostInput
        if(binding.roundUpSwitch.isChecked)
            tip= kotlin.math.ceil(tip)
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.ans.text = getString(R.string.calculated_tip, formattedTip)
        val totalBill=tip+serviceCostInput
        val formattedBill = NumberFormat.getCurrencyInstance().format(totalBill)
        binding.totalAmount.text=getString(R.string.Total_Bill,formattedBill)
    }
}