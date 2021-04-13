package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

object FlowCancellation {

  fun supervisorScope() = CoroutineScope(SupervisorJob())

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    println("Cancellable flow")
    supervisorScope().launch {
      flow {
        for (i in 1..5) {
          log("Emitting $i")
          emit(i)
        }
      }
        .onEach { value ->
          if (value == 3) cancel()
          log(value)
        }
        .collect()
    }.join()

    println("\nNo cancellation checks by defaul")
    supervisorScope().launch {
      flowOf(1, 2, 3, 4, 5).onEach { log("Emitting $it") }
        .onEach { value ->
          if (value == 3) cancel()
          log(value)
        }
        .collect()
    }.join()

    println("\nAdd cancellation checks")
    supervisorScope().launch {
      flowOf(1, 2, 3, 4, 5).onEach { log("Emitting $it") }
        .cancellable()
        .onEach { value ->
          if (value == 3) cancel()
          log(value)
        }
        .collect()
    }.join()
  }
}
