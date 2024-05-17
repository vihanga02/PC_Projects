package Adapter;

// Say you have two classes with compatible interfaces:
// RoundHole and RoundPeg.
class RoundHole{
    private double radius;

    public RoundHole(Double radius){
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg roundPeg){
        return this.getRadius() >= roundPeg.getRadius();
    }
}

class RoundPeg{
    private double radius;

    RoundPeg(){}

    RoundPeg(double radius){
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

// But there's an incompatible class
class SquarePeg{
    private double width;

    SquarePeg(){}

    SquarePeg(double width){
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}

// An adapter class lets you fit square pegs into round holes.
// It extends the RoundPeg class to let the adapter objects act
// as round pegs.
class SquarePegAdapter extends RoundPeg{
    private SquarePeg squarePeg;

    SquarePegAdapter(SquarePeg squarePeg){
        this.squarePeg = squarePeg;
    }

    public double getRadius() {
        // The adapter pretends that it's a round peg with a
        // radius that could fit the square peg that the adapter
        // actually wraps.
        return squarePeg.getWidth() * Math.sqrt(2) / 2;
    }
}

public class Application {
    public static void main(String [] args){
        RoundHole hole = new RoundHole(5.0);
        RoundPeg rpeg = new RoundPeg(5);

        System.out.println(hole.fits(rpeg)); // true

        SquarePeg smallSqPeg = new SquarePeg(5);
        SquarePeg largeSqPeg = new SquarePeg(10);

        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        System.out.println(hole.fits(smallSqPegAdapter)); // true
        System.out.println(hole.fits(largeSqPegAdapter)); // false
    }
}
