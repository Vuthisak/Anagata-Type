package com.anagata.typingkit.repository

import com.anagata.typingkit.repository.model.Typeface
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class FirebaseRepository {

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val namespaceRef = firebaseDatabase.reference.child(namespace)

    fun getData(
        onCompleteListener: (typeface: Typeface) -> Unit,
        onFailureListener: (ex: Exception) -> Unit
    ) {
        namespaceRef
            .parent
            ?.get()
            ?.addOnSuccessListener {
                it.getValue(Typeface::class.java)?.run {
                    onCompleteListener(this)
                }
            }?.addOnFailureListener {
                onFailureListener(it)
            }
    }

    companion object {
        private const val namespace: String = "anagata-font-testing-default-rtdb"
    }

}
