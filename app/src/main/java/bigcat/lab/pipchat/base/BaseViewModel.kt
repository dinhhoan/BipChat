package bigcat.lab.pipchat.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import bizverse.lab.healthylifestyle.utils.extensions.LoadingAware
import bizverse.lab.healthylifestyle.utils.extensions.ViewErrorAware

/*
* Created by hoantd on 31/05/2022.
*/
open class BaseViewModel(application: Application): AndroidViewModel(application), ViewErrorAware,
    LoadingAware
