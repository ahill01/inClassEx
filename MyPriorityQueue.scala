package adt

trait MyPriorityQueue[A] {
  def enqueue(a:A):Unit
  def dequeue():A //take out based on priority!!
  def peek:A //take out based on priority!!
  def isEmpty:Boolean
}