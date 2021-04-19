package stan.qodat.util

import java.util.*

/**
 * TODO: add documentation
 *
 * @author  Stan van der Bend (https://www.rune-server.ee/members/StanDev/)
 * @since   23/09/2019
 * @version 1.0
 */
class ActionCache {

    private val future = ArrayDeque<Action>()
    private val history = ArrayDeque<Action>()

    fun undoLast(){

        if(history.isEmpty())
            return

        history.removeLast().let {
            future.addLast(it)
            it.undoAction()
        }
    }

    fun redoLast(){

        if(future.isEmpty())
            return

        future.removeLast().let {
            history.addLast(it)
            it.action()
        }
    }

    fun cache(action: Action){
        history.addLast(action)
        action.action()
    }

}