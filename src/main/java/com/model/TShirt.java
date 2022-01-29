package com.model;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.persistence.TShirtDatabase;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "tshirt")
public class TShirt {
    @Id
    @Column(name="ID", nullable = false)
    private String id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="COLOUR", nullable = false)
    private String colour;

    @Column(name="GENDER", nullable = false)
    private String gender;

    @Column(name="SIZE", nullable = false)
    private String size;

    @Column(name="PRICE", nullable = false)
    private float price;

    @Column(name="RATING", nullable = false)
    private float rating;

    @Column(name="AVAILABLITY", nullable = false)
    private String availablity;

    public TShirt(){}
    public TShirt(String id, String name, String colour, String gender, String size, String price, String rating, String availablity) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.gender = gender;
        this.size = size;
        this.price = Float.parseFloat(price);
        this.rating = Float.parseFloat(rating);
        this.availablity = availablity;
    }
    
    public static void main(String[] args) {
    	List<TShirt> tshirtList = getAllTShirt();
    	System.out.println(tshirtList.hashCode());
    	tshirtList.remove(0);
    	System.out.println("After removel : "+tshirtList.hashCode());
    	List<TShirt> tshirtList2 = getAllTShirt();
    	System.out.println(tshirtList2.hashCode());
    	
//    	    	TShirtDatabase.insertAllDataToDB(tshirtList);
//    	String[] allFiles = getFilesNames();
//        System.out.println(Arrays.toString(allFiles));
    }

    public static List<TShirt> getTShirtByBrand(String brand) {
        FileReader fileReader = null;
        brand = brand.replaceAll(".csv","");
        String file = "src/main/webapp/tshirt-data/"+brand+".csv";
        List<TShirt> tShirtsList = new ArrayList<>();
        try{
            fileReader = new FileReader(file);
            CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).withSkipLines(1).build();
            String[] nextRecord;
            while((nextRecord = csvReader.readNext()) != null){
                TShirt tShirt = new TShirt(
                        nextRecord[0],
                        nextRecord[1],
                        nextRecord[2].toLowerCase(),        // color
                        nextRecord[3].toLowerCase(),        // gender
                        nextRecord[4].toLowerCase(),        // size
                        nextRecord[5],
                        nextRecord[6],
                        nextRecord[7]
                );
                tShirtsList.add(tShirt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tShirtsList;
    }

    public static List<TShirt> getAllTShirt(){
        String[] files = getFilesNames();
        List<TShirt> allTShirts = new ArrayList<>();
        for(String file : files){
            allTShirts.addAll(getTShirtByBrand(file));
        }
        return allTShirts;
    }

    public static String[] getFilesNames(){
        File folder = new File("src/main/webapp/tshirt-data");
        File[] listOfFiles = folder.listFiles();
        String[] files = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            files[i] = listOfFiles[i].getName();
        }
        return files;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public String getGender() {
        return gender;
    }

    public String getSize() {
        return size;
    }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public String getAvailablity() {
        return availablity;
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", gender='" + gender + '\'' +
                ", size='" + size + '\'' +
                ", price='" + price + '\'' +
                ", rating='" + rating + '\'' +
                ", availablity='" + availablity + '\'' +
                '}';
    }
}
