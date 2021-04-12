package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

object FlowsAreCold {

  fun simple(): Flow<Int> = flow {
    log("Flow started")
    for (i in 1..3) {
      delay(100)
      emit(i)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    log("Calling simple...")
    val flow = simple()
    log("Calling collect...")
    flow.collect(::log)
    log("Calling collect again...")
    flow.collect(::log)
  }
}