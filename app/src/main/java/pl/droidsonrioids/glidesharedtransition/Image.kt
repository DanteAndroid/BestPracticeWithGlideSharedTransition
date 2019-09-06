package pl.droidsonrioids.glidesharedtransition

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Dante
 * 2019-09-06
 */
data class Image(val thumbUrl: String, val originalUrl: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString()!!,
            source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(thumbUrl)
        writeString(originalUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Image> = object : Parcelable.Creator<Image> {
            override fun createFromParcel(source: Parcel): Image = Image(source)
            override fun newArray(size: Int): Array<Image?> = arrayOfNulls(size)
        }
    }
}