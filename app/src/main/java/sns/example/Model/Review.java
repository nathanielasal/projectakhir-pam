package sns.example.Model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.database.PropertyName;

public class Review {

    private String reviewInput;
    private String rateCount;
    private String key;

    public Review() {

    }

    //    public Review(String key, String rateCount, String reviewInput){
//        this.key = key;
//        this.rateCount = rateCount;
//        this.reviewInput = reviewInput;
//    }
    public Review(String rateCount, String reviewInput) {
        this.rateCount = rateCount;
        this.reviewInput = reviewInput;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getReviewInput() {
        return reviewInput;
    }

    public String getRateCount() {
        return rateCount;
    }

    public void setReviewInput(String reviewInput) {
        this.reviewInput = reviewInput;
    }

    public void setRateCount(String rateCount) {
        this.rateCount = rateCount;
    }
//
//    protected Review(Parcel in) {
//        // Baca data dari Parcel ke dalam field objek
//        this.reviewInput = in.readString();
//        this.rateCount = in.readString();
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        // Tulis data dari field objek ke dalam Parcel
//        dest.writeString(rateCount);
//        dest.writeString(reviewInput);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    // Implementasikan creator untuk Parcelable
//    public static final Creator<Review> CREATOR = new Creator<Review>() {
//        @Override
//        public Review createFromParcel(Parcel in) {
//            return new Review(in);
//        }
//
//        @Override
//        public Review[] newArray(int size) {
//            return new Review[size];
//        }

//    public void readFromParcel(Parcel source) {
//        this.key = source.readString();
//        this.reviewInput = source.readString();
//        this.rateCount = source.readString();
//    }
//    protected Review(Parcel in) {
//        this.key = in.readString();
//        this.reviewInput = in.readString();
//        this.rateCount = in.readString();
//    }
//
//    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
//        @Override
//        public Review createFromParcel(Parcel source) {
//            return new Review(source);
//        }
//
//        @Override
//        public Review[] newArray(int size) {
//            return new Review[size];
//        }
//    };
}


