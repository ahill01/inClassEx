package adt

class UnsortedLinkedListPQ[A](hp: (A,A) => Boolean) extends MyPriorityQueue[A] {
  private class Node(var prev: Node, var value: A, var next:Node)
  private var default:A = _
  private val end = new Node(null,default,null)
  end.next = end 
  end.prev = end 
  //doubly linked list better
  
  def enqueue(a:A):Unit = {
    val n = new Node(end, a, end.next)
    end.next.prev = n
    end.next = n
  }
  def dequeue():A = {
    var rover = end.next
    var hpNode = rover
    while (rover != end) {
      if(hp(rover.value, hpNode.value)) {
        hpNode = rover
      }
    }
    val ret = hpNode.value
    hpNode.prev.next = hpNode.next
    hpNode.next.prev = hpNode.prev
    ret
    
  }
  
  def peek:A = {
    var rover = end.next
    var hpNode = rover
    while (rover != end) {
      if(hp(rover.value, hpNode.value)) {
        hpNode = rover
      }
    }
    hpNode.value
  }
  def isEmpty:Boolean = end.next == end
}