package com.cloudsect.myapplication.util


import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.activity.ViewImageActivity
import com.cloudsect.myapplication.ui.product_detail.model.ProductImages

class ImageSliderString(
    private val context: Context,
    private val viewPager: ViewPager,
    private val dotsLayout: LinearLayout,
    private val isAutoslide:Boolean

) {
    private var imageUrls: List<ProductImages>? = null
    private var dots: Array<ImageView?>? = null
    private var currentPage = 0

    private val slideInterval = 2000
    private val handler = Handler(Looper.getMainLooper())


    fun setImageUrls(imageUrls: List<ProductImages>) {
        this.imageUrls = imageUrls
        setupImageSlider()
    }

    private fun setupImageSlider() {
        val adapter = ImageSliderAdapter(context, imageUrls!!)
        viewPager.adapter = adapter

        addDotsIndicator(0)
        handler.postDelayed(slideRunnable, slideInterval.toLong())
        viewPager.addOnPageChangeListener(viewPagerListener)
    }

    private val slideRunnable: Runnable = object : Runnable {
        override fun run() {
            if (isAutoslide)
            {
                currentPage = (currentPage + 1) % (imageUrls!!.size)
                viewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, slideInterval.toLong())
            }
        }
    }

    private fun addDotsIndicator(position: Int) {
        dotsLayout.removeAllViews()
        dots = arrayOfNulls(imageUrls!!.size)

        for (i in imageUrls!!.indices) {
            dots!![i] = ImageView(context)
            dots!![i]!!.setImageResource(R.drawable.ic_dot_unselected)
            dotsLayout.addView(dots!![i])
        }

        if (dots!!.isNotEmpty()) {
            dots!![position]!!.setImageResource(R.drawable.ic_dot_selected)
        }
    }

    private val viewPagerListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                addDotsIndicator(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        }

    private class ImageSliderAdapter(
        private val context: Context,
        private val imageUrls: List<ProductImages>
    ) : PagerAdapter() {

        override fun getCount(): Int {
            return imageUrls.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.slider_layout, container, false)

            val imageView = view.findViewById<ImageView>(R.id.imageView)

            imageView.setOnClickListener {
                val intent = Intent(context,ViewImageActivity::class.java)
                intent.putExtra("images",imageUrls as ArrayList)
                context.startActivity(intent)

            }

            //Picasso.get().load(imageUrls[position]).into(imageView)
            Glide.with(context)
                .load(imageUrls[position])
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}
