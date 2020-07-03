package force;

public class SimpleMoveForce {
	
	// Attribute
    private double mass = 1; // 이동 저항 : 높을 수 록 저항이 큼.
	private double friction = 0.96; // 마찰 저항 : 1에 가까울 수록 마찰력이 낮음.
    
    // Component
    private ForceVector force, velocity, acceleration;
    
    // Constructor
    public SimpleMoveForce() {
    	this.force = new ForceVector();
    	this.velocity = new ForceVector();
    	this.acceleration = new ForceVector();
    }
    
	public double[] getMoveXY() {
		// Move
		this.velocity.add(this.acceleration);
		this.force.scale(1 / this.mass);
		this.acceleration.set(this.force);
		double[] result = {this.velocity.getX(), this.velocity.getY()}; 
		
		// Resist
		this.velocity.scale(this.friction);
		this.force.set(0, 0);
		return result;
	}
	
	// Getter & Setter
	public void setVelocity(double x, double y) {this.velocity.set(x, y);}
	public void reset() {this.force.set(0,0); this.velocity.set(0,0); this.acceleration.set(0,0);}
}
