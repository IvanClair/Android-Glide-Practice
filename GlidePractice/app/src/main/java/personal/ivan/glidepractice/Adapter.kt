package personal.ivan.glidepractice

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import personal.ivan.glidepractice.databinding.VhMainBinding

data class MyModel(val imageUrl: String) {
    companion object {
        const val IMAGE_1 = "https://homepages.cae.wisc.edu/~ece533/images/frymire.png"
        const val IMAGE_2 = "https://homepages.cae.wisc.edu/~ece533/images/peppers.png"
        const val IMAGE_3 = "https://homepages.cae.wisc.edu/~ece533/images/watch.png"
    }
}

class MyVh(private val mBinding: VhMainBinding) : RecyclerView.ViewHolder(mBinding.root) {

    fun bindTo(model: MyModel) {
        mBinding.imageView.apply {
            Glide
                .with(this)
                .load(model.imageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .thumbnail(0.25f)
                .circleCrop()
                .error(ColorDrawable(Color.RED))
                .fallback(ColorDrawable(Color.LTGRAY))
                .transition(DrawableTransitionOptions.withCrossFade(1500))
                .into(this)
        }
    }
}

class MyAdapter : RecyclerView.Adapter<MyVh>() {

    private val mDataSource: List<MyModel> by lazy {
        mutableListOf<MyModel>().apply {
            repeat(100) {
                add(
                    MyModel(
                        imageUrl =
                        when {
                            it % 3 == 0 -> MyModel.IMAGE_3
                            it % 2 == 0 -> MyModel.IMAGE_2
                            else -> MyModel.IMAGE_1
                        }
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = mDataSource.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVh =
        MyVh(
            VhMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyVh, position: Int) {
        mDataSource.getOrNull(position)?.also { holder.bindTo(it) }
    }

}
