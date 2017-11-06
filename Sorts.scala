package utility

object Sorts {
  def bubbleSort[A](a:Array[A])(lt:(A,A)=> Boolean) :Unit = {
    for (i<- 0 until a.length-1) {
      for (j <- 0 until a.length-1-i) {
   //     if(a(j+1) < a(j)) {
          val tmp = a(j)
          a(j) = a(j+1)
          a(j+1) = tmp
     //   }
      }
    }
  }
  
   def bubbleSortR[A <: Ordered[A]](a:Array[A])(lt:(A,A)=> Boolean) :Unit = {
    for (i<- 0 until a.length-1) {
      for (j <- 0 until a.length-1-i) {
        if(a(j+1) < a(j)) {
          val tmp = a(j)
          a(j) = a(j+1)
          a(j+1) = tmp
        }
      }
    }
  }
  def main(args:Array[String]):Unit = {
    val nums = Array.fill(10)(util.Random.nextInt(100))
    println(nums.mkString(", "))
 //   bubbleSort(nums,_ < _)
    println(nums.mkString(", "))
  }
}