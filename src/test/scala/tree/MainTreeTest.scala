package tree

import org.scalatest.FunSuite

class MainTreeTest extends FunSuite {
  test("MainTree.compare test one"){
    val simpleP = TreeNode(1, None, None)
    val simpleQ = TreeNode(1, None, None)
    println(simpleP == simpleQ)
    assert(MainTree.compare(simpleP, simpleQ))
  }
  test("MainTree.compare test two"){
    val treeP = TreeNode(1, Some(TreeNode(2, None, None)), None)
    val treeQ = TreeNode(1, None, Some(TreeNode(2, None, None)))
    println(treeP == treeQ)
    assert(MainTree.compare(treeP, treeQ))
  }
  test("MainTree.compare test three"){
    val treeP = TreeNode(1, None, Some(TreeNode(2, None, None)))
    val treeQ = TreeNode(1, None, Some(TreeNode(2, None, None)))
    println(treeP == treeQ)
    assert(MainTree.compare(treeP, treeQ))
  }
  test("MainTree.compare test four") {
    val treeP = TreeNode(1, None, Some(TreeNode(2, Some(TreeNode(2, None, None)), None)))
    val treeQ = TreeNode(1, None, Some(TreeNode(2, None, None)))
    println(treeP == treeQ)
    assert(MainTree.compare(treeP, treeQ))
  }

}
