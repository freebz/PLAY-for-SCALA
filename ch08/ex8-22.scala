// 목록 8.22  모델 클래스 구조와 일치시키기

case class Contact(email: Option[String], fax: Option[String], phone: Option[String])
case class Company(name: String, contactDetails: Contact)
case class Product(ean: Long, name: String, description: Option[String], pieces: Option[Int], manufacturer: Company, tags: List[String], active: Boolean)
