package com.d3if4201.pra_assesment


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.d3if4201.pra_assesment.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.main = this
        binding.Persegi.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_persegiPanjangFragment2)
        }
        binding.Segitiga.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_segtigaFragment2)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
