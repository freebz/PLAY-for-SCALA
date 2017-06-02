// 목록 10.10  채팅 애플리케이션 채팅방 액터

class ChatRoom extends Actor {
  var users = Set[String]()
  val (enumerator, channel) = Concurrent.broadcast[String]

  def receive = { Actor message
    case Join(nick) => {
      if(!users.contains(nick)) {
        val iteratee = Iteratee.foreach[String]{ message =>
          self ! Broadcast("%s: %s" format (nick, message))
        }.mapDone { _ =>
          self ! Leave(nick)
        }

        users += nick
        channel.push("User %s has joined the room, now %s users"
          format(nick, users.size))
        sender ! (iteratee, enumerator)
      } else {
        val enumerator = Enumerator(
          "Nickname %s is already in use." format nick)
        val iteratee = Iteratee.ignore
        sender ! (iteratee, enumerator)
      }
    }
    case Leave(nick) => {
      users -= nick
      channel.push("User %s has left the room, %s users left"
        format(nick, users.size))
    }
    case Broadcast(msg: String) => channel.push(msg)
  }
}
