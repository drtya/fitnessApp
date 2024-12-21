package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ExerciseListItemBinding
import pl.droidsonroids.gif.GifDrawable

class ExercisesAdapter :
    ListAdapter<ExercisesModel, ExercisesAdapter.ExercisesHolder>(MyComparator()) {
    class ExercisesHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ExerciseListItemBinding.bind(view)
        fun setData(exercise:ExercisesModel)= with(binding){
            tvName.text =exercise.name
            tvCount.text = exercise.time
            chB.isChecked = exercise.isDone
            imEx.setImageDrawable(GifDrawable(root.context.assets,exercise.image))
        }
    }


    // функция которая задает копию разметки для каждого элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.exercise_list_item,parent,false)
        return ExercisesHolder(view)
    }

    // функция для заполнения данных в уже сгенерированную копию разметки
    override fun onBindViewHolder(holder: ExercisesHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class MyComparator: DiffUtil.ItemCallback<ExercisesModel>(){
        //сравнивает новый и предыдущий элементы по уникальному идентификатору, в данном примере оба компоратора сравниваются целиком
        override fun areItemsTheSame(oldItem: ExercisesModel, newItem: ExercisesModel): Boolean {
            return oldItem == newItem
        }

        //целиком сравнивает новый и предыдущий элементы
        override fun areContentsTheSame(oldItem: ExercisesModel, newItem: ExercisesModel): Boolean {
            return oldItem == newItem
        }

    }


}