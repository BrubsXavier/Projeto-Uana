package com.example.uana

import android.content.Context
import com.example.uana.model.ItemDeCarrinho
import io.paperdb.Paper

class ShoppingCart {

    companion object {

        fun addItem(itemDeCarrinho: ItemDeCarrinho) {
            val cart = ShoppingCart.getCart()

            val targetItem = cart?.singleOrNull { it.produto.id == itemDeCarrinho.produto.id }
            if (targetItem == null) {
                itemDeCarrinho.quantidade++
                cart?.add(itemDeCarrinho)
            } else {
                targetItem.quantidade++
            }
            if (cart != null) {
                ShoppingCart.saveCart(cart)
            }
        }

        fun getCart(): MutableList<ItemDeCarrinho>? {
            return Paper.book().read("cart", mutableListOf())
        }

        fun removeItem(itemDeCarrinho: ItemDeCarrinho, context: Context) {
            val cart = ShoppingCart.getCart()

            val targetItem = cart?.singleOrNull { it.produto.id == itemDeCarrinho.produto.id }
            if (targetItem != null) {
                if (targetItem.quantidade > 0) {
                    targetItem.quantidade--
                } else {
                    cart?.remove(targetItem)
                }
            }
            if (cart != null) {
                ShoppingCart.saveCart(cart)
            }
        }

        fun saveCart(cart: MutableList<ItemDeCarrinho>) {
            Paper.book().write("cart", cart)
        }

        fun getShoppingCartSize(): Int {
            var cartSize = 0
            ShoppingCart.getCart()?.forEach {
                cartSize += it.quantidade;
            }

            return cartSize
        }
    }
}