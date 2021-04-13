package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

object FlowCompletion {

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    println("Imperative completion")
    try {
      flowOf(1, 2, 3).collect(::log)
    } finally {
      log("Done")
    }

    println("\nDeclarative completion")
    flowOf(1, 2, 3)
      .onCompletion { log("Done") }
      .collect(::log)

    println("\nCause of completion")
    flow {
      emit(1)
      throw RuntimeException("Boom!")
    }
      .onCompletion { cause ->
        if (cause != null) log("Completed exceptionally")
      }
      .catch { e -> log("Caught $e") }
      .collect(::log)

    println("\nSuccessful completion")
    flowOf(1, 2, 3)
      .onCompletion { cause ->
        when (cause) {
          null -> log("Completed successfully")
          else -> log("Completed with $cause")
        }
      }
      .onEach { value ->
        check(value <= 1) { "Collected $value" }
        log(value)
      }
      .catch { e -> log("Caught $e") }
      .collect()
  }
}