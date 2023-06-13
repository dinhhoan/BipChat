package bigcat.lab.baseappkotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.loadingFlow
import androidx.lifecycle.viewErrorFlow
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import bigcat.lab.baseappkotlin.data.error.ErrorModel
import bigcat.lab.baseappkotlin.utils.view.CustomProgressDialog
import bizverse.lab.healthylifestyle.utils.extensions.collectFlow
import bizverse.lab.healthylifestyle.utils.extensions.hideKeyboard


/*
* Created by ducnguyen on 31/05/2022.
*/
abstract class BaseFragment<V : ViewBinding, VM : BaseViewModel> : Fragment() {
    private val progressDialog by lazy {
        CustomProgressDialog.newInstance()
    }

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> V
    protected var binding: V? = null

    abstract val viewModel: VM

    private var callBackWhenBackPress: OnBackPressedCallback = object : OnBackPressedCallback(
        false
        /** true means that the callback is enabled */
    ) {
        override fun handleOnBackPressed() {
            handleBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater(inflater, container, false)
//        setWindowInsets()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // note that you could enable/disable the callback here as well by setting callback.isEnabled = true/false
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBackWhenBackPress)
        viewModel.run {
            collectFlow(viewErrorFlow) {
                handleCommonError(it)
            }

            collectFlow(loadingFlow) {
                handleProgressDialogStatus(it)
            }
        }
    }

    internal fun handleCommonError(errorModel: ErrorModel) {
    }

    private fun handleProgressDialogStatus(isShow: Boolean) {
        if (isShow) {
            progressDialog.show(
                childFragmentManager,
                CustomProgressDialog::class.java.simpleName
            )
        } else {
            progressDialog.dismissAllowingStateLoss()
        }
    }


    override fun onResume() {
        super.onResume()
        handleAddCallBack(true)
    }

    override fun onPause() {
        super.onPause()
        handleAddCallBack(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    protected open fun handleBackPressed() {
        activity?.let {
            context?.hideKeyboard(it)
        }
        if (findNavController().backQueue.size > 0) {
            findNavController().popBackStack()
        }
    }

//    protected fun findTopNavController(): NavController? {
//        val navHostFragment =
//            activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as? NavHostFragment
//        return navHostFragment?.navController
//    }

//    protected fun handleLoading(isShow: Boolean, canClickToHide: Boolean = false) {
//        (activity as? MainActivity)?.handleLoading(isShow, canClickToHide)
//    }

    private fun handleAddCallBack(isEnable: Boolean = true) {
        callBackWhenBackPress.isEnabled = isEnable
    }

//    private fun setWindowInsets() {
//        binding?.root?.findViewById<ViewGroup>(R.id.content)?.apply {
//            clipToPadding = false
//            ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
//                view.updatePadding(
////                    top = insets.getInsets(WindowInsets.Type.systemBars()).top,
////                    bottom = insets.getInsets(WindowInsets.Type.systemBars()).bottom,
//                    top = insets.systemWindowInsets.top,
//                    bottom = insets.systemWindowInsets.bottom,
//                )
//                insets
//            }
//        }
//    }

    protected fun <V : ViewBinding> fromVB(inflater: (LayoutInflater, ViewGroup?, Boolean) -> V) =
        inflater
}
