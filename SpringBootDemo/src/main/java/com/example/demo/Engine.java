package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Engine {

	private int max_speed;
	private int cylinders;
	private int torque;
	private int cc;
	private String fuel_type;
	private int power;
	private String wheel_drive_type;
	
	public Engine() {
	
		max_speed=320;
		cylinders=6;
		torque=180;
		setCc(6162);
		fuel_type="Diesel";
		power=300;
		wheel_drive_type="Real_wheel";
	}
	
	
	@Autowired
	public Engine(int max_speed, int cylinders, int torque, int cc, String fuel_type, int power,
			String wheel_drive_type) {
		
		super();
		this.max_speed = max_speed;
		this.cylinders = cylinders;
		this.torque = torque;
		this.cc = cc;
		this.fuel_type = fuel_type;
		this.power = power;
		this.wheel_drive_type = wheel_drive_type;
	}



	public int getmax_speed() {
		return max_speed;
	}
	public void setmax_speed(int max_speed) {
		this.max_speed = max_speed;
	}
	public int getCylinders() {
		return cylinders;
	}
	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	public int getTorque() {
		return torque;
	}
	public void setTorque(int torque) {
		this.torque = torque;
	}
	public String getFuel_type() {
		return fuel_type;
	}
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}
	public int getpower() {
		return power;
	}
	public void setpower(int power) {
		this.power = power;
	}
	public String getWheel_drive_type() {
		return wheel_drive_type;
	}
	public void setWheel_drive_type(String wheel_drive_type) {
		this.wheel_drive_type = wheel_drive_type;
	}

	@Override
	public String toString() {
		return "[max_speed=" + max_speed+"Kmph" + ", cylinders=" + cylinders + ", torque=" + torque +" Nm"+ ", cc=" + cc
			+"cc"	+ ", fuel_type=" + fuel_type + ", power=" + power +" Bhp"+ ", wheel_drive_type=" + wheel_drive_type + "]";
	}
	
	
	

	
	
	
}
