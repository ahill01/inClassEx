package utility

class Complex(val r: Double, val i: Double) {
  def +(o: Complex) = Complex(r+o.r, i+o.i)
  def -(o: Complex) = Complex(r-o.r, i-o.i)
  def *(o: Complex) = Complex(r*o.r-i*o.i, r*o.i+i*o.r)
  def /(o: Complex) = {
    val denom = o.r*o.r + o.i*o.i
    Complex((r*o.r + i*o.i)/denom, (i*o.r-r*o.i)/denom)
  }
  def mag = math.sqrt(r*r + i*i)
}

object Complex {
  def apply(r: Double, i: Double) = new Complex(r,i) //constructs
}