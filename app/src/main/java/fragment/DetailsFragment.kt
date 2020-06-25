package fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import main.R


/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =
            inflater.inflate(R.layout.fragment_details, container, false) as ViewGroup
        val memoName = arguments!!.getString("memoName", "DefaultValue")
        val memoStatus = arguments!!.getBoolean("memoStatus", false)
        val memoDescription =
            arguments!!.getString("memoDescription", "No description")
        /*ImageView checkImage = root.findViewById(R.id.image);
        if(memoStatus){
            checkImage.setImageResource(R.mipmap.doneimage);
        } else {
            checkImage.setImageResource(R.mipmap.notdoneimage);
        }
*/
        val name = root.findViewById<TextView>(R.id.name)
        name.text = memoName
        val description = root.findViewById<TextView>(R.id.description)
        description.text = memoDescription
        // Inflate le layout pour ce fragment
        return root
    }
}