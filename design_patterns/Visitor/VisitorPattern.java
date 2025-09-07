package design_patterns.Visitor;

public class VisitorPattern {
    public interface Visitor {
        public void visitDot(Dot d);

        public void visitCircle(Circle c);

    }

    public interface Shape {
        public void draw();

        public void accept(Visitor visitor);

    }

    public static class Dot implements Shape {

        @Override
        public void draw() {
            System.out.println("Drawing dot");
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitDot(this);
        }

    }

    public static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Drawing circle");
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitCircle(this);
        }

    }

    public static class SVGExport implements Visitor {

        @Override
        public void visitDot(Dot d) {
            System.out.println("Exporting Dot SVG");
        }

        @Override
        public void visitCircle(Circle c) {
            System.out.println("Exporting Circle SVG");
        }

    }


    public static class AnimateShapeVisitor implements Visitor {

        @Override
        public void visitDot(Dot d) {
            System.out.println("Animating Dot SVG");
        }

        @Override
        public void visitCircle(Circle c) {
            System.out.println("Animating Circle SVG");
        }

    }

    public static void main(String[] args) {
        Dot dot = new Dot();
        Circle circle = new Circle();

        SVGExport sExport = new SVGExport();
        dot.accept(sExport);
        circle.accept(sExport);

        AnimateShapeVisitor animator = new AnimateShapeVisitor();
        dot.accept(animator);
        circle.accept(animator);

    }
}
