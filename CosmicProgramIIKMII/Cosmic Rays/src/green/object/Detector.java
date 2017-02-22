package green.object;

public class Detector {
	
	private int id;
	private String name;
	private String subcluster;
	
	private double latitude;
	private double longitude;
	private double altitude;
	private boolean active;
	private String cluster;
	private String country;
	
	private int status;
	
	
	public Detector(int ID, String name) {
		this.id = ID;
		this.name = name;
	}
	
	/**
	 * @param ID The numerical ID of this detector
	 * @param name The displayed name of this detector
	 * @param status Numerical representation of this detector's status.
	 * <b>0</b> Unknown
	 * <b>1</b> Down
	 * <b>2</b> Problem
	 * <b>3</b> Up 
	 */
	public Detector(int ID, String name, int status) {
		this.id = ID;
		this.name = name;
		this.status = status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public Detector() {
	}
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubcluster() {
		return subcluster;
	}
	public void setSubcluster(String subcluster) {
		this.subcluster = subcluster;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public Double latitudeRadians(){
		return Math.toRadians(latitude);
	}
	
	public double longitudeRadians(){
		return Math.toRadians(longitude);
	}
}
