package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object SuspendingFunctions {

  suspend fun simple(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3)
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    simple().forEach(::log)
  }
}
