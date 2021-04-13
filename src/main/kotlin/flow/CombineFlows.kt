package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

object CombineFlows {

  @JvmStatic
  fun main(args: Array<String>) = runBlocking {
    val nums = flowOf(1, 2, 3)
    val strs = flowOf("Alpha", "Beta", "Gamma")
    nums.zip(strs) { a, b -> "$a -> $b" }
      .collect(::log)

    println()

    val nums2 = nums.onEach { delay(300) }
    val strs2 = strs.onEach { delay(400) }
    val t = System.currentTimeMillis()
    combine(nums2, strs2) { a, b -> "$a -> $b" }
      .collect(::log)
  }
}