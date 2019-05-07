package app.itgungnir.kwa.search.delegate

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import app.itgungnir.kwa.R
import app.itgungnir.kwa.common.SearchResultActivity
import app.itgungnir.kwa.common.widget.easy_adapter.BaseDelegate
import app.itgungnir.kwa.common.widget.easy_adapter.EasyAdapter
import app.itgungnir.kwa.search.SearchState
import kotlinx.android.synthetic.main.listitem_search_hotkey.view.*
import my.itgungnir.apt.router.api.Router
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor

class SearchHotKeyDelegate(
    private val keyClickCallback: (String) -> Unit
) : BaseDelegate<SearchState.SearchHotKeyVO>() {

    override fun layoutId(): Int = R.layout.listitem_search_hotkey

    override fun onCreateVH(container: View) {
        container.apply {
            // Flex Layout
            children.bind<String>(
                layoutId = R.layout.listitem_search_text,
                items = listOf(),
                render = { view, data ->
                    view.findViewById<TextView>(R.id.name).apply {
                        text = data
                        backgroundColor = Color.parseColor("#FFFFAB00")
                        textColor = Color.WHITE
                        setOnClickListener {
                            keyClickCallback.invoke(data)
                        }
                    }
                }
            )
        }
    }

    override fun onBindVH(
        item: SearchState.SearchHotKeyVO,
        holder: EasyAdapter.VH,
        position: Int,
        payloads: MutableList<Bundle>
    ) {

        holder.render(item) {

            children.update(item.data)
        }
    }
}