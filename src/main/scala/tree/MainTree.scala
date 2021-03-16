package tree

import scala.annotation.tailrec

object MainTree extends App {

  def compare(a: TreeNode[Int], b: TreeNode[Int]): Boolean = {
    (a, b) match {
      case (TreeNode(aValue, Some(aLeft), Some(aRight)), TreeNode(bValue, Some(bLeft), Some(bRight))) if aValue == bValue => {
        compare(aLeft, bLeft) && compare(aRight, bRight)
      }
      case (TreeNode(aValue, None, None), TreeNode(bValue, None, None)) => aValue == bValue
      case _ => false
    }
  }
//  @tailrec
//  def compare(a: TreeNode[Int], b: TreeNode[Int]): Boolean = {
//    if (a != b) {
//      false
//    } else {
//      if (a.left.isDefined && b.left.isDefined && a.right.isDefined && b.right.isDefined) {
//        if (compare(a.left.get, a.right.get))
//          compare(b.left.get, b.right.get) else false
//      } else true
//    }
//  }
}

case class TreeNode[X](value: X, left: Option[TreeNode[X]], right: Option[TreeNode[X]])