// 목록 3.7  컴파일된 템플릿 title.template.scala

package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

object title_Scope0 {
  import models._
  import controllers._
  import play.api.i18n._
  import views.html._
  import play.api.templates.PlayMagic._
  import play.api.mvc._
  import play.api.data._
  class title extends
      BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,
        Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat)
      with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable]
  {


    /**/
    def apply/*1.2*/(title:String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
        {

          Seq[Any](format.raw/*1.16*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
<head>
<title>"""),_display_(/*5.9*/title),format.raw/*5.14*/("""</title>
</head>
</html>"""))
        }
      }
    }

    def render(title:String): play.twirl.api.HtmlFormat.Appendable =
      apply(title)
    def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (title) => apply(title)
    def ref: this.type = this
  }


}

object title extends title_Scope0.title
