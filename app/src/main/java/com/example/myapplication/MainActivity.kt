package com.example.myapplication

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var imageViewDog: ImageView
    private var health = 100
    private var hunger = 0
    private var cleanliness = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        imageViewDog = findViewById(R.id.imageViewDog)

        val buttonFeed = findViewById<Button>(R.id.buttonFeed)
        val buttonPlay = findViewById<Button>(R.id.buttonPlay)
        val buttonClean = findViewById<Button>(R.id.buttonClean)

        val eatAnimation = AnimationUtils.loadAnimation(this, R.anim.dog_eat)
        val playAnimation = AnimationUtils.loadAnimation(this, R.anim.dog_play)
        val cleanAnimation = AnimationUtils.loadAnimation(this, R.anim.dog_clean)

        updateUI()

        buttonFeed.setOnClickListener {
            imageViewDog.startAnimation(eatAnimation)
            feedPet()
            updateUI()
        }

        buttonPlay.setOnClickListener {
            imageViewDog.startAnimation(playAnimation)
            playWithPet()
            updateUI()
        }

        buttonClean.setOnClickListener {
            imageViewDog.startAnimation(cleanAnimation)
            cleanPet()
            updateUI()
        }
    }

    private fun feedPet() {
        hunger -= 10
        if (hunger < 0) hunger = 0
        if (cleanliness < 100) cleanliness += 5
    }

    private fun playWithPet() {
        health += 10
        if (health > 100) health = 100
        hunger += 10
        if (hunger > 100) hunger = 100
        cleanliness -= 5
        if (cleanliness < 0) cleanliness = 0
    }

    private fun cleanPet() {
        cleanliness = 100
    }

    private fun updateUI() {
        val textViewHealth = findViewById<TextView>(R.id.textViewHealth)
        val textViewHunger = findViewById<TextView>(R.id.textViewHunger)
        val textViewCleanliness = findViewById<TextView>(R.id.textViewCleanliness)

        textViewHealth.text = "Health: $health"
        textViewHunger.text = "Hunger: $hunger"
        textViewCleanliness.text = "Cleanliness: $cleanliness"
        }
    }
