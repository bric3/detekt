package io.gitlab.arturbosch.detekt.cli

import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.FileProcessListener
import io.gitlab.arturbosch.detekt.api.SetupContext
import io.gitlab.arturbosch.detekt.api.UnstableApi
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext

class DetektProgressListener : FileProcessListener {

    private lateinit var outPrinter: Appendable

    @OptIn(UnstableApi::class)
    override fun init(context: SetupContext) {
        this.outPrinter = context.outputChannel
    }

    override fun onProcess(file: KtFile, bindingContext: BindingContext) {
        outPrinter.append('.')
    }

    override fun onFinish(files: List<KtFile>, result: Detektion, bindingContext: BindingContext) {
        val middlePart = if (files.size == 1) "file was" else "files were"
        outPrinter.appendLine("\n\n${files.size} kotlin $middlePart analyzed.")
    }
}
