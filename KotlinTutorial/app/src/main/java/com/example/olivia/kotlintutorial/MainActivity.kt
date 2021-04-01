package com.example.olivia.kotlintutorial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.olivia.kotlintutorial.R.string.count
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val response = try {
            URL("https://jsonplaceholder.typicode.com/posts")
                    .openStream()
                    .bufferedReader()
                    .use {
                        it.lines().forEach { line ->
                            println(line)
                        }
                    }
        }
        catch (e: Exception) {
            // handler
        }
        finally {
            // optional finally block
        }

    }

    fun toastMe(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        println( "*** DEBUG *** --> Clicked Toast!")

        Thread({
            val response = try {
                URL("https://jsonplaceholder.typicode.com/posts")
                        .openStream()
                        .bufferedReader()
                        .use {
                            it.lines().forEach { line ->
                                println("*** DEBUG *** " + line)
                            }
                        }
            }
            catch (e: Exception) {
                println("*** DEBUG *** " + e )
            }
        }).start()

        myToast.show()
    }

    fun countMe(view: View){
        //Get the text view
        val showCountTextView  = findViewById(R.id.textView) as TextView

        //Get the value of the text view
        val countString = showCountTextView.text.toString()

        //Convert value to a number and increment it
        var count: Int = Integer.parseInt(countString)
        count++

        Thread({
            val response = try {
                URL("https://jsonplaceholder.typicode.com/posts")
                        .openStream()
                        .bufferedReader()
                        .use {
                            it.lines().forEach { line ->
                                println("*** DEBUG *** " + line)
                            }
                        }
            }
            catch (e: Exception) {
                println("*** DEBUG *** " + e )
            }
        }).start()

        //Display the new value in the text view
        showCountTextView.text = count.toString()

    }

    fun randomMe(view: View){
        //Create an Intent to start the second activity
        val randomIntent = Intent(this, SecondActivity::class.java)

        //Get the current value of the text view
        val countString = textView.text.toString()

        //Convert the count to an int
        val count = Integer.parseInt(countString)

        //Add the count to the extras for the Intent
        randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)

        Thread({
            val response = try {
                URL("https://jsonplaceholder.typicode.com/posts")
                        .openStream()
                        .bufferedReader()
                        .use {
                            it.lines().forEach { line ->
                                println("*** DEBUG *** " + line)
                            }
                        }
            }
            catch (e: Exception) {
                println("*** DEBUG *** " + e )
            }
        }).start()

        //Start the new activity
        startActivity(randomIntent)
    }


}
