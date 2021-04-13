package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

object LatestValue {

  fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
      delay(100)
      emit(i)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
      simple().collectLatest { value ->
        log("Collecting $value")
        delay(300)
        log("Done $value")
      }
    }
    log("Collected in $time ms")
  }
}