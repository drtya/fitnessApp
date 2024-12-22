package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.DaysListItemBinding
import com.example.fitnessapp.db.DayModel

class DaysAdapter(var listener: Listener) :
    ListAdapter<DayModel, DaysAdapter.DayHolder>(MyComparator()) {
    class DayHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = DaysListItemBinding.bind(view)
        fun setData(day: DayModel, listener: Listener) = with(binding) {
            val name = root.context.getString(R.string.day) + " ${adapterPosition + 1}"
            tvName.text = name
            val exCounter = day.exercises.split(",")
                .filter { it -> it.toInt() !== 0 }.size.toString() + " " + root.context.getString(R.string.exercise)
            tvExCounter.text = exCounter
            checkBox.isChecked = day.isDone
            itemView.setOnClickListener { listener.onClick(day.copy(dayNumber = adapterPosition + 1)) }
        }
    }


    // функция которая задает копию разметки для каждого элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.days_list_item, parent, false)
        return DayHolder(view)
    }

    // функция для заполнения данных в уже сгенерированную копию разметки
    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    class MyComparator : DiffUtil.ItemCallback<DayModel>() {
        //сравнивает новый и предыдущий элементы по уникальному идентификатору, в данном примере оба компоратора сравниваются целиком
        override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

        //целиком сравнивает новый и предыдущий элементы
        override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

    }

    interface Listener {
        fun onClick(day: DayModel)
    }
}