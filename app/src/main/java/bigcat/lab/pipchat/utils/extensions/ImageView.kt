package bizverse.lab.healthylifestyle.utils.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * @author mvn-toan.nguyen2 on 6/22/22
 **/

//
//fun ImageView.loadCircleUrl(
//    url: String?,
//    radiusImage: Int = 30.toPx(),
//    @DrawableRes id: Int = 0
//) {
//    url?.also {
//        Glide.with(context)
//            .load(it)
//            .apply(
//                RequestOptions()
//                    .transform(CenterCrop(), RoundedCorners(radiusImage))
//                    .error(id)
//                    .placeholder(id)
//            )
//            .into(this)
//    }
//}
