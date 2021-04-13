package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

object FlattenFlows {

  fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500)
    emit("$i: Second")
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    flowOf(1, 2, 3).onEach { delay(100) }
      .flatMapConcat { requestFlow(it) }
      .collect(::log)

    println()

    flowOf(1, 2, 3).onEach { delay(100) }
      .flatMapMerge { requestFlow(it) }
      .collect(::log)

    println()

    flowOf(1, 2, 3).onEach { delay(100) }
      .flatMapLatest { requestFlow(it) }
      .collect(::log)
  }
}