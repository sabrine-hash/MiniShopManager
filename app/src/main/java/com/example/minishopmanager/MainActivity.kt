package com.example.minishopmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Product(
    val name: String,
    val description: String,
    val price: Double,
    val imageResId: Int
)

class ProductAdapter(
    private val activity: AppCompatActivity,
    private val products: List<Product>
) : ArrayAdapter<Product>(activity, 0, products) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val row = convertView ?: LayoutInflater.from(activity)
            .inflate(R.layout.item_product, parent, false)

        val product = products[position]

        val image = row.findViewById<ImageView>(R.id.imgProduct)
        val name = row.findViewById<TextView>(R.id.txtProductName)
        val description =
            row.findViewById<TextView>(R.id.txtProductDescription)
        val price = row.findViewById<TextView>(R.id.txtProductPrice)

        image.setImageResource(product.imageResId)
        name.text = product.name
        description.text = product.description
        price.text = String.format(
            java.util.Locale.US,
            "%.2f TND",
            product.price
        )

        return row
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("LIFECYCLE", "onCreate appelé")

        // Liste des produits
        val products = listOf(
            Product(
                "Casque audio",
                "Un son clair au quotidien",
                49.99,
                R.drawable.product_headphones
            ),
            Product(
                "Parfum",
                "Une fragrance élégante",
                79.99,
                R.drawable.product_perfume
            ),
            Product(
                "Montre connectée",
                "Suivez votre activité",
                99.99,
                R.drawable.product_watch
            ),
            Product(
                "Sac à main",
                "Un style simple et élégant",
                69.99,
                R.drawable.product_bag
            )
        )

        // Affichage du catalogue
        val listView = findViewById<ListView>(R.id.listViewProducts)
        val adapter = ProductAdapter(this, products)
        listView.adapter = adapter

        // Clic sur un produit
        listView.setOnItemClickListener { _, _, position, _ ->
            val product = products[position]

            Toast.makeText(
                this,
                "Produit sélectionné : ${product.name}",
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart appelé")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "onResume appelé")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "onPause appelé")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "onStop appelé")
    }
}