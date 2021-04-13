package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

object Conflation {

  fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
      delay(100)
      emit(i)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
      simple()
        .conflate()
        .collect { value ->
          delay(300)
          log(value)
        }
    }
    log("Collected in $time ms")
  }
}