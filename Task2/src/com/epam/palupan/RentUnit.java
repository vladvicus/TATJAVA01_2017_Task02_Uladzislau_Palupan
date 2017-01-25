package com.epam.palupan;

import java.util.ArrayList;

public class RentUnit {
    private ArrayList<SportEquipment> units;


    public RentUnit() {
		super();
		this.units=new ArrayList<>();
	}


	public RentUnit(ArrayList<SportEquipment> units) {
		super();
		this.units = units;
	}


	public ArrayList<SportEquipment> getUnits() {
        return units;
    }


    public void setUnits(ArrayList<SportEquipment> units) {
        this.units = units;
    }


    public void showRentUnits() {

        try {
            if (units.isEmpty()) {
                System.out.println("There's no rented units!!!");
            } else {
            	System.out.println("\tRented items:");
                for (SportEquipment unit : units) {
                    System.out.println(unit);
                }
            }
        } catch (NullPointerException exp) {
            System.err.println("Arraylist hasn't been initialised!!!");
            return;
        }
    }

}
