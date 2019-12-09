/*
 * Copyright (c) 2018-present, lmyooyo@gmail.com.
 *
 * This source code is licensed under the GPL license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.chezi008.baseui.pipeline.impl

import android.os.Handler
import com.chezi008.baseui.pipeline.Pipeline
import com.chezi008.baseui.utils.debug_e

/**
 * Created by lmyooyo@gmail.com on 2018/6/20.
 */
class GLEventPipeline private constructor() : Pipeline {

    private object Holder {
        val INSTANCE: Pipeline = GLEventPipeline()
    }

    companion object {
        val INSTANCE: Pipeline by lazy { Holder.INSTANCE }
        fun isMe(pipeline: Pipeline): Boolean {
            return INSTANCE == pipeline
        }
    }

    private var eventPipeline: EventPipeline? = null
    private fun check() {
        if (null == eventPipeline)
            eventPipeline = EventPipeline.create("GLEventPipeline")
    }

    override fun queueEvent(event: Runnable, front: Boolean) {
        check()
        eventPipeline?.queueEvent(event)
    }

    override fun queueEvent(event: Runnable, delayed: Long) {
        check()
        eventPipeline?.queueEvent(event, delayed)
    }

    override fun quit() {
        if (null != eventPipeline) {
            eventPipeline?.quit()
            eventPipeline = null
            debug_e("GLEventPipeline quited")
        }
    }

    override fun started(): Boolean {
        if (null == eventPipeline) return false
        debug_e("GLEventPipeline started")
        return eventPipeline!!.started()
    }

    override fun getName(): String {
        if (null == eventPipeline) return "UNKNOWN"
        return eventPipeline!!.getName()
    }

    override fun getHandler(): Handler {
        return eventPipeline!!.getHandler()
    }

    override fun sleep() {
        eventPipeline?.sleep()
    }

    override fun wake() {
        eventPipeline?.wake()
    }
}