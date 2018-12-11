package net.forevents.foreventsandroid.presentation.MyEvents

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import net.forevents.foreventsandroid.R
import net.forevents.foreventsandroid.presentation.MyEvents.MyEventsFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_my_events_list.view.*
import net.forevents.foreventsandroid.Data.CreateUser.User.AppEvents



class MyItemRecyclerViewAdapter(
    private val mValues: List<AppEvents>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as AppEvents
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_my_events_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.eventName.text=item.name
        holder.eventAddress.text = "${item.city} (${item.province})"
        holder.eventDate.text = item.begin_date
        Glide.with(holder.eventImg)
            .load(item.imgUrl)
            .into(holder.eventImg)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val eventName: TextView = mView.event_name
        val eventDate: TextView = mView.event_date
        val eventAddress: TextView = mView.event_city
        val eventImg: ImageView = mView.event_image
        override fun toString(): String {
            return super.toString() + " '" + eventName.text + "'"
        }
    }
}
