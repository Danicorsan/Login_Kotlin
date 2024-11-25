import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moronlu18.saveactivity.Answer
import com.moronlu18.saveactivity.R

class AnswerAdapter(
    private val context: Context,
    private val answerList: MutableList<Answer>
) : RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {

    var answerPosition = -1
    private var onItemClickListener: ((Int, String) -> Unit)? = null

    // Configurar el listener para clics
    fun setOnItemClickListener(listener: (Int, String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        val answer = answerList[position]
        holder.txvAnswer.text = answer.text
        holder.rdAnswer.isChecked = (position == answerPosition)

        holder.rdAnswer.setOnClickListener {
            if (answerPosition != -1) {
                answerList[answerPosition].selection = !answerList[answerPosition].selection
                notifyDataSetChanged()
            }
            answerPosition = holder.bindingAdapterPosition
            answer.selection = !answer.selection
            notifyDataSetChanged()

            // Llamar al listener para pasar los datos
            onItemClickListener?.invoke(position, answer.text)
        }
    }

    override fun getItemCount(): Int {
        return answerList.size
    }

    fun addAll(list: List<Answer>) {
        answerList.clear()
        answerList.addAll(list)
        notifyDataSetChanged()
    }

    class AnswerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rdAnswer: RadioButton = itemView.findViewById(R.id.rdAnswer)
        val txvAnswer: TextView = itemView.findViewById(R.id.txvAnswer)
    }
}
