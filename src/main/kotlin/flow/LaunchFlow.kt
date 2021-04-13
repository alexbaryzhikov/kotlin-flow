package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

object LaunchFlow {

  fun events(): Flow<Int> = flowOf(1, 2, 3).onEach { delay(100) }

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    println("Blocking collect")
    events()
      .onEach { event -> log("Event: $event") }
      .collect()
    log("Done")

    println("\nNon-blocking launch")
    events()
      .onEach { event -> log("Event: $event") }
      .launchIn(this)
    log("Done")
  }
}