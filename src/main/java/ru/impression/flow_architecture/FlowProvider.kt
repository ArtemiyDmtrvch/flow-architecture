package ru.impression.flow_architecture

internal object FlowProvider {

    operator fun <F : Flow> get(flowClass: Class<F>): F = FlowStore.waitingFlows
        .firstOrNull { it::class.java == flowClass }
        ?.also { FlowStore.waitingFlows.remove(it) } as F?
        ?: flowClass.newInstance()
}