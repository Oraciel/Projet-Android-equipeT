package edu.dacheville.projet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Drink implements Parcelable {
    private final String name;
    private final String description;
    private int image;
    private final double price;

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

    public String getDescription() {
        return description;
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

