package io.jadu.m3App

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.uuid.Uuid

class TodoStore {

    val itemsOld: StateFlow<List<String>>
        field = MutableStateFlow<List<String>>(emptyList())


    val items : List<String>
        field = mutableListOf<String>()

    fun add(item: String) {
        items.add(item) // works inside the class because field is mutable
    }
}

class TodoFetch(){

    val a = TodoStore()

    fun add() {
        val itemStateFlow = a.itemsOld.value
        val items = a.items
    }
}








class Logger { fun log(msg: String) = println("LOG: $msg") }
class Analytics { fun track(event: String) = println("TRACK: $event") }

context(logger: Logger, analytics: Analytics)
fun checkout(amount: Int) {
    logger.log("Checkout started for $amount")
    analytics.track("checkout_$amount")
}

//fun main() {
//    with(Logger()) {
//        with(Analytics()) {
//            checkout(500)
//        }
//    }
//
//    val id = Uuid.random()
//    println(id) // some random uuid
//
//    val parsed = Uuid.parse("550e8400-e29b-41d4-a716-446655440000")
//    val parsedSafe = Uuid.parseOrNull("not-a-uuid") // returns null
//}



data class Transaction(
    val amount: Int,
    val timestamp: Long
)

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val txns = listOf(
        Transaction(amount = 500, timestamp = 3000L),
        Transaction(amount = 200, timestamp = 1000L),
        Transaction(amount = 900, timestamp = 2000L)
    )

    val sortedTxns = if (txns.isSortedByDescending { it.timestamp }) {
        txns
    } else {
        txns.sortedByDescending { it.timestamp }
    }

    println(sortedTxns)



    /* ********* */

    val profileImages = mutableMapOf<String, String?>(
        "rohit" to null
    )


    /*
    * "rohit" exists but image URL is null
    * That may mean:
    * Rohit has no profile image
    * So you may want to keep null
    * then
    * This keeps: rohit = null
    * */

    profileImages.getOrPutIfMissing("rohit") {
        "default-image.png"
    }

    /*
    * But if you want every null image to become default image, use:
    * rohit = default-image.png
    * */

    profileImages.getOrPutIfNull("rohit") {
        "default-image.png"
    }

    suspend fun fetchUser(id: String): User { delay(1000); return User(id) }
    fun userUpdates(): Flow<User> = flowOf(User("1"), User("2"))




}

class User(@all:Deprecated("Use fullName") val name: String)



class EmailSender
class SmsSender

context(e: EmailSender) fun send() = println("Email")
context(s: SmsSender)   fun send() = println("SMS")


class example {
    context(email: EmailSender, sms: SmsSender)
    fun notifyAll() {
        //send(e = email) // explicitly pick email overload
        //send(s = sms)   // explicitly pick sms overload
    }
}
