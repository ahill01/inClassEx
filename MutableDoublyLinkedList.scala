package adt

class MutableDoublyLinkedList[A] extends MyList[A] {
  private class Node(var prev: Node, var value: A, var next:Node)
  private var default:A = _
  private val end = new Node(null,default,null) //never have to change sentinal even tho changing values in sentinal
  
  //sentinal points to ("hugs") itself
  end.next = end //head = end
  end.prev = end //tail = end
  
  private var size = 0
 /*
  * @param index
  * @return value at that index
  */
  def apply(i:Int):A = {
   if(i < 0 || i>= size) throw new IndexOutOfBoundsException(s"Intex $i out of $size")
    var rover = end.next //rover walks thru list, starting @ head
    for(_ <- 0 until i)  rover = rover.next
    rover.value //gives back value
  }
  /*
  * @param index, data element
  * @return stores value
  */
  def update(i:Int, a: A):Unit = {
  if(i < 0 || i>= size) throw new IndexOutOfBoundsException(s"Intex $i out of $size")
    var rover = end.next //rover walks thru list, starting @ head
    for(_ <- 0 until i)  rover = rover.next
    rover.value = a //stores value
  }
  
  def insert(i: Int, a:A): Unit = {
    if(i < 0 || i > size) throw new IndexOutOfBoundsException(s"Intex $i out of $size") //should be allowed to add stuff & make it bigger
    var rover = end.next //rover walks thru list, starting @ head
    for(_ <- 0 until i)  rover = rover.next
    val n = new Node(rover.prev,a,rover) //inserting right before rover, after rover.previous
    //need to make .prev and .next or original nodes point to correct places
    rover.prev.next = n //needs to be first b/c uses rover prev
    rover.prev = n //changes rover prev
    size += 1
  }
  
  def remove(i:Int):A = {
   if(i < 0 || i > size) throw new IndexOutOfBoundsException(s"Intex $i out of $size") //should be allowed to add stuff & make it bigger
    var rover = end.next //rover walks thru list, starting @ head
    for(_ <- 0 until i)  rover = rover.next
    //bring node out of the loop, every next becomes a prev & every prev becomes a next
    rover.prev.next = rover.next 
    rover.next.prev = rover.prev
    size -= 1
    rover.value //returns value we're throwing out
  }
  
  def length: Int = size
}