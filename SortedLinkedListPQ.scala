package adt

class SortedLinkedListPQ[A](hp: (A,A) => Boolean) extends MyPriorityQueue[A] {
  private class Node(var prev: Node, var value: A, var next:Node)
  private var default:A = _
  private val end = new Node(null,default,null)
  end.next = end 
  end.prev = end 
  
  //always removing from head so easier if singly linked list
  
  def enqueue(a:A):Unit = {
    var rover = end.next
    while(hp(rover.value, rover.next.value) == false) {rover = rover.next}
    val n = new Node(rover.prev, a, rover.next)
    rover.prev.next = n 
    rover.prev = n 
  }
  def dequeue():A = {
    var rover = end.next
  ???
  }
  def peek:A = {
    val rover = end.next 
    
    rover.value
    //going to look like dequeue but just returns rover.value
  }
  
  def isEmpty:Boolean = if(end.next == end.prev) true else false

  private def DeterminePriority(a1:A, a2:A):Boolean = {
   ???
  }
  
}