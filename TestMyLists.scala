package adt

import org.junit.Assert
import org.junit.Test
import org.junit.Before

class TestMyLists {
  private var lst:MyList[Int] = null
  @Before def makeList():Unit = {
   lst = new MutableDoublyLinkedList[Int] 
  }
 @Test def testAdd1():Unit = {
    lst.insert(0, 5)
    Assert.assertEquals(5,lst(0))
  }
 @Test def testLength(): Unit = {
   Assert.assertEquals(0,lst.length)
   lst.insert(0,5)
   Assert.assertEquals(1, lst.length)
   lst.insert(0, 3)
   Assert.assertEquals(2, lst.length)
 }
  
}