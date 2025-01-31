package com.example.homerentalapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.homerentalapp.databinding.FragmentSettingBinding
import com.example.homerentalapp.databinding.FragmentThemeFragmentBinding
import java.util.zip.Inflater


class theme_fragment : Fragment(R.layout.fragment_theme_fragment) {
    private var _binding: FragmentThemeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private var value = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThemeFragmentBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("MyFile", Context.MODE_PRIVATE)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedThemeMode =
            sharedPreferences.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(savedThemeMode)
        binding.setting.setOnClickListener {
            val url = "http://www.example.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        binding.SettingView2.setOnClickListener {
            val shareIntend = Intent(Intent.ACTION_SEND)
            shareIntend.type = "text/plain"
            shareIntend.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this cool app: https://play.google.com/store/apps/details?id=com.example.app"
            )
            startActivity(Intent.createChooser(shareIntend, "share it"))
        }
        binding.SettingView3.setOnClickListener {
            val appPackageName = "com.example.homerentalapp"
            val uri = Uri.parse("market://details?id=$appPackageName")
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)

// Add a parameter to the intent to direct the user to the review section of your app's page.
            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
            goToMarket.data = Uri.parse("market://details?id=$appPackageName&reviewId=0")

            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                // If Google Play Store app is not installed, open the app's page in the web browser.
                val webUri =
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName&reviewId=0")
                val webIntent = Intent(Intent.ACTION_VIEW, webUri)
                startActivity(webIntent)
            }
            binding.SettingView4.setOnClickListener {
                val appPackageName = "com.example.homerentalapp"
                val uri = Uri.parse("market://details?id=$appPackageName")
                val goToMarket = Intent(Intent.ACTION_VIEW, uri)

// Add a parameter to the intent to direct the user to the review section of your app's page.
                goToMarket.addFlags(
                    Intent.FLAG_ACTIVITY_NO_HISTORY or
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                )
                goToMarket.data = Uri.parse("market://details?id=$appPackageName&reviewId=0")

                try {
                    startActivity(goToMarket)
                } catch (e: ActivityNotFoundException) {
                    // If Google Play Store app is not installed, open the app's page in the web browser.
                    val webUri =
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName&reviewId=0")
                    val webIntent = Intent(Intent.ACTION_VIEW, webUri)
                    startActivity(webIntent)
                }
//        binding.languageCV.setOnClickListener {
//
//        }

//        binding.themeCV.setOnClickListener {
//            if (value == 0) {
//                binding.themeRadioGroup.visibility = View.VISIBLE
//                binding.arrowUp.visibility = View.VISIBLE
//                binding.arrowDown.visibility = View.GONE
//                value = 1
//            } else {
//                binding.themeRadioGroup.visibility = View.GONE
//                binding.arrowUp.visibility = View.GONE
//                binding.arrowDown.visibility = View.VISIBLE
//                value = 0
//            }
//        }
//        binding.lightRadio.setOnClickListener {
//            saveThemeMode(AppCompatDelegate.MODE_NIGHT_NO)
//        }
//
//        binding.darkRadio.setOnClickListener {
//            saveThemeMode(AppCompatDelegate.MODE_NIGHT_YES)
//        }

            }


        }
    }
}