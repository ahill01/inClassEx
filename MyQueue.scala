package adt

trait MyQueue[A] {
  def enqueue(a:A):Unit
  def dequeue():A
  def peek:A
  def isEmpty:Boolean //don't need to tell queue anything, doesn't change anything
}