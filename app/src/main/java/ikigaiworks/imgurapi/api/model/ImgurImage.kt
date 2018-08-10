package ikigaiworks.imgurapi.api.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class ImgurImage (val id: String, val title: String, val description: String, val dateTime: Int, val type: String, val animated: Boolean,
                  val width: Int, val height: Int, val size: Int, val views: Int, val bandwidth: Int, val deletehash: String,
                  val name: String, val section: String, val link: String, val gifv: String, val mp4: String, val mp4_size: Int,
                  val looping: Boolean, val favorite: Boolean, val nsfw: Boolean, val vote: String, val in_gallery: Boolean) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    /**
     * Time uploaded, as a Date
     */
    fun getDatetimeAsDate(): Date {
        val dateTimeMilliseconds = dateTime * 1000
        return Date(dateTimeMilliseconds.toLong())
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(dateTime)
        parcel.writeString(type)
        parcel.writeByte(if (animated) 1 else 0)
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeInt(size)
        parcel.writeInt(views)
        parcel.writeInt(bandwidth)
        parcel.writeString(deletehash)
        parcel.writeString(name)
        parcel.writeString(section)
        parcel.writeString(link)
        parcel.writeString(gifv)
        parcel.writeString(mp4)
        parcel.writeInt(mp4_size)
        parcel.writeByte(if (looping) 1 else 0)
        parcel.writeByte(if (favorite) 1 else 0)
        parcel.writeByte(if (nsfw) 1 else 0)
        parcel.writeString(vote)
        parcel.writeByte(if (in_gallery) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImgurImage> {
        override fun createFromParcel(parcel: Parcel): ImgurImage {
            return ImgurImage(parcel)
        }

        override fun newArray(size: Int): Array<ImgurImage?> {
            return arrayOfNulls(size)
        }
    }

}