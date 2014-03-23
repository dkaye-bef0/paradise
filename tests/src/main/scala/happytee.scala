import scala.language.experimental.macros
import scala.annotation.StaticAnnotation
import scala.reflect.macros.Context

object happyteeMacro {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    annottees.map(_.tree).toList match {
      case (tsappa @ ValDef(_, _, tpt, _)) :: _ => {
        val tpe = c.typeCheck(TypeApply(Select(Literal(Constant(null)), newTermName("asInstanceOf")), List(tpt))).tpe
        c.Expr(Block(List(tsappa), Literal(Constant(()))))
      }
    }
  }
}

class happytee extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro happyteeMacro.impl
}

package pkg {
  class happytee extends StaticAnnotation {
    def macroTransform(annottees: Any*): Any = macro happyteeMacro.impl
  }
}