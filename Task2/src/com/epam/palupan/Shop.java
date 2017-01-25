package com.epam.palupan;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

public class Shop {
    private Map<SportEquipment, Integer> goods;

    public Shop(Map<SportEquipment, Integer> goods) {
        super();
        this.goods = goods;
    }

    public Shop() {
        try{
        String datafile = Paths.get("data/items.dat").toAbsolutePath().toString();
         goods = readFile(datafile);
         }catch(IOException e){
        	 System.out.println("I/o error!!!");
         }

    }

    // getter
    public Map<SportEquipment, Integer> getGoods() {
        return goods;
    }

    // setter
    public void setGoods(Map<SportEquipment, Integer> goods) {
        this.goods = goods;
    }

    private static Map<SportEquipment, Integer> readFile(String fname) throws IOException {
        Map<SportEquipment, Integer> items = new HashMap<SportEquipment, Integer>();

        FileInputStream fis = new FileInputStream(fname);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].trim();
            }
            int goodID = Integer.parseInt(data[0]);
            String category = data[1];
            String item = data[2];
            String title = data[3];

            float price = Float.parseFloat(data[4]);
            int quantity = Integer.parseInt(data[5]);

            items.put(new SportEquipment(goodID, category, item, title, price), quantity);

        }
        br.close();
        System.out.println("Data are suscessfully loaded from file!");
        return items;
    }


    int inputInt() {
        int x = 0;
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("You inputed not an integer number!!");
            System.out.println("Input integer:");

            sc = new Scanner(System.in);
        }
        x = sc.nextInt();
        // sc.close();
        return x;

    }

    public void showListByCategory() {
        String line = "";
        TreeSet<String> categories = new TreeSet<String>();
        Set<SportEquipment> se = goods.keySet();
        System.out.println("Choose one from the next categories,type and press <Enter>:");

        for (SportEquipment good : se) {
            categories.add(good.getCategory());
        }
        for (String category : categories) {
            System.out.println(category);
        }
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            line = sc.nextLine();
        }
        Iterator<SportEquipment> itr = se.iterator();
        while (itr.hasNext()) {
            SportEquipment good = itr.next();
            if (good.getCategory().equalsIgnoreCase(line)) {

                System.out.println(good);

            }
        }
    }

    public ArrayList<SportEquipment> takeForRent() {
        int limit = 0;
        double sumForRenting = 0;
        String line = "";
        ArrayList<SportEquipment> rentUnits = new ArrayList<SportEquipment>();
       // System.out.println(rentUnits.size());
        outer:
        while (limit < 3) {
            System.out.println("Choose one item by typing its title and pressing <Enter> or type <no> to break");

            Scanner sc1 = new Scanner(System.in);
            if (sc1.hasNextLine()) {
                line = sc1.nextLine();
            }
            if (line.equalsIgnoreCase("no")) {
                break outer;
            }
            for (Entry<SportEquipment, Integer> entry : goods.entrySet()) {
                if (entry.getKey().getTitle().equalsIgnoreCase(line)) {
                    if (entry.getValue() == 0) {
                        System.out.println("You can't rent this unit(not available on time)");
                        continue outer;
                    }
                    System.out.println("You rented next unit--" + entry.toString());
                    sumForRenting += entry.getKey().getPrice();
                    rentUnits.add(entry.getKey());
                    entry.setValue(entry.getValue() - 1);
                    limit++;
                }

            }

            System.out.println("Your payment for " + limit + " goods -->" + sumForRenting);
        }
        return rentUnits;

    }

    public void showListOfRentedItems() {

    }

    public void showGoodsList() {

        for (Entry<SportEquipment, Integer> entry : goods.entrySet()) {

            System.out.print("\t" + entry.getValue() + "pcs");
            System.out.println("\t" + entry.getKey());
        }


        Iterator<Integer> itr1 = goods.values().iterator();
        int sum = 0;
        while (itr1.hasNext()) {
            sum += itr1.next();
        }
        System.out.println("Totally " + sum + " actual positions in the shop");
    }

    public void showListByItem() {
        String line = "";
        TreeSet<String> items = new TreeSet<String>();
        Set<SportEquipment> se = goods.keySet();
        System.out.println("Choose one from the next items,type and press <Enter>:");

        for (SportEquipment good : se) {
            items.add(good.getItem());
        }
        for (String item : items) {
            System.out.println(item);
        }
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            line = sc.nextLine();
        }
        Iterator<SportEquipment> itr = se.iterator();
        while (itr.hasNext()) {
            SportEquipment good = itr.next();
            if (good.getItem().equals(line)) {

                System.out.println(good);

            }
        }
    }

    public void returnRentedUnits(ArrayList<SportEquipment> rentedUnits) {
        String line = "";
        System.out.println("Rented items:");

        for (SportEquipment unit : rentedUnits) {
            System.out.println(unit);
        }
        System.out.println("Choose one to return,type its title and press <Enter>");
        Scanner sc1 = new Scanner(System.in);
        if (sc1.hasNextLine()) {
            line = sc1.nextLine();
        }
        for (SportEquipment unit : rentedUnits) {
            if (unit.getTitle().equalsIgnoreCase(line)) {
                for (Entry<SportEquipment, Integer> entry : goods.entrySet()) {
                    if (entry.getKey().getTitle().equalsIgnoreCase(line)) {
                        entry.setValue(entry.getValue() + 1);
                        rentedUnits.remove(unit);
                        System.out.println("You've returned this unit" + unit);
                        return;
                    }
                }
            }
        }
    }
}
