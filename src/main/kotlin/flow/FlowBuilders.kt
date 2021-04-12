package flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

object FlowBuilders {

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    flowOf("Alpha", "Beta", "Gamma").collect(::log)
    (1..3).asFlow().collect(::log)
  }
}