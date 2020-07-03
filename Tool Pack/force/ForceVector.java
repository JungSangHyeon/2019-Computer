package force;

public class ForceVector {
	
	// Working Variable 
	private double x = 0, y = 0;

	//Constructor
    public ForceVector() {}
    public ForceVector(ForceVector v) {this.x = v.getX(); this.y = v.getY();}
    public ForceVector(double x, double y) {this.x = x; this.y = y;}

    // Getter & Setter
    public void set(ForceVector v) {this.x = v.getX(); this.y = v.getY();}
    public void add(ForceVector v) {this.x += v.getX(); this.y += v.getY();} 
    public void sub(ForceVector v) {this.x -= v.getX(); this.y -= v.getY();}
    public void set(double x, double y) {this.x = x; this.y = y;}
    public void add(double x, double y) {this.x += x; this.y += y;}
    public void sub(double x, double y) {this.x -= x; this.y -= y;}
    public void scale(double s) {this.x *= s; this.y *= s;}
    public double dot(ForceVector v) {return x * v.getX() + y * v.getY();} // 내적
	public double cross(ForceVector v) {return x * v.getY() - y * v.getX();} // 외적
	public double size() {return Math.sqrt(x*x + y*y);}
	public double getX() {return this.x;}
	public double getY() {return this.y;}
}
