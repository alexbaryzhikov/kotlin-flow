package flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

object FlowContext {

  fun simple(): Flow<Int> = flow {
    log("Started simple flow")
    for (i in 1..3) {
      emit(i)
    }
  }

  fun badContexSwitch(): Flow<Int> = flow {
    withContext(Dispatchers.Default) {
      for (i in 1..3) {
        TimeUnit.MILLISECONDS.sleep(100)
        emit(i)
      }
    }
  }

  fun okContextSwitch(): Flow<Int> = flow {
    for (i in 1..3) {
      TimeUnit.MILLISECONDS.sleep(100)
      log("Emitting $i")
      emit(i)
    }
  }.flowOn(Dispatchers.Default)

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    simple().collect { value -> log("Collected $value") }

    println()

    try {
      badContexSwitch().collect(::log)
    } catch (e: Throwable) {
      e.printStackTrace()
    }

    println()

    okContextSwitch().collect { value -> log("Collected $value") }
  }
}