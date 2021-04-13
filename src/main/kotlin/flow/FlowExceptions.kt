package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

object FlowExceptions {

  fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
      log("Emitting $i")
      emit(i) // emit next value
    }
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    println("Catch all")
    try {
      simple()
        .onEach { value ->
          check(value <= 1) { "Crashed on $value" }
        }
        .collect { value ->
          check(value <= 1) { "Collected $value" }
          log(value)
        }
    } catch (e: Throwable) {
      log("Caught $e")
    }

    println("\nTransparent catch")
    simple()
      .onEach { value ->
        check(value <= 1) { "Crashed on $value" }
      }
      .catch { e -> log("Caught $e") }
      .collect { value ->
        check(value <= 1) { "Collected $value" }
        log(value)
      }

    println("\nMove collect logic to onEach")
    simple()
      .onEach { value ->
        check(value <= 1) { "Collected $value" }
        log(value)
      }
      .catch { e -> log("Caught $e") }
      .collect()
  }
}