package com.truecaller.giveapp.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.truecaller.giveapp.R
import com.truecaller.giveapp.model.Item
import com.truecaller.giveapp.utils.configToolbar
import com.truecaller.giveapp.utils.dialNumber
import kotlinx.android.synthetic.main.fragment_item_details.*
import java.util.concurrent.TimeUnit


private const val ARG_ITEM = "item"

class ItemDetailsFragment : Fragment() {
    private var item: Item? = null

    companion object {
        @JvmStatic
        fun newInstance(item: Item) =
            ItemDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ITEM, item)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable(ARG_ITEM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).configToolbar(toolbarDetails, true)

        item?.let { item ->
            collapsingToolbar.title = item.title
            tvItemCategory.text = item.category
            tvItemDescription.text = item.description
            fabCallOwner.setOnClickListener { context?.dialNumber(item.phone) }
        }

        val lifetimePercentage = calculateItemLifetimePercentage()
        animateLifeTimeProgress(lifetimePercentage)
    }

    private fun calculateItemLifetimePercentage(): Int { //TODO Test values
        val daysPassed: Long = calculateDaysPassed(1539508407000) //14.10.2018
        val itemLifeTime = 10
        return if (daysPassed > itemLifeTime) {
            Toast.makeText(context, R.string.error_message_item_expired, Toast.LENGTH_SHORT).show()
            0
        } else {
            val percentage = daysPassed.toDouble() / itemLifeTime * 100
            println("end percentage: $percentage")
            percentage.toInt()
        }
    }

    private fun calculateDaysPassed(creationTimeStamp: Long): Long {
        val differenceMillis = System.currentTimeMillis() - creationTimeStamp
        val daysDifference = TimeUnit.DAYS.convert(differenceMillis, TimeUnit.MILLISECONDS)
        println("Days passed: $daysDifference")
        return daysDifference
    }

    private fun animateLifeTimeProgress(endValue: Int) {
        val progressAnimator = ObjectAnimator.ofInt(
            progressBarLifeTime,
            "Progress",
            0,
            endValue
        )
        progressAnimator.duration = 1000
        progressAnimator.start()
    }

}
