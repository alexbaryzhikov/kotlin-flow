package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

object IntermediateFlowOperators {

  suspend fun performRequest(request: Int): String {
    delay(100)
    return "response $request"
  }

  fun numbers(): Flow<Int> = flow {
    try {
      emit(1)
      emit(2)
      log("This will not execute")
      emit(3)
    } finally {
      log("Finally in numbers")
    }
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    (1..3).asFlow()
      .map { request -> performRequest(request) }
      .collect(::log)

    println()

    (1..3).asFlow()
      .transform { request ->
        emit("Making request $request")
        emit(performRequest(request))
      }
      .collect(::log)

    println()

    numbers()
      .take(2)
      .collect(::log)
  }
}
