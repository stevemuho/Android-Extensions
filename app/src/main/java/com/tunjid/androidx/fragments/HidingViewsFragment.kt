package com.tunjid.androidx.fragments

import android.os.Bundle
import android.view.View
import com.tunjid.androidx.R
import com.tunjid.androidx.baseclasses.AppBaseFragment
import com.tunjid.androidx.core.content.themeColorAt
import com.tunjid.androidx.isDarkTheme
import com.tunjid.androidx.view.animator.ViewHider
import com.tunjid.androidx.view.util.InsetFlags
import com.tunjid.androidx.viewmodels.routeName

/**
 * Fragment demonstrating hiding views
 *
 *
 * Created by tj.dahunsi on 5/6/17.
 */

class HidingViewsFragment : AppBaseFragment(R.layout.fragment_hiding_view) {

    private lateinit var leftHider: ViewHider<View>
    private lateinit var topHider: ViewHider<View>
    private lateinit var rightHider: ViewHider<View>
    private lateinit var bottomHider: ViewHider<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiState = uiState.copy(
                toolbarTitle = this::class.java.routeName,
                toolbarShows = true,
                toolBarMenu = 0,
                fabShows = false,
                showsBottomNav = false,
                insetFlags = InsetFlags.ALL,
                lightStatusBar = !requireContext().isDarkTheme,
                navBarColor = requireContext().themeColorAt(R.attr.nav_bar_color)
        )

        val leftButton = view.findViewById<View>(R.id.left_button)
        val topButton = view.findViewById<View>(R.id.top_button)
        val rightButton = view.findViewById<View>(R.id.right_button)
        val bottomButton = view.findViewById<View>(R.id.bottom_button)

        leftButton.setOnClickListener(this::onClick)
        topButton.setOnClickListener(this::onClick)
        rightButton.setOnClickListener(this::onClick)
        bottomButton.setOnClickListener(this::onClick)
        view.findViewById<View>(R.id.reset).setOnClickListener(this::onClick)

        leftHider = ViewHider.of(leftButton).setDirection(ViewHider.LEFT).build()
        topHider = ViewHider.of(topButton).setDirection(ViewHider.TOP).build()
        rightHider = ViewHider.of(rightButton).setDirection(ViewHider.RIGHT).build()
        bottomHider = ViewHider.of(bottomButton).setDirection(ViewHider.BOTTOM).build()
    }

    private fun onClick(v: View) {
        when (v.id) {
            R.id.left_button -> leftHider.hide()
            R.id.top_button -> topHider.hide()
            R.id.right_button -> rightHider.hide()
            R.id.bottom_button -> bottomHider.hide()
            R.id.reset -> {
                leftHider.show()
                topHider.show()
                rightHider.show()
                bottomHider.show()
            }
        }
    }

    companion object {
        fun newInstance(): HidingViewsFragment = HidingViewsFragment().apply { arguments = Bundle() }
    }
}
