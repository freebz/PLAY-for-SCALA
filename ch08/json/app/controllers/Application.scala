package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class Application @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
    */
  /*
  def index = Action {
    Ok(views.html.index())
  }
   
  def index = Action { request =>
    Ok("Response...")
  }
   */

  def index = AuthenticatedAction { request =>
    Ok("Authenticated Response...")
  }

  /*
  def authenticate(request: Request[AnyContent]) = true

  def AuthenticatedAction(f: Request[AnyContent] => Result):
      Action[AnyContent] = {
    Action { request =>
      if (authenticate(request)) {
        f(request)
      } else {
        Unauthorized
      }
    }
  }
   */

  def readQueryString(request: Request[_]): Option[Either[Result,
    (String, String)]] = {
    request.queryString.get("user").map { user =>
      request.queryString.get("password").map { password =>
        Right((user.head, password.head))
      }.getOrElse {
        Left(BadRequest("Password not specified"))
      }
    }
  }

  def AuthenticatedAction(f: Request[AnyContent] => Result):
      Action[AnyContent] = {
    Action {
      request =>
      val maybeCredentials = readQueryString(request) orElse
        readBasicAuthentication(request.headers)
      maybeCredentials.map { resultOrCredentials =>
        resultOrCredentials match {
          case Left(errorResult) => errorResult
          case Right(credentials) => {
            val (user, password) = credentials
            if (authenticate(user, password)) {
              f(request)
            } else {
              Unauthorized("Invalid user name or password")
            }
          }
        }
      }.getOrElse {
        //Unauthorized("No user name and password provided")
        val authenticate = (http.HeaderNames.WWW_AUTHENTICATE, "Basic")
        Unauthorized.withHeaders(authenticate)
      }
    }
  }

  def authenticate(user: String, password: String) = true

  def readBasicAuthentication(headers: Headers): Option[Either[Result,
    (String, String)]] = {

    headers.get(http.HeaderNames.AUTHORIZATION).map { header =>

      val BasicHeader = "Basic (.*)".r
      header match {
        case BasicHeader(base64) => {
          try {
            import org.apache.commons.codec.binary.Base64
            val decodedBytes =
              Base64.decodeBase64(base64.getBytes)
            val credentials =
              new String(decodedBytes).split(":", 2)
            if (credentials.length != 2) {
              Left(BadRequest("Invalid basic authentication"))
            } else {
              val (user, password) = (credentials(0), credentials(1))
              Right((user, password))
            }
          }
        }
        case _ => Left(BadRequest("Invalid Authorization header"))
      }
    }
  }

}
