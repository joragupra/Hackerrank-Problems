package sierpinskiTriangles

object Solution {

    class Point(val x:Int, val y:Int) {

        def nextOnTop:Point = new Point(x-1, y)

        def nextOnLeft:Point = new Point(x, y-1)

        def nextOnRight:Point = new Point(x, y+1)

        override def toString():String = "[" + x + "," + y + "]"

        override def equals(that: Any) = that match {
            case b: Point => this.x == b.x && this.y == b.y
            case _ => false
        }

    }

    class Line(val origin:Point, val end:Point) {

        def calculatePosition(p:Point):Int =
            if (isPoint)
                if (origin equals p) 0 else 1
            else ((end.x - origin.x)*(p.y - origin.y) - (end.y - origin.y)*(p.x - origin.x))

        def isPoint:Boolean = origin equals end

    }

    class Triangle(val leftToTop:Line, val leftToRight:Line, val rightToTop:Line, val top:Point, val left:Point, val right:Point) {

        def this(top:Point, left:Point, right:Point) {
            this(new Line(left, top), new Line(left, right), new Line(right, top), top, left, right)
        }

        def contains(p:Point):Boolean =
            if (leftToTop.calculatePosition(p) > 0) false
            else if (rightToTop.calculatePosition(p) < 0) false
            else if (leftToRight.calculatePosition(p) < 0) false
            else true

        def fractalize():List[Triangle] = {
            if (isMinimalNonSinglePoint) List(new Triangle(top, top, top), new Triangle(left, left, left), new Triangle(right, right, right))
            else {
                val halfHeight = (left.x - top.x) / 2
                val quarterWidth = (right.y - left.y) / 4
                //calculate the blank triangle points
                val topLeft = new Point(left.x - halfHeight, left.y + quarterWidth)
                val topRight = new Point(right.x - halfHeight, right.y - quarterWidth)
                val bottom = new Point(left.x, top.y)

                val topT = new Triangle(this.top, topLeft.nextOnTop.nextOnRight, topRight.nextOnTop.nextOnLeft)
                val leftT = new Triangle(topLeft, this.left, bottom.nextOnLeft)
                val rightT = new Triangle(topRight, bottom.nextOnRight, this.right)

                List(topT, leftT, rightT)
            }
        }

        def isMinimalNonSinglePoint:Boolean = (left.x == top.x+1) && (left.y-1 == top.y)

        override def toString():String = "Triangle {Top: " + top + " Left " + left + " Right " + right + "}"

    }

    class Solver {

        def compute(N:Int, triangles:List[Triangle]):List[Triangle] = N match {
            case 0 => triangles
            case x => compute(N-1, (for (triangle <- triangles) yield triangle.fractalize).flatten)
        }

    }

    class Drawer(val MAX_ROWS:Int, val MAX_COLS:Int) {

        def drawTriangles(triangles:List[Triangle]) = {
            for (i <- 0 to MAX_ROWS-1) {
                for (j <- 0 to MAX_COLS-1) {
                    val p = new Point(i, j)
                    if (triangles.exists(_.contains(p))) {
                        print("1")
                    } else {
                        print("_")
                    }
                }
                print("\n")
            }
        }

    }

    
    def drawTriangles(N:Int) = {
        val d = new Drawer(32, 63)
        val s = new Solver

        val tri:List[Triangle] = s.compute(N, List(new Triangle(new Point(0, 31), new Point(31, 0), new Point(31, 62))))

        d.drawTriangles(tri)
    }
    
    def main(args: Array[String]) {
        drawTriangles(readInt())
    }
}
