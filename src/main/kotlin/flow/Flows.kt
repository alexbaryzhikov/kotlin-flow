package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Flows {

  fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
      delay(100)
      emit(i)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    launch {
      for (k in 1..3) {
        log("I'm not blocked $k")
        delay(100)
      }
    }
    simple().collect { value -> log(value) }
  }
}