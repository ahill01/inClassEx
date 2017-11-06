package adt

class ListStack[A] extends MyStack[A] {
 private case class Node(value:A, next:Node) //can be a case class b/c once u make a node u don't change it
 private var top: Node = null //need type Node otherwise top = type null & can't really do much
 
  def push(a:A):Unit = {
   //have to make a new Node which should become new top
   top = Node(a, top) 
  }
 
  def pop():A = {
    val ret = top.value //need to remember value to return it
    top = top.next 
    ret
    
  }
  def peek:A = top.value //just returns value doesn't change anything
  
  def isEmpty:Boolean = top == null //don't need to tell queue anything, doesn't change anythin
}