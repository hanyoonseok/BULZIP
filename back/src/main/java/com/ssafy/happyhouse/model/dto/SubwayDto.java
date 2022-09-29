package com.ssafy.happyhouse.model.dto;

public class SubwayDto {
	private int station_id;
	private String institution;
	private String subway_line;
	private String station;
	private double lon;
	private double lat;
	public SubwayDto() {}
	public SubwayDto(String institution, String subway_line, String station, double lon, double lat) {
		this.institution = institution;
		this.subway_line = subway_line;
		this.station = station;
		this.lon = lon;
		this.lat = lat;
	}
	public SubwayDto(int station_id, String institution, String subway_line, String station, double lon, double lat) {
		this.station_id = station_id;
		this.institution = institution;
		this.subway_line = subway_line;
		this.station = station;
		this.lon = lon;
		this.lat = lat;
	}
	public int getStation_id() {
		return station_id;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getSubway_line() {
		return subway_line;
	}
	public void setSubway_line(String subway_line) {
		this.subway_line = subway_line;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "SubwayDto [station_id=" + station_id + ", institution=" + institution + ", subway_line=" + subway_line
				+ ", station=" + station + ", lon=" + lon + ", lat=" + lat + "]";
	}
}
