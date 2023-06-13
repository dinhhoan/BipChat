package bigcat.lab.baseappkotlin.base

import androidx.viewbinding.ViewBinding

/*
* Created by ducnguyen on 31/05/2022.
*/
abstract class BaseContainerFragment<V : ViewBinding, VM : BaseViewModel>: BaseFragment<V, VM>()
