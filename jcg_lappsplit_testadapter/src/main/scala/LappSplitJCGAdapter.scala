import nl.wvdzwan.lapp.Main
import nl.wvdzwan.lapp.split.RunnerMain


object LappSplitJCGAdapter extends JCGTestAdapter {
    override def serializeCG(
        algorithm:  String,
        target:     String,
        mainClass:  String,
        classPath:  Array[String],
        JDKPath:    String,
        analyzeJDK: Boolean,
        outputFile: String
    ): Long = {
        try {
            return RunnerMain.run(target, outputFile);
        } catch {
            case e: Exception => println(e.getMessage)
        }

        return 0
    }

    override def possibleAlgorithms(): Array[String] = Array("RTA")

    override def frameworkName(): String = "LappSplit"
}
