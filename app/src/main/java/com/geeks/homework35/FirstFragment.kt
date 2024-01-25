package com.geeks.homework35


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geeks.homework35.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private var count = 0
    private var changeCount = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        initListener()
    }
    private fun setupViews() {
        binding.mainButton.text = "+1"
        binding.MainTextView.text = "0"
    }
    private fun initListener() {
        binding.mainButton.setOnClickListener {
            if (changeCount) {
                isItTrue()
            } else {
                isItFalse()
            }
        }
    }
    private fun сountChange(increment: Boolean) {
        if (increment && count < 9 || !increment && count > 0) {
            count = if (increment) count + 1 else count - 1
            binding.MainTextView.text = count.toString()
        } else {
            if (increment) {
                switchMode()
            } else {
                onNewFragment()
            }
        }
    }

    private fun isItTrue() {
        сountChange(increment = true)
    }

    private fun isItFalse() {
        сountChange(increment = false)
    }

    private fun switchMode() {
        changeCount = !changeCount
        binding.mainButton.text = if (changeCount) "+1" else "-1"
        count++
        binding.MainTextView.text = count.toString()
    }
    private fun onNewFragment() {
        val bundle = Bundle()
        bundle.putInt(MainActivity.key.KEY1, count)
        val secondFragment = SecondFragment()
        secondFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, secondFragment).commit()
    }
}