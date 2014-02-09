package net.minecraftforge.groovy

import groovy.transform.CompileStatic

/**
 * The Groovy Event Bus.
 */
class GEventBus {
    private Map<String, List<Closure>> handlers = [:]

    @CompileStatic
    void on(String name, Closure handler) {
        if (!(name in handlers)) {
            handlers[name] = []
        }
        handlers[name].add(handler)
    }

    @CompileStatic
    void dispatch(data, useThread = false) {
        def name = data['name'] as String
        if (name == null || !handlers.containsKey(name)) {
            return
        }
        def handlers = handlers[name] as List<Closure>
        def execute = { ->
            handlers.each { Closure handler ->
                handler.call(data)
            }
        }
        if (useThread) {
            def thread = Thread.startDaemon("EventExecutor[${name}]", execute)
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                void uncaughtException(Thread t, Throwable e) {
                    e.printStackTrace()
                }
            })
        } else {
            execute()
        }
    }

    void dispatch(Map data) {
        dispatch(data, false)
    }
}