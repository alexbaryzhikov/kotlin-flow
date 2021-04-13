package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

object Buffering {

  fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
      delay(100)
      emit(i)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
      simple().collect { value ->
        delay(300)
        log(value)
      }
    }
    log("Collected in $time ms")

    println()

    val time2 = measureTimeMillis {
      simple()
        .buffer()
        .collect { value ->
          delay(300)
          log(value)
        }
    }
    log("Collected in $time2 ms")
  }
}