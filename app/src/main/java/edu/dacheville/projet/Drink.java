package edu.dacheville.projet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Drink implements Parcelable {
    private String name;
    private String description;
    private int image;
    private double price;

    public Drink(String name, String description, int image, double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    protected Drink(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readInt();
        price = in.readDouble();
    }

    public static final Creator<Drink> CREATOR = new Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(image);
        parcel.writeDouble(price);
    }
}

