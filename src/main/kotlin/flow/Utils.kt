package flow

import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")

fun <T> log(message: T) {
  println("${LocalTime.now().format(dateTimeFormatter)} [${Thread.currentThread().name}] $message")
}
