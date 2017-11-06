package adt

class MutableSinglyLinkedList[A] extends MyList[A] {
  var listLength = 0 
  class Node(var data:A, var next:Node)
  

  private var head:Node = null
  
  def apply(i:Int):A = {
 ???
  }
  
  def update(i:Int, a: A):Unit = {
     var rover = head
    if (i < listLength) {
      for(j <- 0 until i) {
      rover = rover.next 
      }
      rover.data=a
    }
  }
  
  def insert(i: Int, a:A): Unit = {
    var rover = head
    if(i < listLength){
      for(j <- 1 until i){
        rover = rover.next
      }
      //var newNode:Node = (a, rover.next)
     //rover.next = newNode
    }
  }
  
  def remove(i:Int):A = {
   ??? 
  }
  
  def length: Int = {
  listLength  
  }
}