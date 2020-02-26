package com.d3if4201.pra_assesment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.d3if4201.pra_assesment.databinding.FragmentPersegiPanjangBinding
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*

/**
 * A simple [Fragment] subclass.
 */
class PersegiPanjangFragment : Fragment() {
    private lateinit var binding: FragmentPersegiPanjangBinding
    private var nilaiPanjang: Double = 0.00
    private var nilaiLebar: Double = 0.00
    private var nilaiLuas: Double = 0.00
    private var nilaiKeliling: Double = 0.00

    companion object {
        const val KEY_NILAILUAS = "key_nilaiLuas"
        const val KEY_NILAIKELILING = "key_nilaiKeliling"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_persegi_panjang, container, false)
        if (savedInstanceState != null) {
            nilaiLuas = savedInstanceState.getDouble(KEY_NILAILUAS)
            nilaiKeliling = savedInstanceState.getDouble(KEY_NILAIKELILING)
            updateNilai(nilaiLuas, nilaiKeliling)
        }
        binding.hitung.setOnClickListener {
            if (binding.panjang.text.toString().isEmpty() || binding.lebar.text.toString().isEmpty()) {
                Toast.makeText(this.activity, "Field tidak boleh kosong!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                nilaiPanjang = binding.panjang.text.toString().toDouble()
                nilaiLebar = binding.lebar.text.toString().toDouble()
                nilaiLuas = nilaiPanjang * nilaiLebar
                nilaiKeliling = 2 * (nilaiPanjang + nilaiLebar)
                binding.LuasPersegi.text = "Luas = $nilaiLuas"
                binding.KelilingPersegi.text = "Keliling = $nilaiKeliling"
            }
        }

        binding.share.setOnClickListener {
            val textLuasPP = LuasPersegi.text.toString()
            val textKelilingPP = KelilingPersegi.text.toString()
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, textLuasPP + "\n" + textKelilingPP)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hasil hitung persegi panjang")
            startActivity(Intent.createChooser(shareIntent, "Share text via..."))
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(KEY_NILAILUAS, nilaiLuas)
        outState.putDouble(KEY_NILAIKELILING, nilaiKeliling)
    }

    @SuppressLint("SetTextI18n")
    private fun updateNilai(s1: Double, s2: Double) {
        binding.LuasPersegi.setText("Luas =" + s1.toString())
        binding.KelilingPersegi.setText("Keliling =" + s2.toString())
    }
}
