package flow

import java.util.concurrent.TimeUnit

object Sequences {

  fun simple(): Sequence<Int> = sequence {
    for (i in 1..3) {
      TimeUnit.MILLISECONDS.sleep(100)
      yield(i)
    }
  }

  @JvmStatic
  fun main(args: Array<String>) {
    simple().forEach { value -> log(value) }
  }
}
