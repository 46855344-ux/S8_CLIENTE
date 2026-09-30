package com.example.crocanti_chiken_s5.data.local

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SeedData {

    fun insertarProductos(database: AppDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            val productos = listOf(
                Producto(nombre = "Pollo Entero", categoria = "Pollo", precio = 25.90, descripcion = "Pollo entero crocante", imagenEmoji = "🍗", disponible = true, enPromocion = false),
                Producto(nombre = "1/2 Pollo", categoria = "Pollo", precio = 14.50, descripcion = "Medio pollo crocante", imagenEmoji = "🍗", disponible = true, enPromocion = false),
                Producto(nombre = "Pechuga x2", categoria = "Pollo", precio = 12.00, descripcion = "Dos pechugas crocantes", imagenEmoji = "", disponible = true, enPromocion = false),
                Producto(nombre = "Alitas x6", categoria = "Pollo", precio = 10.50, descripcion = "Seis alitas crocantes", imagenEmoji = "🍗", disponible = true, enPromocion = true),
                Producto(nombre = "Alitas x12", categoria = "Pollo", precio = 18.90, descripcion = "Doce alitas crocantes", imagenEmoji = "🍗", disponible = true, enPromocion = false),
                Producto(nombre = "Piernas x4", categoria = "Pollo", precio = 13.50, descripcion = "Cuatro piernas crocantes", imagenEmoji = "", disponible = true, enPromocion = false),
                Producto(nombre = "Nuggets x10", categoria = "Pollo", precio = 9.90, descripcion = "Diez nuggets de pollo", imagenEmoji = "🍗", disponible = true, enPromocion = false),
                Producto(nombre = "Hamburguesa Pollo", categoria = "Pollo", precio = 11.50, descripcion = "Hamburguesa de pollo", imagenEmoji = "🍗", disponible = true, enPromocion = false),
                Producto(nombre = "Combo Familiar", categoria = "Combos", precio = 35.90, descripcion = "1 pollo + 4 gaseosas", imagenEmoji = "🍱", disponible = true, enPromocion = true),
                Producto(nombre = "Combo Pareja", categoria = "Combos", precio = 22.50, descripcion = "1/2 pollo + 2 gaseosas", imagenEmoji = "🍱", disponible = true, enPromocion = false),
                Producto(nombre = "Combo Individual", categoria = "Combos", precio = 16.90, descripcion = "1/4 pollo + gaseosa + papas", imagenEmoji = "🍱", disponible = true, enPromocion = false),
                Producto(nombre = "Combo Alitas", categoria = "Combos", precio = 24.90, descripcion = "12 alitas + 2 gaseosas", imagenEmoji = "🍱", disponible = true, enPromocion = false),
                Producto(nombre = "Combo Nuggets", categoria = "Combos", precio = 15.90, descripcion = "20 nuggets + gaseosa", imagenEmoji = "", disponible = true, enPromocion = false),
                Producto(nombre = "Gaseosa 500ml", categoria = "Bebidas", precio = 3.50, descripcion = "Gaseosa personal", imagenEmoji = "🥤", disponible = true, enPromocion = false),
                Producto(nombre = "Gaseosa 1.5L", categoria = "Bebidas", precio = 7.50, descripcion = "Gaseosa familiar", imagenEmoji = "🥤", disponible = true, enPromocion = false),
                Producto(nombre = "Gaseosa 3L", categoria = "Bebidas", precio = 12.00, descripcion = "Gaseosa gigante", imagenEmoji = "🥤", disponible = true, enPromocion = false),
                Producto(nombre = "Agua Mineral", categoria = "Bebidas", precio = 2.50, descripcion = "Agua mineral 500ml", imagenEmoji = "", disponible = true, enPromocion = false),
                Producto(nombre = "Jugo Natural", categoria = "Bebidas", precio = 5.50, descripcion = "Jugo de naranja", imagenEmoji = "🧃", disponible = true, enPromocion = false),
                Producto(nombre = "Helado Vainilla", categoria = "Postres", precio = 4.00, descripcion = "Helado de vainilla", imagenEmoji = "🍦", disponible = true, enPromocion = false),
                Producto(nombre = "Helado Chocolate", categoria = "Postres", precio = 4.00, descripcion = "Helado de chocolate", imagenEmoji = "", disponible = true, enPromocion = false),
                Producto(nombre = "Brownie", categoria = "Postres", precio = 5.50, descripcion = "Brownie con chispas", imagenEmoji = "🍫", disponible = true, enPromocion = true),
                Producto(nombre = "Pie de Manzana", categoria = "Postres", precio = 6.00, descripcion = "Pie caliente de manzana", imagenEmoji = "🥧", disponible = true, enPromocion = false),
                Producto(nombre = "Papas Fritas", categoria = "Adicionales", precio = 5.50, descripcion = "Porción de papas fritas", imagenEmoji = "🍟", disponible = true, enPromocion = false),
                Producto(nombre = "Papas Grande", categoria = "Adicionales", precio = 8.50, descripcion = "Porción grande de papas", imagenEmoji = "🍟", disponible = true, enPromocion = false),
                Producto(nombre = "Ensalada", categoria = "Adicionales", precio = 6.50, descripcion = "Ensalada fresca", imagenEmoji = "🥗", disponible = true, enPromocion = false),
                Producto(nombre = "Salsa BBQ", categoria = "Adicionales", precio = 1.50, descripcion = "Salsa BBQ extra", imagenEmoji = "🫙", disponible = true, enPromocion = false),
                Producto(nombre = "Salsa Mayo", categoria = "Adicionales", precio = 1.50, descripcion = "Salsa mayonesa extra", imagenEmoji = "🫙", disponible = true, enPromocion = false),
                Producto(nombre = "Pan Adicional", categoria = "Adicionales", precio = 1.00, descripcion = "Pan extra", imagenEmoji = "🍞", disponible = true, enPromocion = false),
                Producto(nombre = "Queso Extra", categoria = "Adicionales", precio = 2.50, descripcion = "Queso adicional", imagenEmoji = "🧀", disponible = true, enPromocion = false),
                Producto(nombre = "Tocino", categoria = "Adicionales", precio = 3.50, descripcion = "Tocino crocante", imagenEmoji = "🥓", disponible = true, enPromocion = false)
            )

            productos.forEach { producto ->
                database.productoDao().insertarProducto(producto)
            }
        }
    }
}