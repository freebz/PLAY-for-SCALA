// 목록 7.13  인라인으로 중첩된 폼

val appointmentMapping = tuple(
  "location" -> text,
  "start" -> tuple(
    "date" -> date,
    "time" -> text),
  "attendees" -> list(mapping(
    "name" -> text,
    "email" -> email)(Person.apply)(Person.unapply)))
