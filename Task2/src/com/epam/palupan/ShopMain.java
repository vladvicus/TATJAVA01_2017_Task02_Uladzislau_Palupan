package com.epam.palupan;

import java.util.ArrayList;

public class ShopMain {
	public ShopMain() {
	}

	public static void main(String[] args) {
		int t = 0;
		Shop shop = new Shop();
		RentUnit ru = new RentUnit();
		System.out.println("HELLO, MY DEAR CLIENT!!! YOU ARE INSIDE THE RENTING SHOP !!!");
		do {
			System.out.println();
			System.out.println("Input   1-To show goods list\n" + "\t2-Search for category and print result list\n"
					+ "\t3-Search for item and print result list\n"
					+ "\t4-Take a single position for rent(only 3 units)\n"
					+ "\t5-Search for rented units and print result\n" + "\t6-Return rented units back\n"
					+ "\t0-To exit");
			t = shop.inputInt();
			switch (t) {
			case 1:
				shop.showGoodsList();
				System.out.println();
				break;
			case 2:
				shop.showListByCategory();
				System.out.println();
				break;
			case 3:
				shop.showListByItem();
				System.out.println();
				break;
			case 4:
				ArrayList<SportEquipment> unitForRent = shop.takeForRent();
				ru.setUnits(unitForRent);
				break;
			case 5:
				ru.showRentUnits();
				break;
			case 6:

				if (!ru.getUnits().isEmpty()) {
					shop.returnRentedUnits(ru.getUnits());
				} else {
					System.out.println("There is no rented units!!!");
				}

				break;
			case 0:
				System.out.println("Thank YOU!");
				break;
			}
		} while (t != 0);
	}
}
