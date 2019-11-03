package com.example.ecommercedemoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StarkSpireItem {

    public class Category {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("products")
        @Expose
        private List<Product> products = null;
        @SerializedName("child_categories")
        @Expose
        private List<Integer> childCategories = null;

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

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public List<Integer> getChildCategories() {
            return childCategories;
        }

        public void setChildCategories(List<Integer> childCategories) {
            this.childCategories = childCategories;
        }

    }


        @SerializedName("categories")
        @Expose
        private List<Category> categories = null;
        @SerializedName("rankings")
        @Expose
        private List<Ranking> rankings = null;

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public List<Ranking> getRankings() {
            return rankings;
        }

        public void setRankings(List<Ranking> rankings) {
            this.rankings = rankings;
        }


    public class Product {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("date_added")
        @Expose
        private String dateAdded;
        @SerializedName("variants")
        @Expose
        private List<Variant> variants = null;
        @SerializedName("tax")
        @Expose
        private Tax tax;

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

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public List<Variant> getVariants() {
            return variants;
        }

        public void setVariants(List<Variant> variants) {
            this.variants = variants;
        }

        public Tax getTax() {
            return tax;
        }

        public void setTax(Tax tax) {
            this.tax = tax;
        }

    }

    public class Product_ {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("view_count")
        @Expose
        private int viewCount;
        @SerializedName("order_count")
        @Expose
        private int orderCount;
        @SerializedName("shares")
        @Expose
        private int shares;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public int getShares() {
            return shares;
        }

        public void setShares(int shares) {
            this.shares = shares;
        }

    }

    public class Ranking {

        @SerializedName("ranking")
        @Expose
        private String ranking;
        @SerializedName("products")
        @Expose
        private List<Product_> products = null;

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public List<Product_> getProducts() {
            return products;
        }

        public void setProducts(List<Product_> products) {
            this.products = products;
        }

    }

    public class Tax {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("value")
        @Expose
        private double value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

    }

    public class Variant {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("size")
        @Expose
        private Object size;
        @SerializedName("price")
        @Expose
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Object getSize() {
            return size;
        }

        public void setSize(Object size) {
            this.size = size;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

    }
}
