package adt

object bfs extends App {
  val maze = Array(
    Array(0,-1, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0,-1, 0,-1, 0,-1,-1,-1,-1, 0),
    Array(0,-1, 0,-1, 0, 0, 0, 0,-1, 0),
    Array(0,-1,-1,-1, 0,-1,-1,-1,-1,-1),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0,-1,-1,-1,-1,-1,-1,-1,-1,-1),
    Array(0,-1, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0,-1, 0,-1, 0,-1, 0,-1,-1, 0),
    Array(0,-1,-1,-1, 0,-1, 0, 0,-1, 0),
    Array(0, 0, 0, 0, 0,-1,-1, 0,-1, 0))

  val offsets = Array((-1, 0), (1, 0), (0, -1), (0, 1)) //array of tuples of ints for offsets of enqueue

  def bfs(sx: Int, sy: Int, ex: Int, ey: Int): Int = {
    val q = new ArrayQueue[(Int, Int, Int)]()
    q.enqueue((sx, sy, 0))
    val visited = collection.mutable.Set[(Int, Int)]()
    visited += sx -> sy

    while (!q.isEmpty) {
      val (x, y, steps) = q.dequeue()
      for ((dx, dy) <- offsets) {
        val (nx, ny) = (x + dx, y + dy)
        if (x == ex && y == ey) return steps + 1
        if (!visited.contains((nx, ny)) && nx >= 0 && nx < maze.length &&
          ny >= 0 && ny < maze(nx).length && maze(nx)(ny) == 0) {
          q.enqueue((nx, ny, steps + 1))
          visited += nx -> ny
        }
      }
    }
   -1
  }
 println(bfs(0, 0, 9, 9))
}