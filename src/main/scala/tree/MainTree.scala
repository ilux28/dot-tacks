package tree

import scala.annotation.tailrec

object MainTree extends App {

//  @tailrec
  def compare(a: TreeNode[Int], b: TreeNode[Int]): Boolean = {
    if (a != b) {
      false
    } else {
      if (a.left.isDefined && b.left.isDefined && a.right.isDefined && b.right.isDefined) {
        if (compare(a.left.get, a.right.get))
          compare(b.left.get, b.right.get) else false
      } else true
    }
  }
}

case class TreeNode[X](value: X, left: Option[TreeNode[X]], right: Option[TreeNode[X]])