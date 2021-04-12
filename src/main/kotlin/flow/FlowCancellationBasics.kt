package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

object FlowCancellationBasics {

  fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
      delay(100)
      log("Emitting $i")
      emit(i)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    withTimeoutOrNull(250) {
      simple().collect(::log)
    }
    log("Done")
  }
}