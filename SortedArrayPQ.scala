package adt

import scala.reflect.ClassTag

class SortedArrayPQ[A: ClassTag](hp: (A,A) => Boolean) extends MyPriorityQueue[A] {
  private var data = new Array[A](10)
  private var size = 0
 
  
  //highest priority goes to back -> easier to remove
  def enqueue(a:A):Unit = {
  //if things in array are higher priority, copy down else write
  var i = size -1 //location checking
  while(i >= 0 && hp(data(i), a)) {
    data(i+1) = data(i) //
    i-=1 //counts down
  }
  data(i+1) = a
  size += 1
  }
  
  /*
   * all are order 1 
   */
  def dequeue():A = {
    size -= 1 //shrinks array by one
    data(size)
  }
  
  def peek:A = data(size -1)
 
  def isEmpty:Boolean = size == 0
  
}