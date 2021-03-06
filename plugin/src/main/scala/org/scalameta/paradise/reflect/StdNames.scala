package org.scalameta.paradise
package reflect

trait StdNames { self: ReflectToolkit =>

  import global._

  implicit class ParadiseNme(nme: global.nme.type) {
    val annottees = TermName("annottees")
    val macroTransform = TermName("macroTransform")
  }
}
