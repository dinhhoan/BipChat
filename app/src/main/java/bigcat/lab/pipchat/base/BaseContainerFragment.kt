package bigcat.lab.pipchat.base

import androidx.viewbinding.ViewBinding

/*
* Created by hoantd on 31/05/2022.
*/
abstract class BaseContainerFragment<V : ViewBinding, VM : BaseViewModel>: BaseFragment<V, VM>()
