import java.util.*;
import java.io.*;

public class Main {

	private final InputStream in;
	private final OutputStream out;

	public Main(final InputStream in, final OutputStream out) {
		this.in = in;
		this.out = out;
	}

	public static void main(final String... args) {
		new Main(System.in, System.out).exec();
	}

	public void exec() {
		try (final PrintStream ps = new PrintStream(out)) {
			final Scanner scanner = new Scanner(in);

			ps.print("Enter a line:\na = ");
			final double a = scanner.nextDouble();
			ps.print("b = ");
			final double b = scanner.nextDouble();

			ps.print("Enter coord-s of square's diagonal vertices:\n");
			ps.print("Vertex 1:\nx = ");
			final double x1 = scanner.nextDouble();
			ps.print("y = ");
			final double y1 = scanner.nextDouble();
			ps.print("Vertex 2:\nx = ");
			final double x2 = scanner.nextDouble();
			ps.print("y = ");
			final double y2 = scanner.nextDouble();

			final Line line = new Line(a, b);
			final Square square = new Square(new Point(x1, y1), new Point(x2, y2));

			if (line.isParallel(square.getDiagonal()))
				ps.print("Parallel.");
			else
				ps.print("Not parallel.");

			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Point {
	private final double x, y;

	public Point(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}
}

class Line {
	private final double slope;
	private final double intercept;

	public Line(final double slope, final double intercept) {
		this.slope = slope;
		this.intercept = intercept;
	}

	public Line(final Point A, final Point B) {
		slope = (A.y() - B.y()) / (A.x() - B.x());
		intercept = A.y() - slope * A.x();
	}

	public boolean isParallel(Line l) {
		return slope == l.slope && intercept != l.intercept;
	}
}

class Square {
	private final Point vertex1, vertex2;

	public Square(final Point vertex1, final Point vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	public Line getDiagonal() {
		return new Line(vertex1, vertex2);
	}
}
