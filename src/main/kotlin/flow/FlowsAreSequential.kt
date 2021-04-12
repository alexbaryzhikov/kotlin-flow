package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

object FlowsAreSequential {

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    (1..5).asFlow()
      .filter {
        log("Filter $it")
        it % 2 == 0
      }
      .map {
        log("Map $it")
        "string $it"
      }
      .collect {
        log("Collect $it")
      }
  }
}