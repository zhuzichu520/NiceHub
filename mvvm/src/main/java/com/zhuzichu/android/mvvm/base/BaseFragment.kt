package com.zhuzichu.android.mvvm.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.AnimBuilder
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.zhuzichu.android.libs.tool.closeKeyboard
import com.zhuzichu.android.libs.tool.toCast
import com.zhuzichu.android.mvvm.R
import com.zhuzichu.android.widget.dialog.loading.LoadingMaker
import com.zhuzichu.android.widget.toast.toast
import dagger.android.support.DaggerFragment
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<TArgument : BaseArgument, TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    DaggerFragment(), IBaseFragment, IBaseCommon {

    companion object {
        internal const val KEY_ARGUMENT = "KEY_ARGUMENT"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var isInitData = false
    private var isInitLazy = false

    var binding: TBinding? = null
    lateinit var viewModel: TViewModel
    lateinit var argument: TArgument
    lateinit var activityCtx: Activity

    val navController by lazy { activityCtx.findNavController(R.id.delegate_container) }

    abstract fun setLayoutId(): Int
    abstract fun bindVariableId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            argument = it.getParcelable<BaseArgument>(KEY_ARGUMENT).toCast()
        }
        binding = DataBindingUtil.inflate(
            inflater,
            setLayoutId(),
            container,
            false
        )
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewDataBinding()
        registUIChangeLiveDataCallback()
        initVariable()
        initView()
        initViewObservable()
        if (!isInitData) {
            initData()
            isInitData = true
        }
    }

    private fun initViewDataBinding() {
        val type = this::class.java.genericSuperclass
        if (type is ParameterizedType) {
            viewModel = ViewModelProvider(this, viewModelFactory)
                .get(type.actualTypeArguments[2].toCast())
        }
        binding?.setVariable(bindVariableId(), viewModel)
        lifecycle.addObserver(viewModel)
        viewModel.injectLifecycleOwner(viewLifecycleOwner)
    }

    private fun registUIChangeLiveDataCallback() {
        viewModel.uc.startActivityEvent.observe(this, Observer {
            val intent = Intent(activityCtx, it.clz)
            intent.putExtra(KEY_ARGUMENT, it.argument)
            startActivityForResult(intent, it.requestCode, it.options)
            if (it.isPop) {
                activityCtx.finish()
            }
        })

        viewModel.uc.startFragmentEvent.observe(this, Observer {
            navController.navigate(
                it.actionId,
                bundleOf(KEY_ARGUMENT to it.argument),
                getDefaultNavOptions(it.actionId, it.animBuilder)
            )
        })

        viewModel.uc.onBackPressedEvent.observe(this, Observer {
            activityCtx.onBackPressed()
        })

        viewModel.uc.showLoadingEvent.observe(this, Observer {
            closeKeyboard(requireContext())
            view?.postDelayed({
                LoadingMaker.showLoadingDialog(requireContext())
            }, 150)
        })

        viewModel.uc.hideLoadingEvent.observe(this, Observer {
            view?.postDelayed({
                LoadingMaker.dismissLodingDialog()
            }, 150)
        })

        viewModel.uc.toastStringResEvent.observe(this, Observer {
            it.toast(context = requireContext())
        })

        viewModel.uc.toastStringEvent.observe(this, Observer {
            it.toast(context = requireContext())
        })

    }

    private fun getDefaultNavOptions(
        actionId: Int,
        animBuilder: AnimBuilder.() -> Unit
    ): NavOptions? {
        val navOptions = navController.currentDestination?.getAction(actionId)?.navOptions
        var options: NavOptions? = null
        navOptions?.let {
            options = androidx.navigation.navOptions {
                anim(animBuilder)
                launchSingleTop = navOptions.shouldLaunchSingleTop()
                popUpTo(navOptions.popUpTo) {
                    this.inclusive = navOptions.isPopUpToInclusive
                }
            }
        }
        return options
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityCtx = requireActivity()
    }

    override fun onResume() {
        super.onResume()
        if (!isInitLazy) {
            initLazyData()
            isInitLazy = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.unbind()
        binding = null
    }

    override fun back() {
        viewModel.back()
    }

    override fun showLoading() {
        viewModel.showLoading()
    }

    override fun hideLoading() {
        viewModel.hideLoading()
    }

    override fun toast(text: String?) {
        viewModel.toast(text)
    }

    override fun toast(id: Int) {
        viewModel.toast(id)
    }

    override fun startActivity(
        clz: Class<*>,
        argument: BaseArgument,
        isPop: Boolean,
        options: Bundle,
        requestCode: Int
    ) {
        viewModel.startActivity(clz, argument, isPop, options, requestCode)
    }

    override fun startFragment(
        actionId: Int,
        argument: BaseArgument,
        animBuilder: AnimBuilder.() -> Unit
    ) {
        viewModel.startFragment(actionId, argument, animBuilder)
    }

    fun putArgument(argument: BaseArgument): BaseFragment<*, *, *> {
        var bundle = arguments
        if (bundle != null) {
            bundle.putParcelable(KEY_ARGUMENT, argument)
        } else {
            bundle = bundleOf(KEY_ARGUMENT to argument)
        }
        arguments = bundle
        return this
    }
}